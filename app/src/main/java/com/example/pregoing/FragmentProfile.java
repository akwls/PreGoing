package com.example.pregoing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentProfile extends Fragment {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText edtLoginID, edtLoginPW;
    Button btnSignUp, btnLogin;
    String strid, strpw;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        edtLoginID = v.findViewById(R.id.edtLoginID);
        edtLoginPW = v.findViewById(R.id.edtLoginPW);
        btnSignUp = v.findViewById(R.id.btnSignUp);
        btnLogin = v.findViewById(R.id.btnLogin);

        context = container.getContext();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strid = edtLoginID.getText().toString();
                strpw = edtLoginPW.getText().toString();
                if(preferences.getString(strid, "").equals(null)) {
                    editor = preferences.edit();
                    editor.putString(strid, strpw);
                    editor.commit();
                    Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strid = edtLoginID.getText().toString();
                strpw = edtLoginPW.getText().toString();
                String strCheck;
                String pwCheck = preferences.getString(strid, "");
                if(strpw.equals(pwCheck)) {
                    strCheck = "로그인 성공";
                    ((MainActivity)getActivity()).replaceFragment(FragmentSearch.newInstance());
                }
                else {
                    strCheck = "로그인 실패";
                    edtLoginID.setText("");
                    edtLoginPW.setText("");
                }
                Toast.makeText(context, strCheck, Toast.LENGTH_SHORT).show();
            }

        });

        return v;
    }
}
