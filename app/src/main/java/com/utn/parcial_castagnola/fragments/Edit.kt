package com.utn.parcial_castagnola.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabase
import com.utn.parcial_castagnola.database.PlayerDao
import com.utn.parcial_castagnola.entities.Player


class Edit : Fragment() {

    private lateinit var v: View
    lateinit var actual : Player

    lateinit var itemName: TextView
    lateinit var itemLastname: TextView
    lateinit var itemPosition: TextView
    lateinit var itemNumber: TextView
    lateinit var itemHeight: TextView
    lateinit var itemAge: TextView
    lateinit var itemDate: TextView
    lateinit var itemNationality: TextView
    lateinit var itemContract: TextView
    lateinit var itemPrice: TextView
    lateinit var itemURL: TextView

    lateinit var btncancel: Button
    lateinit var btnconfirm: Button

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_edit, container, false)


        return v
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        val position = requireArguments().getInt("edit")

        actual = playerDao!!.fetchUserById(position)

        itemName = v.findViewById(R.id.edit_name)
        itemLastname = v.findViewById(R.id.edit_lastname)
        itemPosition = v.findViewById(R.id.edit_position)
        itemAge = v.findViewById(R.id.edit_age)
        itemNumber = v.findViewById(R.id.edit_number)
        itemHeight = v.findViewById(R.id.edit_height)
        itemDate = v.findViewById(R.id.edit_date)
        itemNationality = v.findViewById(R.id.edit_nationality)
        itemContract = v.findViewById(R.id.edit_contract)
        itemPrice = v.findViewById(R.id.edit_price)
        itemURL = v.findViewById(R.id.edit_photo)

        btncancel = v.findViewById(R.id.btncancel)
        btnconfirm = v.findViewById(R.id.btnconfirm)

        btncancel.setOnClickListener {
            findNavController().popBackStack()
        }

        btnconfirm.setOnClickListener {
            if(itemName.text.isNullOrBlank()){
            itemName.text = actual.name
            }
            if(itemLastname.text.isNullOrBlank()){
                itemLastname.text = actual.lastname
            }
            if(itemAge.text.isNullOrBlank()){
                itemAge.text = actual.age
            }
            if(itemPosition.text.isNullOrBlank()){
                itemPosition.text = actual.position
            }
            if(itemNumber.text.isNullOrBlank()){
                itemNumber.text = actual.number
            }
            if(itemHeight.text.isNullOrBlank()){
                itemHeight.text = actual.height
            }
            if(itemDate.text.isNullOrBlank()){
                itemDate.text = actual.date
            }
            if(itemNationality.text.isNullOrBlank()){
                itemNationality.text = actual.nationality
            }
            if(itemContract.text.isNullOrBlank()){
                itemContract.text = actual.contract
            }
            if(itemPrice.text.isNullOrBlank()){
                itemPrice.text = actual.price
            }
            if(itemURL.text.isNullOrBlank()){
                itemURL.text = actual.photo
            }

            playerDao!!.updatePlayer(
                    itemName.text.toString(),
                    itemLastname.text.toString(),
                    itemNumber.text.toString(),
                    itemURL.text.toString(),
                    itemPosition.text.toString(),
                    itemContract.text.toString(),
                    itemPrice.text.toString(),
                    itemHeight.text.toString(),
                    itemDate.text.toString(),
                    itemAge.text.toString(),
                    itemNationality.text.toString(),
                    position)

            findNavController().popBackStack()
        }

    }

}

