package net.besttoolbars.admitad.response.adv

import com.fasterxml.jackson.annotation.JsonProperty

data class AdmitadAdvAction(
    @JsonProperty("payment_size")
    val paymentSize: String? = null,
    val type: String? = null,
    val name: String? = null,
    val id: Int? = null,
    @JsonProperty("hold_time")
    val holdTime: Int? = null
)
