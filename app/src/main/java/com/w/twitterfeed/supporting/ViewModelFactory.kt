package com.w.twitterfeed.supporting

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w.twitterfeed.data.network.TwitterApiService
import com.w.twitterfeed.data.repository.TwitterFeedRepository
import com.w.twitterfeed.ui.feeds.TwitterFeedViewModel

class ViewModelFactory(private val service : TwitterApiService,private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TwitterFeedViewModel::class.java)) {
            return TwitterFeedViewModel(TwitterFeedRepository(service,sharedPreferences)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}