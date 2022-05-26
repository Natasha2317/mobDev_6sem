package com.example.currencyconverter.data.room.converters

import androidx.room.TypeConverter
import java.time.LocalDate

class DateConverter {
        @TypeConverter
        fun toDate(dateString: String?): LocalDate? {
            return if (dateString == null) {
                null
            } else {
                LocalDate.parse(dateString)
            }
        }

        @TypeConverter
        fun toDateString(date: LocalDate?): String? {
            return date?.toString()
        }

}