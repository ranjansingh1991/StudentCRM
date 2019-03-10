package in.semicolonindia.studentcrm.StudentActivities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import in.semicolonindia.studentcrm.HomeActivity;
import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentAdapters.TransportListAdapter;
import in.semicolonindia.studentcrm.StudentBeans.TransportNames;

@SuppressWarnings("ALL")
public class TransportActivity extends AppCompatActivity {

    ListView lvTransport;
    TransportListAdapter mTransportListAdapter;
    ArrayList<TransportNames> arraylist = new ArrayList<TransportNames>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("");
        final ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
        final TextView tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        tvToolbarTitle.setTypeface(appFontBold);
        tvToolbarTitle.setText(getString(R.string.transport_));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TransportActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        lvTransport = (ListView) findViewById(R.id.lvTransport);
        final Bundle bundle = this.getIntent().getExtras();
        for (int i = 0; i < bundle.getStringArray("transport_id").length; i++) {
            TransportNames mTransportNames = new TransportNames(bundle.getStringArray("transport_id")[i],
                    bundle.getStringArray("route_name")[i],
                    bundle.getStringArray("number_of_vehicle")[i],
                    bundle.getStringArray("route_fare")[i],
                    bundle.getStringArray("description")[i]);
            arraylist.add(mTransportNames);
        }

        mTransportListAdapter = new TransportListAdapter(getApplicationContext(), arraylist, lvTransport);
        lvTransport.setAdapter(mTransportListAdapter);

        final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                mTransportListAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(setIntent);
        finish();
    }

}
