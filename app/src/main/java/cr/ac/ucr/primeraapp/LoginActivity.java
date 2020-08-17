package cr.ac.ucr.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_goto_register:
                gotoRegister();
                break;
            default:
                break;
        }
    }

    private void login() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError(getString(R.string.error_email));
            return;
        }
        if(password.isEmpty()){
            etPassword.setError(getString(R.string.error_password));
            return;
        }

        //TODO: se tiene que sustituir con la logica de autenticación de la aplicación
        if(email.equalsIgnoreCase("admin@email.com") && password.equalsIgnoreCase("123")){

            //TODO: almacenar en el storage el usuario
            Toast.makeText(this,getString(R.string.logged_in), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,getString(R.string.no_match), Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}