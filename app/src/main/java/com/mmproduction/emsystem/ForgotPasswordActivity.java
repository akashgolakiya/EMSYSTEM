package com.mmproduction.emsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
  FirebaseAuth firebaseAuth;
  Button reset;
  EditText change;
    private Toolbar mtoolbar;

    private ProgressDialog mRegprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        change = (EditText) findViewById(R.id.reset_pass);
        reset = (Button)findViewById(R.id.reset_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("ForgotPasswordActivity Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegprogress = new ProgressDialog(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               String email = change.getText().toString();

                mRegprogress.setMessage("sendind reset link to this E-mail");
                mRegprogress.show();
               if(email.equals(""))
               {
                   mRegprogress.dismiss();
                   Toast.makeText(ForgotPasswordActivity.this,"Email is required...",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               mRegprogress.dismiss();
                               Toast.makeText(ForgotPasswordActivity.this,"Password Reset Link send to your mail",Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                           }
                           else {
                               mRegprogress.dismiss();
                               Toast.makeText(ForgotPasswordActivity.this,"Error in sending mail link...",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });
    }
}
