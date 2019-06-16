package net.besttoolbars.admitad.response.coupon

import com.fasterxml.jackson.annotation.JsonProperty
import net.besttoolbars.admitad.response.AdmitadMeta

data class AdmitadCouponResponse(
    @JsonProperty("_meta")
    val meta: AdmitadMeta?,
    val results: List<AdmitadCoupon>
)
