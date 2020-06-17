package com.greedygameimageloader.helper

import com.greedygameimageloader.network.RestClient
import com.greedygameimageloader.network.SOService

object CommonUtils {
    private const val BASE_URL = "https://www.reddit.com/"
    fun getSOService(): SOService {
        return RestClient.getClient(BASE_URL)!!.create(SOService::class.java)
    }

}