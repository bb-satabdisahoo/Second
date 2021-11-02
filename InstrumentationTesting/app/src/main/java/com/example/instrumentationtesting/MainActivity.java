package com.example.instrumentationtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instrumentationtesting.databinding.ActivityMainBinding;
import com.example.instrumentationtesting.db.UserEntity;
import com.example.instrumentationtesting.validator.UserAddressValidator;
import com.example.instrumentationtesting.validator.UserNameValidator;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding activityMainBinding;
    private UserNameValidator userNameValidator;
    private UserAddressValidator userAddressValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        userNameValidator = new UserNameValidator();
        activityMainBinding.userName.addTextChangedListener(userNameValidator);
        userAddressValidator = new UserAddressValidator();
        activityMainBinding.userAddress.addTextChangedListener(userAddressValidator);

        activityMainBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate();
                if(!userNameValidator.isValid()){
                    activityMainBinding.userName.setError("Invalid User Name");
                    return;
                }
                else if(!userAddressValidator.isValid()){
                    activityMainBinding.userAddress.setError("Invalid Address");
                    return;
                }
                else{
                    save();
                }
            }

        });

        activityMainBinding.viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    private void save() {
        String userName = activityMainBinding.userName.getText().toString();
        String userAddress = activityMainBinding.userAddress.getText().toString();

        UserEntity user = new UserEntity(userName,userAddress);
        //user.setName(userName);
        //user.setAddress(userAddress);
        viewModel= new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.insertUser(user);
        Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();
    }

    /*private void validate() {

        boolean[] array = new boolean[2];
        for(int i=0;i<2;i++)
            array[i] = false;

        //user name validation
        String name = activityMainBinding.userName.getText().toString();
        if(name == null)
            activityMainBinding.userName.setError("Field can't be empty");
        else if(name.length()>15)
            activityMainBinding.userName.setError("Can't exceed more than 15 words");
        else if(name.matches("[a-z A-Z]+")==false)
            activityMainBinding.userName.setError("Enter valid Character");
        else
            array[0]=true;


        //address validation
        String inputAddress = activityMainBinding.userAddress.getText().toString();
        if(inputAddress==""){
            activityMainBinding.userAddress.setError("Field can't be empty");
        }
        else if(inputAddress.matches("[a-z A-Z].*[,][a-z A-Z].*")==false){
            activityMainBinding.userAddress.setError("can contain only ,");
        }
        else{
            array[1]=true;
        }

        for(int i=0;i<2;i++)
        {
            if(array[i]==false)
            {
                Toast.makeText(this,"Please Enter Valid details", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String userName = activityMainBinding.userName.getText().toString();
        String userAddress = activityMainBinding.userAddress.getText().toString();

        UserEntity user = new UserEntity();
        user.setName(userName);
        user.setAddress(userAddress);
        viewModel= new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.insertUser(user);
        Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();
    }*/

    private void start() {
        Intent intent = new Intent(this, show.class);
        startActivity(intent);
    }
}