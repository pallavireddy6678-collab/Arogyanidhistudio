package com.example.arogyanidhi.ui

import androidx.lifecycle.ViewModel
import com.example.arogyanidhi.data.model.UserProfile
import com.example.arogyanidhi.data.model.Scheme
import com.example.arogyanidhi.data.model.Hospital
import com.example.arogyanidhi.data.repository.ArogyaRepository
import com.example.arogyanidhi.domain.EligibilityEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArogyaViewModel : ViewModel() {
    private val repository = ArogyaRepository()
    private val eligibilityEngine = EligibilityEngine(repository)

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _eligibleSchemes = MutableStateFlow<List<Scheme>>(emptyList())
    val eligibleSchemes: StateFlow<List<Scheme>> = _eligibleSchemes.asStateFlow()

    private val _hospitals = MutableStateFlow<List<Hospital>>(repository.allHospitals)
    val hospitals: StateFlow<List<Hospital>> = _hospitals.asStateFlow()
    
    val allSchemes = repository.allSchemes

    fun updateUserProfile(profile: UserProfile) {
        _userProfile.value = profile
        calculateEligibility()
        filterHospitals("")
    }

    private fun calculateEligibility() {
        val schemes = eligibilityEngine.checkEligibility(_userProfile.value)
        _eligibleSchemes.value = schemes
    }

    fun filterHospitals(district: String) {
        val state = _userProfile.value.state
        _hospitals.value = repository.getHospitalsByStateAndDistrict(state, district)
    }
    
    fun getSchemeById(id: String): Scheme? {
        return repository.allSchemes.find { it.id == id }
    }
}
