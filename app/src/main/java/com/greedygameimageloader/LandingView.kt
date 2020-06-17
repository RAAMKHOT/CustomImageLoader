package com.greedygameimageloader

import Children
import RedditImagesBaseModel


interface LandingView {
    fun init()
    fun onSuccess(images: ArrayList<Children>)
    fun onError(error: String)
    fun onItemClickListener(position: Int)
}