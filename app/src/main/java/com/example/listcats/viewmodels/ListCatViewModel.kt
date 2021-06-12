package com.example.listcats.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listcats.helpers.JsonHelper
import com.example.listcats.models.CatInfoRepo
import kotlin.math.min

class ListCatViewModel : ViewModel() {
    private var context: Context? = null
    var catInfoRepo: CatInfoRepo? = null
    private var currentScrollToIndex: MutableLiveData<Int>? = null

    fun setContext(context: Context) {
        this.context = context
    }

    fun getCurrentScrollToIndex(): MutableLiveData<Int> {
        if (currentScrollToIndex == null) {
            currentScrollToIndex = MutableLiveData(5)
        }
        return currentScrollToIndex as MutableLiveData<Int>
    }

    fun appendMoreItems(){
        currentScrollToIndex?.value = min(currentScrollToIndex?.value!! + 5, catInfoRepo?.jsonArrayCatInfo?.length() ?: 0)
    }

    fun updateRepo(currentEndIndex: Int) {
        if (catInfoRepo == null) {
            //init
            catInfoRepo = CatInfoRepo()
            catInfoRepo!!.jsonArrayCatInfo = JsonHelper.getListCatInfo(context)
            catInfoRepo!!.currentListCatInfo = CatInfoRepo.castJsonToList(
                catInfoRepo!!.jsonArrayCatInfo,
                currentEndIndex,
                catInfoRepo!!.currentListCatInfo
            )
        } else {
            catInfoRepo!!.currentListCatInfo = CatInfoRepo.castJsonToList(
                catInfoRepo!!.jsonArrayCatInfo,
                currentEndIndex,
                catInfoRepo!!.currentListCatInfo
            )
        }
    }

}