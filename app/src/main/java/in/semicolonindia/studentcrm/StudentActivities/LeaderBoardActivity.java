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
import in.semicolonindia.studentcrm.StudentAdapters.LeaderBoardAdapter;
import in.semicolonindia.studentcrm.StudentBeans.LeaderBoardNames;

/**
 * Created by MPAYAL-PC on 11/28/2017.
 */
@SuppressWarnings("All")

public class LeaderBoardActivity extends AppCompatActivity {
    ListView lvLeaderBoard;
    LeaderBoardAdapter leaderBoardAdapter;
    ArrayList<LeaderBoardNames> arraylist = new ArrayList<LeaderBoardNames>();
    private int[] sImg;
    private String[] sName;
    private String[] sDate;
    private String[] sTime;
    private String[] sClass;
    private String[] sSection;
    private String[] sAchivments;
    private String[] sDesp;


    /*LeaderBoardAdapter leaderBoardAdapter;
    ArrayList<LeaderBoardNames> arraylist = new ArrayList<LeaderBoardNames>();
*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_activity);
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
        tvToolbarTitle.setText(getString(R.string.LeaderBoard));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LeaderBoardActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        sImg = new int[]{R.drawable.dummy_face_1,
                R.drawable.dummy_face_icon_1,
                R.drawable.dummy_icon_2,
                R.drawable.dummy_face_icon,
                R.drawable.dummy_face_2};
        sName = new String[]{"Rahul ", "Ranjan ", "Santosh ",
                "Raj", " Ravi"};
        sClass = new String[]{"Class 1", "Class 2", "Class 3",
                "Class 4", "Class 5"};
        sSection = new String[]{"Section A", "Section B", "Section C",
                "Section 4", "Section 5"};
        sDate = new String[]{"06/28/2017", "06/28/2017", "06/28/2017",
                "06/28/2017", "06/28/2017"};
        sTime = new String[]{"01:12:00", "01:15:00", "01:18:00",
                "01:30:00", "01:11:00"};
        sAchivments = new String[]{"Quiz", "Drawing Competition", "Singing",
                "Dancing", "Playing"};
        sDesp = new String[]{"Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",};

        lvLeaderBoard = (ListView) findViewById(R.id.lvLeaderBoard);

        for (int i = 0; i < sName.length; i++) {
            LeaderBoardNames mLeaderBoardNames = new LeaderBoardNames(sImg[i], sName[i],
                    sClass[i], sSection[i], sDate[i], sTime[i], sAchivments[i], sDesp[i]);
            arraylist.add(mLeaderBoardNames);
        }
        leaderBoardAdapter = new LeaderBoardAdapter(getApplicationContext(), arraylist);
        lvLeaderBoard.setAdapter(leaderBoardAdapter);


    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(setIntent);
        finish();
    }
}
