package com.example.login;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.object.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText edtEmailSU, edtAdrSU, edtPwSU, edtCfPwSU;
    private Button btnSU2, btnSI2;
    private static ArrayList<User> listUser;

    public SingUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingUpFragment newInstance(String param1, String param2) {
        SingUpFragment fragment = new SingUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEmailSU = view.findViewById(R.id.edtEmailSU);
        edtAdrSU = view.findViewById(R.id.edtAdrSU);
        edtPwSU = view.findViewById(R.id.edtPwSU);
        edtCfPwSU = view.findViewById(R.id.edtCfPwSU);


        btnSI2 = view.findViewById(R.id.btnSI2);
        btnSI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginFragment loginFragment = LoginFragment.newInstance(null, null);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frContainer, loginFragment)
                        .commit();
            }

        });
        btnSU2 = view.findViewById(R.id.btnSU2);
        btnSU2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.putData();
                if (edtEmailSU.getText().toString().trim().isEmpty() ||
                        edtPwSU.getText().toString().trim().isEmpty() ||
                        edtAdrSU.getText().toString().trim().isEmpty() ||
                        edtPwSU.getText().toString().trim().isEmpty() ||
                        edtCfPwSU.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Thong tin khong duoc de trong!", LENGTH_SHORT).show();
                } else {
                    if (edtEmailSU.getText().toString().trim().length() < 11 ||
                            !edtEmailSU.getText().toString().substring(
                                    edtEmailSU.getText()
                                            .toString()
                                            .length() - 10).equals("@gmail.com")) {

                        Toast.makeText(getContext(), "Email khong dung dinh dang!", LENGTH_SHORT).show();

                    } else if (checkEmail(edtEmailSU.getText().toString().trim())) {

                        Toast.makeText(getContext(), "Email da ton tai hay nhap mot Email khac!", LENGTH_SHORT).show();

                    } else if (edtPwSU.getText().toString().length() < 6 ||
                            edtPwSU.getText().toString().indexOf(" ") != -1) {
                        Toast.makeText(getContext(), "Luu y mat khau phai nhieu hon 6 ky tu va " +
                                "khong chua khoang trang!", LENGTH_SHORT).show();
                    } else if (!edtCfPwSU.getText().toString().equals(edtPwSU.getText().toString())) {

                        Toast.makeText(getContext(), "Mat khau khong trung khop!", LENGTH_SHORT).show();
                    } else {
                        User user = new User(listUser.size()+1, edtEmailSU.getText().toString().trim(),
                                edtAdrSU.getText().toString().trim(),
                                edtPwSU.getText().toString().trim());
                        Log.d(TAG, "onClick: " + user.getEmail() + user.getEmail() + user.getPassword() +
                                edtEmailSU.getText().toString());
                        MainActivity.addUser(user);
//                        MainActivity.getSize();
                        MainActivity.show();
                        Toast.makeText(getContext(),"Sign Up Successfully",LENGTH_SHORT).show();



                        LoginFragment loginFragment = LoginFragment.newInstance(edtEmailSU.getText().toString().trim(), edtPwSU.getText().toString().trim());
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frContainer, loginFragment)
                                .commit();
                    }
                }
            }
        });

    }

    public boolean checkEmail(String email) {
        for (User i : listUser) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public static void getData(ArrayList<User> list) {
        listUser = list;
    }
}