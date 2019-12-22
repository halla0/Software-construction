package Experiment3;
import java.io.File;
import java.util.Scanner;
public class Select_Exercise{
	/*
	 * 实现选择生成想要的套题数目，并且从中随机三套
	 * 或者继续上次的习题
	 */
	//为选择之前做过的题目使用
	public static String For_Select_Finish_Remain[] = new String[250];
	//刚开始显示在屏幕上的文字
	private void First_Greet(){
		System.out.println("输入数字前对应的功能：");
		//可以打开之前做过的
		System.out.println("1 做之前做过的习题");
		System.out.println("2 随机生成多套题目 随机抽取三套");
		Scanner sc = new Scanner(System.in);
		//获取输入的数字
		int temp = sc.nextInt();
		//System.out.println(temp);
		if( temp == 1){
			new Select_Exercise().Finish_Remain();
		}
		else{
			new Select_Exercise().Random_Exercise();
		}
	}

	//继续之前做过的题目
	private void Finish_Remain(){
		System.out.println("输入以下文件前的编号,选择打开相应的文件:");
		//读取之间做过的题目
		new Select_Exercise().Visit();
		Scanner sc = new Scanner(System.in);
		int temp = sc.nextInt();
	}
	//选择随机生成想要的套题数目，从中随机三套题目来写
	private void Random_Exercise(){
		System.out.println("请输入想生成的套题数目：");
		//新建一个扫描对象
		Scanner sc = new Scanner(System.in);
		int temp = sc.nextInt();
	}
	//遍历练习题文件夹
	private void Visit(){
		//指向目的文件夹的对象
		File file = new File("D:\\文件\\大三课程\\软件构造\\Experiment3File\\Practise");
		File []fs = file.listFiles();
		int sum = 1 ;
		//遍历
		for( File t : fs ){
			//System.out.println( t.getName() );
			String temp = String.valueOf( sum ) + " " + t.getName();
			For_Select_Finish_Remain[ sum++ ] = temp;
			System.out.println(temp);
		}
	}
	
	public static void main(String[] args) {
		Select_Exercise test = new Select_Exercise();
		test.First_Greet();
	}
}