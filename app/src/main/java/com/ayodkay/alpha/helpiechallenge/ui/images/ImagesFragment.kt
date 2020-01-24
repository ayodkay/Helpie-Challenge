package com.ayodkay.alpha.helpiechallenge.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.helpiechallenge.R
import com.ayodkay.alpha.helpiechallenge.adapters.ImagesAdapter
import com.ayodkay.alpha.helpiechallenge.models.Images
import kotlinx.android.synthetic.main.fragment_images.*
import org.json.JSONArray

class ImagesFragment : Fragment() {

    private lateinit var imagesViewModel: ImagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_images, container, false)

        imagesViewModel.getImages().observe(viewLifecycleOwner, Observer {

            recycle_images.apply {
                layoutManager = LinearLayoutManager(context)
                hasFixedSize()
                adapter = ImagesAdapter(handleArray(it))

            }

            image_progress.visibility = View.GONE

        })
        return root
    }


    private fun handleArray(jsonArray: JSONArray):ArrayList<Images>{
        val imagesList :ArrayList<Images> = ArrayList()

        for(i in 0 until jsonArray.length()){
            val jsArray = jsonArray.getJSONObject(i)
            val albumId = jsArray.getInt("albumId")
            val id      = jsArray.getInt("id")
            val url     = jsArray.getString("url")
            val title   = jsArray.getString("title")
            val thumbnailUrl = jsArray.getString("thumbnailUrl")

            imagesList.add(Images(albumId,id,url,title,thumbnailUrl))
        }

        return imagesList
    }
}