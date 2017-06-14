package com.developers.chukimmuoi.androidkotlin.ui.base

import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.developers.chukimmuoi.androidkotlin.R
import com.developers.chukimmuoi.androidkotlin.listener.callback.ICallback
import java.util.logging.Logger


/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/13/17.
 */

class BaseActivity : AppCompatActivity(), BaseView {

    private val TAG = Logger.getLogger(BaseActivity::class.java.name)

    private var mMaterialDialog: MaterialDialog? = null

    private var mToast: Toast? = null

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback,
                                 negative: String, negativeCallback: ICallback,
                                 neutral: String, neutralCallback: ICallback) {
        dismissDialog()

        val builder = MaterialDialog.Builder(this)
                .backgroundColorRes(R.color.colorWhite)
                .title(title)
                .titleColorRes(R.color.colorDialogTitle)
                .content(content)
                .contentColorRes(R.color.colorDialogContent)

        if (!TextUtils.isEmpty(positive)) {
            builder.positiveText(positive).positiveColorRes(R.color.colorDialogPositive)
            if (positiveCallback != null) {
                builder.onPositive { dialog, which -> positiveCallback?.onAction(null) }
            }
        }

        if (!TextUtils.isEmpty(negative)) {
            builder.negativeText(negative).negativeColorRes(R.color.colorDialogNegative)
            if (negativeCallback != null) {
                builder.onNegative { dialog, which -> negativeCallback?.onAction(null) }
            }
        }

        if (!TextUtils.isEmpty(neutral)) {
            builder.neutralText(neutral).neutralColorRes(R.color.colorDialogNeutral)
            if (neutralCallback != null) {
                builder.onNeutral { dialog, which -> neutralCallback?.onAction(null) }
            }
        }

        mMaterialDialog = builder.show()
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback,
                                 negative: String, negativeCallback: ICallback) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                negative, negativeCallback,
                null!!, null!!)
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback,
                                 negative: String) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                negative, null!!)
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                null!!)
    }

    override fun showDialogBasic(title: String, content: String, positive: String) {
        showDialogBasic(title, content,
                positive, null!!)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback,
                                 negative: Int, negativeCallback: ICallback,
                                 neutral: Int, neutralCallback: ICallback) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                getString(negative), negativeCallback,
                getString(neutral), neutralCallback)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback,
                                 negative: Int, negativeCallback: ICallback) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                getString(negative), negativeCallback,
                null!!, null!!)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback,
                                 negative: Int) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                getString(negative), null!!)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                null!!)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), null!!)
    }

    override fun showDialogProgress(title: String, content: String, horizontal: Boolean) {
        dismissDialog()

        val builder = MaterialDialog.Builder(this)
                .backgroundColorRes(R.color.colorWhite)
                .title(title)
                .titleColorRes(R.color.colorDialogTitle)
                .content(content)
                .contentColorRes(R.color.colorDialogContent)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .widgetColorRes(R.color.colorDialogProgress)

        mMaterialDialog = builder.show()
    }

    override fun showDialogProgressCircle(title: String, content: String) {
        showDialogProgress(title, content, false)
    }

    override fun showDialogProgressHorizontal(title: String, content: String) {
        showDialogProgress(title, content, true)
    }

    override fun showDialogProgress(title: Int, content: Int, horizontal: Boolean) {
        showDialogProgress(getString(title), getString(content), horizontal)
    }

    override fun showDialogProgressCircle(title: Int, content: Int) {
        showDialogProgress(title, content, false)
    }

    override fun showDialogProgressHorizontal(title: Int, content: Int) {
        showDialogProgress(title, content, true)
    }

    override fun dismissDialog() {
        mMaterialDialog?.dismiss()
        mMaterialDialog = null
    }

    override fun hideDialog() {
        mMaterialDialog?.hide()
    }

    override fun showToast(message: String, isLongTime: Boolean) {
        runOnUiThread {
            dismissToast()

            mToast = Toast.makeText(applicationContext, message,
                    if (isLongTime) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
            mToast?.setGravity(Gravity.CENTER, 0, 0)
            mToast?.show()
        }
    }

    override fun showToast(message: String) {
        showToast(message, false)
    }

    override fun showToast(message: Int, isLongTime: Boolean) {
        showToast(getString(message), isLongTime)
    }

    override fun showToast(message: Int) {
        showToast(message, false)
    }

    override fun dismissToast() {
        mToast?.cancel()
        mToast = null
    }
}
