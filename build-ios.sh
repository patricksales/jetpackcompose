#!/bin/bash

# Script para gerar iOS XCFramework sem parar
# Uso: chmod +x build-ios.sh && ./build-ios.sh

set -e  # Parar se houver erro

echo "🔨 Iniciando build do iOS Framework..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Opções
if [ "$1" = "clean" ]; then
    echo "🗑️  Limpando arquivos antigos..."
    ./gradlew clean
    echo "✅ Limpeza concluída!"
fi

echo ""
echo "🏗️  Construindo XCFramework..."
echo ""

# Build do framework
./gradlew :composeApp:bundleReleaseXCFramework -x test

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Verificação
if [ -d "composeApp/build/XCFrameworks/release/ComposeApp.xcframework" ]; then
    echo "✅ Framework gerado com SUCESSO!"
    echo ""
    echo "📁 Localização:"
    echo "   composeApp/build/XCFrameworks/release/ComposeApp.xcframework"
    echo ""
    echo "📊 Tamanho:"
    du -sh composeApp/build/XCFrameworks/release/ComposeApp.xcframework
    echo ""
    echo "🎉 Pronto para usar no Xcode!"
else
    echo "❌ Erro ao gerar o framework"
    exit 1
fi

