# ✅ Checklist - iOS XCFramework Setup

## Problema Original
- ❌ Geração de Xcode files não parava (loop infinito)

## Solução Aplicada
- ✅ Removida tarefa problemática do `build.gradle.kts`
- ✅ Configurações otimizadas em `gradle.properties`
- ✅ Adicionadas flags de segurança para evitar loops

## Arquivos Criados
- ✅ `BUILD_IOS.md` - Guia de comandos para build iOS
- ✅ `ANDROID_STUDIO_IOS_SETUP.md` - Setup no Android Studio
- ✅ `build-ios.sh` - Script automatizado
- ✅ `IOS_FRAMEWORK_GUIDE.md` - Guia geral (anterior)

## Próximos Passos

### 1️⃣ Torne o script executável
```bash
chmod +x build-ios.sh
```

### 2️⃣ Teste a geração (escolha um)
```bash
# Opção A: Via script (Recomendado)
./build-ios.sh

# Opção B: Comando direto
./gradlew :composeApp:bundleReleaseXCFramework
```

### 3️⃣ Procure o arquivo gerado
```bash
ls -la composeApp/build/XCFrameworks/release/ComposeApp.xcframework
```

### 4️⃣ Integre com Xcode (quando pronto)
- Abra `iosApp/iosApp.xcodeproj`
- Configure as referências ao framework
- Teste a compilação no Xcode

## 🔧 Configurações de Segurança Adicionadas

No `gradle.properties`:
- `org.gradle.parallel=true` - Builds mais rápidos
- `org.gradle.workers.max=4` - Limite de workers
- `kotlin.native.cacheKind=static` - Cache estável
- `kotlin.native.enableDependencyVerification=false` - Evita loops

## 📋 Comandos Rápidos

| Comando | Descrição |
|---------|-----------|
| `./build-ios.sh` | Build rápido com script |
| `./build-ios.sh clean` | Limpeza + Build |
| `./gradlew :composeApp:bundleReleaseXCFramework` | Build Release completo |
| `./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64` | Build Debug (mais rápido) |
| `./gradlew clean` | Limpar cache |
| `./gradlew --stop` | Parar daemons Gradle |

## 🚨 Se Ainda Tiver Problemas

1. **Build fica pendurado novamente?**
   ```bash
   ./gradlew --stop
   ./gradlew clean
   ./gradlew :composeApp:bundleReleaseXCFramework -x test
   ```

2. **Erro de espaço em disco?**
   - Verifique: `df -h`
   - Limpe caches: `rm -rf ~/.gradle/caches`

3. **Erro de Kotlin/Native?**
   - Instale Xcode Command Line Tools: `xcode-select --install`
   - Atualize Kotlin: edite `libs.versions.toml`

## 📞 Suporte

Se o problema persistir:
1. Cole a mensagem de erro
2. Execute: `./gradlew --version`
3. Verifique: `xcode-select -p`
4. Confirme: `java -version`

---

**Última atualização**: Março 19, 2026
**Status**: ✅ Pronto para uso

