package com.dearwolves.samplerecords.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dearwolves.core.interfaces.IOnItemSelected
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.samplerecords.databinding.ItemMediaBinding

class MediaListAdapter
internal constructor(context: Context, private var mData: LiveData<List<MediaResponse>>, private val iOnClickListener: IOnItemSelected<MediaResponse>) :
    RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMediaBinding.inflate(mInflater, parent, false)
        return ViewHolder(binding)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData.value?.get(position)
        data?.let { holder.bind(it) }
    }

    // total number of rows
    override fun getItemCount(): Int {
        if(mData.value == null) {
            return 0
        } else {
            return mData.value!!.size
        }
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        var media : MediaResponse? = null

        fun bind(value:MediaResponse) {
            binding.viewHolder = this
            media = value
            binding.root.setOnClickListener {
                iOnClickListener.onSelected(value)
            }
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
}