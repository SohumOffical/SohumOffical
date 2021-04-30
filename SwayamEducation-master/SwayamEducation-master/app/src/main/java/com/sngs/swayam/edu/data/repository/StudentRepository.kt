package com.sngs.swayam.edu.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.admgmt.utils.network.ApiClient
import com.sngs.swayam.edu.data.api.StudentApiInterface
import com.sngs.swayam.edu.data.model.Student
import com.sngs.swayam.edu.utils.common.APICallConfig

class StudentRepository private constructor(){
    private object HOLDER {
        val INSTANCE = StudentRepository()
    }

    companion object {
        val instance: StudentRepository by lazy { HOLDER.INSTANCE }
    }

    private var apiInterface: StudentApiInterface? = null
    private val standentList: ArrayList<Student> = ArrayList()
    private val config: APICallConfig = APICallConfig.LOCAL

    init {
        apiInterface = ApiClient.getApiClient().create(StudentApiInterface::class.java)
    }

    fun fetchAllStudent(): LiveData<List<Student>> {
        val data = MutableLiveData<List<Student>>()

        when(config){
            APICallConfig.LOCAL -> {
                standentList.clear()
                standentList.add(Student("1","Naimee","1","English"))
                standentList.add(Student("2","Tejas","2","Gujarati"))
                standentList.add(Student("3","Nirali","3","Hindi"))
                data.value = standentList
            }
            APICallConfig.API -> {

            }
        }
        return data
    }
}