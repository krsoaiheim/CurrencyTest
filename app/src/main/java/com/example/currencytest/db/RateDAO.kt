package com.example.currencytest.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(CurrencyConverter::class)
interface RateDAO {
    @Query("SELECT  * FROM rates")
    fun getList(): Flow<List<RateEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertRate(ratePair: RateEntity)

    @Update
    suspend fun update(list: List<RateEntity>)

    @Delete
    suspend fun deleteRate(ratePair: RateEntity)

}