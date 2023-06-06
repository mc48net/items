package org.madeby48.mb48items.config

import org.madeby48.mb48items.db.source.HikariDataSourceFactory
import org.madeby48.mb48items.db.source.MariaDbDataSourceFactory
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Setting

@JvmRecord
@ConfigSerializable
data class StorageConfig(
    val type: Type = Type.MARIADB,
    val address: String = "localhost",
    val database: String = "items",
    val username: String = "modrequests",
    val password: String = "modrequests",
    val pooling: Pooling = Pooling()
) {

    fun dataSourceFactory(): HikariDataSourceFactory = when (type) {
        Type.MARIADB -> MariaDbDataSourceFactory(this)
        Type.MYSQL, Type.POSTGRESQL -> error("Not implemented yet")
    }

    enum class Type {

        MYSQL,
        MARIADB,
        POSTGRESQL
    }

    @JvmRecord
    @ConfigSerializable
    data class Pooling(
        val maximumPoolSize: Int = 10,
        val minimumIdle: Int = 10,
        val maximumLifetime: Long = 1800000L,
        @Setting("keepalive-time")
        val keepAliveTime: Long = 0L,
        val connectionTimeout: Long = 5000L,
        val properties: Map<String, String> = mapOf("useUnicode" to "true", "characterEncoding" to "utf8")
    )
}