package com.example.listcats.models

import org.json.JSONArray

class CatInfoRepo {
    var jsonArrayCatInfo: JSONArray? = null
    var currentListCatInfo: ArrayList<CatInfo>? = null

    companion object {
        fun castJsonToList(
            jsonArrayCatInfo: JSONArray?,
            currentScrollToIndex: Int,
            currentListCatInfo: ArrayList<CatInfo>?
        ): ArrayList<CatInfo>? {
            var tempListCatInfo = currentListCatInfo

            if (tempListCatInfo == null) {
                tempListCatInfo = ArrayList<CatInfo>()
            }

            for (index: Int in tempListCatInfo.size..currentScrollToIndex - 1) {
                tempListCatInfo.add(CatInfo(jsonArrayCatInfo?.getJSONObject(index)))
            }

            return tempListCatInfo
        }
    }
}