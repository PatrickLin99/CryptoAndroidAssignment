package com.example.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrencies(currencyInfo: CurrencyInfo)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): kotlinx.coroutines.flow.Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencies WHERE code IS NULL")
    fun getCrypto(): kotlinx.coroutines.flow.Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencies WHERE code IS NOT NULL")
    fun getFiat(): kotlinx.coroutines.flow.Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencies WHERE code LIKE :keyword OR id LIKE :keyword OR _name LIKE :keyword")
    fun getFilteredCurrencies(keyword: String): kotlinx.coroutines.flow.Flow<List<CurrencyInfo>>

    @Delete
    suspend fun deleteCurrency(currencyInfo: CurrencyInfo)

    @Query("DELETE FROM currencies")
    suspend fun deleteAllCurrencies()
}