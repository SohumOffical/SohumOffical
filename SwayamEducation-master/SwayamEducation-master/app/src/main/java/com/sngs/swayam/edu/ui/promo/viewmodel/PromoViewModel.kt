package com.sngs.swayam.edu.ui.promo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.data.repository.PromoRepository

class PromoViewModel(application: Application): AndroidViewModel(application){
    private var promoRepository: PromoRepository?=null
    var promoListLiveData : LiveData<List<Promo>>?=null
    var createPromoLiveData : LiveData<Promo>?=null
    var deletePromoLiveData:LiveData<Boolean>?=null
    var updatePromoLiveData : LiveData<Promo>?=null

    init {
        promoRepository = PromoRepository.instance
        promoListLiveData = MutableLiveData()
        createPromoLiveData = MutableLiveData()
        deletePromoLiveData = MutableLiveData()
        updatePromoLiveData = MutableLiveData()
    }

    fun fetchAllPromos(){
        promoListLiveData = promoRepository?.fetchAllPromos();
    }

    fun createPromo(promo: Promo){
        createPromoLiveData = promoRepository?.createPromo(promo)
    }

    fun deletePromo(id:Int){
        deletePromoLiveData = promoRepository?.deletePromo(id)
    }

    fun updatePromo(promo: Promo){
        updatePromoLiveData = promoRepository?.updatePromo(promo)
    }
}