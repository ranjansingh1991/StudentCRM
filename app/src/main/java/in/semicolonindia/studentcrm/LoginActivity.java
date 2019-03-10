package in.semicolonindia.studentcrm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.semicolonindia.studentcrm.StudentActivities.SupportActivity;
import in.semicolonindia.studentcrm.dialogs.NoConnectionDialog;
import in.semicolonindia.studentcrm.dialogs.ProgressDialog;
import in.semicolonindia.studentcrm.rest.BaseUrl;
import in.semicolonindia.studentcrm.util.ConnectionDetector;
import in.semicolonindia.studentcrm.util.PreferencesManager;

@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity implements BaseUrl {

    private Button btnLogin;
    private TextView tvForgetPwd;
    private EditText etEmail;
    private EditText etPwd;
    private TextView tvAppName;
    private ImageView imgHelp;

    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialise();
    }

    private void initialise() {
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPwd = (EditText) findViewById(R.id.etPwd);
        tvForgetPwd = (TextView) findViewById(R.id.tvForgetPwd);
        imgHelp = (ImageView) findViewById(R.id.imgHelp);

        Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        Typeface appFontRegular = Typeface.createFromAsset(getAssets(), "fonts/montserrat_regular.ttf");

        tvAppName.setTypeface(appFontBold);
        etEmail.setTypeface(appFontRegular);
        etPwd.setTypeface(appFontRegular);
        btnLogin.setTypeface(appFontRegular);
        tvForgetPwd.setTypeface(appFontRegular);

        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SupportActivity.class));
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Volley
                if (etEmail.getText().toString().length() > 0 && etPwd.getText().toString().length() > 0) {
                    // 1. Here we create a method to check Internet avalale or not.
                    if (new ConnectionDetector(getApplicationContext()).isConnectingToInternet()) {

                    doLogin(etEmail.getText().toString(), etPwd.getText().toString());
                    } else {
                        final NoConnectionDialog mNoConnectionDialog = new NoConnectionDialog(LoginActivity.this,
                                R.style.DialogSlideAnim);
                        mNoConnectionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mNoConnectionDialog.setCancelable(false);
                        mNoConnectionDialog.setCanceledOnTouchOutside(false);
                        mNoConnectionDialog.getWindow().setGravity(Gravity.BOTTOM);
                        mNoConnectionDialog.show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter Email & Password", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
            }
        });
    }

    private void doLogin(final String sEmail, final String sPassWord) {
        final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this, "Loading please wait...");
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, sLoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String sResult) {
                if (sResult != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(sResult);
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            new PreferencesManager(getApplicationContext()).setEmail(sEmail);
                            String s = new PreferencesManager(getApplicationContext()).getEmail();
                            new PreferencesManager(getApplicationContext()).setClassID(jsonObject.getString("class_id"));
                            new PreferencesManager(getApplicationContext()).setLogintype(jsonObject.getString("login_type"));
                            new PreferencesManager(getApplicationContext()).setUserID(jsonObject.getString("login_user_id"));
                            new PreferencesManager(getApplicationContext()).setSectionID(jsonObject.getString("section_id"));
                            new PreferencesManager(getApplicationContext()).setImage(jsonObject.getString("image_url"));
                            new PreferencesManager(getApplicationContext()).setName(jsonObject.getString("name"));
                            new PreferencesManager(getApplicationContext()).setClassName(jsonObject.getString("class_name"));
                            new PreferencesManager(getApplicationContext()).setSectionName(jsonObject.getString("section_name"));

                            new PreferencesManager(getApplicationContext()).setLoginStatus(true);

                            //Here home_list_menu of all  we chack activity login or not if login then activity will come HomeActivity....
                            new PreferencesManager(getApplicationContext()).setLogedIn(true);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish();
                            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                        } else {
                            Toast.makeText(getApplicationContext(), "Email/Password incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Oops! Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Dismissing the progress dialog
                Log.e("status Response", String.valueOf(volleyError));
                mProgressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String, String> params = new HashMap<>();
                params.put("email", sEmail);
                params.put("password", sPassWord);
                params.put("authenticate", "false");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed(){

        //finish();
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}

