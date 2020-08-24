package com.w.twitterfeed.data.network

import Feeds
import com.google.gson.JsonElement
import com.twitter.sdk.android.core.TwitterAuthToken
import retrofit2.http.*

interface TwitterApiService {

    @GET("1.1/statuses/home_timeline.json")
    suspend fun getFeeds(@Header("Content-Type") contentType: String = "application/json",
                              @Header("Authorization")auth : String) : JsonElement

    @Headers("content-type: application/x-www-form-urlencoded")
    @POST("oauth2/token?grant_type=client_credentials")
     suspend fun getToken(@Header("Authorization")auth: String
    ) : JsonElement


    @GET("1.1/statuses/home_timeline.json?")
    suspend fun getFeedList(@Query("count")count  : Int) : ArrayList<Feeds>

}