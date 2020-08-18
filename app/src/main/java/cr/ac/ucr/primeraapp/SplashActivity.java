package cr.ac.ucr.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import cr.ac.ucr.primeraapp.utils.AppPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        boolean isLoggedIn = AppPreferences.getInstance(this).getBoolean(AppPreferences.Keys.IS_LOGGED_IN, false);

        Intent intent;

        if (isLoggedIn){
            intent = new Intent(this, MainActivity.class);
        }else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);

        //Se utiliza solo si no se quiere volver a esta activity
        finish();
    }
}