# FinBoss Android

Native Android application for the FinBoss financial management ecosystem. Built with Kotlin, Jetpack Compose, and modern Android development practices.

## Overview

FinBoss Android is a native Android app providing financial management capabilities including transactions, budgets, analytics, and user authentication. The app follows the MVVM architecture pattern with Hilt for dependency injection.

## Tech Stack

- **Language:** Kotlin 1.9.21
- **UI Framework:** Jetpack Compose 1.6.0
- **Min SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34 (Android 14)
- **Dependency Injection:** Hilt 2.48.1
- **Networking:** Retrofit 2.10.0 + OkHttp 4.11.0
- **Database:** Room 2.6.1
- **Storage:** DataStore 1.0.0
- **Build System:** Gradle 8.13

## Project Structure

```
FinBossAndroid/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/preetanshumishra/FinBossAndroid/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   └── LoginScreen.kt
│   │   │   │   │   └── theme/
│   │   │   │   │       └── Theme.kt
│   │   │   │   ├── network/
│   │   │   │   │   └── ApiClient.kt
│   │   │   │   └── viewmodel/
│   │   │   ├── AndroidManifest.xml
│   │   │   └── res/
│   │   ├── androidTest/
│   │   └── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew & gradlew.bat
└── .gitignore
```

## Setup Instructions

### Prerequisites

- macOS, Windows, or Linux
- Android Studio 2023.1 or later
- Android SDK 34
- JDK 17 or later
- Gradle 8.13 or later

### Installation

1. **Clone and navigate to project:**
   ```bash
   cd FinBossAndroid
   ```

2. **Sync Gradle dependencies:**
   - Open project in Android Studio
   - Android Studio will auto-sync dependencies
   - Or run: `./gradlew build`

3. **Select target:**
   - Select "app" run configuration
   - Choose Android Emulator or physical device

4. **Run application:**
   - Press Shift+F10 (Windows/Linux) or Ctrl+R (macOS)
   - Or click Run button in Android Studio

## Build & Run

### Using Android Studio
1. Open project in Android Studio
2. Select "app" configuration
3. Click Run (Shift+F10 or Ctrl+R)

### Using Command Line

**Build Debug APK:**
```bash
./gradlew build
```

**Build Release APK:**
```bash
./gradlew assembleRelease
```

**Run on Emulator:**
```bash
./gradlew installDebug
./gradlew runDebug
```

**Build and Run:**
```bash
./gradlew build installDebug
```

## Architecture

### MVVM Pattern
- **Views:** Jetpack Compose UI screens
- **ViewModels:** State management with StateFlow
- **Models:** Data classes for API responses and local storage

### Dependency Injection
Uses Hilt for managing dependencies:
- `@HiltViewModel`: ViewModel creation and injection
- `@Inject`: Constructor injection for services
- Modules: Centralized dependency definitions

### Data Flow
1. **UI screens** trigger actions on **ViewModels**
2. **ViewModels** manage **StateFlow** for state
3. **Services** handle business logic
4. **ApiClient** manages API calls via Retrofit
5. **Room Database** provides local persistence
6. **State updates** automatically recompose UI

## Dependencies

Key libraries:
- **Hilt:** Dependency injection framework
- **Retrofit:** REST API client
- **OkHttp:** HTTP client with logging
- **Room:** Local SQLite database
- **Compose:** Modern declarative UI
- **Coroutines:** Async programming

Install/Update with:
```bash
./gradlew build
```

## Configuration

### Environment
Update backend API URL in `ApiClient.kt`:
```kotlin
const val BASE_URL = "http://10.0.2.2:5000/" // Emulator
// or
const val BASE_URL = "http://localhost:5000/" // Physical device
```

For production:
```kotlin
const val BASE_URL = "https://api.finboss.com/"
```

### Build Configuration
Minimum SDK is configured in `app/build.gradle.kts`:
```kotlin
minSdk = 24
targetSdk = 34
```

## Features

- ✅ User Authentication (Login/Register)
- ✅ Secure Token Storage (DataStore)
- ✅ API Integration (Retrofit)
- ✅ Jetpack Compose UI
- ✅ MVVM Architecture
- ✅ Hilt Dependency Injection
- ✅ Room Database
- ✅ Coroutines & Flow
- ✅ Android 7.0+ Compatibility

## Development Guidelines

### Code Style
- Use Kotlin naming conventions
- Follow Jetpack Compose best practices
- Keep composables pure and side-effect-free
- Use StateFlow for state management

### Adding Dependencies
1. Add dependency to `app/build.gradle.kts`
2. Run `./gradlew build` to sync
3. Add Hilt module if DI needed

### Testing
- Unit tests in `app/src/test/`
- Instrumented tests in `app/src/androidTest/`
- Mock services for testing ViewModels

## Troubleshooting

### Build Fails
```bash
# Clean build
./gradlew clean

# Rebuild with verbose output
./gradlew build --stacktrace

# Clear cache
rm -rf .gradle build app/build
./gradlew build
```

### Gradle Sync Issues
```bash
# Invalidate caches in Android Studio:
# File → Invalidate Caches... → Invalidate and Restart

# Or clean and rebuild
./gradlew clean build
```

### Emulator Issues
```bash
# List available emulators
emulator -list-avds

# Launch specific emulator
emulator -avd <emulator_name>

# Wipe emulator data
emulator -avd <emulator_name> -wipe-data
```

### API Connection Issues
- Verify backend is running on correct port
- Check emulator can reach host: `adb shell ping 10.0.2.2`
- For physical device, use actual IP address
- Check firewall settings

## Gradle Commands

```bash
# Build
./gradlew build                 # Build debug and release
./gradlew assembleDebug        # Build debug APK
./gradlew assembleRelease      # Build release APK

# Testing
./gradlew test                 # Run unit tests
./gradlew connectedAndroidTest # Run instrumented tests

# Cleaning
./gradlew clean                # Clean build outputs
./gradlew cleanBuildCache      # Clean build cache

# Development
./gradlew installDebug         # Install debug APK
./gradlew tasks                # List available tasks
```

## Related Projects

- **FinBossAPI** - Backend API
- **FinBossiOS** - Native iOS app
- **FinBossWeb** - React web app
- **FinBossMobile** - React Native app

## License

Proprietary - FinBoss Ecosystem
