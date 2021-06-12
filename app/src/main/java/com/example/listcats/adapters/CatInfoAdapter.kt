package com.example.listcats.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.listcats.R
import com.example.listcats.viewmodels.ListCatViewModel
import com.squareup.picasso.Picasso

class CatInfoAdapter(
    context: Context,
    listCatViewModel: ListCatViewModel
) :
    BaseAdapter() {
    var context: Context? = null
    var layoutInflater: LayoutInflater? = null
    var listInfoViewModel: ListCatViewModel? = null

    init {
        this.context = context
        this.layoutInflater = LayoutInflater.from(context)
        this.listInfoViewModel = listCatViewModel
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        if (convertView != null){
//            Log.d("@@", convertView.findViewById<TextView>(R.id.txt_name).text.toString())
//        }
        val currentList = listInfoViewModel?.catInfoRepo?.currentListCatInfo

        if (position >= currentList?.size?.minus(1) ?: 0) {
            listInfoViewModel?.appendMoreItems()
        }

        val catInfo = currentList?.get(position)

        val itemView = layoutInflater?.inflate(R.layout.item_cat_info, null)
        itemView?.findViewById<TextView>(R.id.txt_name)?.text =
            "Name: " + catInfo?.name
        itemView?.findViewById<TextView>(R.id.txt_desc)?.text =
            "Description: " + catInfo?.description

        Picasso.get().load(catInfo?.url).into(
            itemView?.findViewById<ImageButton>(R.id.img_cat)
        )

        itemView?.findViewById<ImageButton>(R.id.img_cat)?.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(catInfo?.wikipedia_url)))
        }

        return itemView
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listInfoViewModel?.catInfoRepo?.currentListCatInfo?.size ?: 0
    }

}
