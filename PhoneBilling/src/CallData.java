import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
public class CallData{
    private String phoneNumber;
    private String tBegin;
    private String tEnd;
    private String strDate;
    private String promotion;
    private final Double firstMinCost = 3.00;
    private final Double afterFirstMinCost = 1.00;
    
    public CallData(){

    }
    
    public CallData(String[] arr){
        this.phoneNumber = arr[3];
        this.promotion = arr[4];
        this.strDate = arr[0];
        this.tBegin = arr[1];
        this.tEnd = arr[2];
    }
    
    public CallData(String phoneNumber,String tBegin, String tEnd,String strDate ,String promotion){
        this.phoneNumber = phoneNumber;
        this.tBegin = tBegin;
        this.tEnd = tEnd;
        this.strDate = strDate;
        this.promotion = promotion;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getPromotion(){
        return promotion;
    }
    public String getDate(){
    	//System.out.println(strDate);
        return strDate;
    }
    public String getBeginTime(){
        return tBegin;
    }
    public String getEndTime(){
        return tEnd;
    }
    public long getDifferenceTime(){ /// calculate time from start time to end time
    	Date date1;
    	Date date2;
    	long difference;
    	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try{
                    date1 = format.parse(tBegin);
                    try{
                        date2 = format.parse(tEnd);
                        difference = date2.getTime() - date1.getTime();
                        return difference/1000; // return difference time in sec.
                    }catch(Exception e){
                        e.printStackTrace();
                        return -1;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    return -1;
                }
    }
    public Double getCost() { 
    	Double totalCost = 0.0;
    	long totalTime = getDifferenceTime();
    	if(promotion.equals("P1")) {
    		if(totalTime != -1 && totalTime > 60) { // 60s equals to 1m
        		totalCost += firstMinCost;
        		totalTime -= 60;
        		totalCost += (double)totalTime * (afterFirstMinCost/60.00);
        		//System.out.println((double)totalTime * (afterFirstMinCost/60.00));
        	}else if(totalTime != -1 && totalTime <= 60) {
        		totalCost += firstMinCost;
        		//System.out.println(getPhoneNumber());
        	}
    	}
    	DecimalFormat dec = new DecimalFormat("#0.000");
    	return Double.valueOf(dec.format(totalCost));
    }



}