package com.developers.chukimmuoi.androidkotlin.ui.base

import android.view.View
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
                        positive: String, positiveCallback: ICallback? = null,
                        negative: String? = null, negativeCallback: ICallback? = null,
                        neutral: String? = null, neutralCallback: ICallback? = null)

    fun showDialogBasic(title: Int, content: Int,
                        positive: Int, positiveCallback: ICallback? = null,
                        negative: Int? = null, negativeCallback: ICallback? = null,
                        neutral: Int? = null, neutralCallback: ICallback? = null)

    fun showDialogProgress(title: String, content: String, horizontal: Boolean)

    fun showDialogProgressCircle(title: String, content: String)

    fun showDialogProgressHorizontal(title: String, content: String)

    fun showDialogProgress(title: Int, content: Int, horizontal: Boolean)

    fun showDialogProgressCircle(title: Int, content: Int)

    fun showDialogProgressHorizontal(title: Int, content: Int)

    fun dismissDialog()

    fun hideDialog()

    fun showToast(message: String, isLongTime: Boolean = false)

    fun showToast(message: Int, isLongTime: Boolean = false)

    fun dismissToast()

    fun showSnackbar(view: View, message: String, typeTime: Int,
                     actionName: String? = null, onClickListener: View.OnClickListener? = null)

    fun showSnackbar(view: View, message: Int, typeTime: Int,
                     actionName: Int? = null, onClickListener: View.OnClickListener? = null)

    fun dismissSnackbar()
}