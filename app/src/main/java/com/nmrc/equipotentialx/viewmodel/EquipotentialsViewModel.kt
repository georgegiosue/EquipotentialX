package com.nmrc.equipotentialx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmrc.equipotentialx.model.Charge

class EquipotentialsViewModel : ViewModel() {

    private val charges = MutableLiveData<ArrayList<Charge>>()

    init {
        charges.value = ArrayList()
    }

    fun getCharges(): LiveData<ArrayList<Charge>> {
        return charges
    }

    fun addCharge(charge: Charge) {
        charges.value?.add(charge)
    }


}