package src.entity;
import java.io.Serializable;
/**
*学生实体类
*实现Serializable接口以便序列化到文件
*/
 public class Student implements Serializable {
     private static final long serialVersionUID =1L;
     private String id;   //学号
     private String name; //姓名
     private int age;   //年龄
     private String gender; //性别
     private String className;  //班级
     private double score; //分数
     public Student(){}

     public Student(String id,String name,int age,String gender,String className,double score){
         this.id=id;
         this.name=name;
         this.age=age;
         this.gender=gender;
         this.className=className;
         this.score=score;
     }
     public String getId(){return id;}
     public void setId(String id){this.id=id;}

     public String getName(){return name;}
     public void setName(String name){this.name=name;}

     public int getAge(){return age;}
     public void setAge(int age){this.age=age;}

     public String getGender(){return gender;}
     public void setGender(String gender){this.gender=gender;}

     public String getClassName(){return className;}
     public void setClassName(String className){this.className=className;}

     public double getscore(){return score;}
     public void setScore(){this.score=score;}

     @Override
     public String toString(){
         return String.format("学号: %s, 姓名: %s, 年龄: %d, 性别: %s, 班级: %s, 分数: %.2f",id,name,age,gender,className,score);
     }

     public String toFileString(){
         return String.format("%s,%s,%d,%s,%s,%.2f",id,name,age,gender,className,score);
     }
     public static Student fromFileString(String fileString){
         String[] parts=fileString.split(",");
         if(parts.length != 6){
             throw new IllegalArgumentException("无效的学生格式：" +fileString);
         }
         return new Student(
                 parts[0],
                 parts[1],
                 Integer.parseInt(parts[2]),
                 parts[3],
                 parts[4],
                 Double.parseDouble(parts[5])
         );
     }


}
//class TestStudent{
//     public static void main(String[] args){
//         Student student=new Student("10001","黎名",21,"男","计算机2班",95.576);
//         System.out.println(student);
//         System.out.println("分数"+student.getscore());
//         System.out.println("年龄"+student.getAge());
//     }
//}
