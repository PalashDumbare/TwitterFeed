package com.w.twitterfeed.data

import Feeds
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder



class Prefference(private val sharedPreferences: SharedPreferences) {

    fun cacheResponse(response : ArrayList<Feeds>){
        sharedPreferences.edit().putString("response",Gson().toJson(response)).apply()
    }

    fun getCachedResponse() : ArrayList<Feeds>{
        val resp = sharedPreferences.getString("response","")
        val gson = GsonBuilder().create()
        val array = gson.fromJson(resp , Array<Feeds>::class.java).toList()
        return ArrayList(array)
    }

    fun clearAllData(){
        sharedPreferences.edit().clear()
    }
}