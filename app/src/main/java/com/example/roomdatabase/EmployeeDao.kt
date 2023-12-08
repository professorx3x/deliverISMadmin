package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employEntity: EmployEntity)
    @Update
    suspend fun update(employEntity: EmployEntity)
    @Update
    suspend fun delete(employEntity: EmployEntity)
    @Query("SELECT * FROM `employee-detail`")
    fun fetchAllEmployees():Flow<List<EmployEntity>>
    @Query("SELECT * FROM `employee-detail` where id=:id")
    fun fetchEmployeeById(id:Int):Flow<EmployEntity>

}