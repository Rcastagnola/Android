package com.utn.parcial_castagnola.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "players")
class Player(
    name: String,
    lastname: String,
    number: String,
    photo: String,
    position: String,
    contract: String,
    price: String,
    height: String,
    date: String,
    age: String,
    nationality: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    lateinit var name: String

    @ColumnInfo(name = "lastname")
    lateinit var lastname: String

    @ColumnInfo(name = "number")
    lateinit var number: String

    @ColumnInfo(name = "photo")
    lateinit var photo: String

    @ColumnInfo(name = "position")
    lateinit var position: String

    @ColumnInfo(name = "contract")
    lateinit var contract: String

    @ColumnInfo(name = "price")
    lateinit var price: String

    @ColumnInfo(name = "height")
    lateinit var height: String

    @ColumnInfo(name = "date")
    lateinit var date: String

    @ColumnInfo(name = "age")
    lateinit var age: String

    @ColumnInfo(name = "nationality")
    lateinit var nationality: String

    init {
        this.name = name
        this.lastname = lastname
        this.number = number
        this.photo = photo
        this.position = position
        this.contract = contract
        this.price = price
        this.height = height
        this.date = date
        this.age = age
        this.nationality = nationality
    }
}
