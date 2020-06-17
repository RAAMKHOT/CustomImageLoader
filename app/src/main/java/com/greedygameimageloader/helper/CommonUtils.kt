package com.telstra.helper

import com.telstra.network.RestClient
import com.greedygameimageloader.network.SOService

object CommonUtils {
    //https://www.reddit.com/r/images/hot.json
    val BASE_URL = "https://www.reddit.com/"
    fun getSOService(): SOService {
        return RestClient.getClient(BASE_URL)!!.create(SOService::class.java)
    }

}