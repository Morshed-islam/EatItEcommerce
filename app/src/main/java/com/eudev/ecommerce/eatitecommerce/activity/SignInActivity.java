package com.eudev.ecommerce.eatitecommerce.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eudev.ecommerce.eatitecommerce.R;
import com.eudev.ecommerce.eatitecommerce.model.Common;
import com.eudev.ecommerce.eatitecommerce.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;

public class SignInActivity extends AppCompatActivity {

    private MaterialAutoCompleteTextView phoneNum, password;
    private Button btnSingIn;
    private String TAG = "signInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phoneNum = (MaterialAutoCompleteTextView) findViewById(R.id.phoneNum);
        password = (MaterialAutoCompleteTextView) findViewById(R.id.pass);

        btnSingIn = (Button) findViewById(R.id.btnSignIn);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
                progressDialog.setMessage("Loading.....");
                progressDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phoneNum.getText().toString()).exists()) {
                            progressDialog.dismiss();
                            User user = dataSnapshot.child(phoneNum.getText().toString()).getValue(User.class);

                            Log.i(TAG + "onDataChange  User ---", "" + phoneNum.getText().toString());
                            Log.i(TAG + "onDataChange  User ---", "" + user.getPass());


                            if (user.getPass().equals(password.getText().toString())) {

                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                Common.currentUser = user;
                                startActivity(intent);
                                finish();

                            } else {

                                progressDialog.dismiss();
                                Log.i(TAG + "onDataChange  inside else statement ---", "" + user);
                                Toast.makeText(SignInActivity.this, "Wrong Pass", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "Data not Exists", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Log.i(TAG + "onCanceled , Error is Here---", "" + databaseError);

                    }
                });

            }
        });


    }
}





 /*   final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
                progressDialog.setMessage("Loading.....");
                        progressDialog.show();


                        table_user.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        //check if data not exist
        if (dataSnapshot.child(phoneNum.getText().toString()).exists()) {
        progressDialog.dismiss();

        User user = dataSnapshot.child(phoneNum.getText().toString()).getValue(User.class);

        if (user.getPass().equals(password.getText().toString())) {
        Toast.makeText(SignInActivity.this, "Sign In", Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(SignInActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }

        } else {
        Toast.makeText(SignInActivity.this, "Data not Exists", Toast.LENGTH_SHORT).show();

        }*/
