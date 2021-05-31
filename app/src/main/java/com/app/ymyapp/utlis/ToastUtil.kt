package com.app.ymyapp.utlis

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.app.ymyapp.R
import com.app.ymyapp.application.YmyApplication

/**
 * @author:create by ys
 * 时间:2021/5/25 15
 * 邮箱 894417048@qq.com
 */
object ToastUtil {
    private var toast: Toast? = null
    private var stoast: TextView? = null

    fun showShortToast(msg: String) {
        showCustomToast(YmyApplication.instance, msg, Toast.LENGTH_SHORT)
    }

    fun showShortToast(msgId: Int) {
        YmyApplication.instance?.let { showCustomToast(it, msgId, Toast.LENGTH_SHORT) }
    }

    fun showLongToast(msg: String) {
        showCustomToast(YmyApplication.instance, msg, Toast.LENGTH_LONG)
    }

    fun showLongToast(msgId: Int) {
        YmyApplication.instance?.let { showCustomToast(it, msgId, Toast.LENGTH_LONG) }
    }

    fun showToastInUiThread(activity: Activity?, msg: String) {
        activity?.runOnUiThread { showCustomToast(activity, msg) }
    }

    fun showToastInUiThread(activity: Activity?, stringId: Int) {
        activity?.runOnUiThread { showCustomToast(activity, stringId) }
    }

    private fun showCustomToast(context: Context, msgId: Int) {
        val msg = context.resources.getString(msgId)
        showCustomToast(context, msg)
    }

    private fun showCustomToast(context: Context, msg: String) {
        showCustomToast(context, msg, Toast.LENGTH_SHORT)
    }

    private fun showCustomToast(context: Context, msgId: Int, duration: Int) {
        val msg = context.resources.getString(msgId)
        showCustomToast(context, msg, duration)
    }

    private fun showCustomToast(context: Context?, msg: String, duration: Int) {
        if (context == null) {
            return
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showToast(context, msg, duration)
        } else {
            Handler(Looper.getMainLooper()).post { showToast(context, msg, duration) }
        }
    }

    private fun showToast(context: Context?, msg: String, duration: Int) {
        if (null != context) {
            if (toast == null) {
                toast = Toast(context)
                val inflater = LayoutInflater.from(context)
                val layout: View = inflater.inflate(R.layout.toast_layout, null)
                stoast = layout.findViewById<TextView>(R.id.message)
                stoast?.text = msg
                toast?.duration = duration
                toast?.setView(layout)
            } else {
                stoast?.text = msg
            }
            toast?.show()
        }
    }

}