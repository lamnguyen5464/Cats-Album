package com.example.listcats.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listcats.R
import com.example.listcats.viewmodels.ListCatViewModel
import com.squareup.picasso.Picasso


class CatInfoAdapter(
    context: Context,
    listCatViewModel: ListCatViewModel
) :
    RecyclerView.Adapter<CatInfoAdapter.ViewHolder>() {
    var context: Context? = null
    var listInfoViewModel: ListCatViewModel? = null

    init {
        this.context = context
        this.listInfoViewModel = listCatViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_cat_info, parent, false)

        Log.d("@@", "view type " + viewType)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listInfoViewModel?.catInfoRepo?.currentListCatInfo?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = listInfoViewModel?.catInfoRepo?.currentListCatInfo
        val catInfo = currentList?.get(position)

        if (position >= currentList?.size?.minus(1) ?: 0) {
            listInfoViewModel?.appendMoreItems()
        }
        Log.d("@@@", "load " +  catInfo?.name + " from " + holder.txtName.text)

        //setData
        holder.txtName.text = "Name: " + catInfo?.name
        holder.txtDesc.text = "Description: " + catInfo?.description

        Picasso.get().load(catInfo?.url).placeholder(R.drawable.ic_download).into(
            holder.imgCat
        )
        holder.imgCat.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(catInfo?.wikipedia_url)))
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView
        val txtDesc: TextView
        val imgCat: ImageButton

        init {
            txtName = view.findViewById<TextView>(R.id.txt_name)
            txtDesc = view.findViewById<TextView>(R.id.txt_desc)
            imgCat = view.findViewById<ImageButton>(R.id.img_cat)
        }
    }


}
