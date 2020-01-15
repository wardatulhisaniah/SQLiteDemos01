package id.ac.poliban.mi.atul.sqlitedemos01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.Logo);
        Glide.with(this)
                .load(Uri.parse("https://5.imimg.com/data5/JM/TK/HQ/SELLER-58228872/sambhaji-maharaj-sitting-fiber-statue-500x500.jpg"))
                                .into(logo);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 3000);
    }
}
