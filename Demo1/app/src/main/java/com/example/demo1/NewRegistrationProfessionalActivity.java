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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewRegistrationProfessionalActivity extends AppCompatActivity implements AsyncTaskCompleteListener<String>{

    private TextView mTermsNServicesTv;

    private String mTermsOfServiceNprivacyPolicyText;

    private Button mSignUpBtn;

    @Override
    public void onTaskComplete(String result) {

        Log.d("Result",result);

    }

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

//        new CommonAsyncTask(getApplicationContext(),this).execute();

        mSignUpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Sign up button clicked",Toast.LENGTH_LONG).show();

            }
        });



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor

        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:8000/api/v1/get-country-list/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);
        RequestBody body = RequestBody.create(null, "");


        Call<CountryCodeResponse> call = retrofitServices.getCountryList();

        call.enqueue(new Callback<CountryCodeResponse>() {
            @Override
            public void onResponse(Call<CountryCodeResponse> call, Response<CountryCodeResponse> response) {
                Log.d("","");
            }

            @Override
            public void onFailure(Call<CountryCodeResponse> call, Throwable t) {
                Log.d("","");

            }

        });




    }
}


