package in.semicolonindia.studentcrm.StudentActivities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in.semicolonindia.studentcrm.HomeActivity;
import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentAdapters.ScholarshipListAdapter;
import in.semicolonindia.studentcrm.StudentBeans.ScholarNames;

/**
 * Created by MPAYAL-PC on 11/29/2017.
 **/

@SuppressWarnings("All")

public class ScholarshipActivity extends AppCompatActivity {
    ListView lvScholar;
    ScholarshipListAdapter scholarshipListAdapter;
    ArrayList<ScholarNames> arraylist = new ArrayList<ScholarNames>();
    private String[] sDate;
    private String[] sClass;
    private String[] sDesp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scholarship_activity);

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
        tvToolbarTitle.setText(getString(R.string.Scholarship));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ScholarshipActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });
        sDate = new String[]{"06/28/2017", "06/28/2017", "06/28/2017",
                "06/28/2017", "06/28/2017"};

        sClass = new String[]{"Class 1", "Class 2", "Class 3",
                "Class 4", "Class 5"};

        sDesp = new String[]{"Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",};

        lvScholar = (ListView) findViewById(R.id.lvScholarship);

        for (int i = 0; i < sClass.length; i++) {
            ScholarNames scholarNames = new ScholarNames(sDate[i],
                    sClass[i], sDesp[i]);
            arraylist.add(scholarNames);
        }
        scholarshipListAdapter = new ScholarshipListAdapter(getApplicationContext(), arraylist);
        lvScholar.setAdapter(scholarshipListAdapter);


    }
}
