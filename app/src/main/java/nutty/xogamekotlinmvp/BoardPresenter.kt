package nutty.xogamekotlinmvp

import android.app.Dialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.view.Window
import android.widget.TextView

/**
 * Created by dhero on 10/24/2017.
 */
class BoardPresenter(boardView : BoardView,context : Context) : BoardPresenterInterface,BoardPresenterInterface.CheckTheWinner{
    private var boardView : BoardView? = boardView
    private var context : Context? = context
    private lateinit var message : String
    private lateinit var win : String
    private lateinit var player1 : String
    private lateinit var player2 : String
    private var player : Int = 0
    private var countDraw : Int = 0
    private var boardPlay: Array<Int>? = null
    private var winChk: Array<Array<Int>>? = null

    override fun playTurn(view: View) {
        val mark = view as ImageView
        val markPosition = Integer.parseInt(mark.getTag().toString())
        if (mark.getDrawable() == null) {
            if (player == 1) {
                boardView!!.setImage(view, ContextCompat.getDrawable(context,R.drawable.cross))
                boardPlay!![markPosition] = 1
                player = 2
            } else if (player == 2) {
                boardView!!.setImage(view, ContextCompat.getDrawable(context,R.drawable.circle) )
                boardPlay!![markPosition] = 2
                player = 1
            }
            boardView!!.setAnimationPlayer(player)
            boardView!!.setAnimationPicker(view)
            if (onWinner() || onDraw(countDraw)) {
                boardView!!.showDialog(setupDialog(view))
            }
            countDraw++
        }
    }

    override fun setupDialog(view: View): Dialog {
        val dialog = Dialog(view.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_winner)
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<TextView>(R.id.dialogMessage).text = message
        return dialog
    }

    override fun onWinner(): Boolean {
        for (winchk in this!!.winChk!!) {
            if (boardPlay!![winchk[0]].equals(1) &&
                    boardPlay!![winchk[1]].equals(1)&&
                    boardPlay!![winchk[2]].equals(1)) {
                message = player1 + " " + win
                return true
            } else if (boardPlay!![winchk[0]].equals(2) &&
                    boardPlay!![winchk[1]].equals(2) &&
                    boardPlay!![winchk[2]].equals(2)) {
                message = player2 + " " + win
                return true
            }
        }
        return false
    }

    override fun onDraw(countDraw: Int): Boolean {
        if (countDraw.equals(8)){
            return true;
        }
        return false;
    }

    override fun setupGame() {
        message = context!!.getString(R.string.draw)
        win  = context!!.getString(R.string.win)
        player1 = context!!.getString(R.string.player1)
        player2 = context!!.getString(R.string.player2)
        player = 1
        countDraw = 0
        boardPlay = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        winChk = arrayOf(arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8), arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8), arrayOf(0, 4, 8), arrayOf(2, 4, 6))
        boardView!!.removeImage()
        boardView!!.setAnimationPlayer(player)
    }

    override fun onDestroy() {
        boardView = null
    }
}