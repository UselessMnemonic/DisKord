package uselessmnemonic.diskord

actual object Platform {
    actual val OS: String = System.getProperty("os.name")
}
