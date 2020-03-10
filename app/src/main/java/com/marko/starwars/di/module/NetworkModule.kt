package com.marko.starwars.di.module

import android.content.Context
import com.marko.starwars.data.rest.RestService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-anon-f11aca4ef0-starwars2.apiary-mock.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideCache(context: Context): Cache {
        val cacheSize: Long = 5 * 1024 * 1024 //5mb cache
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    fun provideOkHttp(cache: Cache, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        val cacheLengthInSeconds = 300 //5 min cache
        return Interceptor { chain ->
            val request = chain.request()
            request.newBuilder()
                .header("Cache-Control", "public, max-age=$cacheLengthInSeconds")
                .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }
}