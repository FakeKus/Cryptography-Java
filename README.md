# Cryptography-Java

**Cryptography-Java**, é um projeto desenvolvido para ilustrar conceitos de criptografia utilizando Java. Este repositório explora algoritmos e técnicas criptográficas.

## 📜 Descrição

Este projeto tem como objetivo:
- Demonstrar o uso de algoritmos de criptografia simétrica.
- Fornecer uma base para aplicações envolvendo segurança de informações.

## 🚀 Funcionalidades

- 🔒 **Criptografia simétrica** (com AES).
- 🧾 **Hashing seguro** (SHA-256).
- 📦 **Exemplo prático de aplicação**.

## 🛠️ Tecnologias Utilizadas

- **Java 21** - Linguagem de programação base.
- **Java Cryptography Architecture (JCA)** - API de criptografia da biblioteca padrão Java.
- **SQLite** - Utilizado para criação de um banco de dados local.

## ⚙️ Instalação e Configuração

### Pré-requisitos
- Instale o **Java JDK 21** ou superior.
- Configure as variáveis de ambiente.

### Passos para Clonar e Executar
1. Clone o repositório
   ```bash
   git clone https://github.com/FakeKus/Cryptography-Java.git
   ```
2. Importe o projeto em sua IDE preferida (Ex.: VisualStudio, IntelliJ IDEA).
3. Compile e execute os arquivos conforme necessário.

## 📂 Estrutura do Projeto

```plaintext
Cryptography-Java/
├── src/                    
│   └── main/               
│       ├── java/
│       │   └── org.alexandre/
│       │       ├── connections/                    #Gerencia conexões com o banco de dados.
│       │       │   └── ConnectionFactory.java         #Classe responsável por criar e gerenciar conexões com o banco de dados.
│       │       │   
│       │       ├── dao/                            #Camada de acesso a dados (Data Access Object).
│       │       │   └── ClientDAO.java                 #Classe que gerencia operações de CRUD (Create, Read, Update, Delete) para os clientes.
│       │       │   
│       │       ├── forms/                          #Contém classes que representam entidades do sistema.
│       │       │   └── Client.java                    #Representa um cliente no sistema.
│       │       │          
│       │       ├── helper/                         #Contém classes auxiliares.
│       │       │   └── CryptographyManager.java       #Responsável por funcionalidades de criptografia, como encriptação e decriptação de dados.
│       │       │
│       │       └── Main.java                       #Classe principal do projeto.
│       │
│       └── resources/                          #Diretório para arquivos de recursos do projeto.
│           └── database/                           #Contém arquivos de banco de dados.
│               └── client.db                           #Banco de dados SQLite usado para armazenar informações dos clientes.
│
└── README.md                       #Arquivo de documentação do projeto.
```

## 📖 Referências

- [Documentação do Java Cryptography Architecture (JCA)](https://docs.oracle.com/en/java/javase/21/security/java-cryptography-architecture-jca-reference-guide.html)

## 📋 Contribuições

Contribuições são bem-vindas! Para contribuir:
1. Faça um fork do projeto.
2. Crie uma branch com sua feature (`git checkout -b minha-feature`).
3. Faça o commit de suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Envie para o branch principal (`git push origin minha-feature`).
5. Abra um Pull Request.

---

### 🌟 Obrigado por explorar o Cryptography-Java!
Sinta-se à vontade para abrir issues, sugerir melhorias e colaborar para expandir este projeto. 🚀# Cryptography-Java
