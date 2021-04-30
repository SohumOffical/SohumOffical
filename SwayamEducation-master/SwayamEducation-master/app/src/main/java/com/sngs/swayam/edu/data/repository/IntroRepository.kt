package com.sngs.swayam.edu.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Intro

class IntroRepository {
    fun fetchIntroInfo(): LiveData<List<Intro>> {
        val data = MutableLiveData<List<Intro>>()
        val myList= ArrayList<Intro>()
        myList.add(Intro(R.drawable.intro_shape_1,"Lorem ipsum dummy text Amite"))
        myList.add(Intro(R.drawable.intro_shape_2,"Lorem ipsum dummy text Amite"))
        myList.add(Intro(R.drawable.intro_shape_3,"Lorem ipsum dummy text Amite"))
        data.value = myList
        return data
    }
}