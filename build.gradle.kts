plugins {
    id("com.android.application") version "8.13.2" apply false
    id("com.android.library") version "8.13.2" apply false
    kotlin("android") version "1.9.21" apply false
    kotlin("kapt") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

ext {
    set("compose_version", "1.6.0")
    set("kotlin_version", "1.9.21")
}
