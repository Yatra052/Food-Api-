package com.example.foodapi.Model

data class ReceipeResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)