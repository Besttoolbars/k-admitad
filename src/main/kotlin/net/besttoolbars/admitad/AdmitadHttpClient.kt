package net.besttoolbars.admitad

import mu.KotlinLogging
import net.besttoolbars.affiliate.core.HttpHandler
import java.io.InputStream
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

class AdmitadHttpClient @JvmOverloads constructor(
    private val client: HttpClient = HttpClient.newHttpClient()
) : HttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        logger.debug { "uri: ${request.uri()}" }
        logger.debug { "headers: ${request.headers()}" }
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenApply {
                when {
                    it.statusCode() != 200 -> throw BadAdmitadResponse("bad response with code ${it.statusCode()}")
                    else -> it
                }
            }
            .thenApply { it.body() }
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
