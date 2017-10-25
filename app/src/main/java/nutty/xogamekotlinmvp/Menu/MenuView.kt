package nutty.xogamekotlinmvp.Menu

import android.view.View

/**
 * Created by dhero on 10/25/2017.
 */
interface MenuView {
    fun start(view: View)

    fun exit(view: View)

    fun setTitle()

    fun startBackground()
}