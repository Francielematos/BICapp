# Configuração do GitHub para BIC App

Este guia explica como configurar o repositório GitHub para o projeto BIC App.

## Pré-requisitos

- Conta no GitHub
- Git instalado localmente
- Projeto BIC App já inicializado com Git

## Passos para Configuração

### 1. Criar Repositório no GitHub

1. Acesse [GitHub.com](https://github.com)
2. Clique em "New repository" ou no botão "+"
3. Configure o repositório:
   - **Repository name**: `bic-app`
   - **Description**: `Aplicativo Android para preenchimento de questionários BIC e geração de relatórios PDF`
   - **Visibility**: Public ou Private (conforme sua preferência)
   - **NÃO** marque "Add a README file" (já temos um)
   - **NÃO** marque "Add .gitignore" (já temos um)
   - **NÃO** marque "Choose a license" (já temos um)
4. Clique em "Create repository"

### 2. Conectar Repositório Local ao GitHub

Após criar o repositório no GitHub, você receberá uma URL. Execute os comandos abaixo no terminal, dentro da pasta do projeto:

```bash
# Adicionar o repositório remoto
git remote add origin https://github.com/SEU_USUARIO/bic-app.git

# Verificar se o remote foi adicionado
git remote -v

# Fazer push do código para o GitHub
git push -u origin main
```

**Substitua `SEU_USUARIO` pelo seu nome de usuário do GitHub.**

### 3. Verificar Upload

1. Acesse seu repositório no GitHub
2. Verifique se todos os arquivos foram enviados
3. Confirme se o README.md está sendo exibido corretamente

## Estrutura do Repositório

Após o upload, seu repositório terá a seguinte estrutura:

```
bic-app/
├── .gitignore              # Arquivos ignorados pelo Git
├── LICENSE                 # Licença MIT
├── README.md              # Documentação principal
├── CONTRIBUTING.md        # Guia de contribuição
├── SETUP_GITHUB.md       # Este arquivo
├── app/                   # Código fonte do aplicativo
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/bicapp/
│       └── res/
├── build.gradle           # Configuração do projeto
├── gradle.properties      # Propriedades do Gradle
└── settings.gradle        # Configurações do Gradle
```

## Configurações Recomendadas

### Branch Protection

Para projetos em equipe, configure proteção da branch main:

1. Vá em Settings > Branches
2. Clique em "Add rule"
3. Configure:
   - Branch name pattern: `main`
   - ✅ Require pull request reviews before merging
   - ✅ Require status checks to pass before merging

### Issues e Projects

1. Habilite Issues em Settings > General
2. Considere criar um Project para organizar tarefas
3. Use labels para categorizar issues:
   - `bug` - Correções de bugs
   - `enhancement` - Melhorias
   - `feature` - Novas funcionalidades
   - `documentation` - Documentação

### Actions (CI/CD)

Considere configurar GitHub Actions para:
- Build automático do APK
- Execução de testes
- Análise de código
- Deploy automático

## Comandos Git Úteis

### Workflow Básico
```bash
# Verificar status
git status

# Adicionar mudanças
git add .

# Fazer commit
git commit -m "feat: adiciona nova funcionalidade"

# Enviar para GitHub
git push origin main
```

### Trabalhando com Branches
```bash
# Criar nova branch
git checkout -b feature/nova-funcionalidade

# Trocar de branch
git checkout main

# Fazer merge
git merge feature/nova-funcionalidade

# Deletar branch
git branch -d feature/nova-funcionalidade
```

### Sincronização
```bash
# Baixar mudanças do GitHub
git pull origin main

# Verificar repositórios remotos
git remote -v
```

## Colaboração

### Para Contribuidores

1. Faça fork do repositório
2. Clone seu fork
3. Crie uma branch para sua feature
4. Faça suas mudanças
5. Teste suas mudanças
6. Faça commit e push
7. Abra um Pull Request

### Para Mantenedores

1. Revise Pull Requests
2. Execute testes
3. Faça merge quando aprovado
4. Mantenha documentação atualizada

## Segurança

### Informações Sensíveis

- **NUNCA** commite senhas, chaves API ou tokens
- Use variáveis de ambiente para dados sensíveis
- Configure `.gitignore` adequadamente
- Revise commits antes do push

### Keystore (Para Release)

- **NÃO** commite arquivos de keystore
- Use GitHub Secrets para CI/CD
- Mantenha backup seguro do keystore

## Troubleshooting

### Erro de Autenticação

Se encontrar erro de autenticação:

1. Configure Personal Access Token
2. Use HTTPS com token ou SSH com chave
3. Verifique permissões do repositório

### Conflitos de Merge

Para resolver conflitos:

1. `git pull origin main`
2. Resolva conflitos manualmente
3. `git add .`
4. `git commit -m "resolve merge conflicts"`
5. `git push origin main`

## Próximos Passos

1. Configure o repositório no GitHub
2. Convide colaboradores (se necessário)
3. Configure branch protection
4. Crie issues para próximas funcionalidades
5. Configure CI/CD (opcional)

---

Para dúvidas, consulte a [documentação oficial do GitHub](https://docs.github.com/).

