package com.mmproduction.emsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    EditText user_email,password;
    Button login;
    TextView sign_up,f_password,mcwlogin;
    private Toolbar mtoolbar;

    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //to hide keyboard when activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        user_email = (EditText)findViewById(R.id.u_name);
        password = (EditText)findViewById(R.id.u_password);
        login = (Button) findViewById(R.id.loginbtn);
        sign_up = (TextView)findViewById(R.id.sign);
        f_password = (TextView)findViewById(R.id.reset);
        mcwlogin = (TextView)findViewById(R.id.continue_without_login);

        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null)
        {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user_email.getText().toString();
                String Password = password.getText().toString();

                progressDialog.setMessage("Wait a Second");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);



                if(CheckNetwork.isInternetAvailable(LoginActivity.this)){
                    if (!validate_email(user_email) | !validate_password(password)) {
                        return;
                    }
                    else {
                        if(user_email.getText().toString().equals("param@gmail.com") && password.getText().toString().equals("11111111") )
                        {
                            progressDialog.dismiss();
                            Intent i = new Intent(LoginActivity.this,AdminActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            validate(name, Password);
                        }
                    }
                }
                else{
                    progressDialog.dismiss();

                    Snackbar snackbar = Snackbar.make(login, "No Internet Connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
            }
        });

        f_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });

        mcwlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Home2Activity.class));
                finish();
            }
        });
    }


    private void validate(String email,String Password)
    {
        firebaseAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        // user is verified, so you can finish this activity or send user to activity which you want.
                        finish();
                        Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                        Intent mLginintent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(mLginintent);
                        finish();
                    } else {
                        // email is not verified, so just prompt the message to the user and restart this activity.
                        // NOTE: don't forget to log out the user.
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(LoginActivity.this, "Varify your email first!", Toast.LENGTH_SHORT).show();
                        //restart this activity
                    }

                    //Log.w("TAG", "signInWithEmail:failed", task.getException());

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Failed!!!",Toast.LENGTH_SHORT).show();


                }
            }
        });
    }


    public boolean validate_email(View view) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = user_email.getText().toString();
        if (email.isEmpty()) {
            user_email.setError("Email required");
            return false;
        } else if (!email.matches(emailPattern)) {
            progressDialog.dismiss();
            user_email.setError("Email is not valid");
            return false;
        } else {
            user_email.setError(null);
            return true;
        }
    }

    public boolean validate_password(View view) {
        String pass = password.getText().toString();

        if (pass.isEmpty()) {
            progressDialog.dismiss();
            password.setError("Password required");
            return false;
        } else if (password.getText().toString().length() < 8) {
            progressDialog.dismiss();
            password.setError("Password is too week");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }




}
