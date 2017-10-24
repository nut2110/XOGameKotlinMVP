package nutty.xogamekotlinmvp

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.view.View

/**
 * Created by dhero on 10/24/2017.
 */
interface BoardView {
    fun setImage(view: View,drawable: Drawable)

    fun removeImage()

    fun showDialog(dialog: Dialog)

    fun onClickReset(view: View)

    fun setAnimationPicker(view: View)

    fun <int> setAnimationPlayer(player: int);
}