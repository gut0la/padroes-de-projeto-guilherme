package estruturais.facade.compadrao;


import estruturais.facade.compadrao.facade.FachadaLabirintoFacade;

public class JogoLabirintoComFacade {
    public static void main(String[] args) {
        FachadaLabirintoFacade fachada = new FachadaLabirintoFacade();

        System.out.println("Criando labirinto clássico:");
        fachada.criarLabirintoClassico();

        System.out.println("\nCriando labirinto encantado:");
        fachada.criarLabirintoEncantado();
    }
}
