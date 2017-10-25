package nutty.xogamekotlinmvp.BoardGame

import android.app.Dialog
import android.view.View

/**
 * Created by dhero on 10/24/2017.
 */
interface BoardPresenterInterface {
    interface CheckTheWinner {
        fun onWinner(): Boolean
        fun onDraw(countDraw: Int): Boolean
    }

    fun playTurn(view: View)

    fun setupDialog(view: View): Dialog

    fun setupGame()

    fun onDestroy()
}