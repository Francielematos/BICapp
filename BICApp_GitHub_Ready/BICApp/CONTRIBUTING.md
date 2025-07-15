# Contribuindo para o BIC App

Obrigado por considerar contribuir para o BIC App! Este documento fornece diretrizes para contribuições.

## Como Contribuir

### 1. Fork do Repositório
- Faça um fork do repositório para sua conta GitHub
- Clone o fork para sua máquina local

### 2. Configuração do Ambiente
- Abra o projeto no Android Studio
- Aguarde a sincronização do Gradle
- Execute o projeto para verificar se tudo está funcionando

### 3. Criando uma Branch
```bash
git checkout -b feature/nome-da-sua-feature
```

### 4. Fazendo Mudanças
- Implemente suas mudanças
- Siga as convenções de código Kotlin
- Adicione testes quando apropriado
- Mantenha commits pequenos e focados

### 5. Testando
- Execute todos os testes existentes
- Teste sua funcionalidade em diferentes dispositivos
- Verifique se não há regressões

### 6. Commit e Push
```bash
git add .
git commit -m "feat: adiciona nova funcionalidade X"
git push origin feature/nome-da-sua-feature
```

### 7. Pull Request
- Abra um Pull Request no GitHub
- Descreva claramente as mudanças
- Referencie issues relacionadas

## Convenções de Código

### Kotlin
- Use convenções padrão do Kotlin
- Mantenha funções pequenas e focadas
- Use nomes descritivos para variáveis e funções

### Commits
Use o padrão Conventional Commits:
- `feat:` para novas funcionalidades
- `fix:` para correções de bugs
- `docs:` para documentação
- `style:` para formatação
- `refactor:` para refatoração
- `test:` para testes

### Estrutura de Arquivos
- Mantenha a organização de pacotes existente
- Coloque novos arquivos nos pacotes apropriados
- Atualize documentação quando necessário

## Reportando Bugs

### Antes de Reportar
- Verifique se o bug já foi reportado
- Teste na versão mais recente
- Colete informações sobre o dispositivo

### Informações Necessárias
- Versão do Android
- Modelo do dispositivo
- Passos para reproduzir
- Comportamento esperado vs atual
- Screenshots ou logs quando relevante

## Solicitando Funcionalidades

### Antes de Solicitar
- Verifique se já foi solicitada
- Considere se é adequada para o projeto
- Pense na implementação

### Informações Necessárias
- Descrição clara da funcionalidade
- Justificativa para inclusão
- Possível implementação
- Impacto em funcionalidades existentes

## Processo de Review

### O que Verificamos
- Funcionalidade correta
- Qualidade do código
- Testes adequados
- Documentação atualizada
- Compatibilidade

### Tempo de Resposta
- Reviews iniciais: 2-3 dias úteis
- Feedback adicional: 1-2 dias úteis
- Merge: após aprovação e CI verde

## Configuração de Desenvolvimento

### Requisitos
- Android Studio Arctic Fox ou superior
- JDK 8 ou superior
- Android SDK API 24+
- Git

### Dependências
Todas as dependências estão no `build.gradle`:
- Kotlin
- Android Jetpack
- Room Database
- Hilt
- iText7
- Material Components

### Executando Testes
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Código de Conduta

### Nossos Padrões
- Seja respeitoso e inclusivo
- Aceite críticas construtivas
- Foque no que é melhor para a comunidade
- Mostre empatia com outros membros

### Comportamentos Inaceitáveis
- Linguagem ofensiva ou discriminatória
- Ataques pessoais ou políticos
- Assédio público ou privado
- Publicação de informações privadas

## Licença

Ao contribuir, você concorda que suas contribuições serão licenciadas sob a mesma licença do projeto.

## Contato

Para dúvidas sobre contribuições:
- Abra uma issue no GitHub
- Entre em contato através de [CONTATO]

---

Obrigado por contribuir para o BIC App!

