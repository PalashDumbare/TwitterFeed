package com.w.twitterfeed.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.twitter.sdk.android.core.*
import com.w.twitterfeed.R
import com.w.twitterfeed.ui.base.BaseActivity
import com.w.twitterfeed.ui.feeds.TwitterFeedsActivity
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : BaseActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (getTwitterSession() != null) {
            openTwitterFeeds()
        } else {
            performLogin()
        }
        setUpUI()
    }

    fun setUpUI(){
        supportActionBar?.hide()
    }



    private fun performLogin() {
        btnTwitter.setCallback(object : Callback<TwitterSession?>() {

            override fun success(result:Result<TwitterSession?>?) {
                openTwitterFeeds()
            }

            override fun failure(exception: TwitterException?) {
                exception?.printStackTrace()
                Log.d(TAG, "failure: "+exception?.localizedMessage)
            }
        })
     }

    private fun openTwitterFeeds(){
        startActivity(Intent(this,TwitterFeedsActivity::class.java))
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        btnTwitter.onActivityResult(requestCode,resultCode,data)
    }
}