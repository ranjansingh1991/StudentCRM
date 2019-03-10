package in.semicolonindia.studentcrm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.semicolonindia.studentcrm.util.PreferencesManager;
import in.semicolonindia.studentcrm.widgets.AVLoadingIndicatorViewLight;
@SuppressWarnings("ALL")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Typeface appFontBold = Typeface.createFromAsset(getAssets(), "fonts/montserrat_bold.ttf");
        final AVLoadingIndicatorViewLight loadingIndicator = (AVLoadingIndicatorViewLight) findViewById(R.id.loadingIndicator);
        final TextView tvAppName = (TextView) findViewById(R.id.tvAppName);
        final TextView tvProgressTitle = (TextView) findViewById(R.id.tvProgressTitle);
        tvAppName.setTypeface(appFontBold);
        tvProgressTitle.setTypeface(appFontBold);
        loadingIndicator.setIndicator("SquareSpinIndicator");
        loadingIndicator.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // first time Application lunch then go to splash screen.
                // if Application first time install then go to LoginActivity.
                if(new PreferencesManager(getApplicationContext()).getLogedIn()) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                }else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                }
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }
}
