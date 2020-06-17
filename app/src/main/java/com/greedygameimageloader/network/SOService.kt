package com.greedygameimageloader.network

import RedditImagesBaseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import io.reactivex.Observable

interface SOService {
    @Headers("Content-Type: application/json")
    @GET("r/images/hot.json")
    fun getImages(): Observable<RedditImagesBaseModel>
}