plugins {
    id("com.android.application") version "9.0.0" apply false
    id("com.android.library") version "9.0.0" apply false
    id("com.google.devtools.ksp") version "2.3.5" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

ext {
    set("compose_version", "1.6.0")
}
