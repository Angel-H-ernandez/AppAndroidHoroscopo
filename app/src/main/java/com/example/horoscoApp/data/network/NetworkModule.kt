package com.example.horoscoApp.data.network

import com.example.horoscoApp.data.Resositoryimp
import com.example.horoscoApp.data.core.interceptors.AuthInterceptor
import com.example.horoscoApp.domain.Repository
import com.example.intermedioappkottlin.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient()) //agregar el interceptor para interceptar las cabeceras de autenticación
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
       return OkHttpClient
           .Builder()
           .addInterceptor(interceptor) //agregar el interceptor para interceptar las cabeceras de autenticació
           .addInterceptor(authInterceptor) //agregar el interceptor para interceptar las cabeceras de autenticación
           .build()
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