package com.ayodkay.alpha.helpiechallenge.ui.images

import com.ayodkay.alpha.helpiechallenge.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_enlarged.*
import java.lang.Exception

class ImageEnlarged : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_enlarged)

        val url = intent.extras?.get("url") as String
        Picasso.get().load(url).into(image_enlarged,object:Callback{
            override fun onSuccess() {
                image_enlarged_progress.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                Toast.makeText(this@ImageEnlarged,e!!.message.toString(),
                    Toast.LENGTH_LONG).show()

            }

        })

    }
}
