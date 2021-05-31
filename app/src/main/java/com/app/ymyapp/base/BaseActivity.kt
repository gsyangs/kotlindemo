package com.app.ymyapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife

/**
 * @author:create by ys
 * 时间:2021/5/24 16
 * 邮箱 894417048@qq.com
 */
abstract class BaseActivity<V:BaseView,P:BasePresenter<V>> : AppCompatActivity() ,BaseView{

    //引用V层和P层
    lateinit var presenter: P

    private var view: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayoutViewId() != 0){
            setContentView(getLayoutViewId())
            initView()
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this)

        presenter = createPresenter()

        if (view == null) {
            view = createView()
        }

        if (presenter != null && view != null) {
            presenter!!.attachView(view!!)
        }
        //presenter 绑定 activity什么周期
        lifecycle.addObserver(presenter)
    }

    /**
     * 有返回值
     */
    abstract fun getLayoutViewId() : Int;
    abstract fun createPresenter(): P
    abstract fun createView(): V

    /**
     * 无返回值
     */
    abstract fun initView();

    override fun showMsg(msg: String) {
    }

}