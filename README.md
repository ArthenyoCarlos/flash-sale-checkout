# 🛒 Flash Sale Checkout

> Simulação de um checkout de Black Friday: **100 unidades em estoque, milhares de compradores ao mesmo tempo**. O objetivo é simples de explicar e difícil de fazer: **não vender mais do que existe**.

![Status](https://img.shields.io/badge/status-em%20constru%C3%A7%C3%A3o-yellow)
![Etapa](https://img.shields.io/badge/etapa-1%20de%205-blue)

---

## 📌 O problema

Toda compra passa por três passos: **ler o estoque**, **verificar se ainda tem** e **diminuir uma unidade**.

Com um cliente por vez, isso funciona. Mas quando milhares de requisições chegam no mesmo instante, duas delas podem ler "resta 1 unidade" ao mesmo tempo, ambas acreditam que podem vender, e o sistema vende 2 produtos tendo apenas 1.

Esse comportamento se chama **condição de corrida** (*race condition*) e é o que causa o famoso **overselling**: vender mais do que se tem em estoque.

Este projeto reproduz o problema de propósito e depois resolve, etapa por etapa, medindo o resultado de cada solução com testes de carga.

---

## 🧰 Stack

| Tecnologia | Papel no projeto |
|---|---|
| **Java 25 LTS + Spring Boot** | API de vendas |
| **PostgreSQL** | Banco de dados de produtos e pedidos |
| **Redis** | Reserva de estoque em memória |
| **RabbitMQ** | Fila para processar pedidos sem sobrecarregar o banco |
| **Docker / Docker Compose** | Ambiente completo com um comando |
| **k6** | Testes de carga e coleta de métricas |

---

## 🗺️ Etapas

| # | Etapa | O que acontece | Status |
|---|---|---|---|
| 1 | **O bug** | Implementação ingênua: o sistema vende mais do que tem em estoque | 🔄 Em andamento |
| 2 | **Lock no banco** | Correção com lock otimista (e comparação com outras abordagens) e por que ele sofre sob carga | ⏳ Pendente |
| 3 | **Reserva no Redis** | Estoque controlado em memória com operações atômicas | ⏳ Pendente |
| 4 | **Fila no RabbitMQ** | Pedidos processados de forma assíncrona, no ritmo que o banco aguenta | ⏳ Pendente |
| 5 | **Resultados** | Comparação final dos números: antes × depois | ⏳ Pendente |

> Cada etapa terá uma **tag no Git** para que seja possível ver o código exatamente como estava naquela fase.

---

## 📊 Resultados

Todos os testes usam o mesmo cenário: **100 unidades em estoque**, mesma carga e mesma máquina.

| Etapa | Requisições/s | Tempo p95 | Taxa de erro | Vendas além do estoque |
|---|---|---|---|---|
| 1. Ingênua | — | — | — | — |
| 2. Lock no banco | — | — | — | — |
| 3. Redis | — | — | — | — |
| 4. Redis + RabbitMQ | — | — | — | — |

*Tabela preenchida conforme as etapas forem concluídas.*

---

## ▶️ Como rodar

*Em breve: instruções para subir o ambiente completo com Docker Compose e executar os testes de carga.*

---

## 💡 O que aprendi

*Esta seção será preenchida ao final de cada etapa.*

- **Etapa 1:** —
- **Etapa 2:** —
- **Etapa 3:** —
- **Etapa 4:** —
- **Etapa 5:** —

---

## 📝 Série de posts

Cada etapa deste projeto vira um post contando o problema, a solução e os números.

1. *Vendi 130 PS5 tendo 100 em estoque* — em breve
2. *Lock otimista: resolve, mas a que custo?* — em breve
3. *Reserva de estoque no Redis* — em breve
4. *Fila no RabbitMQ para proteger o banco* — em breve
5. *De X para Y requisições por segundo* — em breve

---

## 👤 Autor

**Arthenyo**

[LinkedIn](#) · [GitHub](#)

---

## 📄 Licença

Distribuído sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.