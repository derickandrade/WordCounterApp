package com.tp2.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class TP2Controller {

    @FXML
    private Label fileText;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextField filePathField;

    @FXML
    private TextArea resultField;

    @FXML
    private Button startButton;

    @FXML
    protected void onLoadFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo:");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Arquivos de texto (*.txt", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter); // Limita a arquivos de texto

        File selectedFile = fileChooser.showOpenDialog(null); // Abre a janela de escolher arquivo

        if (selectedFile != null) {
            String absolutePath = selectedFile.getAbsolutePath();
            fileText.setText("Arquivo selecionado: " + selectedFile.getName());
            TP2Application.textPath = absolutePath;
            filePathField.setText(absolutePath); // Mostra o caminho absoluto do arquivo
            try {
                String conteudo = new String(java.nio.file.Files.readAllBytes(selectedFile.toPath()));
                inputTextArea.setText(conteudo); // Coloca o texto na caixa
            } catch (Exception e) {
                fileText.setText("Erro ao ler o arquivo: " + e.getMessage());
                TP2Application.textPath = "";
                inputTextArea.clear();
            }
        } else {
            fileText.setText("Nenhum arquivo selecionado");
            filePathField.clear();
            inputTextArea.clear();
            TP2Application.textPath = "";
        }

    }

    @FXML
    protected void setStartButton() {
        System.out.println(TP2Application.textPath);
        try {
            // Chama Main.main() passando o caminho como argumento
            System.out.println("Start OK");
            Main.main(new String[]{TP2Application.textPath}); // Simula args[0] = filePath
            System.out.println("Main OK");
            // Atualiza a interface com os resultados
            resultField.setText(String.join("\n", TP2Application.result));
            try {
                String buffer = "";
                for (int i = 0; i < TP2Application.result.length; i++){
                    if (TP2Application.result[i] != null){
                        buffer.join("\n", TP2Application.result[i]);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro no print do resultado");
            }

            ;
        } catch (Exception e) {
            fileText.setText("Erro ao processar: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}