package com.example.mygrocerystore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygrocerystore.R
import com.example.mygrocerystore.models.ExploreCategory

data class ExploreAdapter(
    private val context: Context,
    private val categorylist: List<ExploreCategory>
) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.explore_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = categorylist[position]
        Glide.with(context).load(model.img_url).into(holder.explore_img)
        holder.explore_name.text = model.name
    }

    override fun getItemCount(): Int = categorylist.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val explore_img: ImageView = itemView.findViewById(R.id.explore_img)
        val explore_name: TextView = itemView.findViewById(R.id.explore_name)
    }
}