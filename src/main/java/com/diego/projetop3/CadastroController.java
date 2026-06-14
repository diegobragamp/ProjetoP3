package com.diego.projetop3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {

    @FXML
    private TextField nomeUsuario;

    @FXML
    private TextField idadeUsuario;

    @FXML
    private TextField cpfUsuario;

    @FXML
    private TextField telefoneUsuario;

    @FXML
    private TextField pesoUsuario;

    @FXML
    private Label mensagemLabel;

    @FXML
    private Button salvarButton;

    @FXML
    private Button botaoVoltarLogin;

    @FXML
    protected void onSalvarButtonClick() {
        // 1. Pegando os textos digitados nos campos
        String nome = nomeUsuario.getText().trim();
        String idadeStr = idadeUsuario.getText().trim();
        String cpf = cpfUsuario.getText().trim();
        String telefone = telefoneUsuario.getText().trim();
        String pesoStr = pesoUsuario.getText().trim();

        // 2. Validação: Verificar se algum campo está completamente vazio
        if (nome.isEmpty() || idadeStr.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || pesoStr.isEmpty()) {
            mensagemLabel.setStyle("-fx-text-fill: red;");
            mensagemLabel.setText("Erro: Todos os campos são obrigatórios");
            return; // O return para a execução aqui e não deixa continuar
        }

        // 3. Validação de tipos (Idade precisa ser número inteiro, Peso precisa ser número quebrado)
        try {
            int idade = Integer.parseInt(idadeStr); // Tenta transformar o texto da idade em número
            double peso = Double.parseDouble(pesoStr.replace(",", ".")); // Tenta transformar o peso e aceita vírgula ou ponto

            // Se chegou até aqui, os dados são válidos!
            System.out.println("--- NOVO CADASTRO ---");
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("CPF: " + cpf);
            System.out.println("Telefone: " + telefone);
            System.out.println("Peso: " + peso + " kg");

            mensagemLabel.setStyle("-fx-text-fill: green;");
            mensagemLabel.setText("Cadastro realizado com sucesso!");
            ConexaoBanco c = new ConexaoBanco();
            c.salvarUsuario(nome,idade,telefone,peso,cpf);



        } catch (NumberFormatException e) {
            // Se o usuário digitar letras na idade ou no peso, o Java vai gerar um erro e cair aqui
            mensagemLabel.setStyle("-fx-text-fill: red;");
            mensagemLabel.setText("Erro: Idade e Peso devem ser valores numéricos válidos.");
        }
    }

    @FXML
    protected void onVoltarButtonClick() {
        try {
            // CORREÇÃO 1: Mudar para TelaLogin.fxml (é para lá que queremos voltar!)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // CORREÇÃO 2: Descobrir a janela atual usando o botão que foi clicado (voltarButton)
            Stage stage = (Stage) botaoVoltarLogin.getScene().getWindow();

            // Mudamos o cenário da janela atual para o Login
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Erro ao voltar para a tela de login: " + e.getMessage());
            e.printStackTrace();
        }
    } // Chave que fecha o método onVoltarButtonClick

    private void limparCampos() {
        nomeUsuario.clear();
        idadeUsuario.clear();
        cpfUsuario.clear();
        telefoneUsuario.clear();
        pesoUsuario.clear();
    }

}