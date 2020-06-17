package com.greedygameimageloader.adapters

import Children
import RedditImagesBaseModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.greedygameimageloader.LandingView
import com.greedygameimageloader.R
import com.imageloader.ImageLoader

class ImageAdapter(
    private val items: ArrayList<Children>,
    private val context: Context,
    private val view: LandingView
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_images_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = items[position].data.url
        if (imageUrl != null) {
            ImageLoader.with(context).load(holder.imageView, imageUrl)
        }
        holder.itemView.setOnClickListener {
            view.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(listOfImages: ArrayList<Children>) {
        items.clear()
        items.addAll(listOfImages)
        notifyDataSetChanged()
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView = view.findViewById(R.id.item_imageView) as ImageView
}