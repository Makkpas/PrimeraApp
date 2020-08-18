package cr.ac.ucr.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;

    Pattern pattern = Patterns.EMAIL_ADDRESS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email_register);
        etPassword = findViewById(R.id.et_password_register);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_goto_login:
                gotoLogin();
                break;
            default:
                break;
        }
    }
    
    public void register(){
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty()){
            etName.setError(getString(R.string.error_name));
            return;
        }
        if (email.isEmpty()){
            etEmail.setError(getString(R.string.error_email));
            return;
        }
        if (password.isEmpty()){
            etEmail.setError(getString(R.string.error_password));
            return;

        }
        //TODO: se tiene que sustituir con la logica de autenticación de la aplicación
        if(pattern.matcher(email).matches()){

            Toast.makeText(this, getString(R.string.registered), Toast.LENGTH_SHORT);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,getString(R.string.no_match), Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}