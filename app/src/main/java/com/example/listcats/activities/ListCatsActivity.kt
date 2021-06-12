package com.example.listcats.activities

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.listcats.R
import com.example.listcats.adapters.CatInfoAdapter
import com.example.listcats.helpers.JsonHelper
import com.example.listcats.viewmodels.ListCatViewModel

class ListCatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cats)
        this.supportActionBar?.title = "Cat album"

        val listCatViewModel = ViewModelProvider(this).get(ListCatViewModel::class.java)
        listCatViewModel.setContext(this)

        var listInfoAdapter: CatInfoAdapter? = null

        listCatViewModel.getCurrentScrollToIndex().observe(this, Observer {
            Log.d("@@@ init", it.toString())

            listCatViewModel.updateRepo(it)
            if (it == 5) {
                //init
                listInfoAdapter =
                    listCatViewModel.catInfoRepo?.currentListCatInfo?.let { it1 ->
                        CatInfoAdapter(
                            this,
                            listCatViewModel
                        )
                    }
                findViewById<ListView>(R.id.listCatInfo).adapter = listInfoAdapter
            } else {
                listInfoAdapter?.notifyDataSetChanged()
            }

        })
    }
}