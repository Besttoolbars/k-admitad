package net.besttoolbars.admitad.response.coupon

import com.fasterxml.jackson.annotation.JsonProperty

data class AdmitadCoupon(
    val id: Int,
    val name: String,
    val image: String?,
    @JsonProperty("short_name")
    val shortName: String?,
    val status: String,
    val rating: String?,
    val description: String?,
    val campaign: AdmitadCouponCampaign,
    val promocode: String?,
    @JsonProperty("date_start")
    val dateStart: String?,
    @JsonProperty("date_end")
    val dateEnd: String?,
    val categories: List<AdmitadCouponCategory>?,
    val types: List<AdmitadCouponType>?,
    val exclusive: Boolean?,
    val discount: String?,
    val species: String?,
    val regions: List<String>?,
    @JsonProperty("frameset_link")
    val framesetLink: String?,
    @JsonProperty("goto_link")
    val gotoLink: String?
)

