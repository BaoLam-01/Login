package com.example.login;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.object.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tv3;

    private static EditText edtEmailSI;
    private static EditText edtPwSI;
    private Button btnSI, btnSU;

    private static ArrayList<User> listUser;
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEmailSI = view.findViewById(R.id.edtEmailSI);
        edtPwSI = view.findViewById(R.id.edtPwSI);

        edtEmailSI.setText(mParam1);
        edtPwSI.setText(mParam2);


        btnSI = view.findViewById(R.id.btnSI);
        btnSI.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (edtEmailSI.getText().toString().trim().isEmpty() ||
                        edtPwSI.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Ten dang nhap hoac mat khau khong duoc de trong!", LENGTH_SHORT).show();
                }else{
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.putData();

                    Log.d(TAG, "onClick: " + checkEmailAndPassword(edtEmailSI.getText().toString().trim(),edtPwSI.getText().toString().trim())  );

                    if (checkEmailAndPassword(edtEmailSI.getText().toString().trim(),edtPwSI.getText().toString().trim())) {
                        InfAccount infAccount = InfAccount.newInstance(edtEmailSI.getText().toString().trim(), null);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frContainer, infAccount)
                                .commit();
                        Toast.makeText(getContext(), edtEmailSI.getText().toString(), LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getContext(), "Ten dang nhap hoac mat khau khong dung!", LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnSU = view.findViewById(R.id.btnSU);
        btnSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment = RegisterFragment.newInstance(null, null);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frContainer, registerFragment)
//                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    public boolean checkEmailAndPassword(String email, String password) {
        for (User i : listUser) {
            Log.d(TAG, "checkEmailAndPassword: " + i.getId() + i.getEmail() + i.getPassword() + i.getAddress());
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static void getData(ArrayList<User> list) {
        listUser = list;
    }



}