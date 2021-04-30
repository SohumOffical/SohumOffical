package com.sngs.swayam.edu.ui.intro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.Intro
import com.sngs.swayam.edu.data.repository.IntroRepository

class IntroViewModel(application: Application): AndroidViewModel(application) {
    private var introRepository: IntroRepository? = null
    var introListLiveData : LiveData<List<Intro>>?=null

    init {
        introRepository = IntroRepository()
        introListLiveData = MutableLiveData()
    }

    fun fetchIntroInfo(){
        introListLiveData = introRepository?.fetchIntroInfo();
    }
}