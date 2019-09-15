package com.dearwolves.samplerecords.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dearwolves.core.model.dto.responses.ListResponseDto
import com.dearwolves.core.model.dto.responses.MediaResponseDto
import com.dearwolves.samplerecords.databinding.ItemMediaBinding

class MediaListAdapter
internal constructor(context: Context, private var mData: ListResponseDto<MediaResponseDto>) :
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
        val data = mData.results.get(position)
        holder.bind(data)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.results.size
    }

    fun setData(data: ListResponseDto<MediaResponseDto>) {
        mData = data
        notifyDataSetChanged()

    }

    fun getItem(position: Int): MediaResponseDto {
        return mData.results.get(position)
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        var mediaDto :MediaResponseDto? = null

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        fun bind(value:MediaResponseDto) {
            binding.viewHolder = this
            mediaDto = value
        }

        fun getTitle(): String {
            return if (mediaDto?.trackName != null) (mediaDto?.trackName)!! else ""
        }

        fun getGenre(): String {
            return if (mediaDto?.primaryGenreName != null) (mediaDto?.primaryGenreName)!! else ""
        }

        fun getPrice(): String {
            return if (mediaDto?.trackPrice != null) (mediaDto?.trackPrice.toString()) else ""
        }

        fun getImageUrl(): String {
            return if (mediaDto?.artworkUrl100 != null) (mediaDto?.artworkUrl100)!! else ""
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