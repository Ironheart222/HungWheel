package com.example.hungwheel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText edtforgot;
    Button btnreset;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtforgot = (EditText)findViewById(R.id.forgotemail);
        btnreset =(Button)findViewById(R.id.btn_reset);
        mAuth = FirebaseAuth.getInstance();


        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stremail = edtforgot.getText().toString().trim();

                if (stremail.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail(stremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordActivity.this,loginActivity.class));
                            }else {
                                Toast.makeText(ForgotPasswordActivity.this, "Error occured in sending the email", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });

    }
}
