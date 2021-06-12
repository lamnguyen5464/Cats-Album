package com.example.listcats.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.listcats.R
import com.example.listcats.activities.ListCatsActivity
import com.example.listcats.models.CatInfo
import com.squareup.picasso.Picasso

class CatInfoAdapter(context: Context, catInfoData: ArrayList<CatInfo>) :
    BaseAdapter() {
    var context: Context? = null
    var layoutInflater: LayoutInflater? = null
    var catInfoData: ArrayList<CatInfo>? = null

    init {
        this.context = context
        this.layoutInflater = LayoutInflater.from(context)
        this.catInfoData = catInfoData
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        if (convertView != null) {
//            Log.d("@@", convertView.findViewById<TextView>(R.id.txt_name).text.toString())
//            return convertView
//        }

        Log.d("@@",catInfoData?.get(position)?.url +"")

        val itemView = layoutInflater?.inflate(
            R.layout.item_cat_info, null
        )
        itemView?.findViewById<TextView>(R.id.txt_name)?.text =
            "Name: " + catInfoData?.get(position)?.name
        itemView?.findViewById<TextView>(R.id.txt_desc)?.text =
            "Description: " + catInfoData?.get(position)?.description

        Picasso.get().load(catInfoData?.get(position)?.url).into(
            itemView?.findViewById<ImageButton>(R.id.img_cat)
        )

        itemView?.findViewById<ImageButton>(R.id.img_cat)?.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(catInfoData?.get(position)?.wikipedia_url)))
        }


        return itemView
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return catInfoData?.size ?: 0
    }

}
