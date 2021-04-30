package com.sngs.swayam.edu.data.api

import com.sngs.swayam.edu.data.model.Promo
import retrofit2.Call
import retrofit2.http.*

interface PromoApiInterface {
    @GET("ads")
    fun fetchAllPromos(): Call<List<Promo>>

    @POST("ads")
    fun createPromo(@Body promo: Promo):Call<Promo>

    @DELETE("ads/{id}")
    fun deletePromo(@Path("id") id:Int):Call<String>

    @PUT("ads")
    fun updatePromo(@Body promo: Promo) :Call<Promo>
}