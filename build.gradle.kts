import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20-RC"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.glovoapp.semantic-versioning") version "1.1.10"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") //  For Paper API
    maven("https://repo.aikar.co/content/groups/aikar/") //  For ACF
    maven("https://oss.sonatype.org/content/groups/public/") //
    maven("https://repo.codemc.org/repository/maven-public/") // Item-NBT Api

}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.10.0-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    // BomBardyGamer's Configuration Classes dependencies
    implementation("org.spongepowered:configurate-core:4.1.2")
    implementation("org.spongepowered:configurate-yaml:4.1.2")
    implementation("org.spongepowered:configurate-extra-kotlin:4.1.2")
    implementation("net.kyori", "adventure-serializer-configurate4", "4.11.0") {
        exclude("net.kyori", "adventure-api")
    }

    // More of BomBardyGamer's Configuration Classes dependencies
//    implementation("com.zaxxer:HikariCP:5.0.1") { exclude("org.slf4j", "slf4j-api") } Unused, but here for reference.
    implementation("org.jetbrains.exposed:exposed-core:0.40.1") { exclude("org.slf4j", "slf4j-api") }
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.0.6")
    runtimeOnly("com.h2database:h2:2.1.214")
}

group = "org.madeby48"
version = "0.1-SNAPSHOT"

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    shadowJar {
        relocate("org.spongepowered.configurate", "org.madeby48.items.configurate")
        relocate(
                "net.kyori.adventure.serializer.configurate4",
                "org.madeby48.items.adventure.serializer.configurate4"
        )
        relocate("co.aikar", "org.madeby48.items.acf")
        relocate("com.zaxxer.hikari", "org.madeby48.items.hikari")
        relocate("org.jetbrains.exposed", "org.madeby48.items.exposed")
        relocate("org.mariadb.jdbc", "org.madeby48.items.mariadb")
        relocate("org.h2", "org.madeby48.items.h2")
        relocate("io.leangen.geantyref", "org.madeby48.items.geantyref")
        relocate("org.yaml.snakeyaml", "org.madeby48.items.snakeyaml")
        transform(ServiceFileTransformer::class.java)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
//processResources {
//    filter {
//        it.replace("${version}", version.toString())
//    }
//}
