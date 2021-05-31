package com.app.ymyapp.observer

import android.content.Context
import android.util.Log
import com.app.ymyapp.base.BaseResponse
import com.app.ymyapp.utlis.ExceptionHandle
import com.app.ymyapp.utlis.ProgressDialogHandler
import com.app.ymyapp.utlis.ToastUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 */
class ProgressObserver<T> :Observer<T>,BaseLifecycleObserver{

    private var listener: ObserverResponseListener<T>? = null
    private var mProgressDialogHandler: ProgressDialogHandler? = null
    private var context: Context? = null
    private lateinit var d: Disposable
    private val SUCCESS_CODE = 200


    constructor(context: Context?, listener: ObserverResponseListener<T>?, isDialog: Boolean, cancelable: Boolean) {
        this.listener = listener
        this.context = context
        if (isDialog) {
            mProgressDialogHandler = ProgressDialogHandler(context, cancelable)
        }
    }

    private fun showProgressDialog() {
        mProgressDialogHandler?.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)
            ?.sendToTarget()
    }

    private fun dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler!!.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
                .sendToTarget()
            mProgressDialogHandler = null
        }
    }
    override fun onComplete() {
        dismissProgressDialog();
    }

    override fun onSubscribe(d: Disposable) {
        Log.e("ProgressObserver____", "onSubscribe: ")
        this.d = d
        showProgressDialog()
    }

    override fun onNext(t: T) {
        if (t is BaseResponse<*>) {
            val response: BaseResponse<*> = t
            if (SUCCESS_CODE == response.code) {
                listener!!.onNext(response.result as T)
            } else {
                response.message?.let { ToastUtil.showLongToast(it) }
            }
        }
    }

    override fun onError(e: Throwable) {
        dismissProgressDialog()
        Log.e("ProgressObserver____", "onError: ", e)
        //自定义异常处理
        if (e is ExceptionHandle.ResponeThrowable) {
            listener!!.onError(e)
        } else {
            listener!!.onError(ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN))
        }

        if (e is UnknownHostException) {
            ToastUtil.showLongToast("请打开网络")
        } else if (e is SocketTimeoutException) {
            ToastUtil.showLongToast("请求超时")
        } else if (e is ConnectException) {
            ToastUtil.showLongToast("连接失败")
        } else if (e is HttpException) {
            ToastUtil.showLongToast("请求超时")
        } else {
            ToastUtil.showLongToast("请求失败")
        }
    }

    override fun onCreate() {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        println("MMAIN_结束Rxjava事物")
        if (!d!!.isDisposed) {
            d!!.dispose()
        }
    }

}