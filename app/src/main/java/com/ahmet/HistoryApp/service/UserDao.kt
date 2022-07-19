package com.ahmet.HistoryApp.service

import android.icu.text.StringSearch
import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmet.HistoryApp.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    //Bilim
    @Query("SELECT * FROM user2")
    suspend fun getAllBilim(): List<Bili>

    @Query("SELECT * FROM  user2 WHERE id3= :id")
    suspend fun getBilimID(id: Int): Bili

    @Insert
    suspend fun insertBilim(bilim: Array<Bili>): List<Long>

    @Query("DELETE FROM user2")
    suspend fun deleteBilim()

    @Query("SELECT * FROM user2 WHERE name LIKE :search")
    fun searchDatabaseBilim(search: String):Flow<List<Bili>>

    //Filozof
    @Query("SELECT * FROM filo")
    suspend fun getAllFilozof(): List<Filo>

    @Query("SELECT * FROM  filo WHERE id4= :id")
    suspend fun getFilozofID(id: Int): Filo

    @Insert
    suspend fun insertFilozof(filozof: Array<Filo>): List<Long>

    @Query("DELETE FROM filo")
    suspend fun deleteFilozof()

    @Query("SELECT * FROM filo WHERE name LIKE :search")
    fun searchDatabaseFilozof(search: String):Flow<List<Filo>>

    //Lider
    @Query("SELECT * FROM lieder")
    suspend fun getAllLider(): List<Lieder>

    @Query("SELECT * FROM  lieder WHERE id5= :id")
    suspend fun getLiderID(id: Int): Lieder

    @Insert
    suspend fun insertLider(lider: Array<Lieder>): List<Long>

    @Query("DELETE FROM lieder")
    suspend fun deleteLider()

    @Query("SELECT * FROM lieder WHERE name LIKE :search")
    fun searchDatabase(search: String):Flow<List<Lieder>>

    //Savas
    @Query("SELECT * FROM savvas")
    suspend fun getAllSavas(): List<Savvas>

    @Query("SELECT * FROM  savvas WHERE id7= :id")
    suspend fun getSavasID(id:Int) : Savvas

    @Insert
    suspend fun insertSavas(bilim: Array<Savvas>):List<Long>

    @Query("DELETE FROM savvas")
    suspend fun deleteSavas()

    @Query("SELECT * FROM savvas WHERE name LIKE :search")
    fun searchDatabasesavas(search: String):Flow<List<Savvas>>


    //Not
    @Query(value = "SELECT * FROM users ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


}