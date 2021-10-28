package com.c1ctech.jetpackpagingexp.data.models

data class PassengersResponse(
    val `data`: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)