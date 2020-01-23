package com.ayodkay.alpha.helpiechallenge.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter internal constructor(context: Context):
    RecyclerView.Adapter<ImagesAdapter.ImagesModels>() {

    inner class ImagesModels(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesModels {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ImagesModels, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}