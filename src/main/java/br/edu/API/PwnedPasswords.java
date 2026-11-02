/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PwnedPasswords {
    public String consultar(String hashTexto) throws IOException, InterruptedException {

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

         for (String linha : resposta.body().split("\\r?\\n")) {
             String[] partes = linha.split(":");

             if (partes[0].equalsIgnoreCase(sufixo)) {
                 return(partes[1]);
             }
         }

         // CASO NÃO TENHA ENCONTRADO NA CONSULTA
        return "0";
        }
}
