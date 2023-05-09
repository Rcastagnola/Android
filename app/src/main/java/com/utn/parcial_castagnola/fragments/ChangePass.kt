package com.utn.parcial_castagnola.fragments

import android.annotation.SuppressLint
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
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.UserDao
import com.utn.parcial_castagnola.entities.Register


class ChangePass : Fragment() {

    private lateinit var v: View
    lateinit var actual : Register

    lateinit var itemOldPass: TextView
    lateinit var itemNewPass: TextView
    lateinit var itemReNewPass: TextView


    lateinit var btncancel: Button
    lateinit var btnconfirm: Button

    private var dbLog: AppDatabaseLOGIN? = null
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_change_pass, container, false)


        return v
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()

        dbLog = AppDatabaseLOGIN.getInstance(v.context)
        userDao = dbLog?.userDao()

        val position = requireArguments().getInt("edituser")

        actual = userDao!!.fetchUserById(position)

        itemOldPass = v.findViewById(R.id.change_old_pass)
        itemNewPass = v.findViewById(R.id.change_new_pass)
        itemReNewPass = v.findViewById(R.id.change_new_repass)


        btncancel = v.findViewById(R.id.btn_change_cancel)
        btnconfirm = v.findViewById(R.id.btn_change_confirm)

        btncancel.setOnClickListener {
            findNavController().popBackStack()
        }

        btnconfirm.setOnClickListener {
            if(itemOldPass.text.isNullOrBlank()||
                itemNewPass.text.isNullOrBlank()||
                itemReNewPass.text.isNullOrBlank())
            {
                Toast.makeText(context,"Complete los campos", Toast.LENGTH_SHORT).show()
            }
            else{
                if(itemOldPass.text.toString() != actual.password ){
                    Toast.makeText(context,"Contraseña Actual Incorrecta", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(itemNewPass.text.toString() != itemReNewPass.text.toString()) {
                        Toast.makeText(context,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        userDao!!.changePass(itemNewPass.text.toString(), position)

                        findNavController().popBackStack()
                    }
                }
            }

        }

    }

}