package com.sngs.swayam.edu.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.admgmt.utils.network.ApiClient
import com.sngs.swayam.edu.data.api.PromoApiInterface
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.utils.common.APICallConfig
import com.sngs.swayam.edu.utils.common.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoRepository private constructor() {
    private object HOLDER {
        val INSTANCE = PromoRepository()
    }

    companion object {
        val instance: PromoRepository by lazy { HOLDER.INSTANCE }
    }

    private var apiInterface: PromoApiInterface? = null

    private val promoList: ArrayList<Promo> = ArrayList()

    private val config:APICallConfig=APICallConfig.LOCAL

    init {
        apiInterface = ApiClient.getApiClient().create(PromoApiInterface::class.java)
    }

    fun fetchAllPromos(): LiveData<List<Promo>> {
        val data = MutableLiveData<List<Promo>>()

        when(config){
            APICallConfig.LOCAL -> {
                data.value = promoList
            }
            APICallConfig.API -> {
                apiInterface?.fetchAllPromos()?.enqueue(object : Callback<List<Promo>> {
                    override fun onFailure(call: Call<List<Promo>>, t: Throwable) {
                        Log.println(Log.ERROR, "PromoRepository","fetchAllPromos failed with "+t.message)
                        data.value = null
                    }

                    override fun onResponse(
                            call: Call<List<Promo>>,
                            response: Response<List<Promo>>
                    ) {
                        val res = response.body()
                        if (response.code() == 200 && res != null) {
                            data.value = res
                        } else {
                            data.value = null
                        }
                    }
                })
            }
        }
        return data
    }

    fun createPromo(promo: Promo): LiveData<Promo> {
        val data = MutableLiveData<Promo>()

        when(config) {
            APICallConfig.LOCAL -> {
                promo.id = promoList.size + 1
                promoList.add(promo)
                data.value = promo
            }
            APICallConfig.API -> {
                apiInterface?.createPromo(promo)?.enqueue(object : Callback<Promo> {
                    override fun onFailure(call: Call<Promo>, t: Throwable) {
                        Log.println(Log.ERROR, "PromoRepository", "createPromo failed with " + t.message)
                        data.value = null
                    }

                    override fun onResponse(call: Call<Promo>, response: Response<Promo>) {
                        val res = response.body()

                        if (response.code() == 201 && res != null) {
                            data.value = res
                        } else {
                            data.value = null
                        }
                    }
                })
            }
        }
        return data
    }

    fun deletePromo(id: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        when(config) {
            APICallConfig.LOCAL -> {
                val position:Int = promoList.indexOfFirst { it:Promo -> it.id == id }
                promoList.removeAt(position)
                data.value = true
            }
            APICallConfig.API -> {
                apiInterface?.deletePromo(id)?.enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.println(Log.ERROR, "PromoRepository","deletePromo failed with "+t.message)
                        data.value = false
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        data.value = response.code() == 200
                    }
                })
            }
        }
        return data
    }

    fun updatePromo(promo: Promo): LiveData<Promo> {
        val data = MutableLiveData<Promo>()

        when(config) {
            APICallConfig.LOCAL -> {
                val position:Int = promoList.indexOfFirst { it:Promo -> it.id == promo.id }
                promoList?.removeAt(position)
                promoList.add(position,promo);
                data.value = promo
            }
            APICallConfig.API -> {
                apiInterface?.updatePromo(promo)?.enqueue(object : Callback<Promo> {
                    override fun onFailure(call: Call<Promo>, t: Throwable) {
                        Log.println(Log.ERROR, "PromoRepository","updatePromo failed with "+t.message)
                        data.value = null
                    }

                    override fun onResponse(call: Call<Promo>, response: Response<Promo>) {
                        val res = response.body()
                        if (response.code() == 201 && res != null) {
                            data.value = res
                        } else {
                            data.value = null
                        }
                    }
                })
            }
        }

        return data

    }
}