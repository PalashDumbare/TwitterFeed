package com.w.twitterfeed

import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class ApplicationStartup : Application() {


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);

        val config : TwitterConfig = TwitterConfig.Builder(this)
           .logger(DefaultLogger(Log.DEBUG))
           .twitterAuthConfig(TwitterAuthConfig(BuildConfig.API_KEY,BuildConfig.API_SECRET))
           .debug(true).build()
        Twitter.initialize(config)
    }
}