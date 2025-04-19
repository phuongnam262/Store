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
import com.example.mygrocerystore.models.PopularCategory

data class PopularAdapters(
    private var context: Context,
    private var popularCategoryList: List<PopularCategory>
) : RecyclerView.Adapter<PopularAdapters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.popular_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = popularCategoryList[position]
        Glide.with(context).load(model.img_url).into(holder.pop_img)
        holder.pop_name.text = model.name
        holder.pop_des.text = model.description
        holder.pop_rating.text = model.rating
        holder.pop_discount.text = model.discount
    }

    override fun getItemCount(): Int = popularCategoryList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pop_img: ImageView= itemView.findViewById(R.id.pop_img)
        val pop_name: TextView = itemView.findViewById(R.id.pop_name)
        val pop_des: TextView = itemView.findViewById(R.id.pop_des)
        val pop_rating: TextView = itemView.findViewById(R.id.pop_rating)
        val pop_discount: TextView = itemView.findViewById(R.id.pop_discount)
    }
}
