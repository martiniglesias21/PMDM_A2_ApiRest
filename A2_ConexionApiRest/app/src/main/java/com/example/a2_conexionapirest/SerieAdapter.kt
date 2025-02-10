package com.example.a2_conexionapirest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a2_conexionapirest.model.Series

class SerieAdapter(val series: List<Series>): RecyclerView.Adapter<SerieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return SerieViewHolder(layoutInflater.inflate(R.layout.itemseries, parent, false))
    }

    override fun getItemCount(): Int {
        return series.size
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val item : Series = series[position]
        holder.bind(item)
    }
}