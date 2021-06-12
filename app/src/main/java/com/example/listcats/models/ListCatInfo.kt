package com.example.listcats.models

import android.content.Context
import com.example.listcats.helpers.JsonHandler

class ListCatInfo {
    companion object {
        fun getListCatInfo(context: Context): ArrayList<CatInfo> {
            //load from json
            val stringListCats = JsonHandler.getJsonDataFromAsset(context, "listcats.json");

            val jsonListCats = stringListCats?.let { JsonHandler.getJsonArrayromString(it) }

            val listCatsInfo = ArrayList<CatInfo>()
            for (index: Int in 0..(jsonListCats?.length() ?: 0) - 1) {
                jsonListCats?.getJSONObject(index).let { it ->
                    listCatsInfo.add(CatInfo(it))
                }
            }
            return listCatsInfo
        }
    }
}