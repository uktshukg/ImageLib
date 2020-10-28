package com.dexter.dunzo.ui.main.app_modules

import com.dexter.dunzo.ui.main.api.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException


@Module
class NetworkModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun apiClient (): ApiClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                        val original= chain.request()
                        val originalHttpUrl: HttpUrl = original.url
                        val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", "062a6c0c49e4de1d78497d13a7dbb360")
                            .addQueryParameter("format", "json")
                            .addQueryParameter("nojsoncallback", "1")

                            .build()

                        // Request customization: add request headers
                        val requestBuilder: Request.Builder = original.newBuilder()
                            .url(url)
                        val request: Request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                }).build()
            return Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create()

        }
    }
}
