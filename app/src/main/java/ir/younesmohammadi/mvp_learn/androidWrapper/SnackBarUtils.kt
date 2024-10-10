package ir.younesmohammadi.mvp_learn.androidWrapper

import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object SnackBarUtils {


    fun show(
        view: View,
        text: String
    ) {
        val snack = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        snack.setTextColor(Color.parseColor("#F0F3FF"))
        snack.duration = 1000
        snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snack.setBackgroundTint(Color.parseColor("#404040"))
        ViewCompat.setLayoutDirection(snack.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snack.show()
    }

}