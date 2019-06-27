package net.besttoolbars.admitad

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.admitad.response.adv.AdmitadAdvResponse
import net.besttoolbars.admitad.response.coupon.AdmitadCouponResponse
import net.besttoolbars.admitad.response.token.OAuth2Token
import net.besttoolbars.affiliate.core.AffiliateConnector
import net.besttoolbars.affiliate.core.HttpHandler
import org.apache.http.message.BasicNameValuePair
import java.net.URI
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class Admitad @JvmOverloads constructor(
    httpHandler: HttpHandler = AdmitadHttpClient(),
    timeout: Long = 2,
    timeUnit: TimeUnit = TimeUnit.MINUTES,
    jsonMapper: ObjectMapper = jacksonObjectMapper()
) : AffiliateConnector(httpHandler, jsonMapper, timeout, timeUnit) {

    fun adv(token: String, adSpace: String, query: List<BasicNameValuePair>?): CompletableFuture<AdmitadAdvResponse> {
        val request = buildRequest("$API_URL/advcampaigns/website/$adSpace/", token, query)
        return makeRequest(request, AdmitadAdvResponse::class)
    }

    fun coupons(
        token: String,
        adSpace: String,
        language: String = "ru",
        offset: Int = 1,
        limit: Int = 500
    ): CompletableFuture<AdmitadCouponResponse> {
        val params = listOf(
            BasicNameValuePair("language", language),
            BasicNameValuePair("offset", offset.toString()),
            BasicNameValuePair("limit", limit.toString())
        )
        return coupons(token, adSpace, params)
    }

    fun coupons(
        token: String,
        adSpace: String,
        query: List<BasicNameValuePair>?
    ): CompletableFuture<AdmitadCouponResponse> {
        val request = buildRequest("$API_URL/coupons/website/$adSpace/", token, query)
        return makeRequest(request, AdmitadCouponResponse::class)
    }

    fun token(clientId: String, scope: String, baseAuthToken: String): CompletableFuture<OAuth2Token> {
        val entity = mapToQueryString(
            listOf(
                BasicNameValuePair("grant_type", "client_credentials"),
                BasicNameValuePair("client_id", clientId),
                BasicNameValuePair("scope", scope)
            )
        )

        val request = HttpRequest.newBuilder()
            .uri(URI.create("$API_URL/token/"))
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setHeader("Accept", "application/json")
            .setHeader("Authorization", "Basic $baseAuthToken")
            .POST(HttpRequest.BodyPublishers.ofString(entity))
            .build()

        return makeRequest(request, OAuth2Token::class)
    }

    companion object {
        const val API_URL = "https://api.admitad.com"

        fun provide(config: AdmitadBuilder.() -> Unit) = AdmitadBuilder(config).build()
    }
}
