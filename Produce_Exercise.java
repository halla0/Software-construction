package Experiment3;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import Practice2.Test;
import Pratice2_2.Exercises_2;
public class Produce_Exercise {
	/*
	 * 产生随机想要的习题套数
	 */
	//设置练习题常量，每次将新建的数据存入练习题文件夹
	public static String SOURSE_P = "D:\\文件\\大三课程\\软件构造\\Experiment3File\\Practise";
	//设置批改文件夹
	public static String SOURSE_A = "D:\\文件\\大三课程\\软件构造\\Experiment3File\\Alter";
	//设置习题常量
	public static String SOURSE_E = "D:\\文件\\大三课程\\软件构造\\Experiment3File\\Exercise";
	//设置加法习题文件前缀名字
	public String EX_INDEXNAME_ADD = "exercise_add_50_";
	//设置减法习题文件前缀名字
	public String EX_INDEXNAME_SUB = "exercise_sub_50_";
	//设置加减法习题文件前缀名字
	public String EX_INDEXNAME_ADD_SUB = "exercise_add_sub_50_";	
	//返回一个随机数
	public int Random_N( int N){
		Random r = new Random();
		//注意左闭右开
		return r.nextInt(N);
	}
	//产生多套习题
	private void Produce_N( int N ) throws IOException{
		//新建一个实验二的产生式子的对象，为下面产生习题用
		Exercises_2 ex = new Exercises_2();
		for( int i = 0 ; i < N ; i++ ){
			//随机生成加法，减法,混合加减法
			int temp = new Produce_Exercise().Random_N(3);
			//0生成加法算式
			if( temp == 0 ){
				ex.Addtion_50();
			}
			//1生成减法算式
			else if( temp == 1 ){
				ex.Substract_50();
			}
			//2生成混合加减法算式
			else{
				ex.Addtion_Substract_50();
			}
			//统一将数据存起来,将这个存有数据的对象传方法中进行存储
			new Produce_Exercise().TranData(ex,temp);
		}
	}
	//把产生的算式依次放到文件里面
	private void TranData(Exercises_2 ex , int judge) throws IOException{
		//用来存放真正的后缀
		String lastIndex = "";
		//加法算式
		if( judge == 0 ){
			lastIndex = "//" + EX_INDEXNAME_ADD + new Produce_Exercise().SumExerciseFile();
		}
		//减法算式
		else if( judge == 1){
			lastIndex = "//" + EX_INDEXNAME_SUB + new Produce_Exercise().SumExerciseFile();
		}
		//混合加减法算式
		else{
			lastIndex = "//" + EX_INDEXNAME_ADD_SUB + new Produce_Exercise().SumExerciseFile();
		}
		//使用csv里面的方法创建文件
		//新建一个csv对象来使用它的方法
		CSV c = new CSV();
		//新建获得正确名字的文件,并且将file对象留给下面的存取操作使用
		File file = new File( SOURSE_E + lastIndex );
		c.CreateFile( file );
		//将内容数据传递进去
	    String []temp = new String[51];
		//将式子格式转换好后，依次存取
		for( int i = 0 ; i < 50 ; i++ ){
			//a
			String temp_left = String.valueOf( ex.left_a[i] );
			//a -(+)   //存取加减运算符,0 + , 1 -
			String temp_operator = "";
			if( ex.operation_c[i] == 0){
				temp_operator = " + ";
			}
			else{
				temp_operator = " - ";
			}
			//a -(+) b
			String temp_right = String.valueOf( ex.right_b[i] );
			//a -(+) b =
			String temp_exercise = temp_left + temp_operator + temp_right + " =";
			//将式子赋值
			temp[i] = temp_exercise;
			//System.out.println( temp_exercise );
		}
		//采用特殊字符串将答案和式子进行分割
		temp[50] = "<!-- -->";
		//将数据存储
		c.CsvWrite( temp, SOURSE_E + lastIndex );
		//将答案存储
		for( int i = 0 ; i < 50 ; i++ ){
			temp[i] = String.valueOf( ex.value_d[i] ); 
		}
		c.CsvWrite( temp, SOURSE_E + lastIndex );
	}
	//设置习题文件的后缀，比如005
	public String SumExerciseFile(){
		//测试得到文件下文件的数量
		File file = new File( SOURSE_E );
		//System.out.println( file.listFiles().length );
		String sum = String.valueOf( file.listFiles().length + 1);
		//用来补齐前缀零的数量
		String temp = "";
		//获得标准化后缀格式
		for( int i = 0 ; i < 3 - sum.length() ; i++ ){
			temp += "0";
		}
		//返回00+数量
		return temp + sum + ".csv";
	}
	public static void main(String[] args) throws IOException {
		/*
//		//测试随机数
		Produce_Exercise p = new Produce_Exercise();
		//System.out.println( p.SumExerciseFile() );
		Exercises_2 ex = new Exercises_2();
		p.TranData( ex, 2 );
		*/
		Produce_Exercise p = new Produce_Exercise();
		Exercises_2 ex = new Exercises_2();
		//生成混合算式
		ex.Addtion_Substract_50();
		p.TranData(ex, 2);
	}
}