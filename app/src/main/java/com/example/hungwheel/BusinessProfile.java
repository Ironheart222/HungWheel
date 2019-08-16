package com.example.hungwheel;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class BusinessProfile extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth;
    DatabaseReference reference;

    Button btnSave;
    EditText name;
    TextView email;
    TextView userProfileName;
    EditText phone;
    EditText location;
    EditText address;
    private String strUid;
    ImageView editProfilePic,userProfilePhoto;

    StorageReference mStorage;
    DatabaseReference dbRef;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private ProgressDialog pd;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_business_profile, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Register");
        mAuth = FirebaseAuth.getInstance();

        userProfileName = rootview.findViewById(R.id.user_profile_name);
        btnSave = (Button) rootview.findViewById(R.id.btnsave);
        name = (EditText) rootview.findViewById(R.id.edtname);
        email = (TextView) rootview.findViewById(R.id.edtemail);
        phone = (EditText) rootview.findViewById(R.id.edtphone);
        location = (EditText) rootview.findViewById(R.id.edtlocation);
        address = (EditText) rootview.findViewById(R.id.edtaddress);
        editProfilePic = rootview.findViewById(R.id.edit_profilepic);
        userProfilePhoto = rootview.findViewById(R.id.user_profile_photo);
        pd = new ProgressDialog(getActivity());

        mStorage = FirebaseStorage.getInstance().getReference().child("Profile_Photo").child(mAuth.getCurrentUser().getUid());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("HUNGRY_WHEELS", Context.MODE_PRIVATE);
        strUid = sharedPreferences.getString("USER_UID_KEY", "");
       // Log.e("ACCC",""+strUid);
        String strfn = sharedPreferences.getString("USER_FN_KEY", "");
        String strln = sharedPreferences.getString("USER_LN_KEY", "");

        editProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    UserModel post = userSnapshot.getValue(UserModel.class);
                    String strphn = post.getMobileno().toString();
//                    String strlocation = post.ge().toString();
//                    String stradd = post.get().toString();
                    String stremail = post.getEmail().toString();

                    phone.setText(strphn);
//                    location.setText(strlocation);
//                    address.setText(stradd);
                    email.setText(stremail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        String strphn = sharedPreferences.getString("USER_PHONE_KEY", "");
//        String strlocation = sharedPreferences.getString("USER_LOCATION_KEY", "");
//        String stradd = sharedPreferences.getString("USER_ADDRESS_KEY", "");
//        String stremail = sharedPreferences.getString("USER_EMAIL_KEY", "");


        userProfileName.setText(strfn + " " + strln);
        name.setText(strfn + " " + strln);


        btnSave.setOnClickListener(this);

        return rootview;
    }

    private void selectImage() {

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");

        //SET ITEMS AND THERE LISTENERS
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Profile");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnsave:

                String edtname = name.getText().toString();
                String edtphone = phone.getText().toString();
                String edtlocation = location.getText().toString();
                String edtaddress = address.getText().toString();
                // String edtemail = email.getText().toString();


                reference.child(strUid).child("fn").setValue(edtname);
                reference.child(strUid).child("mobileno").setValue(edtphone);
                reference.child(strUid).child("location").setValue(edtlocation);
                reference.child(strUid).child("address").setValue(edtaddress);
                //reference.child(strUid).child("email").setValue(edtemail);

//                 String edtemail = dataSnapshot.child(strUid).child("email").getValue().toString();
//                  String edtphone = dataSnapshot.child(strUid).child("mobileno").getValue().toString();
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

    private void cameraIntent() {

        //CHOOSE CAMERA
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }else {
                ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 0);
        }

    }

    private void galleryIntent() {

        //CHOOSE IMAGE FROM GALLERY
//        Log.d("gola", "entered here");

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //SAVE URI FROM GALLERY
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();

            pd.setMessage("Uploading...");
            pd.show();
//            imageHoldUri = result.getUri();


            final StorageReference filePath1 =
                    mStorage.child(imageUri.getLastPathSegment());
            filePath1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override                        public void onSuccess(Uri uri) {

                            String downloadUrl = uri.toString();
                            UserModel userModel = new UserModel();
                            userModel.setProfilePhoto(downloadUrl);

                            Glide.with(getActivity()).load(downloadUrl).apply(RequestOptions.circleCropTransform()).into(userProfilePhoto);

                            pd.dismiss();


                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    pd.setMessage(((int) progress) + "% Uploading..");

                }
            });

//            CropImage.activity(imageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1,1)
//                    .start(this);

        } else if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            //SAVE URI FROM CAMERA

            final Uri imageUri = data.getData();

            pd.setMessage("Uploading...");
            pd.show();
//            imageHoldUri = result.getUri();


            final StorageReference filePath1 = mStorage.child(mAuth.getCurrentUser().getUid() + ".jpg");
            filePath1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String downloadUrl = uri.toString();
                            UserModel userModel = new UserModel();
                            userModel.setProfilePhoto(downloadUrl);

                            Glide.with(getActivity()).load(downloadUrl).apply(RequestOptions.circleCropTransform()).into(userProfilePhoto);

                            pd.dismiss();
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    pd.setMessage(((int) progress) + "% Uploading..");

//                    userProfilePhoto.setImageURI(imageUri);
                }
            });

//            CropImage.activity(imageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1,1)
//                    .start(this);

        }
    }
}