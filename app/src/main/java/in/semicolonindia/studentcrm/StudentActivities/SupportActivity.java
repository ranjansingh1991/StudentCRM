package in.semicolonindia.studentcrm.StudentActivities;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentAdapters.SupportVPAdapter;
import in.semicolonindia.studentcrm.StudentSliding.SlidingTabLayout;
@SuppressWarnings("ALL")
public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_activity);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("");
        final ImageView imgBack = (ImageView) findViewById(R.id.imgBack);
        final TextView tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        final Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        final Typeface appFontLight = Typeface.createFromAsset(getAssets(), "fonts/montserrat_light.ttf");
        tvToolbarTitle.setTypeface(appFontBold);
        tvToolbarTitle.setText(getString(R.string.Support));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final TextView tvDevCompanyName = (TextView) findViewById(R.id.tvDevCompanyName);
        tvDevCompanyName.setTypeface(appFontLight);
        final SlidingTabLayout stLGTalking = (SlidingTabLayout) findViewById(R.id.stLGTalking);
        final ViewPager vpLGTalking = (ViewPager) findViewById(R.id.vpLGTalking);

        final SupportVPAdapter mSupportVPAdapter = new SupportVPAdapter(getSupportFragmentManager(),
                getApplicationContext());
        stLGTalking.setCustomTabView(R.layout.tab_title_item, R.id.tvTabName);
        stLGTalking.setDistributeEvenly(false);
        vpLGTalking.setAdapter(mSupportVPAdapter);
        try {
            stLGTalking.setViewPager(vpLGTalking);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}