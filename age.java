import java.util.*;

public class age {
    private static  Date birthDate = new Date();

    public static  int getAge() {
    //get current date
    Date today = new Date();
    Calendar current = Calendar.getInstance();
    current.setTime(today);
    System.out.println(current.getTime());
    Calendar birth = Calendar.getInstance();
    birth.setTime(birthDate);
    System.out.println(birth.getTime());
    //check the year, month, then day 
    int age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR) -1;
    if(birth.get(Calendar.MONTH) <= current.get(Calendar.MONTH) && birth.get(Calendar.DAY_OF_MONTH) <= current.get(Calendar.DAY_OF_MONTH))
      age++;
    return age;
    //for formatting/printing
    //DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
    //String strDate = dateFormat.format(date);
    }

    public static void main (String[] args) {
        int age = getAge();
        System.out.println(age);
    }
}