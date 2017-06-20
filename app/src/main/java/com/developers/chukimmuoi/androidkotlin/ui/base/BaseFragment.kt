package com.developers.chukimmuoi.androidkotlin.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/18/17.
 */

abstract class BaseFragment : Fragment(), BaseFragmentView {

    private val TAG = BaseFragment::class.java.simpleName

    protected var mContext         : BaseActivity? = null

    protected var mFragmentListener: OnFragmentListener? = null

    protected var mFragmentManager : FragmentManager? = null

    interface OnFragmentListener {
        /**
         * Handler event Fragment send Activity send Fragment
         * Implement in BaseActivity

         * @param layoutId R.layout.fragment_name
         * *
         * @param event    Type EVENT in fragment
         * */
        fun onFragmentAction(layoutId: Int, event: Int)
    }

    /**
     * Level 01
     * Call when Fragment connect Activity
     * */
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mContext = activity as BaseActivity

        if (mContext is OnFragmentListener) {
            mFragmentListener = mContext as OnFragmentListener
        }
    }

    /**
     * Level 02
     * Call when create var not UI
     * eg: context, adapter, arrayList
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentManager = childFragmentManager

        val bundle: Bundle = arguments
        createVariableStart(bundle)

        createVariableNormal()
    }

    /**
     * Level 03
     * Set layout XML
     * */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = setLayout(inflater, container, savedInstanceState)

        return view
    }

    /**
     * Set var UI
     * eg: find view by ID
     * */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createVariableView(view, savedInstanceState)
    }

    /**
     * Call when Activity complete method onCreate()
     * */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * Level 04
     * Call when fragment ready show on screen
     * */
    override fun onStart() {
        super.onStart()
    }

    /**
     * Level 05
     * Handle resources. Multi screen
     * */
    override fun onResume() {
        super.onResume()
    }

    /**
     * Level 05
     * */
    override fun onPause() {
        super.onPause()
    }

    /**
     * Level 04
     * */
    override fun onStop() {
        super.onStop()
    }

    /**
     * Level 03
     * */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * Level 02
     * */
    override fun onDestroy() {
        mFragmentManager = null

        super.onDestroy()
    }

    /**
     * Level 01
     * */
    override fun onDetach() {
        mContext = null

        mFragmentListener = null

        super.onDetach()
    }

    /**
     * Bundle bundle = getArguments()
     */
    protected fun createVariableStart(bundle: Bundle) {

    }

    protected abstract fun createVariableNormal()

    protected abstract fun setLayout(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View

    protected abstract fun createVariableView(view: View?, savedInstanceState: Bundle?)

    override fun findingFragment(layoutId: Int, fragmentManager: FragmentManager?): Fragment {
        return mContext?.findingFragment(layoutId, fragmentManager) as Fragment
    }

    override fun findingFragment(layoutId: Int): Fragment {
        return findingFragment(layoutId, mFragmentManager)
    }

    override fun findingFragment(tag: String, fragmentManager: FragmentManager?): Fragment {
        return mContext?.findingFragment(tag, fragmentManager) as Fragment
    }

    override fun findingFragment(tag: String): Fragment {
        return findingFragment(tag, mFragmentManager)
    }

    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean, bundle: Bundle?,
                                 fragmentManager: FragmentManager?) {
        mContext?.displayFragment(idLayoutContainer, fragment, tag, isSaveCache, bundle, fragmentManager)
    }

    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean, bundle: Bundle?) {
        displayFragment(idLayoutContainer, fragment, tag, isSaveCache, bundle, mFragmentManager)
    }

    override fun displayFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                 isSaveCache: Boolean) {
        displayFragment(idLayoutContainer, fragment, tag, isSaveCache, null)
    }

    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?, bundle: Bundle?,
                                      fragmentManager: FragmentManager?) {
        mContext?.displayMultiFragment(idLayoutContainer, fragment, tag, tagParent, bundle, fragmentManager)
    }

    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?, bundle: Bundle?) {
        displayMultiFragment(idLayoutContainer, fragment, tag, tagParent, bundle, fragmentManager)
    }

    override fun displayMultiFragment(idLayoutContainer: Int, fragment: Fragment, tag: String,
                                      tagParent: String?) {
        displayMultiFragment(idLayoutContainer, fragment, tag, tagParent, null)
    }

    override fun backStackFragmentHome(fragmentManager: FragmentManager?) {
        mContext?.backStackFragmentHome(fragmentManager)
    }

    override fun backStackFragmentHome() {
        backStackFragmentHome(mFragmentManager)
    }

    override fun onBackPressed(fragmentManager: FragmentManager?) {
        val countFragment = fragmentManager?.backStackEntryCount ?: 0
        if (countFragment > 1) {
            fragmentManager?.popBackStack()
        } else {
            mContext?.onBackPressed()
        }
    }
}