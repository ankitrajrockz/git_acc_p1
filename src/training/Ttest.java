package training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ttest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		String d="12/20/2019 14:48 PM";
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
          
          Date date = sdf.parse(d);
          
         System.out.println(date);
         
         String k=new SimpleDateFormat("MM/dd/yyyy").format(date);
         
         System.out.println(k);
         Date dat = new Date();
         System.out.println(dat);
         SimpleDateFormat sdf_1 = new SimpleDateFormat("MM/dd/yyyy h:mm a");
         String tim=sdf_1.format(dat);
         System.out.println(tim);
         
       
         
         
         
         ZoneId fromTimeZone = ZoneId.of("Asia/Kolkata");    //Source timezone
         ZoneId toTimeZone = ZoneId.of("America/New_York");  //Target timezone
         
         
         LocalDateTime today = LocalDateTime.now();          //Current time
          
         //Zoned date time at source timezone
         ZonedDateTime currentISTime = today.atZone(fromTimeZone);       
          
         //Zoned date time at target timezone
         ZonedDateTime currentETime = currentISTime.withZoneSameInstant(toTimeZone);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
         //Format date time - optional
         System.out.println(formatter.format(currentISTime));
         System.out.println(formatter.format(currentETime));
        
	}

}
