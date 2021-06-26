package com.nmrc.equipotentialx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmrc.equipotentialx.model.ECharge

class EquipotentialsViewModel : ViewModel() {

    private var charges = MutableLiveData<ArrayList<ECharge>>()
    private var data: ArrayList<ECharge> = ArrayList()

    init {
        setData(data)
    }

    fun getChargers(): LiveData<ArrayList<ECharge>> {
        return charges
    }

    fun addCharge(charge: ECharge) {
        charges.value?.add(charge)
    }

    private fun setData(data: ArrayList<ECharge>) {
        charges.value = data
    }
}