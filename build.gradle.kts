plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version("6.1.0")
}

group = "dev.kscott"
version = "1.0.0-SNAPSHOT"

repositories {
    maven {
        name = "papermc-repo"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/kadenscott/Agate")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("default") {
            from(components["java"])
            // Include any other artifacts here, like javadocs
        }
    }

}

dependencies {
    compileOnly("org.checkerframework:checker-qual:3.5.0")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")

    implementation("com.github.stefvanschie.inventoryframework:IF:0.9.0")
    implementation("net.kyori:adventure-api:4.3.0")
    api("net.kyori:adventure-platform-bukkit:4.0.0-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT") {
        isTransitive = true
    }
}

