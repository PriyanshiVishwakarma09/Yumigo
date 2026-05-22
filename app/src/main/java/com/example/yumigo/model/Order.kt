package com.example.yumigo.model

data class Order(
    val id: String,
    val vehicleType: String,
    val dateTime: String,
    val orderId: String,
    val pickupLocation: String,
    val dropLocation: String,
    val price: Double,
    val status: OrderStatus
)

enum class OrderStatus {
    CANCELLED,
    COMPLETED,
    BOOKED_AGAIN
}
