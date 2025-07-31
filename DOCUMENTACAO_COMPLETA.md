# Documentação Completa - Padrões de Projeto

Este projeto demonstra a implementação de diversos padrões de projeto (Design Patterns) usando o contexto de um jogo de labirinto. Cada padrão é implementado com exemplos práticos que mostram tanto a versão com padrão quanto sem padrão para comparação.

## Estrutura do Projeto

```
src/
├── criacionais/          # Padrões de criação de objetos
│   ├── singleton/        # Garante uma única instância
│   ├── factorymethod/    # Cria objetos sem especificar classes concretas
│   ├── abstractfactory/  # Cria famílias de objetos relacionados
│   ├── builder/          # Constrói objetos complexos passo a passo
│   └── prototype/        # Cria objetos clonando protótipos
├── estruturais/          # Padrões de composição de objetos
│   ├── adapter/          # Adapta interfaces incompatíveis
│   └── facade/           # Simplifica interfaces complexas
└── Main.java            # Classe principal (não utilizada nos exemplos)
```

## Padrões Criacionais

### 1. Singleton
**Localização:** `src/criacionais/singleton/`

**Propósito:** Garantir que uma classe tenha apenas uma instância e fornecer um ponto de acesso global a ela.

**Implementação no projeto:**
- **Classe principal:** `Labirinto.java`
- **Características:**
  - Construtor privado
  - Variável estática para armazenar a instância única
  - Método `getInstancia()` para acesso controlado
  - Lazy initialization (criação sob demanda)

**Exemplo de uso:**
```java
// Primeira chamada cria a instância
Labirinto lab1 = Labirinto.getInstancia("clássico");

// Segunda chamada retorna a mesma instância (ignora o parâmetro)
Labirinto lab2 = Labirinto.getInstancia("encantado");

// lab1 == lab2 é true
```

**Vantagens demonstradas:**
- Controle rigoroso sobre a instanciação
- Economia de memória
- Ponto de acesso global

### 2. Factory Method
**Localização:** `src/criacionais/factorymethod/`

**Propósito:** Criar objetos sem especificar suas classes concretas, delegando a decisão para subclasses.

**Implementação no projeto:**
- **Classe abstrata:** `CriadorLabirinto.java` - Define o template
- **Classes concretas:** `CriadorLabirintoClassico.java`, `CriadorLabirintoEncantado.java`
- **Produtos:** `SalaClassica`, `SalaEncantada`, `PortaClassica`, `PortaEncantada`

**Estrutura:**
```java
// Template method na classe abstrata
public void criarLabirinto() {
    Sala sala1 = criarSala();    // Factory method
    Porta porta = criarPorta();  // Factory method
    // ... algoritmo fixo usando os objetos criados
}

// Factory methods abstratos
protected abstract Sala criarSala();
protected abstract Porta criarPorta();
```

**Vantagens demonstradas:**
- Desacoplamento entre criação e uso
- Extensibilidade (fácil adicionar novos tipos)
- Reutilização do algoritmo principal

### 3. Abstract Factory
**Localização:** `src/criacionais/abstractfactory/`

**Propósito:** Criar famílias de objetos relacionados sem especificar suas classes concretas.

**Implementação no projeto:**
- **Interface abstrata:** `FabricaLabirinto.java`
- **Fábricas concretas:** `FabricaLabirintoClassico.java`, `FabricaLabirintoEncantado.java`
- **Famílias de produtos:** Clássicos (SalaClassica, PortaClassica) e Encantados (SalaEncantada, PortaEncantada)

**Diferença do Factory Method:**
- Factory Method: cria um tipo de produto
- Abstract Factory: cria famílias inteiras de produtos relacionados

**Exemplo de uso:**
```java
// Cria família de produtos clássicos
FabricaLabirinto fabrica = new FabricaLabirintoClassico();
Sala sala = fabrica.criarSala();     // SalaClassica
Porta porta = fabrica.criarPorta();  // PortaClassica
```

### 4. Builder
**Localização:** `src/criacionais/builder/`

**Propósito:** Construir objetos complexos passo a passo, permitindo diferentes representações.

**Implementação no projeto:**
- **Interface Builder:** `ConstrutorLabirinto.java`
- **Director:** `DiretorLabirinto.java` - Conhece a sequência de construção
- **Builders concretos:** `ConstrutorLabirintoClassico.java`, `ConstrutorLabirintoEncantado.java`
- **Produto:** `Labirinto.java` - Objeto complexo sendo construído

**Fluxo de construção:**
```java
DiretorLabirinto diretor = new DiretorLabirinto();
ConstrutorLabirintoClassico construtor = new ConstrutorLabirintoClassico();

diretor.construir(construtor);  // Executa a sequência de passos
Labirinto resultado = construtor.getLabirinto();
```

**Vantagens demonstradas:**
- Separação entre construção e representação
- Controle fino sobre o processo de construção
- Reutilização do algoritmo de construção

### 5. Prototype
**Localização:** `src/criacionais/prototype/`

**Propósito:** Criar objetos clonando protótipos existentes.

**Implementação no projeto:**
- **Interface:** `ComponenteLabirinto.java` - Estende Cloneable
- **Gerenciador:** `GerenciadorPrototipos.java` - Armazena e clona protótipos
- **Protótipos concretos:** `SalaClassica`, `SalaEncantada`, `PortaClassica`, `PortaEncantada`

**Exemplo de uso:**
```java
// Configuração inicial dos protótipos
gerenciador.adicionarPrototipo("classico_sala", new SalaClassica());

// Criação por clonagem
ComponenteLabirinto sala = gerenciador.getPrototipo("classico_sala");
```

**Vantagens demonstradas:**
- Criação eficiente de objetos similares
- Redução de subclasses
- Configuração dinâmica de protótipos

## Padrões Estruturais

### 1. Adapter
**Localização:** `src/estruturais/adapter/`

**Propósito:** Permitir que classes com interfaces incompatíveis trabalhem juntas.

**Implementação no projeto:**
- **Interface alvo:** `Porta.java` - Interface esperada pelo sistema
- **Classe adaptada:** `PortaMagicaExterna.java` - Classe externa incompatível
- **Adapter:** `AdapterPortaMagica.java` - Faz a "tradução" entre interfaces

**Problema resolvido:**
```java
// Sistema espera: porta.abrir()
// Classe externa oferece: portaExterna.desbloquearComMagia()
// Adapter resolve: adapter.abrir() -> portaExterna.desbloquearComMagia()
```

**Estrutura do Adapter:**
```java
public class AdapterPortaMagica implements Porta {
    private PortaMagicaExterna portaExterna;
    
    public void abrir() {
        portaExterna.desbloquearComMagia(); // Tradução da interface
    }
}
```

**Vantagens demonstradas:**
- Integração de código legado
- Reutilização de classes existentes
- Desacoplamento entre interfaces

### 2. Facade
**Localização:** `src/estruturais/facade/`

**Propósito:** Fornecer uma interface simplificada para um subsistema complexo.

**Implementação no projeto:**
- **Subsistemas complexos:** `GerenciadorSalas`, `GerenciadorPortas`, `GerenciadorItens`
- **Facade:** `FachadaLabirintoFacade.java` - Interface simplificada

**Complexidade escondida:**
```java
// Sem Facade (cliente precisa conhecer todos os gerenciadores)
GerenciadorSalas salas = new GerenciadorSalas();
GerenciadorPortas portas = new GerenciadorPortas();
GerenciadorItens itens = new GerenciadorItens();
salas.criarSala("clássica", 1);
salas.criarSala("clássica", 2);
portas.criarPorta("clássica", 1, 2);
itens.adicionarItem("normal", 1);

// Com Facade (interface simplificada)
FachadaLabirintoFacade fachada = new FachadaLabirintoFacade();
fachada.criarLabirintoClassico(); // Esconde toda a complexidade
```

**Vantagens demonstradas:**
- Simplificação da interface cliente
- Desacoplamento do subsistema
- Facilita manutenção e evolução

## Comparações: Com Padrão vs Sem Padrão

Cada padrão inclui exemplos "sem padrão" que demonstram os problemas que os padrões resolvem:

### Singleton
- **Sem padrão:** Múltiplas instâncias podem causar inconsistências
- **Com padrão:** Garantia de instância única e controle de acesso

### Factory Method
- **Sem padrão:** Código cliente acoplado às classes concretas (if/else para tipos)
- **Com padrão:** Desacoplamento e extensibilidade através de herança

### Abstract Factory
- **Sem padrão:** Mistura de produtos de diferentes famílias
- **Com padrão:** Garantia de consistência entre produtos relacionados

### Builder
- **Sem padrão:** Construtores complexos com muitos parâmetros
- **Com padrão:** Construção flexível e controlada passo a passo

### Adapter
- **Sem padrão:** Impossibilidade de usar classes com interfaces incompatíveis
- **Com padrão:** Integração transparente de diferentes interfaces

### Facade
- **Sem padrão:** Cliente precisa conhecer e gerenciar múltiplos subsistemas
- **Com padrão:** Interface unificada e simplificada

## Conceitos Importantes Demonstrados

### 1. **Inversão de Dependência**
- Classes dependem de abstrações (interfaces), não de implementações concretas
- Facilita testes e manutenção

### 2. **Polimorfismo**
- Mesmo método (`abrir()`, `entrar()`) com comportamentos diferentes
- Permite tratamento uniforme de objetos diferentes

### 3. **Encapsulamento**
- Detalhes de implementação escondidos atrás de interfaces
- Facilita mudanças sem afetar clientes

### 4. **Composição vs Herança**
- Adapter usa composição (tem-um) em vez de herança (é-um)
- Maior flexibilidade em tempo de execução

### 5. **Template Method**
- Algoritmo fixo com passos variáveis (Factory Method, Builder)
- Reutilização de código com pontos de extensão

## Quando Usar Cada Padrão

### Singleton
- Quando precisa de exatamente uma instância (configurações, logs, cache)
- **Cuidado:** Pode dificultar testes e criar acoplamento global

### Factory Method
- Quando não sabe antecipadamente que tipos de objetos criar
- Para desacoplar criação de uso

### Abstract Factory
- Quando precisa criar famílias de objetos relacionados
- Para garantir consistência entre produtos

### Builder
- Para objetos complexos com muitas opções de configuração
- Quando o processo de construção deve ser independente das partes

### Adapter
- Para integrar classes com interfaces incompatíveis
- Especialmente útil com código legado ou bibliotecas externas

### Facade
- Para simplificar interfaces complexas
- Quando quer desacoplar clientes de subsistemas

## Conclusão

Este projeto demonstra como os padrões de projeto resolvem problemas comuns de design de software:

1. **Flexibilidade:** Facilita mudanças e extensões
2. **Reutilização:** Promove reuso de código e design
3. **Manutenibilidade:** Código mais organizado e compreensível
4. **Desacoplamento:** Reduz dependências entre componentes
5. **Testabilidade:** Facilita criação de testes unitários

Os padrões não são soluções mágicas, mas ferramentas que, quando aplicadas corretamente, melhoram significativamente a qualidade do código e a produtividade da equipe de desenvolvimento.