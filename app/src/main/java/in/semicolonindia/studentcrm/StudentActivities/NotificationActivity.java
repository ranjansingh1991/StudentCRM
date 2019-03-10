package in.semicolonindia.studentcrm.StudentActivities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentAdapters.NotificationNewAdapter;
import in.semicolonindia.studentcrm.StudentBeans.NotificationModel;

@SuppressWarnings("ALL")
public class NotificationActivity extends AppCompatActivity {

    ListView lvNotification;
    NotificationNewAdapter mNotificationAdapter;
    ArrayList<NotificationModel> arraylist = new ArrayList<NotificationModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_new);

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
        tvToolbarTitle.setText(getString(R.string.notification1));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvNotification = (ListView) findViewById(R.id.lvNotification);

        final Bundle bundle = this.getIntent().getExtras();
        for (int i = 0; i < bundle.getStringArray("title").length; i++) {
            NotificationModel mNoticeNames = new NotificationModel(bundle.getStringArray("title")[i],
                    bundle.getStringArray("from_date")[i],
                    bundle.getStringArray("to_date")[i], bundle.getStringArray("description")[i]);
            arraylist.add(mNoticeNames);

            mNotificationAdapter = new NotificationNewAdapter(getApplicationContext(), arraylist);
            lvNotification.setAdapter(mNotificationAdapter);
        }
    }
}
