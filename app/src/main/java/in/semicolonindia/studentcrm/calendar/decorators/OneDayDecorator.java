package in.semicolonindia.studentcrm.calendar.decorators;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import java.util.Date;

import in.semicolonindia.studentcrm.calendar.CalendarDay;
import in.semicolonindia.studentcrm.calendar.DayViewDecorator;
import in.semicolonindia.studentcrm.calendar.DayViewFacade;

/**
 * Created by Rupesh on 08-08-2017.
 */
@SuppressWarnings("ALL")
public class OneDayDecorator implements DayViewDecorator {

    private CalendarDay date;

    public OneDayDecorator() {
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.RED));
        view.addSpan(new StyleSpan(Typeface.NORMAL));
        view.addSpan(new RelativeSizeSpan(1.4f));
    }

    /**
     * We're changing the internals, so make sure to call {@ linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
