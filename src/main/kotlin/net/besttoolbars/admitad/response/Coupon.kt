package net.besttoolbars.admitad.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
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

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadCouponCampaign(
    val id: Int,
    val name: String,
    @JsonProperty("site_url")
    val siteUrl: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadCouponCategory(val id: Int, val name: String?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadCouponResponse(
    @JsonProperty("_meta")
    val meta: AdmitadMeta?,
    val results: List<AdmitadCoupon>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadCouponType(val id: Int, val name: String?)
