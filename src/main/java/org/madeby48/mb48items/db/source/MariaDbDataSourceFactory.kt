package org.madeby48.mb48items.db.source

import com.zaxxer.hikari.HikariConfig
import org.madeby48.mb48items.config.StorageConfig

class MariaDbDataSourceFactory(configuration: StorageConfig) : HikariDataSourceFactory(configuration) {

    override val defaultPort: String
        get() = "3306"

    override fun configure(config: HikariConfig, address: String, port: String, databaseName: String, username: String, password: String) {
        config.dataSourceClassName = "org.mariadb.jdbc.MariaDbDataSource"
        config.addDataSourceProperty("serverName", address)
        config.addDataSourceProperty("port", port)
        config.addDataSourceProperty("databaseName", databaseName)
        config.username = username
        config.password = password
    }

    override fun setProperties(config: HikariConfig, properties: MutableMap<String, String>) {
        config.addDataSourceProperty("properties", properties.entries.joinToString(";") { (key, value) -> "$key=$value" })
    }
}