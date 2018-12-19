package com.mmproduction.emsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText mname, memail, mcontact, mpassword;
    Button msubmit;
    FirebaseAuth firebaseAuth;
    private Toolbar mtoolbar;


    //progress dialog
    private ProgressDialog mRegprogress;


    private DatabaseReference mDatabase,mDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //to hide keyboard when activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mname = (EditText)findViewById(R.id.user);
        memail = (EditText) findViewById(R.id.email);
        mcontact = (EditText) findViewById(R.id.contact);
        mpassword = (EditText) findViewById(R.id.password);
        msubmit = (Button) findViewById(R.id.submitbtn);

        mtoolbar = (Toolbar) findViewById(R.id.Register_toolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Create Account");
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        mRegprogress = new ProgressDialog(this);

        /*addemail();
        addname();
        addpass();
        addphone();*/

        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckNetwork.isInternetAvailable(SignupActivity.this)){
                if (!validate_email(memail) | !validate_password() | !validate_phone(mcontact) | !validation_name(mname)) {
                    return;
                } else {
                    mRegprogress.setMessage("we register your account");
                    mRegprogress.setCanceledOnTouchOutside(false);
                    mRegprogress.show();
                    mRegprogress.setCanceledOnTouchOutside(false);

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(memail.getText().toString(),
                            mpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                mailVerified();
                                /*sendUserData();
                                mRegprogress.dismiss();
                                firebaseAuth.signOut();
                                Toast.makeText(SignupActivity.this, "Successfully Registration", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));*/
                            } else {
                                mRegprogress.dismiss();
                                Toast.makeText(SignupActivity.this, "Registration Failed!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
            else {
                    Snackbar snackbar = Snackbar.make(msubmit, "No Internet Connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }
    private void mailVerified() {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(SignupActivity.this, "Successfully Registered,Please verified your mail", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    } else {
                        mRegprogress.dismiss();
                        Toast.makeText(SignupActivity.this, "Mail isn't sent...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }

    /*public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

*/
    /*public void addphone() {
        mcontact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String Contact = mcontact.getText().toString();
                if (Contact.isEmpty()) {
                    mcontact.setError("Phone number required");}
                    else if (Contact.length() < 10) {
                        mcontact.setError("Phone number is not valid");
                    }
                  else {
                    validate_phone(mcontact);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
*/
    public boolean validate_phone(View v) {
        String Contact = mcontact.getText().toString();
        if (Contact.isEmpty()) {
            mRegprogress.dismiss();
            mcontact.setError("Phone number required");
            return false;
        } else if (Contact.length() > 10 | Contact.length() < 10) {
            mRegprogress.dismiss();
            mcontact.setError("Phone number is not valid");
            return false;
        } else {
            mcontact.setError(null);
            return true;
        }
    }

   /* public void addemail() {
        memail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (memail.getText().toString().isEmpty()) {
                    memail.setError("Email field Can't be empty ");
                } else if (!memail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    memail.setError("Email is not valid");
                } else {
                    validate_email(memail);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/

    public boolean validate_email(View view) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String Email = memail.getText().toString();
        if (!Email.matches(emailPattern)) {
            mRegprogress.dismiss();
            memail.setError("Email is not valid");
            return false;
        } else if (Email.isEmpty()) {
            mRegprogress.dismiss();
            memail.setError("Email is required");
            return false;
        } else {
            memail.setError(null);
            return true;
        }

    }

 /*   public void addpass() {
        mpassword.addTextChangedListener(new TextWatcher() {
            String pass = mpassword.getText().toString();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pass.isEmpty()) {
                    mpassword.setError("Password field can't be Empty");

                } else if (mpassword.getText().toString().length() < 8) {
                    mpassword.setError("Password is too week");

                } else {
                    validate_password();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/

    public boolean validate_password() {
        String pass = mpassword.getText().toString();
        if (pass.isEmpty()) {
            mRegprogress.dismiss();
            mpassword.setError("Password field can't be Empty");
            return false;
        } else if (mpassword.getText().toString().length() < 8) {
            mRegprogress.dismiss();
            mpassword.setError("Password is too week");
            return false;
        } else {
            mpassword.setError(null);
            return true;
        }
    }

   /* public void addname() {
        mname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mname.getText().toString().isEmpty()) {
                    mname.setError("Last name is required");
                } else {
                    validation_name(mname);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/

    public boolean validation_name(View v) {
        if (mname.getText().toString().isEmpty()) {
            mRegprogress.dismiss();
            mname.setError("Last name is required");
            return false;
        } else {
            mname.setError(null);
            return true;
        }
    }

   private void sendUserData()
    {
        mRegprogress.dismiss();
        FirebaseUser Current_user = FirebaseAuth.getInstance().getCurrentUser();
        // DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());

        String uid = Current_user.getUid();

        //userprofile user = new userprofile(mDisplayname.getText().toString(), mEmail.getText().toString());
        //myref.setValue(user);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(uid);
        //mDatabase1 = FirebaseDatabase.getInstance().getReference().child("reportmsg").child(uid);

        String display_name = mname.getText().toString();
        String email = memail.getText().toString();
        String contact_no = mcontact.getText().toString();
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("name", display_name);
        userMap.put("email", email);
        userMap.put("contact_no", contact_no);
        /*userMap.put("text", contact_no);*/

        /*HashMap<String, String> userMap1 = new HashMap<>();
        userMap1.put("name", display_name);
        userMap1.put("email", email);*/

        mDatabase.setValue(userMap);
        //mDatabase1.setValue(userMap1);
    }
}
