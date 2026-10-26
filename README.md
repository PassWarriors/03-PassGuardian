# PassGuardian 🔐

**Um verificador de senhas baseado na API Have I Been Pwned.**

O PassGuardian é um projeto desenvolvido em Java com Swing que permite consultar se uma senha aparece na base de senhas expostas conhecida pelo serviço Have I Been Pwned.

## ✨ Funcionalidades

- Interface gráfica simples para inserir uma senha.
- Cálculo local do hash SHA-1.
- Consulta à API Pwned Passwords.
- Comparação local dos hashes retornados pela API.
- Exibição da quantidade de ocorrências encontradas.


## 📋 Versão atual: V0.1.0

| Versão | Alterações |
|:---:|:---:|
| `v0.1.0` | Criação da interface gráfica e campo de senha. Implementação do cálculo SHA-1 e integração com a API Pwned Passwords. Todo o programa fica dentro da classe Principal |


## 🛠️ Tecnologias utilizadas

- **Java** — linguagem de programação.
- **Java Swing** — interface gráfica.
- **Java HTTP Client** — comunicação com a API.
- **Have I Been Pwned API** — consulta à base de senhas expostas.

## ⚙️ Como funciona?

1. O usuário digita uma senha na interface.
2. O programa calcula seu hash SHA-1 localmente.
3. Os cinco primeiros caracteres do hash são enviados à API.
4. A API retorna os sufixos correspondentes àquele prefixo.
5. O PassGuardian compara os resultados localmente e exibe a quantidade de ocorrências, quando encontrada.

Esse método segue o modelo de consulta por intervalo (*range query*) da API Pwned Passwords, evitando enviar a senha original ou o hash completo.

## ▶️ Como executar

1. Instale um JDK compatível com o projeto.
2. Abra o projeto em uma IDE Java, como o NetBeans.
3. Execute a classe `Principal`.
4. Digite uma senha de teste e clique em **Verificar**.

É necessária uma conexão com a internet para consultar a API.

## ⚠️ Limitações e segurança

- Uma senha não encontrada na base consultada não é necessariamente segura.
- O resultado indica ocorrências registradas, não o número de pessoas afetadas nem a invasão de uma conta específica.
- O projeto utiliza SHA-1 porque esse é o formato exigido pela API; SHA-1 não deve ser usado para armazenar senhas de usuários.
- A consulta depende da disponibilidade da API e de uma conexão com a internet.
- Por segurança, prefira testar com senhas fictícias e não reutilize senhas pessoais em demonstrações.

## 📚 Créditos
Materiais usados para desenvolver esse projeto

- [Have I Been Pwned](https://haveibeenpwned.com/)
- [Pwned Passwords API](https://haveibeenpwned.com/API/v3#PwnedPasswords)
- [Projeto do RogerSarmento](https://github.com/rogermsarmento/verificador-senhas-vazadas)
- ⬆_ (Projeto do professor usado para fazer o código) _⬆

---

*Projeto educacional desenvolvido para praticar Java, interfaces gráficas, hashing e requisições HTTP.*