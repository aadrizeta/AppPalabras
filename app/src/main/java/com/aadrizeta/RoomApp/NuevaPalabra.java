package com.aadrizeta.RoomApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NuevaPalabra extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText palabraNueva;
    private Button botonGuardar;
    private ImageView botonAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_palabra2);
        palabraNueva = findViewById(R.id.edit_word);
        botonGuardar = findViewById(R.id.saveButton);
        botonAtras = findViewById(R.id.backButton);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMain(view);
            }
        });
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(palabraNueva.getText())){
                    setResult(RESULT_CANCELED);
                } else {
                    String word = palabraNueva.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
    public void launchMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}