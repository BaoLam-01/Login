package com.example.login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.login.object.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfAccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView, textView1;
    public static ArrayList<User> listUser;
    public InfAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static InfAccount newInstance(String param1, String param2) {
        InfAccount fragment = new InfAccount();
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
        return inflater.inflate(R.layout.fragment_inf_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        textView = view.findViewById(R.id.tvIf);
        textView1 = view.findViewById(R.id.tvHl);
        MainActivity mainActivity = new MainActivity();
        mainActivity.putData();

        for (User i : listUser) {
            if (i.getEmail().equals(mParam1)) {
                textView1.setText("Xin chao " + i.getEmail()+ "!");
                textView.setText(
                        "Thong tin khach hang: \n" +
                        "User Id: " + i.getId() + "\n"+
                        "Email: " + i.getEmail() +"\n"+
                        "Address: " + i.getAddress()
                );
            }
        }

    }

    public static void getData(ArrayList<User> list) {
        listUser = list;
    }

}