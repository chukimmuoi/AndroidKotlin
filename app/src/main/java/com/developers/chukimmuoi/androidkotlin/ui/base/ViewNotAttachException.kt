package com.developers.chukimmuoi.androidkotlin.ui.base

import java.lang.RuntimeException

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/13/17.
 */
class ViewNotAttachException : RuntimeException("Please call attachView() before proceeding!") {

}