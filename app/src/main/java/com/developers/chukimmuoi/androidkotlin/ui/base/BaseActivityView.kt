package com.developers.chukimmuoi.androidkotlin.ui.base

import com.developers.chukimmuoi.androidkotlin.listener.callback.ICallback

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/18/17.
 */

interface BaseActivityView : BaseView {
    fun createTypeface()

    fun destroyTypeface()

    fun showDialogBasic(title: String, content: String,
                        positive: String, positiveCallback: ICallback?,
                        negative: String?, negativeCallback: ICallback?,
                        neutral: String?, neutralCallback: ICallback?)

    fun showDialogBasic(title: String, content: String,
                        positive: String, positiveCallback: ICallback?,
                        negative: String?, negativeCallback: ICallback?)

    fun showDialogBasic(title: String, content: String,
                        positive: String, positiveCallback: ICallback?,
                        negative: String?)

    fun showDialogBasic(title: String, content: String,
                        positive: String, positiveCallback: ICallback?)

    fun showDialogBasic(title: String, content: String,
                        positive: String)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int, positiveCallback: ICallback?,
                        negative: Int?, negativeCallback: ICallback?,
                        neutral: Int?, neutralCallback: ICallback?)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int, positiveCallback: ICallback?,
                        negative: Int?, negativeCallback: ICallback?)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int, positiveCallback: ICallback?,
                        negative: Int?)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int, positiveCallback: ICallback?)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int)

    fun showDialogProgress(title: String, content: String, horizontal: Boolean)

    fun showDialogProgressCircle(title: String, content: String)

    fun showDialogProgressHorizontal(title: String, content: String)

    fun showDialogProgress(title: Int, content: Int, horizontal: Boolean)

    fun showDialogProgressCircle(title: Int, content: Int)

    fun showDialogProgressHorizontal(title: Int, content: Int)

    fun dismissDialog()

    fun hideDialog()

    fun showToast(message: String, isLongTime: Boolean)

    fun showToast(message: String)

    fun showToast(message: Int, isLongTime: Boolean)

    fun showToast(message: Int)

    fun dismissToast()
}