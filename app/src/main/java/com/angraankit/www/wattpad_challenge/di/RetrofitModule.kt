package com.angraankit.www.wattpad_challenge.di

import com.angraankit.www.wattpad_challenge.network.StoriesNetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule  {

    @Singleton
    @Provides
    fun provideGsonBuilder () : Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit (gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://www.wattpad.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideStoriesService (retrofit: Retrofit.Builder) : StoriesNetworkService {
        return retrofit
            .build()
            .create(StoriesNetworkService::class.java)
    }

}