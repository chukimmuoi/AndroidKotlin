package com.developers.chukimmuoi.androidkotlin.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/18/17.
 */

interface BaseFragmentView : BaseView {

    fun findingFragment(@IdRes layoutId: Int, fragmentManager: FragmentManager?): Fragment

    fun findingFragment(@IdRes layoutId: Int): Fragment

    fun findingFragment(tag: String, fragmentManager: FragmentManager?): Fragment

    fun findingFragment(tag: String): Fragment

    fun displayFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                        isSaveCache: Boolean, bundle: Bundle?,
                        fragmentManager: FragmentManager?)

    fun displayFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                        isSaveCache: Boolean, bundle: Bundle?)

    fun displayFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                        isSaveCache: Boolean)

    fun displayMultiFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                             tagParent: String?, bundle: Bundle?,
                             fragmentManager: FragmentManager?)

    fun displayMultiFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                             tagParent: String?, bundle: Bundle?)

    fun displayMultiFragment(@IdRes idLayoutContainer: Int, fragment: Fragment, tag: String,
                             tagParent: String?)

    fun backStackFragmentHome(fragmentManager: FragmentManager?)

    fun backStackFragmentHome()

    fun onBackPressed(fragmentManager: FragmentManager?)

    fun addAnimation(fragmentTransaction: FragmentTransaction? = null)
}