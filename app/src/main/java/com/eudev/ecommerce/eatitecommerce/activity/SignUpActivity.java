package com.eudev.ecommerce.eatitecommerce.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eudev.ecommerce.eatitecommerce.R;
import com.eudev.ecommerce.eatitecommerce.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;

public class SignUpActivity extends AppCompatActivity {

    private MaterialAutoCompleteTextView edName, edPhone, edPass;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edName = (MaterialAutoCompleteTextView) findViewById(R.id.edName);
        edPhone = (MaterialAutoCompleteTextView) findViewById(R.id.edPhoneNum);
        edPass = (MaterialAutoCompleteTextView) findViewById(R.id.edPass);

        signUp = (Button) findViewById(R.id.btnSignUp);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edPhone.getText().toString()).exists()) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Data is Exists", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            User user = new User(edName.getText().toString(), edPass.getText().toString());
                            table_user.child(edPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Added Succecsfully", Toast.LENGTH_SHORT).show();
                            finish();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}
