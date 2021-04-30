package com.sngs.swayam.edu.data.api

import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.data.model.Subject
import retrofit2.Call
import retrofit2.http.GET

interface StudAttendanceApiInterface {

    @GET("ads")
    fun fetchAllSubject(): Call<List<Subject>>
}