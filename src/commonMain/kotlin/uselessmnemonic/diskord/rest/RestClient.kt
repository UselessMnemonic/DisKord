package uselessmnemonic.diskord.rest

import io.ktor.client.*
import io.ktor.client.request.*
import uselessmnemonic.diskord.DisKordClientConfig
import uselessmnemonic.diskord.gateway.BOT_GATEWAY
import uselessmnemonic.diskord.rest.requests.GetGatewayResponse

class RestClient(private val httpClient: HttpClient, private val config: DisKordClientConfig) {
    suspend fun getGatewayBot(): GetGatewayResponse {
        return httpClient.get(BOT_GATEWAY) {
            headers(config.makeAuthHeaders())
        }
    }
}
