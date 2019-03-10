package in.semicolonindia.studentcrm.StudentAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.StudentBeans.ExamNames;

/**
 * Created by Rupesh on 30-08-2017.
 */

@SuppressWarnings("ALL")
public class ExamMarksListAdapter extends RecyclerView.Adapter<ExamMarksListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ExamNames> arrayList;
    private List<ExamNames> examNamesList = null;
    RecyclerView rvExam;


    public ExamMarksListAdapter(Context context, ArrayList<ExamNames> arrayList, RecyclerView rvExam) {
        this.context = context;
        this.arrayList = arrayList;
        this.rvExam = rvExam;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExamMarksListAdapter.ViewHolder mViewHolder = null;
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_marks_list_header,
                    parent, false);
            mViewHolder = new ExamMarksListAdapter.ViewHolder(view, viewType);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_marks_list_item,
                    parent, false);
            mViewHolder = new ExamMarksListAdapter.ViewHolder(view, viewType);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<String>();
            for(int i = 0; i < arrayList.size(); i ++) {
                ExamNames examNamesDetails = arrayList.get(i);
                entries.add(new BarEntry(Float.parseFloat(examNamesDetails.getsObtainedMarks()), i));
                labels.add(examNamesDetails.getsSubjectName());
            }
            BarDataSet bardataset = new BarDataSet(entries, "MARKS");
            BarData data = new BarData(labels, bardataset);
            holder.bcExamMarks.setData(data);
            holder.bcExamMarks.setDescription("EXAM MARKS");
            bardataset.setColors(new int[]{0xFFFFFFFF});
            holder.bcExamMarks.setBorderColor(0xFFFFFFFF);
            holder.bcExamMarks.animateY(2500);
        } else

        {
            Typeface appFontRegular = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf");
            Typeface appFontLight = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_light.ttf");
            holder.tvSubject.setTypeface(appFontRegular);
            holder.tvMarksObtained.setTypeface(appFontLight);
            holder.tvMaxMarks.setTypeface(appFontLight);
            holder.tvGrade.setTypeface(appFontLight);
            holder.tvRemarks.setTypeface(appFontLight);

            ExamNames examNamesDetails = arrayList.get(position - 1);
            holder.tvMarksObtained.setText(examNamesDetails.getsObtainedMarks());
            holder.tvMaxMarks.setText(examNamesDetails.getsTotalMarks());
            holder.tvSubject.setText(examNamesDetails.getsSubjectName());
            holder.tvGrade.setText(examNamesDetails.getsGrade());
            holder.tvRemarks.setText(examNamesDetails.getsRemarks());
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        BarChart bcExamMarks;
        TextView tvSubject;
        TextView tvMarksObtained;
        TextView tvMaxMarks;
        TextView tvGrade;
        TextView tvRemarks;

        public ViewHolder(View itemView, int position) {
            super(itemView);
            if (position == 0) {
                bcExamMarks = (BarChart) itemView.findViewById(R.id.bcExamMarks);
            } else {
                tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
                tvMarksObtained = (TextView) itemView.findViewById(R.id.tvMarksObtained);
                tvMaxMarks = (TextView) itemView.findViewById(R.id.tvMaxMarks);
                tvGrade = (TextView) itemView.findViewById(R.id.tvGrade);
                tvRemarks = (TextView) itemView.findViewById(R.id.tvComments);
            }
        }
    }
}
