# Walkthrough - Fixed Compose UI Test Dependency Resolution

I have fixed the issue where `androidx.compose.ui:ui-test-junit4` could not be resolved. The problem was that the Compose Bill of Materials (BOM) was only applied to the `implementation` configuration, leaving `androidTestImplementation` and `debugImplementation` without version information for Compose dependencies.

## Changes Made

### app module

#### [build.gradle.kts](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/build.gradle.kts)

Added the Compose BOM to the following configurations:
- `androidTestImplementation`
- `debugImplementation`

```diff
     testImplementation(libs.junit)
+    androidTestImplementation(platform(libs.androidx.compose.bom))
     androidTestImplementation(libs.androidx.compose.ui.test.junit4)
     androidTestImplementation(libs.androidx.espresso.core)
     androidTestImplementation(libs.androidx.junit)
+    debugImplementation(platform(libs.androidx.compose.bom))
     debugImplementation(libs.androidx.compose.ui.test.manifest)
     debugImplementation(libs.androidx.compose.ui.tooling)
```

## Verification Results

### Automated Tests
- Ran `:app:assembleDebug` and `:app:assembleDebugAndroidTest` successfully.
- Gradle Sync completed without errors.

> [!TIP]
> Always remember to include the `platform(libs.androidx.compose.bom)` for every configuration (`implementation`, `androidTestImplementation`, `debugImplementation`) where you use Compose libraries to ensure consistent versioning.
