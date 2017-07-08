package com.developers.chukimmuoi.androidkotlin

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.commons.adapter.delegate.AdapterDelegate
import com.developers.chukimmuoi.androidkotlin.data.model.Contact
import com.developers.chukimmuoi.androidkotlin.listener.onclick.OnItemClickListener
import com.developers.chukimmuoi.androidkotlin.utils.inflate
import kotlinx.android.synthetic.main.item_contact.view.*

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/29/17.
 */

class ContactDelegate(activity: Activity,
                      listener: OnItemClickListener? = null)
    : AdapterDelegate<List<ItemAdapter>>() {

    private val TAG = ContactDelegate::class.java.simpleName

    private val inflater: LayoutInflater       = activity.layoutInflater

    private var listener: OnItemClickListener? = listener

    override fun isForViewType(items: List<ItemAdapter>,
                               position: Int): Boolean {

        return items[position] is Contact
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {

        return CreateViewHolder(parent)
    }

    override fun onBindViewHolder(items: List<ItemAdapter>,
                                  position: Int,
                                  holder: RecyclerView.ViewHolder,
                                  payloads: List<Any>) {

        val viewHolder = (holder as CreateViewHolder)
        val item       = items[position] as Contact

        val name = item.name
        viewHolder.itemView.contact_name.text = "$name , $position"
        if (item.isOnline) {
            viewHolder.itemView.message_button.text = "Message"
            viewHolder.itemView.message_button.isEnabled = true
        } else {
            viewHolder.itemView.message_button.text = "Offline"
            viewHolder.itemView.message_button.isEnabled = false
        }
    }

    inner class CreateViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.item_contact)) {

        init {
            itemView.setOnClickListener { v ->
                listener?.onItemClick(ContactDelegate::class.java.name, v, adapterPosition) }
        }
    }
}