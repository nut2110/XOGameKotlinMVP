package nutty.xogamekotlinmvp.BoardGame

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import nutty.xogamekotlinmvp.Menu.MenuActivity
import nutty.xogamekotlinmvp.R
import nutty.xogamekotlinmvp.R.id.*

class BoardActivity : AppCompatActivity(), BoardView {
    var mDialog : Dialog? = null;
    var Presenter : BoardPresenter? = null
    var animationPlayer1: YoYo.YoYoString? = null
    var animationPlayer2: YoYo.YoYoString? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Presenter = BoardPresenter(this, applicationContext)
        Presenter!!.setupGame()
    }

    fun onClick(view: View){
        Presenter!!.playTurn(view)
    }

    override fun setImage(view: View, drawable: Drawable) {
        val mark = view as ImageView
        mark.setImageDrawable(drawable)
    }

    override fun removeImage() {
        val marks :IntArray = intArrayOf(mark1,mark2,mark3,mark4,mark5,mark6,mark7,mark8,mark9)
        for (mark in marks){
            findViewById<ImageView>(mark).setImageDrawable(null)
        }
    }

    override fun showDialog(dialog: Dialog) {
        this.mDialog = dialog
        mDialog!!.show()
    }

    override fun onClickReset(view: View) {
        Presenter!!.setupGame()
        mDialog!!.dismiss()
    }

    override fun setAnimationPicker(view: View) {
        YoYo.with(Techniques.BounceIn).playOn(view)
    }

    override fun <int> setAnimationPlayer(player: int) {
        if (player!!.equals(1)){
            animationPlayer1 = YoYo.with(Techniques.Bounce).repeat(YoYo.INFINITE).playOn(findViewById(R.id.player1));
            if (animationPlayer2 != null){
                animationPlayer2!!.stop();
            }
        }else {
            animationPlayer2 = YoYo.with(Techniques.Bounce).repeat(YoYo.INFINITE).playOn(findViewById(R.id.player2));
            animationPlayer1!!.stop();
        }
        findViewById<TextView>(R.id.player2).setRotation(180F);
    }

    override fun onDestroy() {
        Presenter!!.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
