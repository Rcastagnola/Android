package com.utn.parcial_castagnola.fragments

import android.app.AlertDialog
import android.content.Intent
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
import com.utn.parcial_castagnola.LoginActivity
import com.utn.parcial_castagnola.MainActivity
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.UserDao
import com.utn.parcial_castagnola.entities.Register

class User : Fragment() {

    private lateinit var v: View
    lateinit var actual : Register

    lateinit var itemName: TextView
    lateinit var itemLastname: TextView
    lateinit var itemUser: TextView
    lateinit var itemEmail: TextView


    lateinit var btnchange: Button
    lateinit var btnedit: Button
    lateinit var btnsesion: Button

    private var dbLog: AppDatabaseLOGIN? = null
    private var userDao: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_user, container, false)



        return v
    }

    override fun onStart() {
        super.onStart()

        dbLog = AppDatabaseLOGIN.getInstance(v.context)
        userDao = dbLog?.userDao()

        val position = LoginActivity.MySingleton.userSelect

        actual = userDao!!.fetchUserById(position)

        itemName = v.findViewById(R.id.user_name)
        itemLastname = v.findViewById(R.id.user_lastname)
        itemUser = v.findViewById(R.id.user_username)
        itemEmail = v.findViewById(R.id.user_email)

        btnchange = v.findViewById(R.id.user_change)
        btnedit = v.findViewById(R.id.user_edit)
        btnsesion = v.findViewById(R.id.user_end)

        itemName.text = actual.name
        itemLastname.text = actual.lastname
        itemUser.text = String.format("Nombre de usuario: %s", actual.user)
        itemEmail.text = String.format("Email: %s", actual.email)

        val bundle = Bundle().apply {
            putInt("edituser", position)
        }

        btnchange.setOnClickListener {
            findNavController().navigate(R.id.action_user_to_changePass, bundle)
        }


        btnedit.setOnClickListener {
            findNavController().navigate(R.id.action_user_to_editUser, bundle)
        }

        btnsesion.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Quiere cerrar sesion?")
                .setTitle("Log out")
                .setPositiveButton("Si") { dialog, which ->
                    val intent = Intent(v.context, LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .setNegativeButton("No") { dialog, which ->
                    // do nothing
                }
            val dialog = builder.create()
            dialog.show()

        }

    }

}