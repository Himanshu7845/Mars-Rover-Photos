package com.reapairsduniya.unorgassingment.loader

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import com.reapairsduniya.unorgassingment.R

object Loader {
    var dialog: Dialog? = null //obj

    fun showLoading(context: Context) { // function -- context(parent (reference))
        dialog = Dialog(context!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.dialog_loading)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)
        dialog!!.setCanceledOnTouchOutside(false)
        val window = dialog!!.window
        if (window != null) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            )
            //   window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        var animationSet = AnimationSet(false)

        var animationIn = AnimationUtils.loadAnimation(context, R.anim.zoomm_in);
        var animationOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out);
        var rotation = AnimationUtils.loadAnimation(context, R.anim.rotation);

        val loader = dialog?.findViewById(R.id.loader_login) as View
        animationSet.addAnimation(animationIn);
        animationSet.addAnimation(animationOut);
        animationSet.addAnimation(rotation);
        loader.animation = animationSet

        try {

            dialog!!.show()
        } catch (e: Exception) {
        }
    }

    fun hideLoading() {
        try {
            if (dialog != null) {
                dialog!!.dismiss()
            }
        } catch (e: Exception) {
        }
    }
}