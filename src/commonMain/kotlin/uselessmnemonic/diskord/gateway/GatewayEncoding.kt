package uselessmnemonic.diskord.gateway

/**
 * Determines the encoding for payloads sent to and received from the gateway.
 */
enum class GatewayEncoding(val code: String) {

    /**
     * Use JSON to encode payloads.
     * This requires installing Ktor's JsonFeature.
     */
    JSON("json"),

    /**
     * Use ETF to encode payloads.
     * This is currently unsupported.
     */
    //ETF("etf")
}
