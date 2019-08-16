package com.example.hungwheel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth;
    DatabaseReference reference;

    Button btnSave;
    EditText name;
    TextView email;
    EditText phone;
    EditText location;
    EditText address;
    private String strUid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_account, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Register");

        btnSave = (Button) rootview.findViewById(R.id.btn_save);
        name =  (EditText)rootview.findViewById(R.id.edtname);
        email =  rootview.findViewById(R.id.edtemail);
        phone = (EditText) rootview.findViewById(R.id.edtphone);
        location = (EditText) rootview.findViewById(R.id.edtlocation);
        address = (EditText) rootview.findViewById(R.id.edtaddress);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("HUNGRY_WHEELS", Context.MODE_PRIVATE);
         strUid = sharedPreferences.getString("USER_UID_KEY", "");
        Log.e("ACCC",""+strUid);
         String strfn = sharedPreferences.getString("USER_FN_KEY", "");
         String strln = sharedPreferences.getString("USER_LN_KEY", "");
        String strphn = sharedPreferences.getString("USER_PHONE_KEY", "");
        String strlocation = sharedPreferences.getString("USER_LOCATION_KEY", "");
        String stradd = sharedPreferences.getString("USER_ADDRESS_KEY", "");
        String stremail = sharedPreferences.getString("USER_EMAIL_KEY", "");


        name.setText(strfn+" "+strln);
        phone.setText(strphn);
        location.setText(strlocation);
        address.setText(stradd);
        email.setText(stremail);

        btnSave.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Account");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_save:

                String edtname =  name.getText().toString();
                String edtphone = phone.getText().toString();
                String edtlocation = location.getText().toString();
                String edtaddress = address.getText().toString();
                String edtemail = email.getText().toString();


                        reference.child(strUid).child("fn").setValue(edtname);
                        reference.child(strUid).child("mobileno").setValue(edtphone);
                        reference.child(strUid).child("location").setValue(edtlocation);
                        reference.child(strUid).child("address").setValue(edtaddress);
                        reference.child(strUid).child("email").setValue(edtemail);

                       // String edtemail = dataSnapshot.child(strUid).child("email").getValue().toString();
                      //  String edtphone = dataSnapshot.child(strUid).child("mobileno").getValue().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("HUNGRY_WHEELS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
               // editor.putString("USER_UID_KEY", strname);
                editor.putString("USER_FN_KEY", edtname);
                editor.putString("USER_PHONE_KEY", edtphone);
                editor.putString("USER_LOCATION_KEY", edtlocation);
                editor.putString("USER_ADDRESS_KEY", edtaddress);
                //editor.putString("USER_EMAIL_KEY", edtemail);
             //   editor.putString("USER_LN_KEY", strln);
              //  editor.putString("USER_ROLLETYPE", strRolletype);
                editor.commit();



                break;


        }

    }


}
