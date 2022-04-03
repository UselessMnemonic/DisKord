package uselessmnemonic.diskord.rest.entities.channel

interface IThreadMetadata {
    val archived: Boolean
    val autoArchiveDuration: Int
    val archiveTimestamp: String
    val locked: Boolean
    val invitable: Boolean?
}