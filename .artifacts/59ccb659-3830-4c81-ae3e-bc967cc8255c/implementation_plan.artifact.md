# Fix SDK Version Mismatch

The project is currently failing to build because some of the updated dependencies (Compose 1.12.0, Core-KTX 1.19.0, etc.) require the project to be compiled against Android API 37 or higher. Currently, the project is configured to use API 36.

## Proposed Changes

### [Component: Build Configuration]

#### [MODIFY] [build.gradle.kts](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/build.gradle.kts)
- Update `compileSdk` to 37.
- Update `targetSdk` to 37 to match the compilation target.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to verify that the project builds successfully with the updated SDK versions.
