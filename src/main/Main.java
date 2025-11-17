package src.main;
import src.entity.Student;
import src.service.StudentService;
import src.util.ValidationUtil;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * 主程序 - 用户界面和交互
 */
public class Main {
    private StudentService studentService;
    private Scanner scanner;
    public Main(){
        this.studentService=new StudentService();
        this.scanner=new Scanner(System.in);
    }
    public void start() {
        System.out.println("========== 学生信息管理系统 ==========");

        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    deleteStudent();
                    break;
                case "3":
                    updateStudent();
                    break;
                case "4":
                    queryStudent();
                    break;
                case "5":
                    studentService.displayAllStudent();
                    break;
                case "6":
                    sortStudentsByScore();
                    break;
                case "7":
                    showStatistics();
                    break;
                case "0":
                    System.out.println("感谢使用学生信息管理系统，再见！");
                    return;
                default:
                    System.out.println("无效的选择， 请重新输入！");
            }
            System.out.println("\n按回车继续...");
            scanner.nextLine();
        }}
    private void showMenu() {
        System.out.println("\n请选择操作: ");
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 修改学生");
        System.out.println("4. 查询学生");
        System.out.println("5. 显示所有学生");
        System.out.println("6. 按成绩排序");
        System.out.println("7. 统计信息");
        System.out.println("0. 退出系统");
        System.out.println("请输入选择： ");
        }
    private void addStudent(){
        System.out.println("\n-----添加学生-----");
        System.out.println("请输入学号: ");
        String id = scanner.nextLine().trim();

        System.out.println("请输入姓名: ");
        String name =scanner.nextLine().trim();

        System.out.println("请输入年龄: ");
        String ageStr=scanner.nextLine().trim();
        if(!ValidationUtil.isNumeric(ageStr)){
            System.out.println("年龄必须是数字！ ");
            return;
        }
        int age = Integer.parseInt(ageStr);

        System.out.println("请输入性别（男/女）：");
        String gender= scanner.nextLine().trim();

        System.out.println("请输入班级：");
        String className =scanner.nextLine().trim();

        System.out.println("请输入成绩： ");
        String scoreStr = scanner.nextLine().trim();
        if (!ValidationUtil.isNumeric(scoreStr)){
            System.out.println("成绩必须是数字！ ");
            return;
        }
        double score = Double.parseDouble(scoreStr);

        boolean success =studentService.addStudent(id,name,age,gender,className,score);
        if(success){
            System.out.println("添加学生成功! ");
        }
    }
    private void deleteStudent(){
        System.out.println("\n----删除学生----");
        System.out.println("请输入要删除的学生学号： ");
        String id = scanner.nextLine().trim();
        boolean success= studentService.deleteStudent(id);
        if(success){
            System.out.println("删除学生成功! ");
        }
    }
    private void  updateStudent() {
        System.out.println("\n----修改学生信息----");
        System.out.println("请输入要修改的学生学号： ");
        String id = scanner.nextLine().trim();

        System.out.println("请输入新姓名： ");
        String name = scanner.nextLine().trim();

        System.out.println("请输入新年龄： ");
        String ageStr = scanner.nextLine().trim();
        if(!ValidationUtil.isNumeric(ageStr)){
            System.out.println("年龄必须是数字！ ");
            return;
        }
        int age = Integer.parseInt(ageStr);

        System.out.println("请输入新性别（男/女）：");
        String gender = scanner.nextLine().trim();

        System.out.println("请输入新班级: ");
        String className = scanner.nextLine().trim();

        System.out.println("请输入新成绩： ");
        String scoreStr = scanner.nextLine().trim();
        if(!ValidationUtil.isNumeric(scoreStr)){
            System.out.println("成绩必须是数字！ ");
            return;
        }
        double score = Double.parseDouble(scoreStr);
        boolean success = studentService.updateStudent(id,name,age,gender,className, score);
        if (success){
            System.out.println("修改信息成功！ ");
        }
    }
    private void queryStudent() {
        System.out.println("\n----查询学生----");
        System.out.println("请输入学号或姓名： ");
        String input = scanner.nextLine().trim();
        studentService.queryStudent(input);
    }
    private void sortStudentsByScore(){
        System.out.println("\n----按成绩排序----");
        System.out.println("1. 升序排列");
        System.out.println("2. 降序排列");
        System.out.println("请选择: ");

        String choice= scanner.nextLine().trim();
        if("1".equals(choice)){
            studentService.displayStudentSortedByScore(true);
        }else if("2".equals(choice)){
            studentService.displayStudentSortedByScore(false);
        }
        else {
            System.out.println("无效的选择! ");
        }
    }
    private void showStatistics() {
        int count = studentService.getStudentCount();
        System.out.println("\n----统计信息----");
        System.out.println("当前学生总数： "+count);
    }
    public static void main(String[] args){
        Main main=new Main();
        main.start();
    }

    }

