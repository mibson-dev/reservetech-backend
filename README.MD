# ReserveTech — Backend

API REST para gerenciamento de reservas de equipamentos e salas, construída com Spring Boot.

## Sobre o projeto

Sistema desenvolvido para resolver um problema real de um sistema acadêmico originalmente feito em Microsoft Access: permitir que uma única reserva inclua **múltiplos dispositivos diferentes** (ex: projetor + microfone na mesma reserva), em vez de exigir uma reserva separada para cada equipamento.

A modelagem resolve isso através de uma entidade associativa (`ItemReserva`), permitindo que cada reserva tenha uma lista de itens, cada um com seu próprio dispositivo e quantidade.

## Funcionalidades

- Autenticação via JWT, com dois perfis de usuário: **Professor** e **TI**
- Professor: cria reservas com múltiplos dispositivos, consulta suas próprias reservas (com filtro por período: hoje, semana, mês, últimos 6 meses)
- TI: gerencia dispositivos, salas e usuários (CRUD completo); aprova ou cancela reservas pendentes; gera relatórios (todas as reservas, reservas por professor, dispositivos, dispositivos em manutenção)
- Validação de disponibilidade por **quantidade em estoque**, não apenas por exclusividade — múltiplas reservas do mesmo dispositivo no mesmo horário são permitidas, desde que a soma não ultrapasse a quantidade disponível
- Controle de acesso por rota e por perfil (`@PreAuthorize` + regras no `SecurityFilterChain`)

## Tecnologias

- Java 21
- Spring Boot (Web, Data JPA, Security, Validation)
- PostgreSQL
- JWT (jjwt)
- Lombok
- Maven

## Modelagem de dados

```
Usuario (PROFESSOR | TI)
Dispositivo (nome, quantidadeDisponivel, status)
Sala (nome, andar)
Reserva (usuario, sala, data, horarioInicio, horarioFim, status)
  └── ItemReserva (dispositivo, quantidadeReservada) — N por Reserva
```

## Como rodar localmente

1. Clone o repositório
2. Crie um banco PostgreSQL
3. Copie `.env.example` para `.env` e preencha com suas credenciais:
   ```
   DB_URL=jdbc:postgresql://localhost:5432/seu_banco
   DB_USERNAME=seu_usuario
   DB_PASSWORD=sua_senha
   JWT_SECRET=uma_chave_secreta_longa
   ```
4. Configure sua IDE para carregar o `.env` (ou exporte as variáveis manualmente)
5. Rode com Maven: `./mvnw spring-boot:run`

A aplicação sobe em `http://localhost:8080`. As tabelas são criadas automaticamente (`ddl-auto=update`).

## Principais endpoints

| Método | Rota | Acesso |
|---|---|---|
| POST | `/auth/registrar` | Público |
| POST | `/auth/login` | Público |
| GET | `/usuarios/me` | Autenticado |
| GET/POST/PUT/DELETE | `/dispositivos` | GET autenticado, demais só TI |
| GET/POST/PUT/DELETE | `/salas` | GET autenticado, demais só TI |
| POST | `/reservas` | Autenticado |
| GET | `/reservas/minhas` | Autenticado (próprias reservas) |
| GET | `/reservas` | Autenticado (visão TI) |
| PATCH | `/reservas/{id}` | Só TI (aprovar/cancelar) |
| GET | `/reservas/usuario/{id}` | Só TI |

## Projeto relacionado

Frontend: [reservetech-frontend](#) — HTML/CSS/JS puro, consumindo esta API.