package com.utn.parcial_castagnola.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.parcial_castagnola.R
import com.utn.parcial_castagnola.entities.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingUsers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers","Pre-populating database")
            fillWithStartingUsersFromJson(context)
        }
    }

    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabaseLOGIN.getInstance(context)?.userDao()

        try {
            val users = loadJSONArray(context, R.raw.usuarios)
            for (i in 0 until users.length()) {
                val item = users.getJSONObject(i)
                val register = Register(
                    user = item.getString("user"),
                    password = item.getString("password"),
                    name = item.getString("name"),
                    lastname = item.getString("lastname"),
                    email = item.getString("email")

                )

                dao?.insertUser(register)
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
