package net.besttoolbars.admitad

import net.besttoolbars.admitad.http.AdmitadHttpHandler
import java.io.InputStream
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture

internal class MockAdmitad(private val inputStream: InputStream) :
    AdmitadHttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return CompletableFuture.completedFuture(inputStream)
    }
}
