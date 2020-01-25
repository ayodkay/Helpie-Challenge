package com.ayodkay.alpha.helpiechallenge.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.adapters.PostsAdapter
import com.ayodkay.alpha.helpiechallenge.models.Posts
import kotlinx.android.synthetic.main.activity_users_posts.*
import org.json.JSONArray

class UsersPosts : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_posts)

        val userId = intent.extras?.get("userId") as Int

        usersViewModel =
            ViewModelProvider(this).get(UsersViewModel::class.java)


        usersViewModel.getUsersPost(userId).observe(this, Observer {
            post_recycler.apply {
                layoutManager = LinearLayoutManager(this@UsersPosts)
                hasFixedSize()
                adapter = PostsAdapter(handlePost(it))
            }
            post_progress.visibility = View.GONE

        })

    }

    private fun handlePost(jsonArray: JSONArray):ArrayList<Posts>{

        val postsList :ArrayList<Posts> = ArrayList()

        for(i in 0 until jsonArray.length()){

            val jsObj = jsonArray.getJSONObject(i)

            val postId = jsObj.getInt("id")
            val postTitle = jsObj.getString("title")
            val postBody = jsObj.getString("body")

            postsList.add(Posts(postId,postTitle,postBody))
        }

        return postsList
    }
}
