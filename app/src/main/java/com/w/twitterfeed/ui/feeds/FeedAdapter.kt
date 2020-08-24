package com.w.twitterfeed.ui.feeds

import Feeds
import android.content.Context
import android.text.Html
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.w.twitterfeed.R
import kotlinx.android.synthetic.main.row_feed.view.*
import java.security.AccessController.getContext

class FeedAdapter  : RecyclerView.Adapter<FeedAdapter.ViewHolder>(){

    private var data : ArrayList<Feeds>? = null

    init {
        data = ArrayList()
    }

    fun swapValue(data : ArrayList<Feeds>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_feed,parent,false),parent.context)
    }

    override fun getItemCount(): Int {
        return data?.count() ?: 0
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        val feed = data?.get(position)

        holder.name.text = feed?.user?.name
        holder.description.text = HtmlCompat.fromHtml(feed?.getPost().toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        Linkify.addLinks(holder.description, Linkify.WEB_URLS);

        holder.profileImage.setImageURI(feed?.user?.profile_image_url_https)
        holder.screenName.text = "@${feed?.user?.screen_name}"
    }

    class ViewHolder(itemView : View,ctx : Context)  : RecyclerView.ViewHolder(itemView){
        val name   = itemView.name
        val description = itemView.description
        val profileImage = itemView.profileImage
        val screenName = itemView.screenName
        init {
            description.setLinkTextColor(ContextCompat.getColor(ctx,android.R.color.holo_blue_dark));
        }
    }

}