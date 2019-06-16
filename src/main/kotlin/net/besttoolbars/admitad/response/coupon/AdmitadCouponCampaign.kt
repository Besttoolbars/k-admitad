package net.besttoolbars.admitad.response.coupon

import com.fasterxml.jackson.annotation.JsonProperty

data class AdmitadCouponCampaign(
    val id: Int,
    val name: String,
    @JsonProperty("site_url")
    val siteUrl: String
)
