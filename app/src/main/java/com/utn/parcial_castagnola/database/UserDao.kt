package com.utn.parcial_castagnola.database

import androidx.room.*
import com.utn.parcial_castagnola.entities.Register

@androidx.room.Dao
interface UserDao {

    @Query("SELECT * FROM usuarios ORDER BY id")
    fun fetchAllUsers(): MutableList<Register>

    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun fetchUserById(id: Int): Register

    @Query("DELETE FROM usuarios WHERE id = :id")
    fun deletePlayer(id: Int)

    @Query("SELECT * FROM usuarios WHERE user=:user and password=:password")
    fun login(user: String, password: String): Register

    @Query("SELECT * FROM usuarios WHERE user=:user")
    fun comprobar(user: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(register: Register)

    @Delete
    fun delete(register: Register)

    @Update
    fun updateUser(register: Register?)

    @Query("UPDATE usuarios SET user=:user, " +
            "name_user=:name, " +
            "lastname_user=:lastname," +
            "email=:email WHERE id = :id")
    fun updateUsuario(user: String,
                     name: String,
                     lastname: String,
                     email: String,
                     id: Int)

    @Query("UPDATE usuarios SET password=:password WHERE id=:id")
    fun changePass(password: String, id: Int)

}