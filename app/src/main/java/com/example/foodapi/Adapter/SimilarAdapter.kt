package com.example.foodapi.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapi.Model.SimilarItem
import com.example.foodapi.R
import com.example.foodapi.databinding.SimilarItemBinding
import com.squareup.picasso.Picasso


class SimilarAdapter(private var context: Context) :
    RecyclerView.Adapter<SimilarAdapter.SimilarItemViewHolder>() {


    private var similarItems: List<SimilarItem> = listOf(
        SimilarItem(image = R.drawable.similar.toString(), name = "Veg Biryani"),
        SimilarItem(image = R.drawable.paneer.toString(), name = "Paneer Biryani"),
        SimilarItem(image = R.drawable.mutton.toString(), name = "Mutton Biryani")
    )
    class SimilarItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.img)

        fun bind(item: SimilarItem) {

            Picasso.get().load(item.image).into(image) // Directly load the image from URL or resource

            name.text = item.name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.similar_item, parent, false)
        return SimilarItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarItemViewHolder, position: Int) {
        val item = similarItems[position]
        holder.bind(item)

    }

    override fun getItemCount() = similarItems.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<SimilarItem>) {
        similarItems = newItems
        notifyDataSetChanged()
    }
}
