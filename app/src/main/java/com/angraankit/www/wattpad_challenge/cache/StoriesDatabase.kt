package com.angraankit.www.wattpad_challenge.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StoriesCacheEntity::class, UserCacheEntity::class], version = 1)
abstract class StoriesDatabase : RoomDatabase() {

    abstract fun storiesDao() : StoriesDao

    companion object {
        val DATABASE_NAME = "stories_db"
    }
}