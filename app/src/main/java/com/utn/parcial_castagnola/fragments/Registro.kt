package com.utn.parcial_castagnola.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabase
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.PlayerDao
import com.utn.parcial_castagnola.database.UserDao
import com.utn.parcial_castagnola.entities.Register


class Registro : Fragment() {

    private lateinit var v: View

    lateinit var itemUsername: TextView
    lateinit var itemPassword: TextView
    lateinit var itemRepassword: TextView
    lateinit var itemName: TextView
    lateinit var itemLastname: TextView
    lateinit var itemEmail: TextView

    lateinit var registercancel: Button
    lateinit var registerconfirm: Button

    private var dbLog: AppDatabaseLOGIN? = null
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_registro, container, false)


        return v
    }

    override fun onStart() {
        super.onStart()

        dbLog = AppDatabaseLOGIN.getInstance(v.context)
        userDao = dbLog?.userDao()

        itemUsername = v.findViewById(R.id.register_username)
        itemPassword = v.findViewById(R.id.register_password)
        itemRepassword = v.findViewById(R.id.register_repassword)
        itemName = v.findViewById(R.id.register_name)
        itemLastname = v.findViewById(R.id.register_lastname)
        itemEmail = v.findViewById(R.id.register_email)

        registercancel = v.findViewById(R.id.registercancel)
        registerconfirm = v.findViewById(R.id.registerconfirm)

        registercancel.setOnClickListener {
            findNavController().popBackStack()
        }


        registerconfirm.setOnClickListener {
            if(itemUsername.text.isNullOrBlank()||
                itemPassword.text.isNullOrBlank()||
                itemRepassword.text.isNullOrBlank()||
                itemName.text.isNullOrBlank()||
                itemLastname.text.isNullOrBlank()||
                itemEmail.text.isNullOrBlank())
            {
                Toast.makeText(context,"Complete los campos",Toast.LENGTH_SHORT).show()
            }
            else {
                val comp = userDao?.comprobar(itemUsername.text.toString())
                if(comp == true) {
                    Toast.makeText(context, "Este Usuario ya existe", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (itemPassword.text.toString() == itemRepassword.text.toString()) {
                        val agregar = Register(
                            user = itemUsername.text.toString(),
                            password = itemPassword.text.toString(),
                            name = itemName.text.toString(),
                            lastname = itemLastname.text.toString(),
                            email = itemEmail.text.toString()
                        )

                        userDao?.insertUser(agregar)
                        Toast.makeText(context, "Registro completado", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()

                    } else {
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

}
