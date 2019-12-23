package Experiment3;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Exercises_2 {
	public String [] equation_50 = new String[250];
	public static int sum = 0;
	public static int N = 50;
	//以下为式子的各个部分
	public int left_a[] = new int[50];
	public int right_b[] = new int[50];
	public int operation_c[] = new int[50];
	public int value_d[] = new int[50];
	
	//使用HashMap来判断是否重复
	Map<String,Boolean> mp = new HashMap();
	//生成50道加法算式
	public void Addtion_50(){
		Addtion a = new Addtion();
		//Produce方法里面自动判断结果重复
		//每次使用清理map
		mp.clear();
		sum = 0;
		String temp = "";
		while( sum < N ){
			a.Produce();
			temp = a.getEquation();
			if( mp.get(temp) == null ){
				mp.put(temp, true);
				
				//将数据存放在数组中
				left_a[sum] = a.getLeft();
				right_b[sum] = a.getRight();
				operation_c[sum] = 0;
				value_d[sum] = a.getValue();
				
				//字符串存放式子
				equation_50[ sum++ ] = temp;
				
			}
		}
	}
	//生成50道减法算式
	public void Substract_50(){
		Substract s = new Substract();
		//Produce方法里面自动判断结果重复
		//每次使用清理map
		mp.clear();
		sum = 0;
		String temp = "";
		while( sum < N ){
			s.Produce();
			temp = s.getEquation();
			//判断是否重复
			if( mp.get(temp) == null ){
				mp.put(temp, true);
				//将数据存放在数组中
				left_a[sum] = s.getLeft();
				right_b[sum] = s.getRight();
				operation_c[sum] = 1;
				value_d[sum] = s.getValue();
				equation_50[ sum++ ] = temp;
			}
		}		
	}
	//生成混合50道加减法算式
	public void Addtion_Substract_50(){
		Random ra = new Random();
		Substract s = new Substract();
		Addtion a = new Addtion();
		//每次使用map都清理一次
		mp.clear();
		sum = 0;
		String temp = "";
		while( sum < N ){
			int tempindex = ra.nextInt(2);
			//如果生成的随机数为1,则为减法
			if(tempindex == 1){
				//判断是否重复
				s.Produce();
				temp = s.getEquation();
				if ( mp.get(temp) == null ) {
					mp.put(temp, true);
					
					//将数据存放在数组中
					left_a[sum] = s.getLeft();
					right_b[sum] = s.getRight();
					operation_c[sum] = 1;
					value_d[sum] = s.getValue();
					
					//用式子存放字符串
					equation_50[sum++] = temp;
				}				
			}
			else{
				//判断是否重复
				a.Produce();
				temp = a.getEquation();
				if ( mp.get(temp) == null ) {
					mp.put(temp, true);
					
					//将数据存放在数组中
					left_a[sum] = a.getLeft();
					right_b[sum] = a.getRight();
					operation_c[sum] = 0;
					value_d[sum] = a.getValue();
					
					//用式子存放字符串
					equation_50[sum++] = temp;
				}					
			}
			
		}
	}
	public void Print(){
		for(int i = 0 ; i < N ; i++){
			System.out.print((i+1) + ": "+ equation_50[i]+" ");
			if( (i+1)%5 ==0 ){
				System.out.print("\n");
			}
		}
	}
}