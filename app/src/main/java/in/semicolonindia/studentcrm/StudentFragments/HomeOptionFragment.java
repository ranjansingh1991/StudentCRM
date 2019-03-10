package in.semicolonindia.studentcrm.StudentFragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentActivities.BenchActivity;
import in.semicolonindia.studentcrm.StudentActivities.ExtraCurriculamActivity;
import in.semicolonindia.studentcrm.StudentActivities.FeedbackActivity;
import in.semicolonindia.studentcrm.StudentActivities.GalleryActivity;
import in.semicolonindia.studentcrm.StudentActivities.LeaderBoardActivity;
import in.semicolonindia.studentcrm.StudentActivities.ScholarshipActivity;
import in.semicolonindia.studentcrm.StudentActivities.SupportActivity;
import in.semicolonindia.studentcrm.StudentAdapters.HomeOptionAdapter;
import in.semicolonindia.studentcrm.StudentActivities.NewsActivity;
import in.semicolonindia.studentcrm.StudentBeans.FirstName;
import in.semicolonindia.studentcrm.dialogs.NoConnectionDialog;
import in.semicolonindia.studentcrm.dialogs.ProgressDialog;
import in.semicolonindia.studentcrm.util.ConnectionDetector;

/**
 * Created by RANJAN SINGH on 8/28/2017.
 */
@SuppressWarnings("All")

public class HomeOptionFragment extends Fragment {
    ProgressDialog mProgressDialog;
    private ArrayList<FirstName> arraylist = new ArrayList<FirstName>();
    private HomeOptionAdapter homeOptionAdapter;
    ListView lv_Second;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_list_option, container, false);
        lv_Second = rootView.findViewById(R.id.lv_Second);

        HomeOptionAdapter homeOptionAdapter = new HomeOptionAdapter(getContext(),
                getResources().getStringArray(R.array.home_list_item));
        lv_Second.setAdapter(homeOptionAdapter);

        lv_Second.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (new ConnectionDetector(getActivity()).isConnectingToInternet()) {
                    switch (position) {
                        case 0:
                            getActivity().startActivity(new Intent(getActivity(), GalleryActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                        case 1:
                            getActivity().startActivity(new Intent(getActivity(), NewsActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                        case 2:
                            getActivity().startActivity(new Intent(getActivity(), FeedbackActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;

                        case 3:
                            getActivity().startActivity(new Intent(getActivity(), LeaderBoardActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;

                        case 4:
                            getActivity().startActivity(new Intent(getActivity(), BenchActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                        case 5:
                            getActivity().startActivity(new Intent(getActivity(), ScholarshipActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                        case 6:
                            getActivity().startActivity(new Intent(getActivity(), ExtraCurriculamActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                        case 7:
                            getActivity().startActivity(new Intent(getActivity(), SupportActivity.class));
                            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                            break;
                    }

                } else {
                    final NoConnectionDialog mNoConnectionDialog = new NoConnectionDialog(getActivity(),
                            R.style.DialogSlideAnim);
                    mNoConnectionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mNoConnectionDialog.setCancelable(false);
                    mNoConnectionDialog.setCanceledOnTouchOutside(false);
                    mNoConnectionDialog.getWindow().setGravity(Gravity.BOTTOM);
                    mNoConnectionDialog.show();
                }
            }
        });


        return rootView;
    }
}