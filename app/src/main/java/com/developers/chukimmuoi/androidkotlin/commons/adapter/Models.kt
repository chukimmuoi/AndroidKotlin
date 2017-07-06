package com.developers.chukimmuoi.androidkotlin.commons.adapter

import com.developers.chukimmuoi.androidkotlin.constants.Constants

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/27/17.
 */

class LoadMoreCircleItem : ItemAdapter {
    override fun getViewType(): Int = Constants.VIEW_PROGRESS
}

data class Contact(val name: String, val isOnline: Boolean) : ItemAdapter {
    override fun getViewType(): Int = Constants.VIEW_ITEM

    companion object {
        private var lastContactId = 0

        fun createContactsList(numContacts: Int, offset: Int): MutableList<ItemAdapter> {
            val contacts = ArrayList<ItemAdapter>()

            (1..numContacts).mapTo(contacts) { Contact("Person " + ++lastContactId + " offset: " + offset, it <= numContacts / 2) }

            return contacts
        }
    }
}