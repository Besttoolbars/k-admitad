package net.besttoolbars.admitad

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.admitad.http.AdmitadHttpClient
import net.besttoolbars.admitad.http.AdmitadHttpHandler
import net.besttoolbars.admitad.response.adv.AdmitadAdvResponse
import net.besttoolbars.admitad.response.coupon.AdmitadCouponResponse
import net.besttoolbars.admitad.response.token.OAuth2Token
import org.apache.http.Consts
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.message.BasicNameValuePair
import java.net.URI
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class Admitad @JvmOverloads constructor(
    private val httpHandler: AdmitadHttpHandler = AdmitadHttpClient(),
    private val timeout: Long = 2,
    private val timeUnit: TimeUnit = TimeUnit.MINUTES,
    private val jsonMapper: ObjectMapper = jacksonObjectMapper()
) {

    fun adv(token: String, adSpace: String, query: List<BasicNameValuePair>?): CompletableFuture<AdmitadAdvResponse> {
        val request = buildRequest("$API_URL/advcampaigns/website/$adSpace/", token, query)
        return httpHandler.executeRequest(request)
            .thenApply { jsonMapper.readValue(it, AdmitadAdvResponse::class.java) }
            .orTimeout(timeout, timeUnit)

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
        return httpHandler.executeRequest(request)
            .thenApply { jsonMapper.readValue(it, AdmitadCouponResponse::class.java) }
            .orTimeout(timeout, timeUnit)
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

        return httpHandler.executeRequest(request)
            .thenApply { jsonMapper.readValue(it, OAuth2Token::class.java) }
            .orTimeout(timeout, timeUnit)
    }

    fun mapToQueryString(query: List<BasicNameValuePair>): String {
        return URLEncodedUtils.format(query, Consts.UTF_8)
    }

    fun buildRequest(url: String, token: String, query: List<BasicNameValuePair>?): HttpRequest {
        if (query.isNullOrEmpty()) {
            return buildRequest(url, token)
        }
        val queryString = mapToQueryString(query)
        return buildRequest("$url?$queryString", token)
    }

    fun buildRequest(url: String, token: String): HttpRequest {
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .setHeader("Content-Type", "application/json")
            .setHeader("Accept", "application/json")
            .setHeader("Authorization", "Bearer $token")
            .build()
    }

    companion object {
        const val API_URL = "https://api.admitad.com"

        fun provide(config: AdmitadBuilder.() -> Unit) = AdmitadBuilder(
            config
        ).build()
    }
}
