package com.developers.chukimmuoi.androidkotlin.ui.base

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/13/17.
 */

class Presenter<T : BaseView> : BasePresenter<T> {

    var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun isAttachView(): Boolean {
        return view != null
    }

    fun checkViewAttached(): Unit {
        if (!isAttachView()) throw ViewNotAttachException()
    }
}
