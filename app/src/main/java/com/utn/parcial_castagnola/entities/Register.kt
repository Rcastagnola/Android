package com.utn.parcial_castagnola.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "usuarios")
class Register(
    user: String,
    password: String,
    name: String,
    lastname: String,
    email: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "user")
    lateinit var user: String

    @ColumnInfo(name = "password")
    lateinit var password: String

    @ColumnInfo(name = "name_user")
    lateinit var name: String

    @ColumnInfo(name = "lastname_user")
    lateinit var lastname: String

    @ColumnInfo(name = "email")
    lateinit var email: String


    init {
        this.user = user
        this.password  = password
        this.name = name
        this.lastname = lastname
        this.email = email

    }
}