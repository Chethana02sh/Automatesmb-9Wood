package base;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class JavaUtils {

    private LocalDate getLocalDate(){
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    public String getCurrentMonth(){
        return getLocalDate().getMonth().name().toLowerCase();
    }

    public String getYear(){
        return getLocalDate().getYear()+"";
    }

    public String getDay(){
        return getLocalDate().getDayOfMonth()+"";
    }

    public static void main(String[] args) {
        JavaUtils utils=new JavaUtils();
        System.out.println(utils.getDay());
        System.out.println(utils.getCurrentMonth());
        System.out.println(utils.getYear());
    }
}
