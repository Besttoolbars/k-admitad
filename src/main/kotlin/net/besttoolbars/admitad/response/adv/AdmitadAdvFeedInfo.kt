package net.besttoolbars.admitad.response.adv

import com.fasterxml.jackson.annotation.JsonProperty

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
