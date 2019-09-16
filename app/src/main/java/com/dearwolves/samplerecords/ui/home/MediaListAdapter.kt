package com.dearwolves.samplerecords.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.samplerecords.databinding.ItemMediaBinding

class MediaListAdapter
internal constructor(context: Context, private var mData: List<MediaResponse>) :
    RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMediaBinding.inflate(mInflater, parent, false)
        return ViewHolder(binding)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData.get(position)
        holder.bind(data)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        var media : MediaResponse? = null

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        fun bind(value:MediaResponse) {
            binding.viewHolder = this
            media = value
        }

        fun getTitle(): String {
            return if (media?.trackName != null) (media?.trackName)!! else ""
        }

        fun getGenre(): String {
            return if (media?.primaryGenreName != null) (media?.primaryGenreName)!! else ""
        }

        fun getPrice(): String {
            return if (media?.trackPrice != null) (media?.trackPrice.toString()) else ""
        }

        fun getImageUrl(): String {
            return if (media?.artworkUrl100 != null) (media?.artworkUrl100)!! else ""
        }
    }

    // allows clicks events to be caught
    internal fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}