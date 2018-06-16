package eu.mihau.placeholderapisample.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import eu.mihau.placeholderapisample.BuildConfig
import eu.mihau.placeholderapisample.api.APIService
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Accept", "*/*").build()
                    chain.proceed(request)
                }

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create<APIService>(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderRepository(service: APIService, schedulerProvider: SchedulerProvider): JsonPlaceholderRepository {
        return JsonPlaceholderRepository(service, schedulerProvider)
    }
}