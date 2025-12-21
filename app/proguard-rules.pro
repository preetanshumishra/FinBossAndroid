# Retrofit rules
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**

# Gson rules
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }
-dontwarn com.google.gson.**

# OkHttp rules
-dontwarn okhttp3.**
-dontwarn okio.**

# Hilt rules
-keep class dagger.hilt.** { *; }
-keep interface dagger.hilt.** { *; }

# Keep data classes
-keepclassmembers class * {
    *** get*();
    void set*(***);
}

# R8 specific rules
-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile
