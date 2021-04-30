package com.sngs.swayam.edu.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.admgmt.utils.network.ApiClient
import com.sngs.swayam.edu.data.api.SubjectApiInterface
import com.sngs.swayam.edu.data.model.Subject
import com.sngs.swayam.edu.utils.common.APICallConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectRepository private constructor(){
    private object HOLDER {
        val INSTANCE = SubjectRepository()
    }

    companion object {
        val instance: SubjectRepository by lazy { HOLDER.INSTANCE }
    }

    private var apiInterface: SubjectApiInterface? = null
    private val subjectList: ArrayList<Subject> = ArrayList()
    private val config: APICallConfig = APICallConfig.LOCAL

    init {
        apiInterface = ApiClient.getApiClient().create(SubjectApiInterface::class.java)
    }

    fun fetchAllSubject(): LiveData<List<Subject>> {
        val data = MutableLiveData<List<Subject>>()

        when(config){
            APICallConfig.LOCAL -> {
                data.value = subjectList
            }
            APICallConfig.API -> {

            }
        }
        return data
    }
}