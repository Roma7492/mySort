package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatTest {
  public static void main(String[] args) throws ParseException {
    String dateS = "2022-08-05T07:04:45.830+03:00";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    Date date = formatter.parse(dateS);
    System.out.println(date);
    System.out.println(formatter.format(date));
  }
}
