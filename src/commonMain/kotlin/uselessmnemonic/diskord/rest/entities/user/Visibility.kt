package uselessmnemonic.diskord.rest.entities.user

enum class Visibility(val bit: Int) {
    None(0),
    Everyone(1)
}