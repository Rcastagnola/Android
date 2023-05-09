package com.utn.parcial_castagnola


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.navigation.fragment.NavHostFragment
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.UserDao

class LoginActivity : AppCompatActivity() {

    object MySingleton {
        var userSelect: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }

    override fun onBackPressed() {

    }
}