package com.developers.chukimmuoi.androidkotlin.ui.base

import android.graphics.Typeface
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.developers.chukimmuoi.androidkotlin.R
import com.developers.chukimmuoi.androidkotlin.listener.callback.ICallback
import com.developers.chukimmuoi.androidkotlin.utils.overrideFont
import com.developers.chukimmuoi.androidkotlin.utils.overrideSize

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/13/17.
 */

open class BaseActivity : AppCompatActivity(), BaseActivityView, BaseFragmentView,
        BaseFragment.OnFragmentListener {

    private val TAG = BaseActivity::class.java.simpleName

    var typefaceBold   : Typeface? = null

    var typefaceItalic : Typeface? = null

    var typefaceLight  : Typeface? = null

    var typefaceMedium : Typeface? = null

    var typefaceRegular: Typeface? = null

    var typefaceThin   : Typeface? = null

    private var mMaterialDialog: MaterialDialog? = null

    private var mToast: Toast? = null

    private var mSnackbar: Snackbar? = null

    private var mFragmentManager: FragmentManager? = null

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    /**
     * Level 01
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createTypeface()

        mFragmentManager = supportFragmentManager
    }

    /**
     * Level 02
     * Call when activity VISIBLE
     * */
    override fun onStart() {
        super.onStart()
    }

    /**
     * Level 02
     * Call when activity STOP => RESTART => START
     * */
    override fun onRestart() {
        super.onRestart()
    }

    /**
     * Level 03
     * Call when activity start interacting by user
     * */
    override fun onResume() {
        super.onResume()
    }

    /**
     * Level 03
     * Call when save data, stop animation, CPU danger ...
     * */
    override fun onPause() {
        super.onPause()
    }

    /**
     * Level 02
     * Call when activity INVISIBLE
     * */
    override fun onStop() {
        dismissDialog()
        dismissToast()
        dismissSnackbar()

        super.onStop()
    }

    /**
     * Level 01
     * */
    override fun onDestroy() {
        destroyTypeface()

        mFragmentManager = null

        super.onDestroy()
    }

    override fun createTypeface() {
        overrideFont(applicationContext, "SERIF", "fonts/Roboto-Regular.ttf")
        overrideSize(this@BaseActivity, 1.0f)

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

    override fun showSnackbar(view: View, message: String, typeTime: Int,
                              actionName: String?, onClickListener: View.OnClickListener?) {
        dismissSnackbar()

        mSnackbar = Snackbar.make(view, message, typeTime).setAction(actionName, onClickListener)
        mSnackbar?.show()
    }

    override fun showSnackbar(view: View, message: String, typeTime: Int) {
        showSnackbar(view, message, typeTime, null, null)
    }

    override fun showSnackbar(view: View, message: Int, typeTime: Int,
                              actionName: Int?, onClickListener: View.OnClickListener?) {
        showSnackbar(view, getString(message), typeTime,
                actionName?.let { getString(actionName) }, onClickListener)
    }

    override fun showSnackbar(view: View, message: Int, typeTime: Int) {
        showSnackbar(view, getString(message), typeTime)
    }

    override fun dismissSnackbar() {
        mSnackbar?.dismiss()
        mSnackbar = null
    }

    /**
     * Handle event multi fragment
     * */
    override fun onFragmentAction(layoutId: Int, event: Int) {

    }

    override fun findingFragment(layoutId: Int, fragmentManager: FragmentManager?): Fragment {
        return fragmentManager?.findFragmentById(layoutId) as Fragment
    }

    override fun findingFragment(layoutId: Int): Fragment {
        return findingFragment(layoutId, mFragmentManager)
    }

    override fun findingFragment(tag: String, fragmentManager: FragmentManager?): Fragment {
        return fragmentManager?.findFragmentByTag(tag) as Fragment
    }

    override fun findingFragment(tag: String): Fragment {
        return findingFragment(tag, mFragmentManager)
    }

    /**
     * @param idLayoutContainer FrameLayout
     * @param fragment          new Fragment()
     * @param tag               FragmentCustom.class.getCanonicalName() or this.getClass().getCanonicalName()
     * @param isSaveCache       saveCache back stack
     * @param bundle            Set first data
     */
    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean, bundle: Bundle?,
                                 fragmentManager: FragmentManager?) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        addAnimation(fragmentTransaction)

        if (bundle != null) {
            fragment.arguments = bundle
        }
        fragmentTransaction?.replace(idLayoutContainer, fragment, tag)
        if (isSaveCache) {
            fragmentTransaction?.addToBackStack(tag)
        }

        fragmentTransaction?.commit()
    }

    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean, bundle: Bundle?) {
        displayFragment(idLayoutContainer, fragment, tag, isSaveCache, bundle, mFragmentManager)
    }

    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean) {
        displayFragment(idLayoutContainer, fragment, tag, isSaveCache, null)
    }

    /**
     * @param idLayoutContainer FrameLayout
     * @param fragment          new Fragment()
     * @param tag               FragmentCustom.class.getCanonicalName() or this.getClass().getCanonicalName()
     * @param tagParent         get fragment parent
     * @param bundle            Set first data
     */
    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?, bundle: Bundle?,
                                      fragmentManager: FragmentManager?) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        addAnimation(fragmentTransaction)

        //Hide fragment parent.
        if (!TextUtils.isEmpty(tagParent)) {
            val parentFragment = findingFragment(tagParent as String)
            if (parentFragment != null) {
                fragmentTransaction?.hide(parentFragment)
            }
        }

        //Show fragment if exist.
        if (fragment.isAdded) {
            fragmentTransaction?.show(fragment)
        }
        //If fragment not exist.
        else {
            //Remove old fragment if old fragment = tag.
            val fragmentOld = findingFragment(tag)
            if (fragmentOld != null) {
                fragmentTransaction?.remove(fragmentOld)
            }

            //Add new fragment.
            if (bundle != null) {
                fragment.arguments = bundle
            }

            fragmentTransaction?.add(idLayoutContainer, fragment, tag)
        }

        fragmentTransaction?.commit()
    }

    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?, bundle: Bundle?) {
        displayMultiFragment(idLayoutContainer, fragment, tag, tagParent, bundle, mFragmentManager)
    }

    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?) {
        displayMultiFragment(idLayoutContainer, fragment, tag, tagParent, null)
    }

    /**
     * Event key back to home
     */
    override fun backStackFragmentHome(fragmentManager: FragmentManager?) {
        val countFragment = fragmentManager?.backStackEntryCount ?: 0
        if (countFragment > 0) {
            val firstFragment = fragmentManager?.getBackStackEntryAt(0)
            fragmentManager?.popBackStack(firstFragment?.id as Int, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun backStackFragmentHome() {
        backStackFragmentHome(mFragmentManager)
    }

    override fun onBackPressed(fragmentManager: FragmentManager?) {
        val countFragment = fragmentManager?.backStackEntryCount ?: 0
        if (countFragment > 1) {
            fragmentManager?.popBackStack()
        } else {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBackPressed(mFragmentManager)
    }

    override fun addAnimation(fragmentTransaction: FragmentTransaction?) {
        fragmentTransaction?.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out,
                R.anim.abc_popup_enter, R.anim.abc_popup_exit)
    }
}
