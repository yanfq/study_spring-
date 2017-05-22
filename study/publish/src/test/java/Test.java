import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yanfq on 17-5-2.
 */
public class Test {
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(date));
    }
}
