package com.example.a2_conexionapirest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.a2_conexionapirest.databinding.ItemseriesBinding
import com.example.a2_conexionapirest.model.Series
import com.squareup.picasso.Picasso

class SerieViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemseriesBinding.bind(view)

    fun bind(serie: Series){
        Picasso.get().load(serie.image).into(binding.ivSerie)
        binding.tvTitle.setText("Titulo: "+serie.title)
        binding.tvRating.setText("Rating : "+ serie.rating.toString())
        binding.tvDates.setText("Fechas :"+ serie.dates.toString())
        binding.tvChannel.setText("Canal :" + serie.channel)
    }
}