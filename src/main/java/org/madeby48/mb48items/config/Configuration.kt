package org.madeby48.mb48items.config

import net.kyori.adventure.serializer.configurate4.ConfigurateComponentSerializer
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.spongepowered.configurate.ConfigurationOptions
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File
import java.io.IOException

@JvmRecord
@ConfigSerializable
data class Configuration(
    val storage: StorageConfig = StorageConfig(),
    val colours: Colours = Colours(),
    val maxRequests: Int = 5,
    val pageSize: Int = 5,
    val dateFormat: String = "dd/MM/yyyy HH:mm:ss"
) {

    @JvmRecord
    @ConfigSerializable
    data class Colours(val online: TextColor = NamedTextColor.GREEN, val offline: TextColor = NamedTextColor.RED)

    companion object {

        @JvmField
        val OPTIONS: ConfigurationOptions = ConfigurationOptions.defaults().serializers {
            it.registerAll(ConfigurateComponentSerializer.builder()
                .scalarSerializer(LegacyComponentSerializer.builder()
                    .character(LegacyComponentSerializer.AMPERSAND_CHAR)
                    .extractUrls()
                    .hexColors()
                    .build())
                .outputStringComponents(true)
                .build()
                .serializers())
            it.registerAnnotatedObjects(objectMapperFactory())
        }

        @JvmStatic
        fun load(file: File): Configuration {
            val loader = YamlConfigurationLoader.builder().file(file).defaultOptions(OPTIONS).build()
            val node = loader.load()
            val config = node.get<Configuration>() ?: throw IOException("Unable to load configuration!")
            loader.save(node)
            return config
        }

    }

}
