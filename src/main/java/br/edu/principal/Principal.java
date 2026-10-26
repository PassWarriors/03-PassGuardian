
package br.edu.principal;

import java.io.IOException;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
    public static void main(String[] args) {
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
            
            JButton BtnMostrarSenha = new JButton("");

            // AÇÃO QUANDO APERTAR O BOTÃO VERIFICAR
            botao.addActionListener(e -> {
                // PEGA A SENHA DIGITADA
                char[] senha = campoSenha.getPassword();
                
                try {
                    // CRIAR O CALCULADOR SHA-1
                    MessageDigest md = MessageDigest.getInstance("SHA-1");
                    
                    // TRANSFORMA SENHA EM BYTES
                    byte[] dados = new String(senha).getBytes(StandardCharsets.UTF_8);
                    // CALCULAR O HASH 
                    byte[] hash = md.digest(dados);
                    
                    // CONVERTER OS BYTES EM HEXADECIMAL
                    StringBuilder hexadecimal = new StringBuilder();
                    
                    for (byte b : hash) {
                        hexadecimal.append(String.format("%02X", b & 0xff));
                    }

                    // TRANSFORMA A STRINGBUILDER EM STRING NORMA
                    String hashTexto = hexadecimal.toString();
                    
                   // SEPARAÇÃO DO HASH EM PREFIXO E SUFIXO 
                    String prefixo = hashTexto.substring(0, 5);
                    String sufixo = hashTexto.substring(5);
                    HttpClient cliente = HttpClient.newHttpClient();
                    
                    // MONTAR O PEDIDO HTTP
                    HttpRequest pedido = HttpRequest.newBuilder()
                        .uri(URI.create(
                            "https://api.pwnedpasswords.com/range/" + prefixo
                        ))
                        .header("Add-Padding", "true")
                        .GET()
                        .build();
                    
                    // RECEBER A RESPOSTA
                    HttpResponse<String> resposta = cliente.send(
                        pedido,
                        HttpResponse.BodyHandlers.ofString()
                    );

                    System.out.println("Código HTTP: " + resposta.statusCode());
                    System.out.println("Resposta recebida: " + resposta.body().length() + " caracteres");
                    
                    // PROCURAR O SUFIXO
                    boolean vazada = false;

                    for (String linha : resposta.body().split("\\r?\\n")) {
                        String[] partes = linha.split(":");

                        if (partes[0].equalsIgnoreCase(sufixo)) {
                            vazada = true;
                            textresposta.setText("Encontrada! Ocorrências: " + partes[1]);
                            break;
                        }
                    }
                    
                    // CASO NÃO TENHA ENCONTRADO NA CONSULTA
                    if (!vazada) {
                        textresposta.setText("Nenhuma ocorrência encontrada na consulta.");
                    }

                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        ex.printStackTrace();
                    }
                System.out.println("Senha digitada: " + senha.length + " caracteres");
                java.util.Arrays.fill(senha, '\0');
            });

            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
