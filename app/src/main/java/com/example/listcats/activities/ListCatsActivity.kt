package com.example.listcats.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.listcats.R
import com.example.listcats.helpers.JsonHandler
import com.example.listcats.models.CatInfo

class ListCatsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cats)

        val stringListCats = JsonHandler.getJsonDataFromAsset(this, "listcats.json");

        val jsonListCats = stringListCats?.let { JsonHandler.getJsonArrayromString(it) }

        val listCatsInfo = ArrayList<CatInfo>()

        for (index: Int in 0..(jsonListCats?.length() ?: 0) - 1) {
            jsonListCats?.getJSONObject(index).let {it ->
                listCatsInfo.add(CatInfo(it))
            }
        }

//        listCatsInfo.forEach { it.name?.let { it1 -> Log.d("@@@", it1) } }

    }
}