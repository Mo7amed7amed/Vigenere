package com.example.mohamed.vigenere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText message;
    Button convertBtn;
    TextView encrypted_message, decrypted_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = findViewById(R.id.message);
        convertBtn = findViewById(R.id.btn);
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "MONO";
                String Sentence = message.getText().toString();
                String encryptedMsg = encrypt(Sentence, key);
                String decryptedMsg = decrypt(encryptedMsg, key);
                encrypted_message = findViewById(R.id.encrypted_message);
                decrypted_message = findViewById(R.id.decrypted_message);
                encrypted_message.setText(encryptedMsg);
                decrypted_message.setText(decryptedMsg);
            }
        });
    }

    public static String encrypt(String Txt,  String key) {
        String result = "";
        Txt = Txt.toUpperCase();
        for (int i = 0, j = 0; i < Txt.length(); i++) {
            char c = Txt.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            result += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return result;
    }

    public static String decrypt(String Txt, final String key) {
        String result = "";
        Txt = Txt.toUpperCase();
        for (int i = 0, j = 0; i < Txt.length(); i++) {
            char c = Txt.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            result += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return result;
    }
}
