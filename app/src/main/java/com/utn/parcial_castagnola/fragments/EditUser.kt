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
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.UserDao
import com.utn.parcial_castagnola.entities.Register


class EditUser : Fragment() {

    private lateinit var v: View
    lateinit var actual : Register

    lateinit var itemName: TextView
    lateinit var itemLastname: TextView
    lateinit var itemUsername: TextView
    lateinit var itemEmail: TextView


    lateinit var btncancel: Button
    lateinit var btnconfirm: Button

    private var dbLog: AppDatabaseLOGIN? = null
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_edit_user, container, false)


        return v
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()

        dbLog = AppDatabaseLOGIN.getInstance(v.context)
        userDao = dbLog?.userDao()

        val position = requireArguments().getInt("edituser")

        actual = userDao!!.fetchUserById(position)

        itemName = v.findViewById(R.id.edit_user_name)
        itemLastname = v.findViewById(R.id.edit_user_lastname)
        itemUsername = v.findViewById(R.id.edit_user_username)
        itemEmail = v.findViewById(R.id.edit_user_email)


        btncancel = v.findViewById(R.id.btn_edit_user_cancel)
        btnconfirm = v.findViewById(R.id.btn_confirm_edit_user)

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
            if(itemUsername.text.isNullOrBlank()){
                itemUsername.text = actual.user
            }
            if(itemEmail.text.isNullOrBlank()){
                itemEmail.text = actual.email
            }

            userDao!!.updateUsuario(
                itemUsername.text.toString(),
                itemName.text.toString(),
                itemLastname.text.toString(),
                itemEmail.text.toString(),
                position)

            findNavController().popBackStack()
        }

    }

}