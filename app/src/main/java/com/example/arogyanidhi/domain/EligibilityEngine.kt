package com.example.arogyanidhi.domain

import com.example.arogyanidhi.data.model.Scheme
import com.example.arogyanidhi.data.model.UserProfile
import com.example.arogyanidhi.data.repository.ArogyaRepository

class EligibilityEngine(private val repository: ArogyaRepository) {

    fun checkEligibility(profile: UserProfile): List<Scheme> {
        val allSchemes = repository.allSchemes
        val eligibleSchemes = mutableListOf<Scheme>()

        for (scheme in allSchemes) {
            when (scheme.id) {
                "s1" -> {
                    // Ayushman Bharat PM-JAY: Families with BPL status.
                    if (profile.isBpl) {
                        eligibleSchemes.add(scheme)
                    }
                }
                "s2" -> {
                    // State Farmer Health Scheme: Income < 200000 AND Occupation = Farmer
                    if (profile.income < 200000.0 && profile.occupation.equals("Farmer", ignoreCase = true)) {
                        eligibleSchemes.add(scheme)
                    }
                }
                "s3" -> {
                    // Chief Minister's Comprehensive Health Insurance: Income < 250000
                    if (profile.income < 250000.0) {
                        eligibleSchemes.add(scheme)
                    }
                }
            }
        }
        
        return eligibleSchemes
    }
}
