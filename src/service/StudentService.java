package src.service;
import com.sun.org.apache.xpath.internal.objects.XString;
import src.dao.StudentDao;
import src.entity.Student;
import src.util.ValidationUtil;

import java.sql.SQLOutput;
import java.util.List;

/**
 * 业务逻辑层 -处理学生管理的业务逻辑
 */
public class StudentService {
    private StudentDao studentDao;
    public StudentService(){
        this.studentDao=new StudentDao();
    }
    /**
     * 添加学生
     */
    public boolean addStudent(String id,String name,int age,String gender,String className,Double score){
        //数据验证
        if(!ValidationUtil.isValidStudentId(id)){
            System.out.println("学号格式不正确！");
            return false;
        }
        if(!ValidationUtil.isValidName(name)){
            System.out.println("姓名格式不正确");
            return false;
        }
        if(!ValidationUtil.isVailAge(age)){
            System.out.println("年龄必须在16-60岁之间!");
            return false;
        }
        if(!ValidationUtil.isVailGender(gender)){
            System.out.println("性别必须是男或女！");
            return false;
        }
        if(!ValidationUtil.isVailScore(score)){
            System.out.println("成绩必须在0-100之间！");
            return false;
        }
        Student student =new Student(id,name,age,gender,className,score);
        boolean success=studentDao.addStudent(student);
        if(!success){
            System.out.println("添加失败：字号"+ id +"已存在！");
        }
        return success;
    }

    /**
     * 删除学生
     * @param studentId
     * @return
     */
    public boolean deleteStudent(String studentId){
        boolean success=studentDao.deleteStudent(studentId);
        if(!success){
            System.out.println("删除失败：学号"+studentId+"不存在！");
        }
        return success;
    }

    /**
     * 更新学生信息
     * @param id
     * @param name
     * @param age
     * @param gender
     * @param className
     * @param score
     * @return
     */
    public boolean updateStudent(String id, String name,int age,String gender,String className,Double score){
        Student existingStudent=studentDao.fineStudentById(id);
        if (existingStudent ==null){
            System.out.println("更新失败:学号"+id+"不存在!");
            return false;
        }
        if (!ValidationUtil.isValidName(name)){
            System.out.println("姓名格式不正确！");
            return false;
        }
        if(!ValidationUtil.isVailAge(age)){
            System.out.println("年龄必须在16岁-60岁之间");
            return false;
        }
        if(!ValidationUtil.isVailGender(gender)){
            System.out.println("性别必须是男或女！");
            return false;
        }
        if (!ValidationUtil.isVailScore(score)){
            System.out.println("成绩必须在0-100之间！");
            return false;
        }
        Student updateStudent=new Student(id,name,age,gender,className,score);
        return studentDao.updateStudent(updateStudent);
    }

    /**
     * 查询学生
     * @param input
     */
    public void queryStudent(String input){
        Student student =studentDao.fineStudentById(input);
        if(student !=null){
            System.out.println("查询结果(按学号):");
            System.out.println(student);
            return;
        }
        List<Student> students=studentDao.findStudentByname(input);
        if(!students.isEmpty()){
            System.out.println("查询结果（按姓名）：");
            displayStudentList(students);
        }else {
            System.out.println("未找到匹配的学生!");
        }
    }

    /**
     * 显示所有学生
     */
    public void displayAllStudent(){
        List<Student> students = studentDao.getAllStudents();
        if (students.isEmpty()){
            System.out.println("当前没有学生数据");
        }else {
            System.out.println("所有学生信息： ");
            displayStudentList(students);
        }
    }

    /**
     * 按成绩排序显示
     * @param ascending
     */
    public void displayStudentSortedByScore(boolean ascending){
        List<Student> students=studentDao.getStudentsSoredByScore(ascending);
        if (students.isEmpty()){
            System.out.println("当前没有学生数据！");
        }else{
            String order = ascending ? "升序":"降序";
            System.out.println("按成绩"+order+"排序: ");
            displayStudentList(students);
        }
    }

    /**
     * 显示学生列表
     * @param students
     */
    private void displayStudentList(List<Student> students){
        System.out.println("┌──────┬──────────┬──────┬────────┬──────────┬────────┐");
        System.out.println("│ 学号 │   姓名   │ 年龄 │  性别  │   班级   │  成绩  │");
        System.out.println("├──────┼──────────┼──────┼────────┼──────────┼────────┤");
        for (Student student:students){
            System.out.printf("│ %-4s │ %-8s │ %-4d │ %-6s │ %-8s │ %-6.1f │\n",
                    student.getId(),student.getName(),student.getAge(),student.getGender(),
                    student.getClassName(),student.getscore());
        }
        System.out.println("└──────┴──────────┴──────┴────────┴──────────┴────────┘");
        System.out.println("共"+students.size()+"个学生");
    }

    /**
     * 获取学生总数
     * @return
     */
    public int getStudentCount() {
        return studentDao.getAllStudents().size();
    }

}
