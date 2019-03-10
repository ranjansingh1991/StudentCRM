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
import in.semicolonindia.studentcrm.StudentAdapters.StudyMatListAdapter;
import in.semicolonindia.studentcrm.StudentBeans.StudyMatNames;
@SuppressWarnings("ALL")
public class StudyMatActivity extends AppCompatActivity {

    ListView lvStudyMat;
    StudyMatListAdapter mStudyMatListAdapter;
    private  String[] sTitle;
    private  String[] sSubject;
    private String[] sClass;
    private String[] sDate;
    private  String[] sFileNmae;
    private String[] sDesp;
    ArrayList<StudyMatNames> arraylist = new ArrayList<StudyMatNames>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mat);
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
        tvToolbarTitle.setText(getString(R.string.study_material_));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudyMatActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });



        lvStudyMat = (ListView) findViewById(R.id.lvStudyMat);

        final Bundle bundle = this.getIntent().getExtras();
        for (int i = 0; i < bundle.getStringArray("title").length; i++) {
            StudyMatNames mStudyMatNames = new StudyMatNames(bundle.getStringArray("title")[i],
                    bundle.getStringArray("subject_name")[i],
                    bundle.getStringArray("class_name")[i],
                    bundle.getStringArray("date_added")[i],
                    bundle.getStringArray("fileName")[i],
                    bundle.getStringArray("description")[i]
            );

            arraylist.add(mStudyMatNames);
        }
        mStudyMatListAdapter = new StudyMatListAdapter(StudyMatActivity.this, arraylist);
        lvStudyMat.setAdapter(mStudyMatListAdapter);
        final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                mStudyMatListAdapter.filter(text);
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
