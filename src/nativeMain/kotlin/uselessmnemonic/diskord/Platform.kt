package uselessmnemonic.diskord

actual object Platform {
    actual val OS: String = kotlin.native.Platform.osFamily.toString()
}
