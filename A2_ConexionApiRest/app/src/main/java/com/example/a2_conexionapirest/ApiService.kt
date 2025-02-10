package com.example.a2_conexionapirest

import com.example.a2_conexionapirest.model.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getAllProducts(@Url url: String): Response<SeriesResponse>
}