package in.semicolonindia.studentcrm.calendar.formats;

import android.support.annotation.NonNull;

import in.semicolonindia.studentcrm.calendar.CalendarDay;


/**
 * Created by Rupesh on 08-08-2017.
 */
@SuppressWarnings("ALL")
public interface DayFormatter {

    /**
     * Format a given day into a string
     *
     * @param day the day
     * @return a label for the day
     */
    @NonNull
    String format(@NonNull CalendarDay day);

    /**
     * Default implementation used by MaterialCalendarView
     */
    public static final DayFormatter DEFAULT = new DateFormatDayFormatter();
}
