plugins {
    id("com.android.application") version "9.0.0" apply false
    id("com.android.library") version "9.0.0" apply false
    kotlin("android") version "2.2.20" apply false
    kotlin("kapt") version "2.2.20" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

ext {
    set("compose_version", "1.6.0")
    set("kotlin_version", "2.2.20")
}
