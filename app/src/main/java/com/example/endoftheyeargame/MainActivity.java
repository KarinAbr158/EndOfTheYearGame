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
    String a, b, c, name1, name2, name3, input, nameInput;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
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

        sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPref.edit();
        input = password.getText().toString();
        nameInput = name.getText().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = password.getText().toString();
                nameInput = name.getText().toString();

                if (input.equals("") && !nameInput.equals("")) {
                    Toast.makeText(MainActivity.this, "Password required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (nameInput.equals("") && !input.equals("")) {
                    Toast.makeText(MainActivity.this, "Username required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (input.equals("") && nameInput.equals("")) {
                    Toast.makeText(MainActivity.this, "No data was entered at all", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean valid =
                        (nameInput.equals(name1) && input.equals(a)) ||
                                (nameInput.equals(name2) && input.equals(b)) ||
                                (nameInput.equals(name3) && input.equals(c));

                if (!valid) {
                    Toast.makeText(MainActivity.this, "Incorrect password or username", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    editor = sharedPref.edit();
                    editor.putString("user", nameInput);
                    editor.apply();

                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
            }
        });
    }
}