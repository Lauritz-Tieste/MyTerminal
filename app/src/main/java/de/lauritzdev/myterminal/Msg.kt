package de.lauritzdev.myterminal

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Msg {
    fun show(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun e(text: String) {
        Log.e("TAG", text)
    }

    fun d(text: String) {
        Log.d("TAG", text)
    }

    fun success(context: Context, title: String, msg: String) {
        MotionToast.createToast(
            context as Activity,
            title,
            msg,
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
        )
    }

    fun error(context: Context, title: String, msg: String) {
        MotionToast.createToast(
            context as Activity,
            title,
            msg,
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
        )
    }

    fun warn(context: Context, title: String, msg: String) {
        MotionToast.createToast(
            context as Activity,
            title,
            msg,
            MotionToastStyle.WARNING,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
        )
    }

    fun info(context: Context, title: String, msg: String) {
        MotionToast.createToast(
            context as Activity,
            title,
            msg,
            MotionToastStyle.INFO,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
        )
    }
}