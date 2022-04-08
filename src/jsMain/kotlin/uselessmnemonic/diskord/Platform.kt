package uselessmnemonic.diskord

actual object Platform {
    actual val OS: String = kotlinx.browser.window.navigator.platform
}
