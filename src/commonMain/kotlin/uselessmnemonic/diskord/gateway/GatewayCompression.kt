package uselessmnemonic.diskord.gateway

/**
 * Determines the type of compression that will be used in the gateway client.
 */
enum class GatewayCompression {

    /**
     * Enables compression for payloads received from the gateway.
     * Only JSON-encoded payloads are compressible. No payload shares
     * a compression context.
     */
    PAYLOAD,

    /**
     * Enables transport compression for the connection to the gateway.
     * All payloads share a compression context.
     */
    TRANSPORT,

    /**
     * Disables payload compression.
     */
    NONE
}
