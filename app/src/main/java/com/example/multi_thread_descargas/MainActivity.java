package com.example.multi_thread_descargas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI
    ProgressBar progressGorra, progressCamiseta, progressPantalon, progressZapatos;
    TextView statusGorra, statusCamiseta, statusPantalon, statusZapatos;
    ImageView gorra, camiseta, pantalon, zapatos;

    Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias
        progressGorra = findViewById(R.id.progressGorra);
        progressCamiseta = findViewById(R.id.progressCamiseta);
        progressPantalon = findViewById(R.id.progressPantalon);
        progressZapatos = findViewById(R.id.progressZapatos);

        statusGorra = findViewById(R.id.statusGorra);
        statusCamiseta = findViewById(R.id.statusCamiseta);
        statusPantalon = findViewById(R.id.statusPantalon);
        statusZapatos = findViewById(R.id.statusZapatos);

        gorra = findViewById(R.id.gorra);
        camiseta = findViewById(R.id.camiseta);
        pantalon = findViewById(R.id.pantalon);
        zapatos = findViewById(R.id.zapatos);

        // Botones
        findViewById(R.id.btnGorra).setOnClickListener(v -> startDownload(progressGorra, statusGorra, gorra));
        findViewById(R.id.btnCamiseta).setOnClickListener(v -> startDownload(progressCamiseta, statusCamiseta, camiseta));
        findViewById(R.id.btnPantalon).setOnClickListener(v -> startDownload(progressPantalon, statusPantalon, pantalon));
        findViewById(R.id.btnZapatos).setOnClickListener(v -> startDownload(progressZapatos, statusZapatos, zapatos));
    }

    private void startDownload(ProgressBar progressBar, TextView statusText, ImageView prenda) {
        statusText.setText(R.string.descargando);
        progressBar.setProgress(0);

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                int progress = i;
                uiHandler.post(() -> progressBar.setProgress(progress));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            uiHandler.post(() -> {
                statusText.setText(R.string.descarga_completa);
                prenda.setVisibility(View.VISIBLE);
            });
        }).start();
    }
}
