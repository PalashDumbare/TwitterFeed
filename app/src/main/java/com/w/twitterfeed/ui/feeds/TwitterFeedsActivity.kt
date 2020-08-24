package com.w.twitterfeed.ui.feeds

import Feeds
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import com.twitter.sdk.android.core.TwitterCore
import com.w.twitterfeed.R
import com.w.twitterfeed.data.network.RetrofitInstance
import com.w.twitterfeed.data.network.TwitterApiService
import com.w.twitterfeed.supporting.Status
import com.w.twitterfeed.supporting.ViewModelFactory
import com.w.twitterfeed.ui.base.BaseActivity
import com.w.twitterfeed.ui.login.LoginActivity
import kotlinx.android.synthetic.main.twitter_feeds.*


class TwitterFeedsActivity : BaseActivity() {


    private lateinit var viewModel: TwitterFeedViewModel
    private var feedAdapter : FeedAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_feeds)
        setUpUi()
        setUpViewModel()
        setUpObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feed_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout ->{
                TwitterCore.getInstance().sessionManager.clearActiveSession()
                viewModel.clearAllData()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setUpUi(){
        supportActionBar?.title = getString(R.string.Feeds)
        tweetFeeds.layoutManager = LinearLayoutManager(this)
        feedAdapter = FeedAdapter()
        tweetFeeds.adapter = feedAdapter
    }


    fun setUpViewModel(){
        val session = getTwitterSession()

        viewModel  = ViewModelProviders.of(this,
            ViewModelFactory(RetrofitInstance.shared(this,
                session?.authToken?.token!!,
                session?.authToken?.secret!!)
                .create(TwitterApiService::class.java),
                sharedPreferences = getSharedPreferences("feed",Context.MODE_PRIVATE))).get(TwitterFeedViewModel::class.java)
    }

    fun setUpObservers(){
        /**
         * taking only 200 feeds
         * */
         viewModel.getFeeds(200).observe(this, Observer {
            it.let {resource ->
                when(resource.status) {
                    Status.Success ->{
                        hideLoadingDialog()
                        feedAdapter?.swapValue((it.data!!))
                    }
                    Status.Error->{
                        showMessage(it.message.toString())
                        hideLoadingDialog()
                    }
                    Status.Loading->{
                        showLoadingDialog()
                     }
                }
            }
        })
    }

    override fun showLoadingDialog() {
        tweetFeeds.visibility = View.GONE
        shimmer_view_container.visibility = View.VISIBLE
        shimmer_view_container.startShimmerAnimation()
    }

    override fun hideLoadingDialog() {
        tweetFeeds.visibility = View.VISIBLE
        shimmer_view_container.visibility = View.GONE
        shimmer_view_container.stopShimmerAnimation()
    }



}