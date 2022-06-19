package com.ds.norris.network

import com.ds.norris.model.Norris
import com.ds.norris.model.NorrisList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkInterface {

    @GET("jokes/random")
    suspend fun getRandomNorris(): Response<Norris>

    @GET("jokes/search")
    suspend fun searchNorris(@Query("query") key: String): Response<NorrisList>

    @GET("jokes/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("jokes/random")
    suspend fun searchNorrisbyCategory(@Query("category") key: String): Response<Norris>

}