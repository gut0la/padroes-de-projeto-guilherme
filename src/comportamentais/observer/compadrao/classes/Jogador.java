package comportamentais.observer.compadrao.classes;

import comportamentais.observer.compadrao.interfaces.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Jogador que implementa o padrão Observer como Subject
 * 
 * PADRÃO OBSERVER - SUBJECT:
 * Esta classe representa o "Subject" (objeto observável) no padrão Observer.
 * Ela mantém uma lista de observers e os notifica quando seu estado muda.
 * 
 * BENEFÍCIOS ALCANÇADOS:
 * - Desacoplamento: Jogador não conhece classes específicas de observers
 * - Extensibilidade: Novos observers podem ser adicionados sem modificar esta classe
 * - Flexibilidade: Observers podem ser adicionados/removidos dinamicamente
 * - Responsabilidade Única: Jogador foca apenas em sua lógica, observers cuidam de suas reações
 */
public class Jogador {
    private String nome;
    private int x, y;
    private int vida;
    
    // Lista de observers que serão notificados sobre mudanças
    private List<Observer> observers;
    
    /**
     * Construtor do Jogador
     * 
     * VANTAGEM: Não precisa receber dependências específicas!
     * Observers serão adicionados dinamicamente conforme necessário.
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.x = 0;
        this.y = 0;
        this.vida = 100;
        this.observers = new ArrayList<>();
        
        System.out.println("Jogador " + nome + " criado na posição (0,0) com " + vida + " de vida");
    }
    
    /**
     * Adiciona um observer à lista
     * 
     * PADRÃO OBSERVER: Permite que qualquer objeto interessado
     * se registre para receber notificações
     */
    public void adicionarObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer adicionado: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Remove um observer da lista
     */
    public void removerObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removido: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Notifica todos os observers sobre um evento
     * 
     * PADRÃO OBSERVER: Este é o coração do padrão!
     * Todos os observers registrados são notificados automaticamente.
     */
    private void notificarObservers(String evento, Object dados) {
        for (Observer observer : observers) {
            observer.notificar(evento, dados);
        }
    }
    
    /**
     * Move o jogador para uma direção
     * 
     * PADRÃO OBSERVER EM AÇÃO:
     * - Atualiza o estado interno
     * - Notifica TODOS os observers automaticamente
     * - Não precisa conhecer quem são os observers!
     */
    public void mover(String direcao) {
        // Salva posição anterior para o evento
        int xAnterior = x;
        int yAnterior = y;
        
        // Atualiza posição baseada na direção
        switch (direcao.toLowerCase()) {
            case "norte": y++; break;
            case "sul": y--; break;
            case "leste": x++; break;
            case "oeste": x--; break;
            default:
                System.out.println("Direção inválida: " + direcao);
                return;
        }
        
        System.out.println(nome + " moveu para " + direcao + " -> (" + x + "," + y + ")");
        
        // Cria dados do evento de movimento
        DadosMovimento dados = new DadosMovimento(nome, xAnterior, yAnterior, x, y, direcao);
        
        // NOTIFICA TODOS OS OBSERVERS AUTOMATICAMENTE!
        notificarObservers("movimento", dados);
    }
    
    /**
     * Recebe dano
     */
    public void receberDano(int dano) {
        int vidaAnterior = vida;
        vida = Math.max(0, vida - dano);
        
        System.out.println(nome + " recebeu " + dano + " de dano. Vida: " + vidaAnterior + " -> " + vida);
        
        // Notifica sobre mudança de vida
        DadosVida dadosVida = new DadosVida(nome, vidaAnterior, vida, dano);
        notificarObservers("dano", dadosVida);
        
        // Se morreu, notifica sobre morte
        if (vida <= 0) {
            notificarObservers("morte", nome);
        }
    }
    
    /**
     * Coleta um item
     */
    public void coletarItem(String item) {
        System.out.println(nome + " coletou: " + item);
        
        // Notifica sobre coleta de item
        DadosItem dadosItem = new DadosItem(nome, item, x, y);
        notificarObservers("coleta", dadosItem);
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVida() { return vida; }
    public int getNumeroObservers() { return observers.size(); }
}

/**
 * Classe para encapsular dados de movimento
 * 
 * DESIGN: Usar classes específicas para dados torna o código mais legível
 * e type-safe, em vez de usar arrays ou Maps genéricos.
 */
class DadosMovimento {
    public final String nomeJogador;
    public final int xAnterior, yAnterior;
    public final int xNovo, yNovo;
    public final String direcao;
    
    public DadosMovimento(String nomeJogador, int xAnterior, int yAnterior, 
                         int xNovo, int yNovo, String direcao) {
        this.nomeJogador = nomeJogador;
        this.xAnterior = xAnterior;
        this.yAnterior = yAnterior;
        this.xNovo = xNovo;
        this.yNovo = yNovo;
        this.direcao = direcao;
    }
}

/**
 * Classe para encapsular dados de vida/dano
 */
class DadosVida {
    public final String nomeJogador;
    public final int vidaAnterior, vidaNova;
    public final int danoRecebido;
    
    public DadosVida(String nomeJogador, int vidaAnterior, int vidaNova, int danoRecebido) {
        this.nomeJogador = nomeJogador;
        this.vidaAnterior = vidaAnterior;
        this.vidaNova = vidaNova;
        this.danoRecebido = danoRecebido;
    }
}

/**
 * Classe para encapsular dados de coleta de item
 */
class DadosItem {
    public final String nomeJogador;
    public final String item;
    public final int x, y;
    
    public DadosItem(String nomeJogador, String item, int x, int y) {
        this.nomeJogador = nomeJogador;
        this.item = item;
        this.x = x;
        this.y = y;
    }
}