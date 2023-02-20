package com.example.retrofitcoroutines.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val URL = "https://quotable.io/"
    private const val REQUEST_TIMEOUT = 200

    // Get the instance of the Retrofit
    fun getInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}

/**
 * @httpCline
 * or
 * @OkHttpClient.builder
 * OkHttpClient.Builder class, which is used for configuring and creating HTTP clients.
 * The code sets various timeouts on the client, including the connection
 * timeout,
 * read timeout,
 * and write timeout.
 *
 * .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS): This line sets the connection timeout for the client.
 * The connectTimeout method sets the maximum time that the client will wait for a connection to be established.
 * The REQUEST_TIMEOUT variable is the timeout value in seconds, and TimeUnit.SECONDS is the time unit for the timeout.
 *
 * .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS): This line sets the read timeout for the client.
 * The readTimeout method sets the maximum time that the client will wait for a response to be read.
 * The REQUEST_TIMEOUT variable is the timeout value in seconds, and TimeUnit.SECONDS is the time unit for the timeout.
 *
 * .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS): This line sets the write timeout for the client.
 * The writeTimeout method sets the maximum time that the client will wait for a request to be written.
 * The REQUEST_TIMEOUT variable is the timeout value in seconds, and TimeUnit.SECONDS is the time unit for the timeout.
 *
 * Overall,  OkHttpClient.Builder and sets the connection timeout, read timeout, and write timeout for the client to the same value,
 * which is defined by the REQUEST_TIMEOUT constant. These timeouts ensure that the client will not wait indefinitely for a response
 * or a connection, and can instead time out and return an error after a certain amount of time has elapsed.
 *
 * */


/**
 * @HttpLoggingInterceptor
 * HttpLoggingInterceptor is a class provided by the
 * OkHttp library for logging HTTP requests and responses for debugging and troubleshooting purposes.
 * It intercepts the network traffic between the app and the server, and logs the details of each request and response.
 * setLevel is a method of the HttpLoggingInterceptor class that sets the logging level for the interceptor.
 * There are four levels of logging available: NONE, BASIC, HEADERS, and BODY. Here, the level is set to BODY,
 * which means that the interceptor will log the full request and response body, including the headers, request and response line,
 * and request and response bodies.
 * In summary, the code creates an instance of HttpLoggingInterceptor with the logging level set to BODY,
 * which will log the full details of each request and response made by the app.
 * need to use this dependency for the retrofit 2 --> implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3'
 * */