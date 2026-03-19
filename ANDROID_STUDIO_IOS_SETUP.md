# 🛠️ Android Studio - Gerando iOS sem Loop Infinito

## ✅ Problema Resolvido!

Removi a configuração que estava causando loops. Agora você pode gerar os arquivos Xcode de forma segura.

## 🚀 3 Formas de Gerar no Android Studio

### Método 1: Usar o Script (Mais Fácil)
```bash
chmod +x build-ios.sh
./build-ios.sh
```

Ou com limpeza:
```bash
./build-ios.sh clean
```

### Método 2: Terminal do Android Studio
1. Abra **View** → **Tool Windows** → **Terminal**
2. Copie e cole um dos comandos:

#### Build rápido (Debug):
```bash
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

#### Build completo (Release):
```bash
./gradlew :composeApp:bundleReleaseXCFramework
```

#### Build para dispositivo real:
```bash
./gradlew :composeApp:linkReleaseFrameworkIosArm64
```

### Método 3: Gradle Window (GUI)
1. Clique em **Gradle** (lado direito)
2. Navegue até: `composeApp` → `Tasks` → `build`
3. Procure por uma tarefa com `iOS` ou `Framework` no nome
4. Clique duas vezes para executar

## ⏱️ Quanto Tempo Leva?

- **Primeira vez**: 3-5 minutos (compila tudo)
- **Builds subsequentes**: 30-60 segundos (cache)
- **Com `--parallel`**: Até 2x mais rápido

## ⚙️ Se Precisar Parar um Build

**No Terminal:**
- Pressione `Ctrl + C`

**No Android Studio:**
- Clique no botão vermelho de **Stop** na aba Build

## 📊 Monitorar o Progresso

Para ver detalhes do que está acontecendo:

```bash
./gradlew :composeApp:bundleReleaseXCFramework --info
```

Para ver ainda mais detalhes (Debug):
```bash
./gradlew :composeApp:bundleReleaseXCFramework --debug
```

## 🔍 Troubleshooting

### Build fica pendurado?
```bash
# Parar todos os daemons Gradle
./gradlew --stop

# Depois tente novamente
./gradlew :composeApp:bundleReleaseXCFramework
```

### Erro de memória?
Edite `gradle.properties` e aumente:
```ini
org.gradle.jvmargs=-Xmx6144M -Dfile.encoding=UTF-8
```

### Erro de Kotlin/Native?
```bash
./gradlew clean
./gradlew :composeApp:bundleReleaseXCFramework
```

## 📦 Resultado Final

Após a execução bem-sucedida, você terá:
```
composeApp/build/XCFrameworks/release/ComposeApp.xcframework
```

Este arquivo contém:
- ✅ Código Kotlin compilado para iOS
- ✅ Suporte para device (ARM64)
- ✅ Suporte para simulador (Arm64)
- ✅ Recursos e assets

## 🔗 Integração com Xcode

1. Abra `iosApp/iosApp.xcodeproj` no Xcode
2. Em Build Settings, procure por "Framework Search Paths"
3. Adicione o caminho: `composeApp/build/XCFrameworks/release`
4. Em Build Phases → Link Binary with Libraries, adicione `ComposeApp.xcframework`

## 💡 Dica Pro

Para automatizar a cada build do Xcode:
1. No Xcode, vá em **Product** → **Scheme** → **Edit Scheme**
2. Na aba **Pre-actions**, adicione um script:
```bash
cd "${PROJECT_DIR}/.."
./gradlew :composeApp:bundleReleaseXCFramework
```

Assim, toda vez que você compilar no Xcode, o framework Kotlin é atualizado automaticamente!

