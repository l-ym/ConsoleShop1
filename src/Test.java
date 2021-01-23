import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Scanner sc=new Scanner(System.in);
    //创建一个购物车
    static Product carts[]=new Product[5];
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
            InputStream inproduct=Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel=new ReadUserExcel();//创建对象
            User[] users;
            users = readExcel.readExcel(in);
            for(int i=0;i<users.length;i++){
                if(username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())){
                    System.out.println("登录成功");
                    bo=false;
                    /*
                    显示菜单
                     */
                    while(true){
                        System.out.println("加入购物车请按1");
                        System.out.println("查看购物车请按2");
                        System.out.println("付款请按3");
                        System.out.println("退出按4");
                        int choose =sc.nextInt();
                        if(choose==1){
                            shopping(inproduct);
                        }else if(choose == 2){
                            System.out.println("当前购物车商品：");
                            for(Product p: carts){
                                if(p!=null) {
                                    System.out.print(p.getPid() + "\t");
                                    System.out.print(p.getPname() + "\t");
                                    System.out.print(p.getPrice() + "\t");
                                    System.out.println(p.getPdescr());
                                }
                            }
                        }else if(choose==4){
                            break;
                        }else if(choose==3){
                            Order order=new Order();

                        }

                    break;
                }else{
                    System.out.println("登录失败");
                }
            }
        }
    }
        public static void
        public static void shopping(InputStream in){
            /*
               显示商品
                    */
            ReadProductExcel rpe=new ReadProductExcel();
            Product products[]=rpe.readExcel(in);
            for(Product product:products){
                System.out.print(product.getPid()+"\t");
                System.out.print(product.getPname()+"\t");
                System.out.print(product.getPrice()+"\t");
                System.out.println(product.getPdescr());
            }

            System.out.println("请输入商品编号加入购物车");
            String pid=sc.next();
            int count=0;

            //根据此id 去查找是否有该id商品信息，如果有则返回该商品
            in=null;
            in=Class.forName("Test").getResourceAsStream("/product.xlsx");
            Product product=rpe.geyProductById(pid,in);
            if(product!=null){
                carts[count++]=product;
            }

        }

    }
}