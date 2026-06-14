package com.diego.projetop3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // ADICIONADO: Import do carregador de FXML
import javafx.scene.Scene;       // ADICIONADO: Import da Cena
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;       // ADICIONADO: Import do Palco (Janela), resolve o erro do Stage!

public class LoginController {

    @FXML
    private TextField usuarioLogin;

    @FXML
    private PasswordField senhaLogin;

    @FXML
    private Label erroLogin;

    @FXML
    private Label cadastroLogin;

    @FXML
    private Button botaoLogin;

    @FXML
    protected void onLoginButtonClick() {
        String usuario = usuarioLogin.getText();
        String senha = senhaLogin.getText();

        // Validação simples de exemplo
        if (usuario.isEmpty() || senha.isEmpty()) {
            erroLogin.setText("Por favor, preencha todos os campos.");
        } else if ("admin".equals(usuario) && "1234".equals(senha)) {
            erroLogin.setStyle("-fx-text-fill: green;");
            erroLogin.setText("Login efetuado com sucesso!");
        } else {
            erroLogin.setStyle("-fx-text-fill: red;");
            erroLogin.setText("Usuário ou senha incorretos.");
        }
    }

    @FXML
    protected void onCadastroClick(){
        try {

            FXMLLoader Nfxml = new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
            Scene scene = new Scene(Nfxml.load());

            Stage novaJanela = new Stage();
            novaJanela.setTitle("Cadastro de Novo Usuário");
            novaJanela.setScene(scene);

            // 3. Pega a janela atual de login e fecha ela
            Stage janelaAtual = (Stage) cadastroLogin.getScene().getWindow();
            janelaAtual.close();

            // 4. Mostra a nova janela na tela
            novaJanela.show();
        }catch (Exception e) {
            System.out.println("Erro ao abrir a tela de cadastro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}