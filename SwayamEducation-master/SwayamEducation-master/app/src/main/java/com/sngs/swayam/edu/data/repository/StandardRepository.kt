package com.sngs.swayam.edu.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.admgmt.utils.network.ApiClient
import com.sngs.swayam.edu.data.api.StandardApiInterface
import com.sngs.swayam.edu.data.model.Standard
import com.sngs.swayam.edu.utils.common.APICallConfig

class StandardRepository  private constructor(){
    private object HOLDER {
        val INSTANCE = StandardRepository()
    }

    companion object {
        val instance: StandardRepository by lazy { HOLDER.INSTANCE }
    }

    private var apiInterface: StandardApiInterface? = null
    private val standardList: ArrayList<Standard> = ArrayList()
    private val config: APICallConfig = APICallConfig.LOCAL

    init {
        apiInterface = ApiClient.getApiClient().create(StandardApiInterface::class.java)
    }

    fun fetchAllStandard(): LiveData<List<Standard>> {
        val data = MutableLiveData<List<Standard>>()

        when(config){
            APICallConfig.LOCAL -> {
                standardList.clear()
                standardList.add(Standard("1","English"))
                standardList.add(Standard("2","Gujarati"))
                standardList.add(Standard("3","Hindi"))
                data.value = standardList
            }
            APICallConfig.API -> {

            }
        }
        return data
    }
}