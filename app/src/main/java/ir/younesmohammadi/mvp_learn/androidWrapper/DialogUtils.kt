package ir.younesmohammadi.mvp_learn.androidWrapper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View

object DialogUtils {

    private lateinit var dialog: Dialog

    fun show(context: Context, view: View, cancelable: Boolean) {
        dialog = Dialog(context).apply {
            setContentView(view)
            setCancelable(cancelable)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }

    fun dismiss() {
        dialog.dismiss()
    }

}