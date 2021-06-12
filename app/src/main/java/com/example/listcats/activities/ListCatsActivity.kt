package com.example.listcats.activities

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.example.listcats.R
import com.example.listcats.adapters.CatInfoAdapter
import com.example.listcats.models.ListCatInfo

class ListCatsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cats)

        val catInfoData = ListCatInfo.getListCatInfo(this)
        val catInfoAdapter =
            CatInfoAdapter(this, catInfoData)

        val catInfoListView = findViewById<ListView>(R.id.listCatInfo)
        catInfoListView.adapter = catInfoAdapter

    }
}