<h1 align="center">API-Banco-DigitalP</h1>

<p align="center">Nesta API encontrará as funções essenciais relacionadas ao gerenciamento de contas bancárias.</p>

<p align="center">
 <a href="#inicio">Inicio</a> •
 <a href="#pré-requisitos">Pré Requisitos (Instalação)</a> •
 <a href="#como-executar-o-projeto">Como Executar o protejo</a> •
 <a href="#testar-no-swagger">Testar no Swagger</a> •
 <a href="#testar-no-postman">Testar no Postman</a> •
 <a href="#tecnologias">Tecnologias</a> •
 <a href="#autor">Autor</a>
</p>

---

<br>

### 🚀 Inicio

- [x] Para abrir uma conta é necessário apenas o nome completo e CPF da pessoa, mas só é permitido uma conta por pessoa
- [x] Com essa conta é possível realizar transferências para outras contas e depositar
- [x] Não aceitamos valores negativos nas contas
- [x] Por questão de segurança cada transação de depósito não pode ser maior do que R$2.000
- [x] Este Projeto foi Documentado com Swagger para facilitar os testes

### 📋 Pré-requisitos

⚠️ As ferramentas informadas aqui foram as que eu utilizei, fique a vontade para escolher a que for mais familiar a você.

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows) para executar o projeto de maneira local; 
- [JDK 17.0.1](https://jdk.java.net/17/);
- [MySQL 8.0.27](https://dev.mysql.com/downloads/installer/);
- Opcional [MySQL Workbench](https://dev.mysql.com/downloads/installer/), caso deseje visualizar o banco de dados;
- Opcional [Postman](https://www.postman.com/downloads/) opcional caso queira visualizar com o postman.

Você também precisará fazer a seguinte configuração (siga o caminho abaixo)
- Clique com o botão direito em "Meu Computador";
- Clique em "Propriedades";
- Clique em "Configurações Avançadas do Sistema";
- Clique em "Variáveis de Ambiente";
- Copie o caminho para onde você fez o download do [JDK 17.0.1](https://jdk.java.net/17/);
- Em "Variáveis de Ambiente>Variáveis do Sistema" clique em "novo" e insira o nome da variável como sendo "JAVA_HOME" e insira o caminho do [JDK 17.0.1](https://jdk.java.net/17/) que você copiou e clique em "OK";
- Ainda em "Variáveis de Ambiente>Variáveis do Sistema" clique em "novo" e insira o nome da variável como sendo "CLASSPATH" e no valor coloque um ponto "." e depois clique em "OK";
- Em "Variáveis do Sistema" localize o "Path" e dê um duplo clique sobre ele, e adicione "%JAVA_HOME%\bin" e mova-o para cima e depois clique em "OK" e novamente em "OK";

- [x] Para validar a instalação do JDK abra o cmd e digite "java -version" e aparecerá a versão do java instalado.

### ⚙️Como Executar o protejo

```bash
# Clone este repositório
$ git clone https://github.com/ergonlima/API-Banco-Digital/tree/main/desafio-rest-api
```
- Abra o [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows) que você instalou e depois abra o projeto;
- Localize na parte direita superior lateral uma aba chamada "Maven" e a abra;
- Clique em "Reload All Maven Projects" para sincronizar e baixar as dependências do Maven
- Após isso, basta clicar na seta verde ou apertar "shift+F10" e a API já estará executando.
### Testar no Swagger

### Testar no Postaman

### Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- [JDK 17.0.1](https://jdk.java.net/17/);
- [MySQL 8.0.27](https://dev.mysql.com/downloads/installer/);
- [Postman](https://www.postman.com/downloads/)
- [Swagger](https://swagger.io/)

---

### Autor

Made by Ergon Zamarian Lima 👋😁
