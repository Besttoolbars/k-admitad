package net.besttoolbars.admitad.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OAuth2Token(
    val username: String? = null,
    @JsonProperty("first_name")
    val firstName: String? = null,
    @JsonProperty("last_name")
    val lastName: String? = null,
    val language: String? = null,
    @JsonProperty("access_token")
    val accessToken: String? = null,
    @JsonProperty("token_type")
    val tokenType: String? = null,
    @JsonProperty("expires_in")
    val expiresIn: Long? = null,
    @JsonProperty("refresh_token")
    val refreshToken: String? = null,
    val scope: String? = null,
    val group: String? = null,
    val id: Long? = null
)
