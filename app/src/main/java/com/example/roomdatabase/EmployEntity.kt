package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("employee-detail")
data class EmployEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo("employee-name")
    val name:String=" ",
    @ColumnInfo("email-id")
    val email:String=" "
)
