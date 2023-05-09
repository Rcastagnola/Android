package com.utn.parcial_castagnola.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.entities.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingPlayers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingPlayers","Pre-populating database")
            fillWithStartingUsersFromJson(context)
        }
    }

    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.playerDao()

        try {
            val users = loadJSONArray(context, R.raw.players)
            for (i in 0 until users.length()) {
                val item = users.getJSONObject(i)
                val player = Player(
                    name = item.getString("name"),
                    lastname = item.getString("lastname"),
                    number = item.getString("number"),
                    photo = item.getString("photo"),
                    position = item.getString("position"),
                    contract = item.getString("contract"),
                    price = item.getString("price"),
                    height = item.getString("height"),
                    date = item.getString("date"),
                    age = item.getString("age"),
                    nationality = item.getString("nationality")

                )

                dao?.insertUser(player)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}









