package org.madeby48.mb48items.db.source

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.madeby48.mb48items.config.StorageConfig
import java.util.concurrent.TimeUnit

abstract class HikariDataSourceFactory(protected val configuration: StorageConfig) {

    protected abstract val defaultPort: String

    protected abstract fun configure(config: HikariConfig, address: String, port: String, databaseName: String, username: String, password: String)

    protected open fun overrideProperties(properties: MutableMap<String, String>) {
        properties.putIfAbsent("socketTimeout", TimeUnit.SECONDS.toMillis(30).toString())
    }

    protected open fun setProperties(config: HikariConfig, properties: MutableMap<String, String>) {
        properties.forEach { config.addDataSourceProperty(it.key, it.value) }
    }

    protected open fun postInit(source: HikariDataSource) {
        // Nothing by default
    }

    fun create(): HikariDataSource {
        val config = HikariConfig()
        config.poolName = "items-hikari"

        val addressSplit = configuration.address.split(":")
        val address = addressSplit[0]
        val port = if (addressSplit.size > 1) addressSplit[1] else defaultPort
        configure(config, address, port, configuration.database, configuration.username, configuration.password)

        val properties = HashMap(configuration.pooling.properties)
        overrideProperties(properties)
        setProperties(config, properties)

        config.maximumPoolSize = configuration.pooling.maximumPoolSize
        config.minimumIdle = configuration.pooling.minimumIdle
        config.maxLifetime = configuration.pooling.maximumLifetime
        config.keepaliveTime = configuration.pooling.keepAliveTime
        config.connectionTimeout = configuration.pooling.connectionTimeout
        config.initializationFailTimeout = -1

        return HikariDataSource(config).also(::postInit)
    }
}