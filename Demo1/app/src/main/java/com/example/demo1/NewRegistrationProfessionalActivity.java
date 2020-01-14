package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewRegistrationProfessionalActivity extends AppCompatActivity {

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

        SpannableString ss = new SpannableString(mTermsOfServiceNprivacyPolicyText);
        ClickableSpan clickableTerms = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                // show toast here

                Toast.makeText(getApplicationContext(),"Terms of service clicked",Toast.LENGTH_LONG).show();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

            }
        };
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 29, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 47,ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        mTermsNServicesTv.setText(ss);
        mTermsNServicesTv.setMovementMethod(LinkMovementMethod.getInstance());
        mTermsNServicesTv.setHighlightColor(Color.TRANSPARENT);


        mSignUpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });


    }
}
