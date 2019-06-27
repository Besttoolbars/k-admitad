package net.besttoolbars.admitad

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.affiliate.core.HttpHandler
import java.util.concurrent.TimeUnit

class AdmitadBuilder() {
    private var httpHandler: HttpHandler = AdmitadHttpClient()
    private var timeout: Long = 2
    private var timeUnit: TimeUnit = TimeUnit.MINUTES
    private var mapper: ObjectMapper = jacksonObjectMapper()

    fun withTimeout(timeout: Long, timeUnit: TimeUnit = TimeUnit.MINUTES) = apply {
        this.timeout = timeout
        this.timeUnit = timeUnit
    }

    fun withHttpHandler(httpHandler: HttpHandler) =
        apply { this.httpHandler = httpHandler }

    fun withMapper(mapper: ObjectMapper) = apply { this.mapper = mapper }

    fun build() = Admitad(httpHandler, timeout, timeUnit)

    constructor(config: AdmitadBuilder.() -> Unit) : this() {
        apply(config)
    }
}
