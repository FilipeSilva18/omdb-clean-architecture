package br.com.filipe.data.remote

import br.com.filipe.data.remote.dto.MovieDetailDto
import br.com.filipe.data.remote.dto.MovieDto
import br.com.filipe.data.remote.interceptor.RxRemoteErrorInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieService {

    @GET("?s=batman&apikey=45023bb7")
    fun getPopularMovies(): Single<MovieDto.Response>

    @GET("?apikey=45023bb7")
    fun searchMovie(@Query("s") search: String): Single<MovieDto.Response>

    @GET("?apikey=45023bb7")
    fun getMovieDetail(@Query("i") omdbId: String): Single<MovieDetailDto.MovieDetailResponse>

    companion object {
        fun createMovieService(
            baseUrl: String,
            rxRemoteErrorInterceptor: RxRemoteErrorInterceptor
        ): MovieService {
            val client = OkHttpClient().newBuilder()
                .addInterceptor(rxRemoteErrorInterceptor)
                .addInterceptor(getHttpLoggingInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseUrl)
                .build()
                .create(MovieService::class.java)
        }

        private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }
    }
}