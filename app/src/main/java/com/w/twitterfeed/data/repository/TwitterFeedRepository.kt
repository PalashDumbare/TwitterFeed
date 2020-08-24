package com.w.twitterfeed.data.repository

import Feeds
import android.content.SharedPreferences
import com.twitter.sdk.android.core.TwitterAuthToken
import com.w.twitterfeed.data.Prefference
import com.w.twitterfeed.data.network.TwitterApiService
import org.w3c.dom.Entity

class TwitterFeedRepository (val service: TwitterApiService,val sharedPreferences: SharedPreferences){

    suspend fun getFeeds(count : Int): ArrayList<Feeds> {
        val resp = service.getFeedList(count)
        Prefference(sharedPreferences).cacheResponse(resp)
        return resp
    }

    fun getOfflineFeeds() = Prefference(sharedPreferences).getCachedResponse()

    fun clearAllData() = Prefference(sharedPreferences).clearAllData()

 }