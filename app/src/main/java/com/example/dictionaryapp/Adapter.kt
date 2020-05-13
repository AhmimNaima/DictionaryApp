package com.example.dictionaryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class Adapter(
    internal var mContext: Context
    ): BaseAdapter() {
        internal var inflater: LayoutInflater
        private val arraylist: ArrayList<Word>

        init {
            inflater = LayoutInflater.from(mContext)
            this.arraylist = ArrayList()
            this.arraylist.addAll(FragmentList.WordList)
        }

        inner class ViewHolder {

            internal var word: TextView? = null
        }

        override fun getCount(): Int {
            return FragmentList.WordList.size
        }

        override fun getItem(position: Int):Word {
            return FragmentList.WordList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            var view = view
            val holder: ViewHolder
            if (view == null) {
                holder = ViewHolder()
                view = inflater.inflate(R.layout.item_view, null)
                holder.word = view!!.findViewById(R.id.word) as TextView
                view.tag = holder
            } else {
                holder = view.tag as ViewHolder
            }
            // Set the results into TextViews
            holder.word!!.text = FragmentList.WordList[position].getWord()

            return view
        }

    }