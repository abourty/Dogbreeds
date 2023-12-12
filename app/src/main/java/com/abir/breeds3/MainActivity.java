package com.abir.breeds3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// ... (importations et autres méthodes)

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DogAdapter dogAdapter;
    private EditText breedGroupEditText;
    private Button filterButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues et des adaptateurs

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dogAdapter = new DogAdapter();
        recyclerView.setAdapter(dogAdapter);


        breedGroupEditText = findViewById(R.id.breedGroupEditText);
        filterButton = findViewById(R.id.filterButton);
        logoutButton = findViewById(R.id.logoutButton);

        // ...

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breedGroup = breedGroupEditText.getText().toString();
                fetchDogs(breedGroup);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fetchDogs(String breedGroup) {
        DogApiService dogApiService = DogApiManager.getDogApiService();
        Call<List<Dog>> call = dogApiService.getDogs(breedGroup);
        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if (response.isSuccessful()) {
                    List<Dog> dogs = response.body();

                    // Mise à jour des URL d'image pour chaque chien
                    for (Dog dog : dogs) {
                        dog.setImageUrl("https://cdn2.thedogapi.com/images/" + dog.getImageUrl() + ".jpg");
                    }

                    dogAdapter.setDogs(dogs);
                } else {
                    // Gérer les erreurs
                }
            }


            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                // Gérer les erreurs réseau
            }
        });
    }
}
