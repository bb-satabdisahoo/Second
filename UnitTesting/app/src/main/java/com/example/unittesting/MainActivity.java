package com.example.unittesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.example.unittesting.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    //SharedPreferences sharedPreferences;
    private UserNameValidator userNameValidator;
    private PasswordValidator passwordValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        userNameValidator = new UserNameValidator();
        activityMainBinding.userName.addTextChangedListener(userNameValidator);
        passwordValidator = new PasswordValidator();
        activityMainBinding.userPassword.addTextChangedListener(passwordValidator);
        
        activityMainBinding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate();
                if(!userNameValidator.isValid()){
                    activityMainBinding.userName.setError("Invalid User Name");
                    return;
                }
                else if(!passwordValidator.isValid()){
                    activityMainBinding.userPassword.setError("Invalid Password");
                    return;
                }
                else{
                    String userName = activityMainBinding.userName.getText().toString();
                    String userPassword = activityMainBinding.userPassword.getText().toString();

                    //sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
                    //SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("Name", userName);
                    //editor.putString("Password", userPassword);
                    //editor.apply();

                    //Intent intent = new Intent(getApplicationContext(),Show.class);
                    //startActivity(intent);
                    Intent intent = new Intent(MainActivity.this, Show.class);
                    Bundle extras = new Bundle();
                    extras.putString("Name", userName);
                    extras.putString("Password", userPassword);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }

    private void validate() {
        //userNameValidate();
        //passwordValidate();
        if(userNameValidate() && passwordValidate()){
            String userName = activityMainBinding.userName.getText().toString();
            String userPassword = activityMainBinding.userPassword.getText().toString();

            //sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
           // //SharedPreferences.Editor editor = sharedPreferences.edit();
           // editor.putString("Name", userName);
            //editor.putString("Password", userPassword);
            //editor.apply();

            //Intent intent = new Intent(getApplicationContext(),Show.class);
           // startActivity(intent);
            Intent intent = new Intent(MainActivity.this, Show.class);
            Bundle extras = new Bundle();
            extras.putString("Name", userName);
            extras.putString("Password", userPassword);
            intent.putExtras(extras);
            startActivity(intent);
        }
        else
            return;
    }

    private boolean userNameValidate(){

        String name = activityMainBinding.userName.getText().toString();
        boolean result = false;
        if(name == null)
            activityMainBinding.userName.setError("Field can't be empty");
        else if(name.length()>15)
            activityMainBinding.userName.setError("Can't exceed more than 15 words");
        else if(name.matches("[a-z A-Z]+")==false)
            activityMainBinding.userName.setError("Enter valid Character");
        else
            result = true;
        return result;
    }

    private boolean passwordValidate() {

        String password = activityMainBinding.userPassword.getText().toString();
        boolean result = false;
        boolean b = password.matches("[A-Z]{1}.*[! | @ | # | $ | %| ^ | & | * | ( |)| _ | \\- | + | =|]{1}.");
        boolean b1 = password.matches(".+\\d.*");
        b = b&&b1;
        if(password=="")
            activityMainBinding.userPassword.setError("Field can't be empty");
        else if(b==false)
            activityMainBinding.userPassword.setError("Should start with capital letter and contain at least one special character,number");
        else
            result = true;
        return result;

    }

}