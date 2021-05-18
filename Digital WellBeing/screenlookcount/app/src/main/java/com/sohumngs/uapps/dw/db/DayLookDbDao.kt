package com.sohumngs.uapps.dw.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface DayLookDbDao {

    @Query("SELECT * FROM DAY_LOOK WHERE DATE = :dateToSearch LIMIT 1")
    fun getDayLookByDate(dateToSearch: String?): Maybe<DayLookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dayLook: DayLookEntity)

    @Query("SELECT * FROM DAY_LOOK")
    fun getAllDayLooks(): Flowable<List<DayLookEntity>>

    @Query("DELETE FROM DAY_LOOK")
    fun deleteAll()
}
