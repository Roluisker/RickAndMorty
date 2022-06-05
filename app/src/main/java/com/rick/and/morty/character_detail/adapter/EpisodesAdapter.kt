package com.rick.and.morty.character_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rick.and.morty.databinding.EpisodeListItemBinding
import com.rick.and.morty.domain.model.episode.Episode

class EpisodesAdapter :
    RecyclerView.Adapter<EpisodeViewHolder>() {

    private var episodes: ArrayList<Episode> = ArrayList()

    fun addEpisodes(episodes: List<Episode>) {
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EpisodeListItemBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.binding.episode = item
        }
    }

    private fun getItem(index: Int): Episode? {
        return when {
            episodes.size >= index -> episodes[index]
            else -> null
        }
    }

    override fun getItemCount(): Int = episodes.size

}