package in.semicolonindia.studentcrm.calendar.decorators;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.StyleSpan;

import java.util.Calendar;

import in.semicolonindia.studentcrm.calendar.CalendarDay;
import in.semicolonindia.studentcrm.calendar.DayViewDecorator;
import in.semicolonindia.studentcrm.calendar.DayViewFacade;


/**
 * Created by Rupesh on 08-08-2017.
 */
@SuppressWarnings("ALL")
public class HighlightWeekendsDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#66FFFFFF");

    public HighlightWeekendsDecorator() {
        highlightDrawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // weekDay = Color.parseColor("#C02336");

        return weekDay == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
        view.addSpan(new StyleSpan(Typeface.BOLD));
    }
}
