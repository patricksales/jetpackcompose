# iOS Xcode Framework Generation Guide

## Overview
Your KMP Compose project has iOS support. To generate Xcode-compatible files from Android Studio, you need to build the iOS framework.

## Quick Commands

### Option 1: Using Custom Gradle Task (Easiest)
```bash
./gradlew buildIosFramework
```

### Option 2: Direct XCFramework Build
```bash
./gradlew :composeApp:assembleXCFramework
```

### Option 3: Platform-Specific Builds
```bash
# For iOS device (ARM64)
./gradlew :composeApp:linkReleaseFrameworkIosArm64

# For iOS Simulator (ARM64)
./gradlew :composeApp:linkReleaseFrameworkIosSimulatorArm64
```

## Output Location
After running any of these commands, the framework will be located at:
```
composeApp/build/XCFrameworks/release/ComposeApp.xcframework
```

## Integration with Xcode

1. In Xcode (`iosApp/iosApp.xcodeproj`), you can reference this framework
2. The framework contains compiled Kotlin code + resources
3. It's generated for both iOS device and simulator architectures automatically

## Automating in Android Studio

### Method 1: Run via Gradle Window
- Open "Gradle" panel on the right side in Android Studio
- Navigate to: `composeApp` → `Tasks` → `other` → `buildIosFramework`
- Double-click to run

### Method 2: Create Run Configuration
1. Go to **Run** → **Edit Configurations**
2. Click **+** and select **Gradle**
3. Set:
   - **Name**: "Build iOS Framework"
   - **Gradle project**: `:composeApp`
   - **Tasks**: `buildIosFramework`
4. Click OK and run from the Run menu

### Method 3: Terminal in Android Studio
- Use the terminal at the bottom of Android Studio
- Run: `./gradlew buildIosFramework`

## After Building

Your Xcode project in `iosApp/` will be able to use the compiled framework. Make sure:
- The framework is properly linked in Xcode build settings
- CocoaPods or manual linking is configured (if using CocoaPods)
- The framework architecture matches your build target (device/simulator)

## Troubleshooting

If the build fails:
1. Ensure you have Kotlin/Native toolchain installed
2. Check that you have enough disk space
3. Run `./gradlew clean` first to clear caches
4. Make sure XCode Command Line Tools are installed: `xcode-select --install`

