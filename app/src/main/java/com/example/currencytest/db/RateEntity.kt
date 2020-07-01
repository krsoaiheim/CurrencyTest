package com.example.currencytest.db

import androidx.room.*

@Entity(tableName = "rates")
@TypeConverters(CurrencyConverter::class)
data class RateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "baseCurrency")
    val baseCurrency: ConvertCurrency,
    @ColumnInfo(name = "second")
    val second: ConvertCurrency,
    @ColumnInfo(name = "convertValue")
    var convertValue: Float
)

enum class ConvertCurrency(val code: String) {
    EUR("EUR"), USD("USD"), RUB("RUB"), JPY("JPY"), GBP("GBP")
}

class CurrencyConverter {
    @TypeConverter
    fun fromCateg(data: ConvertCurrency): String {
        return data.code
    }

    @TypeConverter
    fun toCateg(row: String): ConvertCurrency {
        return ConvertCurrency.valueOf(row)
    }
}
