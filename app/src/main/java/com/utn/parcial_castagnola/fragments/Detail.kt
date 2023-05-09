package com.utn.parcial_castagnola.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabase
import com.utn.parcial_castagnola.database.PlayerDao
import com.utn.parcial_castagnola.entities.Player

class Detail : Fragment() {

    private lateinit var v: View
    lateinit var actual : Player

    lateinit var itemName: TextView
    lateinit var itemLastname: TextView
    lateinit var itemHeight: TextView
    lateinit var itemDate: TextView
    lateinit var itemNationality: TextView
    lateinit var itemContract: TextView
    lateinit var itemPrice: TextView
    lateinit var itemPhoto: ImageView

    lateinit var btnback: Button
    lateinit var btnedit: Button
    lateinit var btndelete: Button

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_detail, container, false)



        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        val position = requireArguments().getInt("pos")

        actual = playerDao!!.fetchUserById(position)

        itemName = v.findViewById(R.id.detail_name)
        itemLastname = v.findViewById(R.id.detail_lastname)
        itemHeight = v.findViewById(R.id.detail_height)
        itemDate = v.findViewById(R.id.detail_date)
        itemNationality = v.findViewById(R.id.detail_nationality)
        itemContract = v.findViewById(R.id.detail_contract)
        itemPrice = v.findViewById(R.id.detail_price)
        itemPhoto = v.findViewById(R.id.detail_photo)

        btnback = v.findViewById(R.id.btnback)
        btnedit = v.findViewById(R.id.btnedit)
        btndelete = v.findViewById(R.id.btndelete)

        itemName.text = actual.name
        itemLastname.text = actual.lastname
        itemHeight.text = String.format("Altura: %s", actual.height)
        itemDate.text = String.format("Fecha de nacimiento: %s", actual.date)
        itemNationality.text = String.format("Nacionalidad: %s", actual.nationality)
        itemContract.text = String.format("Vencimiento de contrato: %s", actual.contract)
        itemPrice.text = String.format("Valor Actual: %s", actual.price)
        Glide.with(itemPhoto.context).load(actual.photo).into(itemPhoto)

        btnback.setOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = Bundle().apply {
            putInt("edit", position)
        }

        btnedit.setOnClickListener {
            findNavController().navigate(R.id.action_detail_to_edit, bundle)
        }

        btndelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Esta seguro que quiere eliminar este jugador?")
                .setTitle("Borrar")
                .setPositiveButton("Si") { dialog, which ->
                    playerDao!!.deletePlayer(position)
                    findNavController().popBackStack()
                }
                .setNegativeButton("No") { dialog, which ->
                    // do nothing
                }
            val dialog = builder.create()
            dialog.show()
        }

    }

}
