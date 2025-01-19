package com.example.zakatpayment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity {

    Toolbar aboutToolbar;
    private View tvWebsiteLink2;
    private View tvWebsiteLink3;
    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);

        aboutToolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setTitle("About");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.my_light_primarydark));
        }

        // Link setup
        tvWebsiteLink2 = findViewById(R.id.tvWebsiteLink2);
        tvWebsiteLink2.setOnClickListener(v -> {
            // Open the link in a browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/niknurulalya/niknurulalya-2022458596-zakatPayment-.git"));
            startActivity(browserIntent);
        });

        // Link setup
        tvWebsiteLink3 = findViewById(R.id.tvWebsiteLink3);
        tvWebsiteLink3.setOnClickListener(v -> {
            // Open the link in a browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/student.uitm.edu.my/nikalya/home"));
            startActivity(browserIntent);
        });

        // Button setup to navigate to InstructionActivity
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(AboutActivity.this, InstructionActivity.class);
            startActivity(intent);
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}