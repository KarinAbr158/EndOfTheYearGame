package com.example.endoftheyeargame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, password;
    Button btn;
    String a, b, c, name1, name2, name3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button);

        //passwords
        a = "123456789";
        b = "987654321";
        c = "192837465";
        //username
        name1 = "WalterWhite158";
        name2 = "SaulGoodman249";
        name3 = "JessePinkman259";
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = password.getText().toString();
                String nameInput = name.getText().toString();
                if(input.isEmpty() && !nameInput.isEmpty()){
                    Toast.makeText(MainActivity.this, "Password required", Toast.LENGTH_SHORT).show();
                }
                else if(nameInput.isEmpty() && !input.isEmpty()){
                    Toast.makeText(MainActivity.this, "Username required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "No data was entered at all", Toast.LENGTH_SHORT).show();
                }
                if(!input.equals(a) && !nameInput.equals(name1) || !nameInput.equals(name2) && !input.equals(b) || !input.equals(c) && !nameInput.equals(name3)){
                    Toast.makeText(MainActivity.this, "Incorrect password or username", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                editor.putString("user", nameInput);
                editor.apply();
            }
        });
    }
}