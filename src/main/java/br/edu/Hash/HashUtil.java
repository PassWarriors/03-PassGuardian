package br.edu.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashUtil {
    
    public String calcularHash(char[] senha) {
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

            return hashTexto;

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
}
}

