package uselessmnemonic.diskord.rest.entities.user

enum class UserFlags(val bit: Int) {
    None(0),
    STAFF(1 shl 0),
    PARTNER(1 shl 1),
    HYPESQUAD(1 shl 2),
    BUG_HUNTER_LEVEL_1(1 shl 3),
    HYPESQUAD_ONLINE_HOUSE_1(1 shl 6),
    HYPESQUAD_ONLINE_HOUSE_2(1 shl 7),
    HYPESQUAD_ONLINE_HOUSE_3(1 shl 8),
    PREMIUM_EARLY_SUPPORTER(1 shl 9),
    TEAM_PSEUDO_USER(1 shl 10),
    BUG_HUNTER_LEVEL_2(1 shl 14),
    VERIFIED_BOT(1 shl 16),
    VERIFIED_DEVELOPER(1 shl 17),
    CERTIFIED_MODERATOR(1 shl 18),
    BOT_HTTP_INTERACTIONS(1 shl 19),
}