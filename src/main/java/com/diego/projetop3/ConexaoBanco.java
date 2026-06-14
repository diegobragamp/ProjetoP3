package com.diego.projetop3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Mudamos de Statement para PreparedStatement
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/ProjetoP3";
    private static final String USER = "postgres";
    private static final String PASSWORD = "D!3g01024";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void salvarUsuario(String nome, int idade, String telefone, Double peso, String cpf) {
        // Usamos as interrogações (?) como placeholders para os valores
        String sql = "INSERT INTO usuario (nome, idade, telefone, peso, cpf) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // O Java preenche os '?' na ordem correta e trata aspas, tipos e segurança automaticamente
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, telefone);
            stmt.setDouble(4, peso);
            stmt.setString(5, cpf);

            stmt.executeUpdate(); // Executa a inserção
            System.out.println("Incluindo usuário: " + nome);

        } catch (SQLException e) {
            System.out.println("Erro ao incluir usuário: " + e.getMessage());
            // Dica: deixe o printStackTrace enquanto estuda, ajuda muito a achar erros!
            e.printStackTrace();
        }
    }
}