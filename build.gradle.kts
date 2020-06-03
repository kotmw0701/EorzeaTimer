plugins {
    kotlin("jvm") version "1.3.61"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "com.kotmw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-net:commons-net:3.6")
    implementation("com.google.api-client:google-api-client:1.30.4")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.30.4")
    implementation("com.google.apis:google-api-services-sheets:v4-rev581-1.25.0")
    implementation(kotlin("stdlib-jdk8"))
}

javafx {
    version = "13"
    modules("javafx.controls", "javafx.fxml")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    mainClassName = "com.kotmw.eorzeatimer.MainKt"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.kotmw.eorzeatimer.MainKt"
    }
    from(
        configurations.compileClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}
