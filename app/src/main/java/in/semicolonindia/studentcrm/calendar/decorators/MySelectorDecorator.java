package in.semicolonindia.studentcrm.calendar.decorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import in.semicolonindia.studentcrm.R;
import in.semicolonindia.studentcrm.calendar.CalendarDay;
import in.semicolonindia.studentcrm.calendar.DayViewDecorator;
import in.semicolonindia.studentcrm.calendar.DayViewFacade;


/**
 * Created by Rupesh on 08-08-2017.
 */
@SuppressWarnings("ALL")
public class MySelectorDecorator implements DayViewDecorator {

    private final Drawable drawable;

    public MySelectorDecorator(Activity context) {
        // drawable = context.getResources().getDrawable(R.drawable.my_selector);
        drawable = context.getResources().getDrawable(R.drawable.sun_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
