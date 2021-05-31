package com.app.ymyapp.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.app.ymyapp.R
import net.frakbot.jumpingbeans.JumpingBeans
import java.text.DecimalFormat


/**
 * @author:create by ys
 * 时间:2021/5/24 17
 * 邮箱 894417048@qq.com
 */
class LoadingDialog : Dialog {

    private var mContext: Context
    private var mCancelable = false
    private var title: String? = null
    private var showLoadingPercent = false
    private var percentTxt: TextView? = null
    private var loadingView: ImageView? = null
    private var decimalFormat: DecimalFormat? = null
    private lateinit var loadingTitle: TextView
    private var jumpingBeans: JumpingBeans? = null

    constructor(context: Context) : super(context, R.style.dialog) {
        mContext = context
    }

    constructor(context: Context, title: String?) : super(
        context,
        R.style.dialog
    ) {
        mContext = context
        this.title = title
    }

    constructor(context: Context, cancelable: Boolean) : super(
        context,
        R.style.dialog
    ) {
        mContext = context
        mCancelable = cancelable
    }

    constructor(
        context: Context,
        title: String?,
        showLoadingPercent: Boolean,
        cancelable: Boolean
    ) : super(context, R.style.dialog) {
        mContext = context
        mCancelable = cancelable
        this.title = title
        this.showLoadingPercent = showLoadingPercent
        decimalFormat = DecimalFormat("#.##") //小数点格式化对象
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_dialog_layout)
        setCanceledOnTouchOutside(false)
        setCancelable(mCancelable)
        loadingView = findViewById(R.id.loading_image)
        loadingTitle = findViewById(R.id.loading_title)
        if (title != null && "" != title) {
            loadingTitle?.setText(title)
        }

        if (jumpingBeans == null) {
            jumpingBeans = JumpingBeans.with(loadingTitle)
                    .makeTextJump(0, loadingTitle?.text.toString().length)
                    .setIsWave(true)
                    .setLoopDuration(1000)
                    .build()
        }
        if (showLoadingPercent) {
            percentTxt = findViewById(R.id.loading_percent)
            percentTxt?.setVisibility(View.VISIBLE)
        }
    }

    fun setPercent(percent: Double) {
        if (percentTxt == null) {
            return
        }
        val showValue = decimalFormat!!.format(percent) + "%"
        percentTxt!!.text = showValue
    }

    fun setTitle(title: String?) {
        if (title == null) {
            return
        }
        loadingTitle!!.text = title
    }

    override fun show() {
        super.show()
        loadingView!!.startAnimation(
            AnimationUtils.loadAnimation(
                mContext,
                R.anim.progress_anim
            )
        )
    }
}