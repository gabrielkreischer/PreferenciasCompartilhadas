package com.gabrielkreischer.preferenciascompartilhadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // declaração de variaveis
    private TextInputEditText inputNome;
    private Button btnEnviar;
    private TextView txtOla;

    // declaração de constantes
    private static final String ARQUIVO = "Arquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNome = findViewById(R.id.inputNome);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtOla = findViewById(R.id.txtOla);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if(inputNome.getText().toString().equals("")){
                    // mensagem que aparece momentaneamente na tela
                    Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_LONG).show();
                }else{
                    //recupera a variavel
                    String nome = inputNome.getText().toString();
                    //define em qual chave a string será salva
                    editor.putString("nome", nome);
                    //Salva o dado no arquivo
                    editor.commit();
                    //exibe o nome na tela
                    txtOla.setText("Olá "+nome);
                }

            }
        });
        // acessa o arquivo e armasena em preferences
        SharedPreferences preferences = getSharedPreferences(ARQUIVO, 0);
        // verifica se existe algo em nome
        if (preferences.contains("nome")){
            //pega o valor armasenado em nome
            String nome = preferences.getString("nome","usuário não definido");
            txtOla.setText(nome);
        }else{
            txtOla.setText("Olá, usuário não definido");
        }
    }
}