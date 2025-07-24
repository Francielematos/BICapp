#!/bin/bash

# Script de Build para Release - BIC App
# Este script automatiza o processo de geração do bundle de release

set -e  # Parar execução em caso de erro

echo "🚀 Iniciando build de release do BIC App..."

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para imprimir mensagens coloridas
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Verificar se estamos no diretório correto
if [ ! -f "build.gradle" ]; then
    print_error "Este script deve ser executado na raiz do projeto Android"
    exit 1
fi

# Verificar se o arquivo gradlew existe
if [ ! -f "gradlew" ]; then
    print_error "Arquivo gradlew não encontrado"
    exit 1
fi

# Limpar builds anteriores
print_status "Limpando builds anteriores..."
./gradlew clean

# Verificar se keystore existe
KEYSTORE_FILE="bic-app-release-key.keystore"
if [ ! -f "$KEYSTORE_FILE" ]; then
    print_warning "Keystore não encontrado. Você precisa gerar um keystore primeiro."
    print_status "Execute o seguinte comando para gerar o keystore:"
    echo "keytool -genkey -v -keystore $KEYSTORE_FILE -alias bic-app -keyalg RSA -keysize 2048 -validity 10000"
    exit 1
fi

# Executar testes
print_status "Executando testes unitários..."
./gradlew test

if [ $? -eq 0 ]; then
    print_success "Testes unitários passaram"
else
    print_error "Testes unitários falharam"
    exit 1
fi

# Gerar bundle de release
print_status "Gerando bundle de release..."
./gradlew bundleRelease

if [ $? -eq 0 ]; then
    print_success "Bundle de release gerado com sucesso"
else
    print_error "Falha ao gerar bundle de release"
    exit 1
fi

# Verificar se o bundle foi criado
BUNDLE_PATH="app/build/outputs/bundle/release/app-release.aab"
if [ -f "$BUNDLE_PATH" ]; then
    print_success "Bundle encontrado em: $BUNDLE_PATH"
    
    # Mostrar tamanho do arquivo
    BUNDLE_SIZE=$(du -h "$BUNDLE_PATH" | cut -f1)
    print_status "Tamanho do bundle: $BUNDLE_SIZE"
else
    print_error "Bundle não foi encontrado no caminho esperado"
    exit 1
fi

# Verificar se bundletool está disponível
BUNDLETOOL="bundletool-all.jar"
if [ ! -f "$BUNDLETOOL" ]; then
    print_status "Baixando bundletool para testes..."
    wget -q https://github.com/google/bundletool/releases/latest/download/bundletool-all.jar
    
    if [ $? -eq 0 ]; then
        print_success "Bundletool baixado com sucesso"
    else
        print_warning "Falha ao baixar bundletool. Testes locais não serão executados."
    fi
fi

# Gerar APKs para teste local (se bundletool estiver disponível)
if [ -f "$BUNDLETOOL" ]; then
    print_status "Gerando APKs para teste local..."
    java -jar "$BUNDLETOOL" build-apks --bundle="$BUNDLE_PATH" --output=app-test.apks
    
    if [ $? -eq 0 ]; then
        print_success "APKs de teste gerados: app-test.apks"
        
        # Verificar se há dispositivo conectado
        if command -v adb &> /dev/null; then
            DEVICES=$(adb devices | grep -v "List of devices" | grep "device$" | wc -l)
            if [ $DEVICES -gt 0 ]; then
                print_status "Dispositivo Android detectado. Deseja instalar para teste? (y/n)"
                read -r INSTALL_CHOICE
                if [ "$INSTALL_CHOICE" = "y" ] || [ "$INSTALL_CHOICE" = "Y" ]; then
                    java -jar "$BUNDLETOOL" install-apks --apks=app-test.apks
                    if [ $? -eq 0 ]; then
                        print_success "App instalado no dispositivo para teste"
                    else
                        print_error "Falha ao instalar app no dispositivo"
                    fi
                fi
            else
                print_warning "Nenhum dispositivo Android conectado para teste"
            fi
        fi
    else
        print_warning "Falha ao gerar APKs de teste"
    fi
fi

# Resumo final
echo ""
echo "📋 RESUMO DO BUILD"
echo "=================="
print_success "Bundle de release: $BUNDLE_PATH"
print_success "Tamanho: $BUNDLE_SIZE"
print_status "Próximos passos:"
echo "  1. Teste o aplicativo em dispositivos reais"
echo "  2. Faça upload do bundle na Google Play Console"
echo "  3. Configure os metadados na Play Store"
echo "  4. Submeta para revisão"

echo ""
print_success "Build de release concluído com sucesso! 🎉"

