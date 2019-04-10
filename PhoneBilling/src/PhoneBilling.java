import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import java.io.PrintWriter; 


public class PhoneBilling{
    //private static final String REGEX_STRING = "|";
    public static String[] arr ;
    public static JSONArray jsonArr = new JSONArray();
    public static void main(String[] args) {
        String path = "../PhoneBilling/logfile/promotion1.log"; 
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                arr = line.split("\\|");
                CallData data = new CallData(arr);
                JSONObject obj = new JSONObject();
                obj.put("phoneNumber", data.getPhoneNumber());
                obj.put("promotion", data.getPromotion());
                obj.put("beginTime", data.getBeginTime());
                obj.put("endTime", data.getEndTime());   
                obj.put("totalTime", data.getDifferenceTime());
                obj.put("cost", data.getCost());
                obj.put("date", data.getDate());
                jsonArr.add(obj);
			}
			br.close();
			PrintWriter pw = new PrintWriter("../PhoneBilling/jsonResult/billReport.json"); 
	        pw.write(jsonArr.toJSONString()); 
	        pw.flush(); 
	        pw.close(); 
	        System.out.print("Success Write >> ");
	        System.out.println(jsonArr);
	        System.out.println(">> to /jsonResult/billReport.json");
		} catch (IOException e) {
            System.out.println("Unable to read log file.");
            e.printStackTrace();
        }
        
        
    }
}