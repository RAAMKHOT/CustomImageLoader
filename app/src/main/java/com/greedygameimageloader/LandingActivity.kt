package com.greedygameimageloader

import Children
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.greedygameimageloader.adapters.ImageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class LandingActivity : AppCompatActivity(), LandingView {
    private var landingPresenter: LandingPresenter? = null
    private lateinit var imageAdapter: ImageAdapter
    private var imagesArray: ArrayList<Children> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun init() {
        landingPresenter = LandingPresenter(this)
        imageAdapter = ImageAdapter(ArrayList<Children>(), this, this)
        val mLayoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        imagesRecyclerView.layoutManager = mLayoutManager
        imagesRecyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        imagesRecyclerView.adapter = imageAdapter

        landingPresenter!!.getRedditImages()
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(position: Int) {
        val intent = Intent(this, FullSceenImageActivity::class.java)
        intent.putExtra("ImageUrl", imagesArray[position].data.url)
        startActivity(intent)
    }

    override fun onSuccess(images: ArrayList<Children>) {
        imagesArray.clear()
        imagesArray.addAll(images)
        imageAdapter.setList(images)
    }
}
