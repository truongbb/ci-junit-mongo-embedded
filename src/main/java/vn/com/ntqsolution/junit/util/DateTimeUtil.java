package vn.com.ntqsolution.junit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

  public static Date currentDate() {
    return Calendar.getInstance().getTime();
  }

  public static Calendar currentCalendar() {
    return Calendar.getInstance();
  }

  public static String dateToYyyyMmDd(Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
    return dateFormat.format(date);
  }

  public static String calculateDateFromAge(int age) {
    Calendar instance = currentCalendar();
    instance.add(Calendar.YEAR, age);

    return dateToYyyyMmDd(instance.getTime());
  }

}
