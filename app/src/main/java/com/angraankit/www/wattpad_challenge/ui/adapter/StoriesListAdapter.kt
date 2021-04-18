package com.angraankit.www.wattpad_challenge.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angraankit.www.wattpad_challenge.R
import com.angraankit.www.wattpad_challenge.model.Story
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.story_list_view.view.*

class StoriesListAdapter : RecyclerView.Adapter<StoriesListAdapter.StoriesViewHolder>() {

    private var storyList : List<Story> = emptyList()

    fun refreshList ( list: List<Story>) {
        storyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoriesViewHolder (inflater, parent)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val story = storyList [position]
        holder.bind(story)
    }

    class StoriesViewHolder ( inflater : LayoutInflater , parent : ViewGroup)
        : RecyclerView.ViewHolder( inflater.inflate(R.layout.story_list_view, parent, false)) {

        fun bind (story : Story) {

            story.title?.let {
                itemView.title_textview.text = it
            } ?: run {
                itemView.title_layout.visibility = View.GONE
            }

            story.user?.name?.let {
                itemView.author_textview.text = it
            } ?: run {
                itemView.author_layout.visibility = View.GONE
            }

            Glide.with(itemView)
                .load(story.cover)
                .placeholder(ColorDrawable(Color.BLUE))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .into(itemView.story_imageview)

        }
    }
}