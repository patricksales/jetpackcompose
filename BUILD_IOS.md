# 📱 Gerando Framework iOS sem Loop Infinito

## ⚠️ Problema Resolvido
Se você estava tendo problemas com geração contínua de arquivos Xcode, o problema foi identificado e corrigido.

## ✅ Solução: Use Estes Comandos

### Opção 1: Build completo do XCFramework (Recomendado)
```bash
./gradlew :composeApp:bundleReleaseXCFramework
```

### Opção 2: Build otimizado (mais rápido)
```bash
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

### Opção 3: Build para dispositivo real
```bash
./gradlew :composeApp:linkReleaseFrameworkIosArm64
```

### Opção 4: Limpar cache e reconstruir
```bash
./gradlew clean :composeApp:bundleReleaseXCFramework
```

## 📍 Onde encontrar os arquivos gerados

Após executar qualquer comando acima, o framework estará em:
```
composeApp/build/XCFrameworks/release/ComposeApp.xcframework
```

ou para debug:
```
composeApp/build/XCFrameworks/debug/ComposeApp.xcframework
```

## 🎯 Integração com Xcode

1. Abra seu projeto em `iosApp/iosApp.xcodeproj`
2. No Xcode, vá em: **Build Phases** → **Link Binary with Libraries**
3. Adicione manualmente ou use CocoaPods para integrar o framework

## ⚙️ Configuração via Gradle.properties (Para evitar loops)

Se quiser desabilitar processamento automático, adicione ao `gradle.properties`:

```ini
# Evita problemas com processamento contínuo
org.gradle.parallel=true
org.gradle.workers.max=4
kotlin.mpp.enableCInteropCommonization=true
```

## 🔍 Debugging

Se ainda tiver problemas:

```bash
# Limpar todos os caches
./gradlew clean

# Sincronizar Gradle
./gradlew --refresh-dependencies

# Verificar qual tarefa está causando problemas
./gradlew :composeApp:tasks | grep -i xc
```

## 📝 Exemplo de Uso em CI/CD

Para automatizar em pipelines (GitHub Actions, GitLab CI, etc):

```bash
#!/bin/bash
set -e  # Para na primeira falha

# Limpar
./gradlew clean

# Build framework
./gradlew :composeApp:bundleReleaseXCFramework

# Verificar resultado
if [ -d "composeApp/build/XCFrameworks/release/ComposeApp.xcframework" ]; then
    echo "✅ Framework gerado com sucesso!"
else
    echo "❌ Erro ao gerar framework"
    exit 1
fi
```

## ⏹️ Para parar um build em andamento

No terminal:
- **macOS/Linux**: `Ctrl + C`
- **Windows**: `Ctrl + C`

No Android Studio:
- Clique no botão vermelho de **Stop** na janela de build

