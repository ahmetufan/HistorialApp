package com.ahmet.HistoryApp.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmet.HistoryApp.model.*

@Dao
interface UserDao {
    //Bilim
    @Query("SELECT * FROM user2")
    suspend fun getAllBilim(): List<Bili>

    @Query("SELECT * FROM  user2 WHERE id= :id")
    suspend fun getBilimID(id: Int): Bili

    @Insert
    suspend fun insertBilim(bilim: Array<Bili>): List<Long>

    @Query("DELETE FROM user2")
    suspend fun deleteBilim()

    //Filozof
    @Query("SELECT * FROM filo")
    suspend fun getAllFilozof(): List<Filo>

    @Query("SELECT * FROM  filo WHERE id= :id")
    suspend fun getFilozofID(id: Int): Filo

    @Insert
    suspend fun insertFilozof(filozof: Array<Filo>): List<Long>

    @Query("DELETE FROM filo")
    suspend fun deleteFilozof()

    //Lider
    @Query("SELECT * FROM lieder")
    suspend fun getAllLider(): List<Lieder>

    @Query("SELECT * FROM  lieder WHERE id= :id")
    suspend fun getLiderID(id: Int): Lieder

    @Insert
    suspend fun insertLider(lider: Array<Lieder>): List<Long>

    @Query("DELETE FROM lieder")
    suspend fun deleteLider()

    //Onemli
    @Query("SELECT * FROM one")
    suspend fun getAllOnemli(): List<One>

    @Query("SELECT * FROM  one WHERE id= :id")
    suspend fun getOnemliID(id: Int): One

    @Insert
    suspend fun insertOnemli(onemli: Array<One>): List<Long>

    @Query("DELETE FROM one")
    suspend fun deleteOnemli()

    //Savas
    @Query("SELECT * FROM savvas")
    suspend fun getAllSavas(): List<Savvas>

    @Query("SELECT * FROM  savvas WHERE id= :id")
    suspend fun getSavasID(id:Int) : Savvas

    @Insert
    suspend fun insertSavas(bilim: Array<Savvas>):List<Long>

    @Query("DELETE FROM savvas")
    suspend fun deleteSavas()



    @Query(value = "SELECT * FROM users ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


}