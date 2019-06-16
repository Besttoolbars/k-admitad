package net.besttoolbars.admitad.http

import java.io.InputStream
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture

interface AdmitadHttpHandler {
    fun executeRequest(request: HttpRequest): CompletableFuture<InputStream>
}

