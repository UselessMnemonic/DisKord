package uselessmnemonic.diskord.rest.entities.auditlog

import kotlinx.serialization.SerialName

enum class AuditLogChangeKey {
    afk_channel_id,
    afk_timeout,
    allow,
    application_id,
    archived,
    asset,
    auto_archive_duration,
    available,
    avatar_hash,
    banner_hash,
    bitrate,
    channel_id,
    code,
    color,
    deaf,
    default_auto_archive_duration,
    default_message_notifications,
    deny,
    description,
    discovery_splash_hash,
    enable_emoticons,
    expire_behavior,
    expire_grace_period,
    explicit_content_filter,
    format_type,
    guild_id,
    hoist,
    icon_hash,
    id,
    inviter_id,
    locked,
    max_age,
    max_uses,
    mentionable,
    mfa_level,
    mute,
    @SerialName("name")
    Name,
    nick,
    nsfw,
    owner_id,
    permission_overwrites,
    permissions	,
    position,
    preferred_locale,
    privacy_level,
    prune_delete_days,
    public_updates_channel_id,
    rate_limit_per_user,
    region,
    rules_channel_id,
    splash_hash,
    system_channel_id,
    tags,
    temporary,
    topic,
    type,
    unicode_emoji,
    user_limit,
    uses,
    vanity_url_code,
    verification_level,
    widget_channel_id,
    widget_enabled,
    add,
    remove
}