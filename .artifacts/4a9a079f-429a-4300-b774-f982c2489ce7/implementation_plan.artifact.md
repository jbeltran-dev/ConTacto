# Reorganización Arquitectónica y Ajustes de UI

El objetivo es mover el proyecto hacia una arquitectura profesional orientada a características (Package by Feature) y realizar ajustes en la cabecera (Header) para que sea estática y no reactiva a clics.

## User Review Required

> [!IMPORTANT]
> La reorganización de paquetes implica mover casi todos los archivos del proyecto. Esto actualizará los imports en `MainActivity` y en todos los componentes.

> [!NOTE]
> Moveré el `Header` fuera del `LazyColumn` para que quede "bloqueado" (estático) en la parte superior de la pantalla. Dado que el `Header` es alto (280dp), esto reducirá el área visible de la lista de contactos, pero asegura que las instrucciones estén siempre visibles.

## Proposed Changes

### Reorganización de Paquetes

Separaremos el código en `core` (funcionalidad compartida) y `features` (funcionalidad específica de negocio).

#### [core]
- `core/data`: [Contact.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/data/Contact.kt), [SampleContacts.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/data/SampleContacts.kt)
- `core/call`: [PhoneCaller.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/call/PhoneCaller.kt)
- `core/permissions`: [PermissionManager.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/permissions/PermissionManager.kt)
- `core/ui`: [DoubleTapGesture.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/DoubleTapGesture.kt)

#### [features/home]
- `features/home`: [HomeScreen.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/HomeScreen.kt), [HomeViewModel.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/HomeViewModel.kt)
- `features/home/components`: [Header.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/Header.kt), [BottomBar.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/BottomBar.kt), [ContactCard.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/ContactCard.kt)

### Ajustes de UI

#### [MODIFY] [Header.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/Header.kt)
- Añadir `Modifier.pointerInput(Unit) {}` al Box principal para asegurar que no capture ni procese clics.

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/adria/AndroidStudioProjects/contacto/app/src/main/java/com/example/con_tacto/ui/home/HomeScreen.kt)
- Mover el componente `Header()` del `LazyColumn` al slot `topBar` del `Scaffold` (o dentro de una `Column` fuera del scroll) para que permanezca fijo.

## Verification Plan

### Automated Tests
- Ejecutar `./gradlew assembleDebug` para verificar que todos los imports y rutas de paquetes sean correctos después del movimiento.

### Manual Verification
- Abrir la app y verificar que el Header no se desplaza al hacer scroll en la lista de contactos.
- Intentar hacer clic en el Header para confirmar que no hay interacción.
