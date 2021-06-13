package com.example.listcats.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.listcats.R
import com.example.listcats.adapters.CatInfoAdapter
import com.example.listcats.viewmodels.ListCatViewModel


class ListCatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cats)
        this.supportActionBar?.title = "Cat album"

        //set up recycleView
        val listRecyclerView = findViewById<RecyclerView>(R.id.listCatInfo)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.scrollToPosition(0)
        listRecyclerView.setLayoutManager(layoutManager);

        //set up viewModel
        val listCatViewModel = ViewModelProvider(this).get(ListCatViewModel::class.java)
        listCatViewModel.setContext(this)

        var listInfoAdapter: CatInfoAdapter? = null

        listCatViewModel.getCurrentScrollToIndex().observe(this, Observer {
            Log.d("@@@ init", it.toString())

            listCatViewModel.updateRepo(it)
            if (listInfoAdapter == null) {
                //init
                listInfoAdapter =
                    listCatViewModel.catInfoRepo?.currentListCatInfo?.let { it1 ->
                        CatInfoAdapter(
                            this,
                            listCatViewModel
                        )
                    }
                listRecyclerView.adapter = listInfoAdapter
            } else {
                listRecyclerView.post(Runnable {
                    listInfoAdapter?.notifyDataSetChanged()
                })
            }

        })
    }
}