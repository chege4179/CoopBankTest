package com.peterchege.coopbanktest.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peterchege.coopbanktest.core.api.CoopBankService
import com.peterchege.coopbanktest.core.util.Constants
import com.peterchege.coopbanktest.data.AppRepository
import com.peterchege.coopbanktest.data.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = true
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context = context)
                    .collector(ChuckerCollector(context = context))
                    .maxContentLength(length = 250000L)
                    .redactHeaders(headerNames = emptySet())
                    .alwaysReadResponseBody(enable = false)
                    .build()
            )
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideUserApi(
        client: OkHttpClient,
        networkJson: Json,
    ): CoopBankService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(CoopBankService::class.java)
    }


    @Provides
    @Singleton
    fun provideAppRepository(
        apiService: CoopBankService,
    ):AppRepository{
        return AppRepositoryImpl(apiService)
    }
}