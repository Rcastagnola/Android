package com.utn.parcial_castagnola.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.entities.Player


class PlayerAdapter(private var playerList : MutableList<Player>): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemPhoto: ImageView
        lateinit var itemName: TextView
        lateinit var itemLastname: TextView
        lateinit var itemAge: TextView
        lateinit var itemPosition: TextView
        lateinit var itemNumber: TextView

        init {
            itemPhoto = itemView.findViewById(R.id.card_photo)
            itemName = itemView.findViewById(R.id.card_name)
            itemLastname = itemView.findViewById(R.id.card_lastname)
            itemAge = itemView.findViewById(R.id.card_age)
            itemPosition = itemView.findViewById(R.id.card_position)
            itemNumber = itemView.findViewById(R.id.card_number)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val actualPlayer = playerList[position]
        viewHolder.itemName.text = actualPlayer.name
        viewHolder.itemLastname.text = actualPlayer.lastname
        viewHolder.itemAge.text = actualPlayer.age
        viewHolder.itemPosition.text = actualPlayer.position
        viewHolder.itemNumber.text = actualPlayer.number
        Glide.with(viewHolder.itemPhoto.context).load(actualPlayer.photo).into(viewHolder.itemPhoto)

        val bundle = Bundle().apply {
            putInt("pos", actualPlayer.id)
        }

        viewHolder.itemPhoto.setOnClickListener { view -> view.findNavController().navigate(R.id.action_home_to_detail, bundle) }

    }
}
