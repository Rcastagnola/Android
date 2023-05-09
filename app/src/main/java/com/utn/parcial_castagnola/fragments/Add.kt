package com.utn.parcial_castagnola.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabase
import com.utn.parcial_castagnola.database.PlayerDao
import com.utn.parcial_castagnola.entities.Player


class Add : Fragment() {

    private lateinit var v: View

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
        v = inflater.inflate(R.layout.fragment_add, container, false)


        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        itemName = v.findViewById(R.id.add_name)
        itemLastname = v.findViewById(R.id.add_lastname)
        itemPosition = v.findViewById(R.id.add_position)
        itemAge = v.findViewById(R.id.add_age)
        itemNumber = v.findViewById(R.id.add_number)
        itemHeight = v.findViewById(R.id.add_height)
        itemDate = v.findViewById(R.id.add_date)
        itemNationality = v.findViewById(R.id.add_nationality)
        itemContract = v.findViewById(R.id.add_contract)
        itemPrice = v.findViewById(R.id.add_price)
        itemURL = v.findViewById(R.id.add_photo)

        btncancel = v.findViewById(R.id.addcancel)
        btnconfirm = v.findViewById(R.id.addconfirm)

        btncancel.setOnClickListener {
            findNavController().popBackStack()
        }


        btnconfirm.setOnClickListener {
            if(itemName.text.isNullOrBlank()||
                itemLastname.text.isNullOrBlank()||
                itemPosition.text.isNullOrBlank()||
                itemAge.text.isNullOrBlank()||
                itemNumber.text.isNullOrBlank()||
                itemHeight.text.isNullOrBlank()||
                itemDate.text.isNullOrBlank()||
                itemNationality.text.isNullOrBlank()||
                itemContract.text.isNullOrBlank()||
                itemPrice.text.isNullOrBlank()||
                itemURL.text.isNullOrBlank())
                {
                    Toast.makeText(context,"Complete los campos",Toast.LENGTH_SHORT).show()
                }
            else{
                val agregar = Player(
                    name = itemName.text.toString(),
                    lastname = itemLastname.text.toString(),
                    number = itemNumber.text.toString(),
                    photo = itemURL.text.toString(),
                    position = itemPosition.text.toString(),
                    contract = itemContract.text.toString(),
                    price = itemPrice.text.toString(),
                    height = itemHeight.text.toString(),
                    date = itemDate.text.toString(),
                    age = itemAge.text.toString(),
                    nationality = itemNationality.text.toString()
                    )

                playerDao?.insertUser(agregar)

            findNavController().popBackStack()
            }
        }

    }

}
