package com.nelsonalmendra.rocketlaunches.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nelsonalmendra.rocketlaunches.R
import com.nelsonalmendra.rocketlaunches.data.database.Rocket

class RocketListAdapter internal constructor(context: Context, val clickListener: (Rocket) -> Unit) : RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var rockets = emptyList<Rocket>()
    private var filteredRockets = rockets

    inner class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val country: TextView = itemView.findViewById(R.id.country)
        val engines: TextView = itemView.findViewById(R.id.engines)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return RocketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val current = filteredRockets[position]
        holder.name.text = current.name
        holder.country.text = current.country
        holder.engines.text = current.engines.number.toString()
        holder.itemView.setOnClickListener { clickListener(current) }
    }

    fun setRockets(rockets: List<Rocket>) {
        this.rockets = rockets
        filteredRockets = rockets
        notifyDataSetChanged()
    }

    override fun getItemCount() = filteredRockets.size

    fun filterActive() {
        filteredRockets = rockets.filter { it.active }
        notifyDataSetChanged()
    }

    fun resetFilter() {
        filteredRockets = rockets
        notifyDataSetChanged()
    }
}