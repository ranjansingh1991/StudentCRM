package in.semicolonindia.studentcrm.StudentActivities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentAdapters.ChatListAdapter;
import in.semicolonindia.studentcrm.StudentBeans.Chats;
import in.semicolonindia.studentcrm.dialogs.ProgressDialog;
import in.semicolonindia.studentcrm.util.PreferencesManager;

import static in.semicolonindia.studentcrm.rest.BaseUrl.sGetMsg;
import static in.semicolonindia.studentcrm.rest.BaseUrl.sSendMsg;

@SuppressWarnings("ALL")
public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvMessages;
    private ChatListAdapter chatArrayAdapter;
    private String sReceiverID;
    private EditText etTxtMsg;
    private ImageView btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle mBundle = this.getIntent().getExtras();
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("");
        final ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
        final ImageView civReceiverPic = (ImageView) findViewById(R.id.civReceiverPic);
        final TextView tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        final TextView tvToolbarSubTitle = (TextView) findViewById(R.id.tvToolbarSubTitle);
        final Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        final Typeface appFontLight = Typeface.createFromAsset(getAssets(), "fonts/montserrat_light.ttf");
        tvToolbarTitle.setTypeface(appFontBold);
        tvToolbarSubTitle.setTypeface(appFontLight);
        tvToolbarTitle.setText(mBundle.getString("sTeachersName"));
        tvToolbarSubTitle.setText(mBundle.getString("sEmail"));
        Picasso.with(getApplicationContext()).load(mBundle.getString("sImgURL")).into(civReceiverPic);
        sReceiverID = mBundle.getString("sID");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        lvMessages = (ListView) findViewById(R.id.lvMessages);
        etTxtMsg = (EditText) findViewById(R.id.etTxtMsg);
        btnSend = (ImageView) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        lvMessages.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        chatArrayAdapter = new ChatListAdapter(getApplicationContext(), R.layout.sender_chat_msg);
        lvMessages.setAdapter(chatArrayAdapter);
        receiveOld();

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                lvMessages.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                if (etTxtMsg.getText().length() < 1) {
                    etTxtMsg.setError("No message!");
                } else {
//                    String htmlEncodedString = Html.toHtml(etTxtMsg.getText());
                    send(etTxtMsg.getText().toString().trim());
                }
                break;
        }
    }

    private boolean populateChatMessage(boolean isLeft, String msg, String dateTime) {
        chatArrayAdapter.add(new Chats(isLeft, msg, dateTime));
        if (isLeft) {
            etTxtMsg.setText("");
        }
        return true;
    }

    private void send(final String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        populateChatMessage(true, msg, sdf.format(new Date()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, sSendMsg, new Response.Listener<String>() {
            @Override
            public void onResponse(String sResult) {
                if (sResult != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(sResult);
                        if (!jsonObject.getBoolean("error")) {
                            //Do nothing
                        } else {
                            Toast.makeText(getApplicationContext(), "Message sent failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Message sent failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Dismissing the progress dialog
                Log.e("status Response", String.valueOf(volleyError));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String, String> params = new HashMap<>();
                params.put("sender_id", new PreferencesManager(getApplicationContext()).getUserID());
                params.put("sender_type", "student");
                params.put("reciever_id", sReceiverID);
                params.put("reciever_type", "teacher");
                params.put("message", msg);
                params.put("authenticate", "false");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void receiveOld() {
        final ProgressDialog mProgressDialog = new ProgressDialog(ChatActivity.this, "Loading...");
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, sGetMsg, new Response.Listener<String>() {
            @Override
            public void onResponse(String sResult) {
                if (sResult != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(sResult);
                        JSONArray jsonArray = jsonObject.getJSONArray("message");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (jsonArray.getJSONObject(i).getString("sender_type").equalsIgnoreCase("student")) {
                                populateChatMessage(true, jsonArray.getJSONObject(i).getString("message"),
                                        jsonArray.getJSONObject(i).getString("time"));
                            } else {
                                populateChatMessage(false, jsonArray.getJSONObject(i).getString("message"),
                                        jsonArray.getJSONObject(i).getString("time"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mProgressDialog.dismiss();
                } else {
                    mProgressDialog.dismiss();
                }
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
                params.put("sender_id", new PreferencesManager(getApplicationContext()).getUserID());
                params.put("sender_type", "student");
                params.put("reciever_id", sReceiverID);
                params.put("reciever_type", "teacher");
                params.put("authenticate", "false");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
