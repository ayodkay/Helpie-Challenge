package com.ayodkay.alpha.helpiechallenge.ui.images

import com.ayodkay.alpha.helpiechallenge.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_enlarged.*

class ImageEnlarged : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_enlarged)

        val url = intent.extras?.get("url") as String
        Picasso.get().load(url).into(image_enlarged)

    }
}
