package br.edu.principal;

import br.edu.Swing.TelaGuardian; // Certifique-se de importar o nome correto da sua classe de tela

public class Principal {
    public static void main(String[] args) {
        /* Garante que a interface gráfica seja executada na Thread correta do Swing */
        java.awt.EventQueue.invokeLater(() -> {
            // Cria a instância da sua tela gráfica
            TelaGuardian minhaTela = new TelaGuardian(); 
            
            // Faz a janela aparecer na tela do usuário
            minhaTela.setVisible(true); 
        });
    }
}
