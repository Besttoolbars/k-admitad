package net.besttoolbars.admitad.response.adv

import com.fasterxml.jackson.annotation.JsonProperty
import net.besttoolbars.admitad.response.AdmitadMeta

data class AdmitadAdvResponse(
    @JsonProperty("_meta")
    val meta: AdmitadMeta?,
    val results: List<AdmitadAdv> = listOf()
)
