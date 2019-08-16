package com.example.hungwheel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddRestaurant extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference reference;

    RadioGroup service, owner_manager_group, opening_status, payment,foodtype;
    CheckBox monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    LinearLayout check_layout;
    EditText restaurant_name, city_name, restaurant_no, address, from_time, to_time, edtmoney;
    Spinner spinner;
    Button submit;


    String[] strLang = {"Select Category", "Food Trucks", "Cafes", "Late Night", "Buffets", "Street Food", "Tiffin Services"};

    private String resname;
    private String cityname;
    private String resnumber;
    private String resaddress;
    private String fromtime;
    private String totime;
    private String money;
    private String strUid;

    ArrayList<String> stringArrayList;
    private int radioOwnerId;
    private int radioServiceId;
    private int radioPaymentId;
    private int radioOpeningId;
    private int radioFoodtypeId;
    private RadioButton radioOpening;
    private RadioButton radioOwner;
    private RadioButton radioService;
    private RadioButton radioPayment;
    private RadioButton radioFoodtype;
    private String strdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        /*FirebaseApp.initializeApp(this);*/
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Restaurant");

        SharedPreferences sharedPreferences = getSharedPreferences("HUNGRY_WHEELS", Context.MODE_PRIVATE);
        strUid = sharedPreferences.getString("USER_UID_KEY", "");
        Log.e("ACCC", "uid" + strUid);

        stringArrayList = new ArrayList<String>();
        service = findViewById(R.id.service);
       // seating = findViewById(R.id.seating);
        //noseating = findViewById(R.id.noseating);
       // indoor = findViewById(R.id.indoor_seating);
        //outdoor = findViewById(R.id.outdoor_seating);
        check_layout = findViewById(R.id.checkbox_layout);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);
        restaurant_name = findViewById(R.id.restaurant_name);
        city_name = findViewById(R.id.city_name);
        restaurant_no = findViewById(R.id.restaurant_no);
        address = findViewById(R.id.address);
        spinner = findViewById(R.id.categories);
        from_time = findViewById(R.id.from_time);
        to_time = findViewById(R.id.to_time);
        edtmoney = findViewById(R.id.edt_money);
        // add_time = findViewById(R.id.add_time);
        owner_manager_group = findViewById(R.id.owner_manager_group);
       // opening_soon = findViewById(R.id.opening_soon);
        opening_status = findViewById(R.id.opening_status);
        payment = findViewById(R.id.payment);
        foodtype = findViewById(R.id.Foodtype);
        //owner_manager = findViewById(R.id.owner_manager);
        //not_owner_manager = findViewById(R.id.not_owner_manager);
        //already_open = findViewById(R.id.already_open);
        //card_cash = findViewById(R.id.card_cash);
        //cash_only = findViewById(R.id.cash_only);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strLang);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                     strdata = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }
/*

    private boolean validate() {
        if (resname.isEmpty()) {
            restaurant_name.setError("Enter Restaurant Name");
            return false;
        } else if (cityname.isEmpty()) {
            city_name.setError("Enter City Name");
            return false;
        } else if (resnumber.isEmpty()) {
            restaurant_no.setError("Enter Restaurant No.");
            return false;
        } else if (!owner_manager.isChecked() && !not_owner_manager.isChecked()) {
            Toast.makeText(AddRestaurant.this, "Please select a type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!already_open.isChecked() && !opening_soon.isChecked()) {
            Toast.makeText(AddRestaurant.this, "Please Select opening status", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!seating.isChecked() && !noseating.isChecked()) {
            Toast.makeText(AddRestaurant.this, "Please select services", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!card_cash.isChecked() && !cash_only.isChecked()) {
            Toast.makeText(AddRestaurant.this, "Please select Payment method", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.submit:

                if (monday.isChecked()) {
                    stringArrayList.add(monday.getText().toString());
                }
                if (tuesday.isChecked()) {
                    stringArrayList.add(tuesday.getText().toString());
                }
                if (wednesday.isChecked()) {
                    stringArrayList.add(wednesday.getText().toString());
                }
                if (thursday.isChecked()) {
                    stringArrayList.add(thursday.getText().toString());
                }
                if (friday.isChecked()) {
                    stringArrayList.add(friday.getText().toString());
                }
                if (saturday.isChecked()) {
                    stringArrayList.add(saturday.getText().toString());
                }
                if (sunday.isChecked()) {
                    stringArrayList.add(sunday.getText().toString());
                }


                radioOwnerId = owner_manager_group.getCheckedRadioButtonId();
                radioServiceId = service.getCheckedRadioButtonId();
                radioPaymentId = payment.getCheckedRadioButtonId();
                radioOpeningId = opening_status.getCheckedRadioButtonId();
                radioFoodtypeId = foodtype.getCheckedRadioButtonId();
                radioOwner = (RadioButton) findViewById(radioOwnerId);
                radioService = (RadioButton) findViewById(radioServiceId);
                radioPayment = (RadioButton) findViewById(radioPaymentId);
                radioOpening = (RadioButton) findViewById(radioOpeningId);
                radioFoodtype = (RadioButton)findViewById(radioFoodtypeId);

               /* resname = sharedPreferences.getString("USER_RESNAME_KEY","");
                cityname = sharedPreferences.getString("USER_CITY_KEY","");
                owner = sharedPreferences.getString("USER_OWNER_KEY","");
                notowner = sharedPreferences.getString("USER_NOTOWNER_KEY","");
                resnumber = sharedPreferences.getString("USER_RESNUMB_KEY","");
                placeopen = sharedPreferences.getString("USER_PLACEOPEN_KEY","");
                placeopensoon = sharedPreferences.getString("USER_NOTOPEN_KEY","");
                resaddress = sharedPreferences.getString("USER_RESADD_KEY","");
                seat = sharedPreferences.getString("USER_SEAT_KEY","");
                noseat = sharedPreferences.getString("USER_NOSEAT_KEY","");
                cashandcard = sharedPreferences.getString("USER_CASHCARD_KEY","");
                cashonly = sharedPreferences.getString("USER_CASHONLY_KEY","");
                category = sharedPreferences.getString("USER_CATEGORY_KEY","");
                mon = sharedPreferences.getString("USER_MON_KEY","");
                tue = sharedPreferences.getString("USER_TUE_KEY","");
                wed = sharedPreferences.getString("USER_WED_KEY","");
                thu = sharedPreferences.getString("USER_THU_KEY","");
                fri = sharedPreferences.getString("USER_FRI_KEY","");
                sat = sharedPreferences.getString("USER_SAT_KEY","");
                sun = sharedPreferences.getString("USER_SUN_KEY","");
                fromtime = sharedPreferences.getString("USER_FROM_KEY","");
                totime = sharedPreferences.getString("USER_TO_KEY","");
                btntime = sharedPreferences.getString("USER_TIME_KEY","");


*/



                resname = restaurant_name.getText().toString();
                cityname = city_name.getText().toString();
               // owner = owner_manager.getText().toString();
                //notowner = not_owner_manager.getText().toString();
                resnumber = restaurant_no.getText().toString();
                //open = already_open.getText().toString();
                //opensoon = opening_soon.getText().toString();
                resaddress = address.getText().toString();
                money = edtmoney.getText().toString();
                //seat = seating.getText().toString();
                //noseat= noseating.getText().toString();
                //cashcard = card_cash.getText().toString();
                //cashonly = cash_only.getText().toString();
                fromtime = from_time.getText().toString();
                totime = to_time.getText().toString();
                String strowner = radioOwner.getText().toString();
                String strservice = radioService.getText().toString();
                String strpayment = radioPayment.getText().toString();
                String stropening = radioOpening.getText().toString();
                String strfoodtype = radioFoodtype.getText().toString();




                RestModel resmodel = new RestModel();
                String strRe_Key = reference.push().getKey();

                resmodel.setResname(resname);
                resmodel.setCityname(cityname);
                resmodel.setMoney(money);
                 resmodel.setOwner(strowner);
                // resmodel.setNotowner(notowner);
                resmodel.setResnumber(resnumber);
                resmodel.setPlaceopen(stropening);
                //resmodel.setPlaceopensoon(opensoon);
                resmodel.setResaddress(resaddress);
                 resmodel.setSeat(strservice);
                 resmodel.setCategory(strdata);
                 //resmodel.setNoseat(noseat);
                resmodel.setPayment(strpayment);
               // resmodel.setCashonly(cashonly);
                resmodel.setFoodType(strfoodtype);
                 resmodel.setStringArrayList(stringArrayList);
                resmodel.setFromtime(fromtime);
                resmodel.setTotime(totime);
                resmodel.setRes_key(strRe_Key);
                resmodel.setStrUserUid(strUid);

                reference.child(strRe_Key).setValue(resmodel);

                Toast.makeText(this, "Restaurant has been registered", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

