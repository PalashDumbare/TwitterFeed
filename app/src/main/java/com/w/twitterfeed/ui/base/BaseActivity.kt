package com.w.twitterfeed.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession

open class BaseActivity : AppCompatActivity() {

    fun getTwitterSession() : TwitterSession? {
        val session = TwitterCore.getInstance().sessionManager.activeSession
        return session
    }

    fun showMessage(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    open fun showLoadingDialog(){

    }

    open fun hideLoadingDialog(){

    }


    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}