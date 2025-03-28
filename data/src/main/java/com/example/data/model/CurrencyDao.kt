package com.example.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrencies(currencyInfo: CurrencyInfo)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencies WHERE code IS NULL")
    fun getCrypto(): Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencies WHERE code IS NOT NULL")
    fun getFiat(): Flow<List<CurrencyInfo>>

    @Delete
    suspend fun deleteCurrency(currencyInfo: CurrencyInfo)

    @Query("DELETE FROM currencies")
    suspend fun deleteAllCurrencies()
}