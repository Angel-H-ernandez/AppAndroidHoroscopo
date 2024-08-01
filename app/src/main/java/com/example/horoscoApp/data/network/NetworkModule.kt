package com.example.horoscoApp.data.network

import com.example.horoscoApp.data.Resositoryimp
import com.example.horoscoApp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://newastro.vercel.app/")
            .client(OkHttpClient()) //agregar el interceptor para interceptar las cabeceras de autenticación
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
       return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideHoroscopeDataService(retrofit: Retrofit):HoroscopeDataService{
        return retrofit.create(HoroscopeDataService::class.java)
    }

    @Provides
    fun provideRepositirty(apiService:HoroscopeDataService): Repository{
        return Resositoryimp(apiService)
    }
}