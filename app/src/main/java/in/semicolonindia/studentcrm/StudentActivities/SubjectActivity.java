package in.semicolonindia.studentcrm.StudentActivities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import in.semicolonindia.studentcrm.StudentAdapters.SubjectListAdapter;
import in.semicolonindia.studentcrm.StudentBeans.SubjectNames;
@SuppressWarnings("ALL")
public class SubjectActivity extends AppCompatActivity {

    ListView lvSubjects;
    SubjectListAdapter mSubjectListAdapter;
    ArrayList<SubjectNames> arraylist = new ArrayList<SubjectNames>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("");
        final ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
        final TextView tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        final Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        tvToolbarTitle.setTypeface(appFontBold);
        tvToolbarTitle.setText(getString(R.string.subjects));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubjectActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        lvSubjects = (ListView) findViewById(R.id.lvSubjects);

        final Bundle bundle = this.getIntent().getExtras();
        for (int i = 0; i < bundle.getStringArray("sSubjectNames").length; i++) {
            SubjectNames mSubjectNames = new SubjectNames(bundle.getStringArray("sSubjectNames")[i],
                    bundle.getStringArray("sYear")[i]);
            arraylist.add(mSubjectNames);
        }
        mSubjectListAdapter = new SubjectListAdapter(getApplicationContext(), arraylist);
        lvSubjects.setAdapter(mSubjectListAdapter);

        final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                mSubjectListAdapter.filter(text);
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
