
# Jantar dos Filósofos - Solução com Threads e Semáforos

## Descrição

Este projeto implementa a **solução para o problema clássico do Jantar dos Filósofos**, utilizando **Threads** e **Semáforos** em Java. O problema envolve cinco filósofos sentados ao redor de uma mesa, onde cada um precisa de dois garfos para comer. Eles alternam entre pensar e comer, e o objetivo é garantir que os filósofos possam comer sem bloqueios ou deadlocks.

## Objetivo

- **Cinco filósofos** alternam entre **pensar** e **comer**.
- Para comer, cada filósofo precisa pegar dois garfos (um à esquerda e outro à direita).
- A solução usa **Threads** para representar os filósofos e **Semáforos** para controlar o acesso aos garfos, evitando **deadlocks**.

## Como Funciona

1. **Filósofos**: Cada filósofo é representado por uma Thread que alterna entre os estados de **pensando** e **comendo**.
2. **Garfos**: São representados por objetos que utilizam **Semáforos** para garantir que apenas um filósofo possa pegar o garfo por vez.
3. **Sincronização**: Cada filósofo tenta pegar seus dois garfos (esquerdo e direito). Se não conseguir, libera o garfo que já pegou e tenta novamente.

## Componentes

- **Classe `Garfo`**: Representa um garfo com um semáforo para controlar o acesso exclusivo a ele.
- **Classe `Filosofo`**: Representa cada filósofo como uma Thread, que tenta pegar os garfos e comer.
- **Classe `JantarFilosofos`**: A classe principal, que cria os garfos e filósofos, e inicia as Threads.

## Como Rodar

1. Compile o código em Java.
2. Execute a classe `JantarFilosofos` para iniciar a simulação.
3. Os filósofos começarão a pensar e comer de forma alternada.

## Exemplo de Saída

```
Filósofo 1 está pensando.
Filósofo 2 está pensando.
Filósofo 3 está pensando.
Filósofo 4 está pensando.
Filósofo 5 está pensando.
Filósofo 1 pegou o garfo esquerdo.
Filósofo 1 pegou o garfo direito e está comendo.
Filósofo 1 terminou de comer.
...
```
