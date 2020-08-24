package com.w.twitterfeed.ui.feeds


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.w.twitterfeed.data.Prefference
import com.w.twitterfeed.data.repository.TwitterFeedRepository
import com.w.twitterfeed.supporting.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import twitter4j.JSONArray
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.conf.ConfigurationBuilder


class TwitterFeedViewModel(private val repository: TwitterFeedRepository) : ViewModel() {

    /****
     * if internet not connected it will fetch data from shared prefference
     * if data in shared pref is empty and the exception in for connectivity it
     * will throw "Please check network connection" error
     * **/
    fun getFeeds(count : Int) = liveData(Dispatchers.IO){

        emit(Resource.loading(data = null))
        try {
             emit(Resource.success(data = repository.getFeeds(count)))
         }catch (exception : Exception){
            if (exception is java.net.UnknownHostException) {
                val data = repository.getOfflineFeeds()
                if (data.isEmpty()){
                    emit(Resource.error(data = null,message = "Please check network connection"))
                }else{
                    emit(Resource.success(data = data))
                }
            }else {
                emit(Resource.error(data = null,message = exception.localizedMessage?: "Something went wrong"))
            }
        }
    }

    fun clearAllData(){
        repository.clearAllData()
     }

}