import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;//登陆成功后停止循环，失败后继续输入
        while(bo){
            System.out.println("请输入用户名：");
            Scanner sc=new Scanner(System.in);
            String username=sc.next();//阻塞方法
            System.out.println("请输入密码：");
            String password=sc.next();

            /*读取文件*/
            //File file=new File("C:\\Users\\dell\\IdeaProjects\\ConsoleShop1\\src\\user.xlsx");//linux上没有c、d盘
            InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
            ReadUserExcel readExcel=new ReadUserExcel();//创建对象
            User[] users;
            users = readExcel.readExcel(in);
            for(int i=0;i<users.length;i++){
                if(username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())){
                    System.out.println("登录成功");
                    bo=false;
                    break;
                }else{
                    System.out.println("登录失败");
                }
            }
        }
    }
}