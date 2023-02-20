package com.example.retrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitcoroutines.model.QuoteList
import com.example.retrofitcoroutines.model.api.QuotesAPI
import com.example.retrofitcoroutines.model.api.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiCall()
    }

    private fun apiCall() {
        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)
            if (result != null && result.code() == 200) logQuoteList(result)
            else Log.d(TAG, "apiCall: API RESPONSE STATUS CODE --> ${result.code()}")
        }
    }

    private fun logQuoteList(result: Response<QuoteList>) {
        val quoteList = result.body()
        if (quoteList != null) {
            quoteList.results.forEach {
                Log.d(TAG, "apiCall: API RESPONSE --> ${it.content}")
            }
        }
    }
}