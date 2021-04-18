package com.angraankit.www.wattpad_challenge.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (storiesCacheEntity: StoriesCacheEntity) : Long

    @Query("SELECT * FROM stories")
    suspend fun getStories () : List<StoriesCacheEntity>

    @Query("DELETE FROM stories")
    suspend fun deleteAll ()
}