package com.mmproduction.emsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountsettingActivity extends AppCompatActivity {

    private DatabaseReference mUserdatabase;
    private FirebaseUser mCurrentuser;

    private static  final int GALLARY_PICK = 1;
    //android layout
    private CircleImageView mDisplayimage;
    private TextView mDisplayname,mDisplayemail,mDisplaycontact;
   // private Button mChangeimagebtn;

    //storage
    private StorageReference mImagestorage;

    //progress dialog
    private ProgressDialog mProgressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);

        mDisplayimage = (CircleImageView)findViewById(R.id.setting_profile_image);
        mDisplayname = (TextView) findViewById(R.id.setting_profile_name);
        mDisplaycontact = (TextView) findViewById(R.id.setting_profile_contact);
        //mDisplayemail = (TextView)findViewById(R.id.setting_profile_email);
     //   mChangeimagebtn = (Button)findViewById(R.id.setting_changeimage);

        mImagestorage = FirebaseStorage.getInstance().getReference();

        mCurrentuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrentuser.getUid();
        mUserdatabase = FirebaseDatabase.getInstance().getReference().child("user").child(uid);

        mUserdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(SettingsActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name = dataSnapshot.child("user").child("name").getValue().toString();
                String email = dataSnapshot.child("user").child("email").getValue().toString();
                String contact = dataSnapshot.child("user").child("contact no").getValue().toString();
                //String image = dataSnapshot.child("images").getValue().toString();
               // String thumb_image = dataSnapshot.child("thumb_image").getValue().toString();


                mDisplayname.setText(name);
                mDisplayemail.setText(email);
                mDisplaycontact.setText(contact);


                //Picasso.get().load(image).into(mDisplayimage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //for handle error while retriving data
            }
        });



       /* mChangeimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start picker to get image for cropping and then use the image in cropping activity
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(AccountsettingActivity.this);


            }
        });*/
    }


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLARY_PICK && requestCode == RESULT_OK) {


            Uri imageuri = data.getData();

            // start cropping activity for pre-acquired image saved on the device
            CropImage.activity(imageuri)
                    .start(this);
        }

        //a loop ma nathi jatu
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mProgressdialog = new ProgressDialog(AccountsettingActivity.this);
                mProgressdialog.setMessage("uploading image");
                mProgressdialog.show();

                Uri resultUri = result.getUri();

                String current_user_id = mCurrentuser.getUid();

                StorageReference filepath = mImagestorage.child("profile_images").child(current_user_id + ".jpg  ");

                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){


                            //Toast.makeText(SettingsActivity.this,"working",Toast.LENGTH_LONG).show();
                            String  download_uri = task.getResult().toString();
                            mUserdatabase.child("image").setValue(download_uri).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mProgressdialog.dismiss();
                                        Toast.makeText(AccountsettingActivity.this,"success uploading",Toast.LENGTH_LONG).show();;
                                    }
                                }
                            });

                        }
                        else {
                            Toast.makeText(AccountsettingActivity.this,"error in upload",Toast.LENGTH_LONG).show();
                            mProgressdialog.dismiss();

                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.v("6","hiiii");
                Exception error = result.getError();
            }
        }
        else{
            Log.v("9","hiiii");
        }


    }

    public static String random(){
        Random generator =new Random();
        StringBuilder randomStringBuilder= new StringBuilder();

        int randomLength = generator.nextInt(30);

        char tempchar;

        for(int i=0 ; i<randomLength; i++)
        {
            tempchar  = (char)(generator.nextInt(96)+32);
            randomStringBuilder.append(tempchar);
        }
        return randomStringBuilder.toString();

    }*/
}
