package com.github.kotlintelegrambot.entities.payments.provider

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class CustomQuery(
    val id: String,
    val method: String,
    @SerializedName("bot_id") val botId: Long,
    @SerializedName("bot_account") val botAccount: String,
    @SerializedName("bot_username") val botUsername: String,
    @SerializedName("bot_owner_id") val botOwnerId: Long,
    @SerializedName("customer_id") val customerId: Long,
    @SerializedName("charge_id") val chargeId: String?,
    @SerializedName("total_amount") val totalAmount: BigInteger?,
    @SerializedName("customer_info") val customerInfo: CustomerInfo?,
    @SerializedName("save_credentials") val saveCredentials: Boolean?,
    val currency: String?,
    val credentials: Credentials?,
    val payload: String?,
    val callback: String?,
    val data: String?
)

data class Credentials(
    val type: String,
    val token: String? = null,
    val savedCredentials: Credentials? = null
)

data class CustomerInfo(
    val phone: String,
    val email: String
)
