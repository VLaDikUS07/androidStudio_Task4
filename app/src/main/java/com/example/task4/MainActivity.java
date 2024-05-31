package com.example.task4;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editText);
        bControl = (Button)findViewById(R.id.button);

        guess = (int)(Math.random()*100);
        gameFinished = false;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        bControl.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        if (!gameFinished) {
            String input = etInput.getText().toString();
            if (input.isEmpty()) {
                tvInfo.setText(R.string.empty_input);
                return;
            }

            int inp;
            try {
                inp = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                tvInfo.setText(R.string.invalid_input);
                etInput.setText("");
                return;
            }

            if (inp < 0 || inp > 100) {
                tvInfo.setText(R.string.out_of_range);
                etInput.setText("");
                return;
            }

            if (inp > guess)
                tvInfo.setText(R.string.ahead);
            else if (inp < guess)
                tvInfo.setText(R.string.behind);
            else {
                tvInfo.setText(R.string.hit);
                bControl.setText(R.string.play_more);
                gameFinished = true;
            }
        }
        else {
            guess = (int)(Math.random() * 100);
            bControl.setText(R.string.input_value);
            tvInfo.setText(R.string.try_to_guess);
            gameFinished = false;
        }

        etInput.setText("");
    }
}