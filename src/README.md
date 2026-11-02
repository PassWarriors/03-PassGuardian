# Source Code ☕

Pasta que contém o código-fonte Java do projeto **PassGuardian**.

## 📁 Estrutura

```text
src/
└── br/
    └── edu/
        ├── principal/
        │   └── Principal.java
        │
        ├── Swing/
        │   └── TelaPrincipal.java
        │
        ├── Hash/
        │   └── HashUtil.java
        │
        └── API/
            └── PwnedPasswords.java
```

## 🧩 Organização do código

O projeto utiliza uma estrutura dividida em classes com responsabilidades específicas:

| Pacote | Classe | Responsabilidade |
|---|---|---|
| `principal` | `Principal` | Inicialização do programa. |
| `Swing` | `TelaPrincipal` | Interface gráfica utilizando Java Swing. |
| `Hash` | `HashUtil` | Cálculo do hash SHA-1. |
| `API` | `PwnedPasswords` | Comunicação com a API Have I Been Pwned e consulta de ocorrências. |

## ⚙️ Funcionamento

O fluxo de execução do programa é:

1. `Principal` inicia a aplicação.
2. `TelaPrincipal` cria a interface gráfica.
3. `HashUtil` calcula o hash SHA-1 da senha informada.
4. `PwnedPasswords` consulta a API utilizando o sistema de *range query*.
5. O resultado retorna à interface e é apresentado ao usuário.

## 🛠️ Tecnologias

- Java
- Java Swing
- Java HTTP Client
- SHA-1
- Have I Been Pwned API

---

*Código-fonte do PassGuardian. Projeto educacional desenvolvido para praticar programação Java, organização de classes, hashing e comunicação HTTP.*