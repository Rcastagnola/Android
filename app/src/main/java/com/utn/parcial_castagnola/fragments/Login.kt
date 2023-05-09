package com.utn.parcial_castagnola.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.utn.parcial_castagnola.LoginActivity
import com.utn.parcial_castagnola.MainActivity
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.UserDao


class Login : Fragment() {

    private lateinit var v: View

    private lateinit var capa1: RelativeLayout
    private lateinit var capa2: RelativeLayout
    private lateinit var btnlogin: Button
    private lateinit var btnregister: Button

    lateinit var usernamelogin: EditText
    lateinit var passwordlogin: EditText

    private var dbLog: AppDatabaseLOGIN? = null
    private var userDao: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        return v
    }

    override fun onStart() {
        super.onStart()

        capa1 = v.findViewById(R.id.capa1)
        capa2 = v.findViewById(R.id.capa2)
        btnlogin = v.findViewById(R.id.loginbtn)
        btnregister = v.findViewById(R.id.registerbtn)
        usernamelogin = v.findViewById(R.id.username)
        passwordlogin = v.findViewById(R.id.password)

        dbLog = AppDatabaseLOGIN.getInstance(v.context)
        userDao = dbLog?.userDao()

        userDao?.fetchAllUsers()

        Handler(Looper.getMainLooper()).postDelayed({
            capa1.visibility = RelativeLayout.VISIBLE
            capa2.visibility = RelativeLayout.VISIBLE
        }, 1500)

        btnlogin.setOnClickListener {

            if (usernamelogin.text.isNullOrBlank() || passwordlogin.text.isNullOrBlank()) {
                Toast.makeText(v.context, "Complete los campos", Toast.LENGTH_SHORT).show()
            } else {
                val actual = userDao?.login(usernamelogin.text.toString(),passwordlogin.text.toString())

                if(actual == null){
                    Toast.makeText(v.context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(v.context, MainActivity::class.java)
                    LoginActivity.MySingleton.userSelect = actual.id
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }

        btnregister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_registro)
        }
    }
}