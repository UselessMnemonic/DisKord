package uselessmnemonic.diskord.zlib

class ZlibContext {
    private var flag = 0
    private val crc = CRC()
    private var remaining = 0L
    private val buf = ByteArray(512)
}
