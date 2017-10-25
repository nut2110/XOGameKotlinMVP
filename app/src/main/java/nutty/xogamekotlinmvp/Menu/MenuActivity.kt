package nutty.xogamekotlinmvp.Menu

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import nutty.xogamekotlinmvp.BoardGame.BoardActivity
import nutty.xogamekotlinmvp.R
import android.graphics.Typeface
import android.view.WindowManager
import android.widget.VideoView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import android.net.Uri

class MenuActivity : AppCompatActivity(), MenuView {
    var videoBG : VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setTitle()
        startBackground()
    }

    override fun start(view: View){
        startActivity(Intent(applicationContext, BoardActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    override fun exit(view: View){
        onBackPressed()
    }

    @SuppressLint("WrongViewCast")
    override fun setTitle() {
        val title : TextView = findViewById<TextView>(R.id.title)
        val font = Typeface.createFromAsset(assets, "fonts/Riffic-Bold.ttf")
        title.setTypeface(font)
        YoYo.with(Techniques.Bounce).repeat(YoYo.INFINITE).playOn(title)
    }

    override fun startBackground() {
        videoBG = findViewById<VideoView>(R.id.videoBG)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.background)
        videoBG.run {
            this!!.setVideoURI(uri)
            start()
            setOnPreparedListener { mp -> mp.isLooping = true }
        }
    }

    override fun onResume() {
        super.onResume()
        videoBG!!.start()
    }
}
