package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewRegistrationProfessional extends AppCompatActivity {

    private TextView mTermsNServicesTv;

    private String mTermsOfServiceNprivacyPolicyText;

    private Button mSignUpBtn;


    public void getReferenceOfViews(){
        mTermsNServicesTv = (TextView) findViewById(R.id.id_terms_privacy_policy_tv);
        mSignUpBtn        = (Button) findViewById(R.id.id_sign_up_btn);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_registration_professional);

        getSupportActionBar().hide();
        getReferenceOfViews();

        mTermsOfServiceNprivacyPolicyText   =  getResources().getString(R.string.terms_privacy_policy);

        mSignUpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });


    }
}
