package com.example.dictionaryapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.fragment_fragment_list.*
import java.util.ArrayList


class FragmentList : Fragment() {

    private var adapter: Adapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_fragment_list, container, false)

        var array = arrayOf( Word(resources.getStringArray(R.array.word_array)[0]),
            Word(resources.getStringArray(R.array.word_array)[1]),
            Word(resources.getStringArray(R.array.word_array)[2]))



        val liste = rootView.findViewById(R.id.words) as ListView
        WordList = ArrayList()

        for (i in array!!.indices) {
            val word = array!![i]
            // Binds all strings into an array
            WordList.add(word)
        }

        adapter = Adapter(activity!!)
        liste.adapter = adapter
        liste.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                 val bundle = Bundle()
                 bundle.putInt("amount", position)

                 val fragment = FragmentDetail()
                 val fragmentManager = activity!!.supportFragmentManager

                 val fragmentTransaction = fragmentManager.beginTransaction()
                 fragmentTransaction.replace(R.id.contaner, fragment)
                 fragment.arguments=bundle
                 fragmentTransaction.addToBackStack(null)
                 fragmentTransaction.commit()


            }
        }

        return rootView
    }
    companion object {
        var WordList = ArrayList<Word>()
    }

}



