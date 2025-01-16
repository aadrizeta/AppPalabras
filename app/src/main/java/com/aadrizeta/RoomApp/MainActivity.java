package com.aadrizeta.RoomApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private PalabraViewModel palabraViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listaPalabras = findViewById(R.id.recyclerview);
        FloatingActionButton addButton = findViewById(R.id.floatingActionButton);

        final PalabraListAdapter adapter = new PalabraListAdapter(new PalabraListAdapter.PalabraDiff(), palabraViewModel);
        listaPalabras.setAdapter(adapter);
        listaPalabras.setLayoutManager(new LinearLayoutManager(this));

        palabraViewModel = new ViewModelProvider(this).get(PalabraViewModel.class);

        palabraViewModel.getAllPalabras().observe(this, palabras -> {
            adapter.submitList(palabras);
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NuevaPalabra.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Palabra palabra = new Palabra(data.getStringExtra(NuevaPalabra.EXTRA_REPLY));
            palabraViewModel.insert(palabra);
            Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

}