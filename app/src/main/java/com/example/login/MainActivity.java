package com.example.login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.login.object.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<User> listUser = new ArrayList<>();
    private  int id = 1;

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        init();
        Log.d(TAG, "onCreate: " + listUser.size());


        show();





        HomeFragment homeFragment = HomeFragment.newInstance(null, null);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frContainer,homeFragment)
                .commit();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public static void getSize() {
        Log.d(TAG, "onCreate: " + listUser.size() + listUser.get(2).toString());
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }
        super.onBackPressed();
    }

    public void init() {
        if (listUser.isEmpty()){
            listUser.add(new User(id++, "lamdinh00689@gmail.com", "Ha Noi", "123456"));
            listUser.add(new User(id++, "lam", "Ha Noi", "1"));
        }

    }

    public static void addUser(User user) {
        Log.d(TAG, "addUser: " + user.getEmail() + user.getAddress() + user.getPassword());
        listUser.add(user);
    }


//    public static void checkEmail(String email) {
//        boolean z = false;
//
//
//        for (int i = 0; i < listUser.size(); i++) {
//            if (listUser.get(i).getEmail().equals(email)) {
//                z = true;
//                LoginFragment loginFragment = new LoginFragment();
//                loginFragment.getz(z);
//                break;
//            }
//        }
//    }



    public static void sendUser(String email, String password) {
//        LoginFragment fr = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.loginFr);
//        if (fr != null || fr.isInLayout()) {
        Log.d(TAG, "sendUser: " + email + password);
//            LoginFragment.updateFragment(email,password);
//            Toast.makeText(this, email + password, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Khong tim thay, hoac fragment khong hien", Toast.LENGTH_SHORT).show();
//        }
//
    }

    public void putData() {
            InfAccount.getData(listUser);
            SingUpFragment.getData(listUser);
            LoginFragment.getData(listUser);
    }

    public static void show() {
        for (User i :
                listUser) {
            System.out.println(i.getId() + "\n" +
                    i.getEmail() + "\n" +
                    i.getAddress() + "\n" +
                    i.getPassword() + "\n" );
        }
    }


}