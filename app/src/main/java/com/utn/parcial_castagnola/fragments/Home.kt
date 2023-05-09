package com.utn.parcial_castagnola.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.adapter.PlayerAdapter
import com.utn.parcial_castagnola.database.AppDatabase
import com.utn.parcial_castagnola.database.AppDatabaseLOGIN
import com.utn.parcial_castagnola.database.PlayerDao
import com.utn.parcial_castagnola.database.UserDao


class Home : Fragment() {

    private lateinit var v: View

    private var db: AppDatabase? = null
    private var playerDao: PlayerDao? = null


    private lateinit var adapter: PlayerAdapter
    private lateinit var recyclerView: RecyclerView

    lateinit var btnadd: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_home, container, false)

        return v
    }


    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        playerDao = db?.playerDao()

        btnadd = v.findViewById(R.id.btnadd)

        // Dummy call to pre-populate db
        playerDao?.fetchAllUsers()

        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)!!

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = playerDao?.let { PlayerAdapter(it.fetchAllUsers()) }!!


        recyclerView.adapter = adapter

        btnadd.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_add)
        }


    }

}
