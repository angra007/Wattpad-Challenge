package com.angraankit.www.wattpad_challenge.di

import android.content.Context
import androidx.room.Room
import com.angraankit.www.wattpad_challenge.cache.StoriesDao
import com.angraankit.www.wattpad_challenge.cache.StoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule  {

    @Provides
    @Singleton
    fun provideStoriesDb (@ApplicationContext context: Context) : StoriesDatabase {
        return Room.databaseBuilder (
            context,
            StoriesDatabase::class.java,
            StoriesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoriesDao (stroriesDatabase: StoriesDatabase) : StoriesDao {
        return stroriesDatabase.storiesDao()
    }
}