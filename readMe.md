# 📌 API Registration

## 📖 Sobre
**API Registration** é uma aplicação **REST** desenvolvida em **Java com Spring Boot**, utilizando **PostgreSQL** como banco de dados.  
O projeto foi **dockerizado** para simplificar a configuração do banco, permitindo que o PostgreSQL seja executado em um container Docker isolado, garantindo consistência entre ambientes de desenvolvimento e produção.

A API permite que os usuários se **cadastrem** e gerencia as informações de usuários de forma segura e eficiente.

## 🛠 Tecnologias

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Docker Compose](https://img.shields.io/badge/Docker_Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/compose/)

## ⚙️ Pré-requisitos
Antes de rodar o projeto, você precisa ter instalado em sua máquina:
- [Java 21+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) + [Docker Compose](https://docs.docker.com/compose/)

---

### 1. Rodando com **Docker Compose**
O jeito mais simples: subir API e banco juntos em containers.

```bash
# Clonar o repositório
git clone https://github.com/Gustavo-Vinicius-Santana/registration-api
cd rpg-helper-api

# Subir os containers
docker compose up -d --build

# Rodar a API no maven
./mvnw spring-boot:run
```
➡️ A API ficará disponível em:
```arduino
http://localhost:8080
```

---