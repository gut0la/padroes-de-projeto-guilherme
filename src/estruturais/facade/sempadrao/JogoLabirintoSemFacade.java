package estruturais.facade.sempadrao;

import estruturais.facade.sempadrao.classes.GerenciadorItens;
import estruturais.facade.sempadrao.classes.GerenciadorPortas;
import estruturais.facade.sempadrao.classes.GerenciadorSalas;

/**
 * Exemplo de código SEM o padrão Facade
 * 
 * Demonstra os problemas que o Facade resolve:
 * - Cliente precisa conhecer todos os subsistemas
 * - Código repetitivo para coordenar múltiplos gerenciadores
 * - Alto acoplamento entre cliente e subsistemas
 * - Dificuldade de manutenção quando subsistemas mudam
 */
public class JogoLabirintoSemFacade {
    
    public static void main(String[] args) {
        // Cliente precisa criar e gerenciar todos os subsistemas
        GerenciadorSalas salas = new GerenciadorSalas();
        GerenciadorPortas portas = new GerenciadorPortas();
        GerenciadorItens itens = new GerenciadorItens();

        // Cliente precisa conhecer a sequência correta de chamadas
        System.out.println("Criando labirinto clássico:");
        salas.criarSala("clássica", 1);
        salas.criarSala("clássica", 2);
        portas.criarPorta("clássica", 1, 2);
        itens.adicionarItem("normal", 1);

        // Código repetitivo - mesma sequência com parâmetros diferentes
        System.out.println("\nCriando labirinto encantado:");
        salas.criarSala("encantada", 1);
        salas.criarSala("encantada", 2);
        portas.criarPorta("encantada com feitiço", 1, 2);
        itens.adicionarItem("mágico", 1);
        
        // Problemas evidentes:
        // 1. Muito código para uma tarefa simples
        // 2. Cliente acoplado aos detalhes internos
        // 3. Difícil de manter se os subsistemas mudarem
    }
}
