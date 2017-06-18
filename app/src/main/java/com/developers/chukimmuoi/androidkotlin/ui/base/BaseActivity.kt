package com.developers.chukimmuoi.androidkotlin.ui.base

import android.graphics.Typeface
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.developers.chukimmuoi.androidkotlin.R
import com.developers.chukimmuoi.androidkotlin.listener.callback.ICallback
import com.developers.chukimmuoi.androidkotlin.utils.TypefaceUtil




/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/13/17.
 */

open class BaseActivity : AppCompatActivity(), BaseView {

    private val TAG = BaseActivity::class.java.simpleName

    var typefaceBold: Typeface? = null

    var typefaceItalic: Typeface? = null

    var typefaceLight: Typeface? = null

    var typefaceMedium: Typeface? = null

    var typefaceRegular: Typeface? = null

    var typefaceThin: Typeface? = null

    private var mMaterialDialog: MaterialDialog? = null

    private var mToast: Toast? = null

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createTypeface()
    }

    override fun createTypeface() {
        TypefaceUtil.overrideFont(applicationContext, "SERIF", "fonts/Roboto-Regular.ttf")
        TypefaceUtil.overrideSize(this@BaseActivity, 0.5f)

        typefaceBold    = Typeface.createFromAsset(assets, "fonts/Roboto-Bold.ttf")
        typefaceItalic  = Typeface.createFromAsset(assets, "fonts/Roboto-Italic.ttf")
        typefaceLight   = Typeface.createFromAsset(assets, "fonts/Roboto-Light.ttf")
        typefaceMedium  = Typeface.createFromAsset(assets, "fonts/Roboto-Medium.ttf")
        typefaceRegular = Typeface.createFromAsset(assets, "fonts/Roboto-Regular.ttf")
        typefaceThin    = Typeface.createFromAsset(assets, "fonts/Roboto-Thin.ttf")
    }

    override fun destroyTypeface() {
        typefaceBold    = null
        typefaceItalic  = null
        typefaceLight   = null
        typefaceMedium  = null
        typefaceRegular = null
        typefaceThin    = null
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback?,
                                 negative: String?, negativeCallback: ICallback?,
                                 neutral: String?, neutralCallback: ICallback?) {
        dismissDialog()

        val builder = MaterialDialog.Builder(this)
                .backgroundColorRes(R.color.colorWhite)
                .title(title)
                .titleColorRes(R.color.colorDialogTitle)
                .content(content)
                .contentColorRes(R.color.colorDialogContent)

        if (positive != null && positive.length > 0) {
            builder.positiveText(positive).positiveColorRes(R.color.colorDialogPositive)
            positiveCallback?.let {
                builder.onPositive { dialog, which -> positiveCallback.onAction(null) }
            }
        }

        if (negative != null && negative.length > 0) {
            builder.negativeText(negative).negativeColorRes(R.color.colorDialogNegative)
            negativeCallback?.let {
                builder.onNegative { dialog, which -> negativeCallback.onAction(null) }
            }
        }

        if (neutral != null && neutral.length > 0) {
            builder.neutralText(neutral).neutralColorRes(R.color.colorDialogNeutral)
            neutralCallback?.let {
                builder.onNeutral { dialog, which -> neutralCallback.onAction(null) }
            }
        }

        mMaterialDialog = builder.show()
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback?,
                                 negative: String?, negativeCallback: ICallback?) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                negative, negativeCallback,
                null, null)
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback?,
                                 negative: String?) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                negative, null)
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String, positiveCallback: ICallback?) {
        showDialogBasic(title, content,
                positive, positiveCallback,
                null)
    }

    override fun showDialogBasic(title: String, content: String,
                                 positive: String) {
        showDialogBasic(title, content,
                positive, null)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback?,
                                 negative: Int?, negativeCallback: ICallback?,
                                 neutral: Int?, neutralCallback: ICallback?) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                negative?.let { getString(it) }, negativeCallback,
                neutral?.let { getString(it) }, neutralCallback)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback?,
                                 negative: Int?, negativeCallback: ICallback?) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                negative?.let { getString(it) }, negativeCallback,
                null, null)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback?,
                                 negative: Int?) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                negative?.let { getString(it) }, null)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int, positiveCallback: ICallback?) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), positiveCallback,
                null)
    }

    override fun showDialogBasic(title: Int, content: Int,
                                 positive: Int) {
        showDialogBasic(getString(title), getString(content),
                getString(positive), null)
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

    override fun onStop() {
        dismissDialog()
        dismissToast()

        super.onStop()
    }

    override fun onDestroy() {
        destroyTypeface()

        super.onDestroy()
    }
}
