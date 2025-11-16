package src.util;
import java.util.regex.Pattern;
public class ValidationUtil {
    //学号格式：数字，长度4-10位
    private static final Pattern STUDENT_ID_PATTERN = Pattern.compile("^\\d{4,10}$");
    //姓名格式：中文或英文，长度2-20位
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z]{2,20}$");

    public static boolean isValidStudentId(String id) {
        return id != null && STUDENT_ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isVailAge(int age){
        return age<=60 && age>= 16;
    }

    public static boolean isVailGender(String name){
        return name.equals("男") || name.equals("女");
    }

    public static  boolean isVailScore(double score){
        return score<=100 && score>=0;
    }

    public static boolean isNumeric(String str){
        if (str==null) {return false;}
        try{
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}