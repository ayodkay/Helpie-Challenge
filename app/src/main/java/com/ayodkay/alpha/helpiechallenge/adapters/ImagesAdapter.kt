package com.ayodkay.alpha.helpiechallenge.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.models.Images
import com.ayodkay.alpha.helpiechallenge.ui.images.ImageEnlarged
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ImagesAdapter internal constructor(private val images:ArrayList<Images>):
    RecyclerView.Adapter<ImagesAdapter.ImagesHolder>() {

    inner class ImagesHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val albumTitle:TextView = itemView.findViewById(R.id.album_title)

        val image:ImageView = itemView.findViewById(R.id.image)

        val imageTitle:TextView = itemView.findViewById(R.id.image_title)

        val picassoProgress:ProgressBar = itemView.findViewById(R.id.picassoProgress)
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

        with(holder){
            with(imagesPosition){
                Picasso.get().load(thumbnailUrl).into(image, object :Callback{
                    override fun onSuccess() {
                        picassoProgress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        Toast.makeText(context,e!!.message.toString(),Toast.LENGTH_LONG).show()
                    }

                })

                albumTitle.text = "album: $albumId"
                imageTitle.text = title

                itemView.setOnClickListener {
                    Toast.makeText(context,id.toString(),Toast.LENGTH_LONG).show()

                    context.startActivity(Intent(context,ImageEnlarged::class.java).apply {
                        putExtra("url",url)
                        flags = FLAG_ACTIVITY_NEW_TASK
                    })
                }

            }
        }

    }
}