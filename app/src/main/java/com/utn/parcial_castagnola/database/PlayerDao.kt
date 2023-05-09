package com.utn.parcial_castagnola.database

import androidx.room.*
import com.utn.parcial_castagnola.entities.Player

@androidx.room.Dao
interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY id")
    fun fetchAllUsers(): MutableList<Player>

    @Query("SELECT * FROM players WHERE id = :id")
    fun fetchUserById(id: Int): Player

    @Query("DELETE FROM players WHERE id = :id")
    fun deletePlayer(id: Int)

    @Query("UPDATE players SET name=:name, " +
            "lastname=:lastname, " +
            "number=:number, " +
            "photo=:photo," +
            "position=:position," +
            "contract=:contract," +
            "price=:price," +
            "height=:height," +
            "date=:date," +
            "age=:age," +
            "nationality=:nationality  WHERE id = :id")
    fun updatePlayer(name: String,
                     lastname: String,
                     number: String,
                     photo: String,
                     position: String,
                     contract: String,
                     price: String,
                     height: String,
                     date: String,
                     age: String,
                     nationality: String,
                     id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(player: Player)

    @Delete
    fun delete(player: Player)

    @Update
    fun updateUser(player: Player?)
}