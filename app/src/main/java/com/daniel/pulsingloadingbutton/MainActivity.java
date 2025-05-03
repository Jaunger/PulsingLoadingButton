package com.daniel.pulsingloadingbutton;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daniel.pulsingbutton.PulsingLoadingButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PulsingLoadingButton button = findViewById(R.id.demoButton);

        button.setOnClickListener(v -> {
            button.setState(PulsingLoadingButton.State.LOADING);

            new Handler().postDelayed(() -> {
                button.setState(PulsingLoadingButton.State.SUCCESS);
            }, 3000);
        });
    }
}