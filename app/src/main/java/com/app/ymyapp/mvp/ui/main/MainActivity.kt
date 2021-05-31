package com.app.ymyapp.mvp.ui.main

import com.app.ymyapp.R
import com.app.ymyapp.base.BaseActivity
import com.app.ymyapp.bean.ImageBo
import com.app.ymyapp.bean.InfosBo
import com.app.ymyapp.mvp.contract.MainContract
import com.app.ymyapp.mvp.presenter.MainPresenter

class MainActivity : BaseActivity<MainContract.IMainView,MainContract.IMainPresenter>(),MainContract.IMainView{

    override fun createPresenter(): MainContract.IMainPresenter {
        return MainPresenter(this)
    }

    override fun createView(): MainContract.IMainView {
        return this
    }

    override fun getLayoutViewId(): Int {
        return R.layout.activity_main;
    }

    override fun initView() {
        presenter.getImages()
        presenter.getWangYiNews()
    }

    override fun getImages(imageBos: List<ImageBo>) {
        TODO("Not yet implemented")
    }

    override fun getWangYiNews(infosBos: List<InfosBo>) {
        TODO("Not yet implemented")
    }

}