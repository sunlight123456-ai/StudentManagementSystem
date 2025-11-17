package src.dao;
import src.entity.Student;
import src.util.FileUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * 数据访问层 - 负责学生数据的持久化操作
 */
public class StudentDao {
    private static final String DATA_FILE = "data/students.txt";
    private List<Student> students;
    public StudentDao(){
        this.students=new ArrayList<>();
        loadStudentsFromFile();
    }

    /**
     * 从文件加载学生数据
     */
    private void loadStudentsFromFile(){
        List<String> lines = FileUtil.readFromFile(DATA_FILE);
        for(String line:lines){
            try{
                Student student=Student.fromFileString(line);
                students.add(student);
            } catch (Exception e){
                System.err.println("加载数据失败：" +line +", 错误: "+e.getMessage());
            }
        }
        System.out.println("成功加载: "+ students.size() + "个学生数据");
    }
    /**
     *   保存所有学生数据到文件
     */
    private void saveStudentsToFile(){
        List<String> lines =new ArrayList<>();
        for(Student student: students){
            lines.add(student.toFileString());
        }
        FileUtil.writeToFile(DATA_FILE,lines);
    }
    /**
     * 添加学生
     */
    public boolean addStudent(Student student){
        for(Student s: students){
            if(s.getId().equals(student.getId())){
                return false;
            }
        }
        students.add(student);
        saveStudentsToFile();
        return true;
    }
    /**
     * 删除学生
     */
    public boolean deleteStudent(String studentId){
        Student studentToRemove=null;
        for(Student student:students){
            if(student.getId().equals(studentId)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove !=null){
            students.remove(studentToRemove);
            saveStudentsToFile();
            return true;
        }
        return false;
    }
    /**
     * 更新学生信息
     */
    public boolean updateStudent(Student updateStudent){
         for(int i=0; i<students.size();i++){
             Student student=students.get(i);
             if (student.getId().equals(updateStudent.getId())){
                 students.set(i,updateStudent);
                 saveStudentsToFile();
                 return true;
             }
         }
         return false;
    }
    /**
     * 根据学号查询学生
     * */
    public Student fineStudentById(String studentId){
        for(Student student:students){
            if(student.getId().equals(studentId)){
                return student;
            }
        }
        return null;
    }
    /**
     * 根据姓名查询学生（支持学生查询）
     */
    public List<Student> findStudentByname(String studentName){
        List<Student> result =new ArrayList<>();
        for (Student student:students){
            if (student.getName().contains(studentName)) {
                result.add(student);
            }
        }
        return result;
    }
    /**
     * 获取所有学生
     */
    public List<Student> getAllStudents(){
        return  new ArrayList<>(students);
    }
    /**
     * 按成绩排序
     */
    public List<Student> getStudentsSoredByScore(boolean ascending){
        List<Student> sorted =new ArrayList<>(students);
        sorted.sort((s1,s2) -> {
            if(ascending){
                return Double.compare(s1.getscore(),s2.getscore());
            } else {
                return Double.compare(s2.getscore(), s1.getscore());
            }
        });
        return sorted;
    }
}
