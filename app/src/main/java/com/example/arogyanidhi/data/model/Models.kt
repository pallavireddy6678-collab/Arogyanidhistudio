package com.example.arogyanidhi.data.model

data class UserProfile(
    val income: Double = 0.0,
    val isBpl: Boolean = false,
    val occupation: String = "",
    val state: String = "",
    val familySize: Int = 1
)

data class Document(
    val id: String,
    val name: String,
    val description: String
)

data class Scheme(
    val id: String,
    val name: String,
    val description: String,
    val eligibilityRules: String,
    val requiredDocuments: List<Document>,
    val liveUrl: String = ""
)

data class Hospital(
    val id: String,
    val name: String,
    val state: String,
    val district: String,
    val address: String,
    val empaneledSchemes: List<String> // List of Scheme IDs
)
