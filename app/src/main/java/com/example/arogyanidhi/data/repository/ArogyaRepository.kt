package com.example.arogyanidhi.data.repository

import com.example.arogyanidhi.data.model.Document
import com.example.arogyanidhi.data.model.Hospital
import com.example.arogyanidhi.data.model.Scheme

class ArogyaRepository {

    private val aadhar = Document("doc1", "Aadhar Card", "Proof of identity and address.")
    private val incomeCert = Document("doc2", "Income Certificate", "Proof of annual family income.")
    private val bplCard = Document("doc3", "BPL Ration Card", "Proof of Below Poverty Line status.")
    private val casteCert = Document("doc4", "Caste Certificate", "For category-based benefits.")

    val allSchemes = listOf(
        Scheme(
            id = "s1",
            name = "Ayushman Bharat PM-JAY",
            description = "Health cover of Rs. 5 lakhs per family per year for secondary and tertiary care hospitalization.",
            eligibilityRules = "Families with BPL status.",
            requiredDocuments = listOf(aadhar, bplCard),
            liveUrl = "https://pmjay.gov.in/"
        ),
        Scheme(
            id = "s2",
            name = "State Farmer Health Scheme",
            description = "Financial protection and health insurance specifically for farmers and their families.",
            eligibilityRules = "Income < 200000 AND Occupation = Farmer",
            requiredDocuments = listOf(aadhar, incomeCert, casteCert),
            liveUrl = "https://pmkisan.gov.in/"
        ),
        Scheme(
            id = "s3",
            name = "Chief Minister's Comprehensive Health Insurance",
            description = "Cashless treatment in empaneled hospitals for low-income groups.",
            eligibilityRules = "Income < 250000",
            requiredDocuments = listOf(aadhar, incomeCert),
            liveUrl = "https://www.cmchistn.com/"
        )
    )

    val allHospitals = listOf(
        Hospital("h1", "City General Hospital", "Telangana", "Hyderabad", "Main Road, Hyderabad", listOf("s1", "s2")),
        Hospital("h2", "Rural Health Care", "Telangana", "Warangal", "Village Center, Warangal", listOf("s1")),
        Hospital("h3", "Sanjeevani Specialty Hospital", "Telangana", "Hyderabad", "Banjara Hills, Hyderabad", listOf("s1", "s2", "s3")),
        Hospital("h4", "District Hospital", "Telangana", "Karimnagar", "Civil Lines, Karimnagar", listOf("s1", "s2")),
        Hospital("h5", "Sunrise Medicare", "Telangana", "Warangal", "Kazipet, Warangal", listOf("s3")),
        Hospital("h6", "Apex Care", "Telangana", "Nizamabad", "Station Road, Nizamabad", listOf("s1")),
        Hospital("h7", "Community Health Center", "Telangana", "Adilabad", "Near Bus Stand, Adilabad", listOf("s1", "s2", "s3")),
        Hospital("h8", "Life Line Hospital", "Telangana", "Khammam", "Wyra Road, Khammam", listOf("s2")),
        Hospital("h9", "Global Hospital", "Telangana", "Hyderabad", "Lakdikapul, Hyderabad", listOf("s1", "s3")),
        Hospital("h10", "Sri Sai Clinic", "Telangana", "Nalgonda", "Clock Tower, Nalgonda", listOf("s1", "s2")),
        Hospital("h11", "Bangalore Medical Center", "Karnataka", "Bangalore", "MG Road, Bangalore", listOf("s1", "s2", "s3")),
        Hospital("h12", "Mysore General Hospital", "Karnataka", "Mysore", "Palace Road, Mysore", listOf("s1", "s3")),
        Hospital("h13", "Hubli Healthcare", "Karnataka", "Hubli", "Station Road, Hubli", listOf("s2")),
        Hospital("h14", "Amaravati Health Center", "Andhra Pradesh", "Amaravati", "Main Road, Amaravati", listOf("s1", "s2")),
        Hospital("h15", "Vizag Coastal Hospital", "Andhra Pradesh", "Visakhapatnam", "Beach Road, Vizag", listOf("s1", "s3"))
    )

    fun getHospitalsByStateAndDistrict(state: String, district: String): List<Hospital> {
        val st = state.trim()
        val dist = district.trim()
        return allHospitals.filter { 
            (st.isBlank() || it.state.contains(st, ignoreCase = true) || st.contains(it.state, ignoreCase = true)) &&
            (dist.isBlank() || it.district.contains(dist, ignoreCase = true)) 
        }
    }
}
