package in.semicolonindia.studentcrm.StudentFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import in.semicolonindia.studentcrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    private WebView webView_AboutUs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_about_us, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        webView_AboutUs = (WebView) rootView.findViewById(R.id.webView_AboutUs);
        String url = "http://semicolonindia.in/";
        webView_AboutUs.loadUrl(url);
    }
}