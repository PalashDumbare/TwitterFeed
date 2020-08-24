package com.w.twitterfeed.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.w.twitterfeed.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

object RetrofitInstance {

    private const val BASE_URL = "https://api.twitter.com/"

    fun shared(ctx:Context,token : String , secret : String): Retrofit {


          val consumer = OkHttpOAuthConsumer(BuildConfig.API_KEY, BuildConfig.API_SECRET)
        consumer.setTokenWithSecret(token, secret)

        val client: OkHttpClient = OkHttpClient().newBuilder()
             .addInterceptor(SigningInterceptor(consumer))
            .build()

        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()


    }




}