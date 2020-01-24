package com.ayodkay.alpha.helpiechallenge.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.models.Images
import com.ayodkay.alpha.helpiechallenge.ui.images.ImageEnlarged
import com.squareup.picasso.Picasso

class ImagesAdapter internal constructor(private val images:ArrayList<Images>):
    RecyclerView.Adapter<ImagesAdapter.ImagesHolder>() {

    inner class ImagesHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val albumTitle:TextView = itemView.findViewById(R.id.album_title)

        val image:ImageView = itemView.findViewById(R.id.image)

        val imageTitle:TextView = itemView.findViewById(R.id.image_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.images_card, parent, false)

        return ImagesHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        val imagesPosition = images[position]

        Picasso.get().load(imagesPosition.thumbnailUrl).into(holder.image)
        holder.albumTitle.text = "album: ${imagesPosition.albumId}"
        holder.imageTitle.text = imagesPosition.title

        holder.itemView.setOnClickListener {
            Toast.makeText(context,imagesPosition.id.toString(),Toast.LENGTH_LONG).show()

            context.startActivity(Intent(context,ImageEnlarged::class.java).apply {
                putExtra("url",imagesPosition.url)
                flags = FLAG_ACTIVITY_NEW_TASK
            })
        }

    }
}