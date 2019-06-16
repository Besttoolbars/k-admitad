package net.besttoolbars.admitad.http

import net.besttoolbars.admitad.errors.BadAdmitadResponse
import java.io.InputStream
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

class AdmitadHttpClient @JvmOverloads constructor(
    private val client: HttpClient = HttpClient.newHttpClient()
) : AdmitadHttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenApply {
                when {
                    it.statusCode() != 200 -> throw BadAdmitadResponse("bad response with code ${it.statusCode()}")
                    else -> it
                }
            }
            .thenApply { it.body() }
    }
}
