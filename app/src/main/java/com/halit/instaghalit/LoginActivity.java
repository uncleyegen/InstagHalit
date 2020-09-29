package com.halit.instaghalit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;

    EditText username_et, password_et;
    TextView sign_up_btn,forgot_pass_btn;
    Button login_btn;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //animation drawable variable & config
        mLoginContainer = findViewById(R.id.login_container);
        mAnimationDrawable = (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(5000);
        mAnimationDrawable.setExitFadeDuration(2000);

        username_et = findViewById(R.id.user_name);
        password_et = findViewById(R.id.user_password);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        login_btn = findViewById(R.id.login_btn);
        forgot_pass_btn = findViewById(R.id.forgot_pass_btn);
        mProgressDialog = new ProgressDialog(this);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent signIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signIntent);
            }
        });

        forgot_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void logIn() {

        mProgressDialog.setTitle("Log In");
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();

        final String username = username_et.getText().toString();
        final String password = password_et.getText().toString();

        if (TextUtils.isEmpty(username)){
            username_et.setError("Please fill in this field");
            username_et.requestFocus();
            mProgressDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(password)){
            password_et.setError("Please fill in this field");
            password_et.requestFocus();
            mProgressDialog.dismiss();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.login_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{

                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")){

                                mProgressDialog.dismiss();

                                JSONObject jsonObjectUser = jsonObject.getJSONObject("user");

                                User user = new User(jsonObjectUser.getInt("id"),jsonObjectUser.getString("email"),jsonObjectUser.getString("username"));

                                // Store user data inside sharedPreference



                                // let user in
                                finish();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                            }else {

                                Toast.makeText(LoginActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){

                            e.printStackTrace();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> authenticationVariables = new HashMap<>();
                authenticationVariables.put("username",username);
                authenticationVariables.put("password",password);
                return authenticationVariables;
            }
        };// End of String Request

            VolleyHandler.getInstance(getApplicationContext());

    }

    @Override
    protected void onPostResume() {

        super.onPostResume();

        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }
}
