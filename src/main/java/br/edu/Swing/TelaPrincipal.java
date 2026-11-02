package br.edu.Swing;

import br.edu.Hash.HashUtil;
import javax.swing.*;
import br.edu.API.PwnedPasswords;
import java.io.IOException;

public class TelaPrincipal {
    public void abrir() {
        SwingUtilities.invokeLater(() -> {
            // CRIAR JANELA
            JFrame janela = new JFrame("PassGuardian");
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setSize(400, 220);
            janela.setLayout(null);
            janela.setResizable(false);
            
            // CONFIGURAR ITENS DA TELA
            JLabel titulo = new JLabel("Verificador de [Palavras-Passe]");
            titulo.setBounds(110, 20, 200, 25);
            janela.add(titulo);

            JPasswordField campoSenha = new JPasswordField();
            campoSenha.setBounds(70, 60, 250, 30);
            janela.add(campoSenha);

            JButton botao = new JButton("Verificar");
            botao.setBounds(140, 105, 120, 30);
            janela.add(botao);
            
            JLabel textresposta = new JLabel("Verifique o vazamento dessa senha com a API");
            textresposta.setBounds(20, 140, 360, 35);
            janela.add(textresposta);
            

            // AÇÃO QUANDO APERTAR O BOTÃO VERIFICAR
            botao.addActionListener(e -> {
                // PEGA A SENHA DIGITADA
                char[] senha = campoSenha.getPassword();
                
                try {
                    HashUtil hashUtil = new HashUtil();
                    String hash = hashUtil.calcularHash(senha);

                    PwnedPasswords api = new PwnedPasswords();
                    String ocorrencias = api.consultar(hash);

                    if (ocorrencias.equals("0")) {
                        textresposta.setText("Nenhuma ocorrência encontrada.");
                    } else {
                        textresposta.setText("Encontrada! Ocorrências: " + ocorrencias);
                    }

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    textresposta.setText("Consulta interrompida.");
                } catch (IOException ex) {
                    textresposta.setText("Erro ao consultar a API.");
                }
                
                java.util.Arrays.fill(senha, '\0');
                
            });
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
