package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewRegistrationProfessional extends AppCompatActivity {

    private TextView mTermsNServicesTv;

    private String mTermsOfServiceNprivacyPolicyText;




    public void getReferenceOfViews(){
        mTermsNServicesTv = (TextView) findViewById(R.id.id_terms_privacy_policy_tv);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_registration_professional);

        getReferenceOfViews();

        mTermsOfServiceNprivacyPolicyText   =  getResources().getString(R.string.terms_privacy_policy);



















    }
}
