package com.ayodkay.alpha.helpiechallenge.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayodkay.alpha.helpiechallenge.App.Companion.context
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.models.Posts

class PostsAdapter internal constructor(private val postsList: ArrayList<Posts>):
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){

    inner class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val postid : TextView = itemView.findViewById(R.id.postId)
        val postTitle : TextView = itemView.findViewById(R.id.postTitle)
        val postBody : TextView = itemView.findViewById(R.id.postBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.
            from(context).inflate(R.layout.post_card,parent,false)

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postPosition = postsList[position]
        with(holder){
            with(postPosition){
                postid.text = "Post id: $posts_id"
                postTitle.text = "Post Title: $posts_title"
                postBody.text = "Post Body: $posts_body"

            }
        }
    }
}