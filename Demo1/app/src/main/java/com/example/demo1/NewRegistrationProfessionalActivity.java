package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
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

        SpannableStringBuilder spanTxt = new SpannableStringBuilder("By signing Up I agree to the ");
        spanTxt.append("Terms of service");
        spanTxt.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(getApplicationContext(), "Terms of services Clicked",
                            Toast.LENGTH_SHORT).show();
                }
            }, spanTxt.length() - "Terms of service".length(), spanTxt.length(), 0);
        spanTxt.append(" &");

        spanTxt.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.mediumSeaGreen)), 29, spanTxt.length(), 0);
        spanTxt.append(" Privacy Policy");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getApplicationContext(), "Privacy Policy Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        }, spanTxt.length() - "Privacy Policy".length(), spanTxt.length(), 0);

        spanTxt.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.mediumSeaGreen)), spanTxt.length() - " Privacy Policy".length(), spanTxt.length(), 0);


        mTermsNServicesTv.setHighlightColor(Color.TRANSPARENT);
        mTermsNServicesTv.setMovementMethod(LinkMovementMethod.getInstance());
        mTermsNServicesTv.setText(spanTxt, TextView.BufferType.SPANNABLE);


        mSignUpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });




    }




}


