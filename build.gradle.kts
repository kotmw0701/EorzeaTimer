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
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("commons-net:commons-net:3.6")
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
        configurations.compile.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}
