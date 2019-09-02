package net.besttoolbars.admitad.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdv(
    val id: Long? = null,
    val name: String? = null,
    val image: String? = null,
    val status: String? = null,
    val rating: Float? = null,
    val description: String? = null,
    @JsonProperty("raw_description")
    val rawDescription: String? = null,
    @JsonProperty("site_url")
    val siteUrl: String? = null,
    val exclusive: Boolean? = null,
    val currency: String? = null,
    val regions: List<AdmitadAdvRegion> = listOf(),
    val categories: List<AdmitadAdvCategory> = listOf(),
    val actions: List<AdmitadAdvAction> = listOf(),
    val cr: Float? = null,
    @JsonProperty("cr_trend")
    val crTrend: Float? = null,
    val ecpc: Float? = null,
    @JsonProperty("ecpc_trend")
    val ecpcTrend: Float? = null,
    val epc: Float? = null,
    @JsonProperty("epc_trend")
    val epcTrend: Float? = null,
    @JsonProperty("rate_of_approve")
    val rateOfApprove: String? = null,
    @JsonProperty("more_rules")
    val moreRules: String? = null,
    val geotargeting: Boolean? = null,
    @JsonProperty("coupon_iframe_denied")
    val couponIframeDenied: Boolean? = null,
    @JsonProperty("activation_date")
    val activationDate: String? = null,
    @JsonProperty("modified_date")
    val modifiedDate: String? = null,
    @JsonProperty("date_start")
    val dateStart: String? = null,
    @JsonProperty("max_hold_time")
    val maxHoldTime: String? = null,
    val connected: Boolean? = null,
    @JsonProperty("avg_hold_time")
    val avgHoldTime: Int? = null,
    @JsonProperty("avg_money_transfer_time")
    val avgMoneyTransferTime: Int? = null,
    val denynewwms: Boolean? = null,
    @JsonProperty("goto_cookie_lifetime")
    val gotoCookieLifetime: Int? = null,
    val retag: Boolean? = null,
    @JsonProperty("show_products_links")
    val showProductsLinks: Boolean? = null,
    @JsonProperty("products_csv_link")
    val productsCsvLink: String? = null,
    @JsonProperty("products_xml_link")
    val productsXmlLink: String? = null,
    val traffics: List<AdmitadAdvTraffic> = listOf(),
    @JsonProperty("landing_code")
    val landingCode: String? = null,
    @JsonProperty("landing_title")
    val landingTitle: String? = null,
    @JsonProperty("action_type")
    val actionType: String? = null,
    @JsonProperty("individual_terms")
    val individualTerms: Boolean? = null,
    @JsonProperty("allow_deeplink")
    val allowDeeplink: Boolean? = null,
    val gotolink: String? = null,
    @JsonProperty("connection_status")
    val connectionStatus: String? = null,
    @JsonProperty("feeds_info")
    val feedsInfo: List<AdmitadAdvFeedInfo> = listOf(),
    val mobileOsType: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvAction(
    @JsonProperty("payment_size")
    val paymentSize: String? = null,
    val type: String? = null,
    val name: String? = null,
    val id: Int? = null,
    @JsonProperty("hold_time")
    val holdTime: Int? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvCategory(
    val id: Int? = null,
    val language: String? = null,
    val name: String? = null,
    val parent: AdmitadAdvCategory? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvFeedInfo(
    val name: String? = null,

    @JsonProperty("admitad_last_update")
    val admitadLastUpdate: String? = null,

    @JsonProperty("advertiser_last_update")
    val advertiserLastUpdate: String? = null,

    @JsonProperty("csv_link")
    val csvLink: String? = null,

    @JsonProperty("xml_link")
    val xmlLink: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvRegion(
    val region: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvResponse(
    @JsonProperty("_meta")
    val meta: AdmitadMeta?,
    val results: List<AdmitadAdv> = listOf()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdmitadAdvTraffic(
    val enabled: Boolean? = null,
    val name: String? = null,
    val id: Int? = null
)
