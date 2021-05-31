package com.app.ymyapp.utlis

import android.content.Context
import android.os.Handler
import android.os.Message
import com.app.ymyapp.common.LoadingDialog

/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 */
class ProgressDialogHandler( context: Context?, cancelable: Boolean): Handler() {

    companion object{
        val SHOW_PROGRESS_DIALOG = 1
        val DISMISS_PROGRESS_DIALOG = 2
    }

    private var sad: LoadingDialog? = null
    private var context: Context? = context
    private var cancelable = cancelable

    private fun initProgressDialog() {
        if (sad == null) {
            sad = LoadingDialog(context!!, cancelable)
            if (!sad!!.isShowing) {
                sad!!.show()
            }
        } else {
            sad!!.show()
        }
    }

    private fun dismissProgressDialog() {
        if (sad != null) {
            sad!!.dismiss()
            sad = null
        }
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            SHOW_PROGRESS_DIALOG -> initProgressDialog()
            DISMISS_PROGRESS_DIALOG -> dismissProgressDialog()
        }
    }
}