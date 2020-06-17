package com.greedygameimageloader

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullSceenImageActivity : AppCompatActivity() {

    private var imageUrl: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_full_screen_image)
        if (intent.extras != null && intent.hasExtra("ImageUrl")) {
            imageUrl = intent.extras!!.getString("ImageUrl")
        }

        ImageLoader.with(this).load(imageViewFullScreen, imageUrl!!)

        imageViewBackArrow.setOnClickListener {finish()}
    }

}
