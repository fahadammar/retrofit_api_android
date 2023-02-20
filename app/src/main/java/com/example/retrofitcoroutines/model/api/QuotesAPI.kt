package com.example.retrofitcoroutines.model.api

import com.example.retrofitcoroutines.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {

    // BASE_URL + "/quotes?page=1"
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page : Int) : Response<QuoteList>

}