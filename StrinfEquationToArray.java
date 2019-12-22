package Experiment3;

import Pratice2_2.Exercises_2;

public class StrinfEquationToArray {
	//a - b = d，符合位是c
	int a[] = new int[50];
	int b[] = new int[50];
	int c[] = new int[50];
	int d[] = new int[50];
	//将String转换为int
	public int String_To_Int(String temp){
		int sum = 0;
		for( int i = 0 ; i < temp.length() ; i++ ){
			sum = sum * 10 + ( temp.charAt(i) - '0' );
		}
		return sum;
	}
	//截取数字
	public void Trans(){
		Exercises_2 ex = new Exercises_2();
		ex.Addtion_50();
		int length = ex.N;
		System.out.println(length);
		for( int i = 0 ; i < length ; i++ ){
			//System.out.println(ex.equation_50[i]);
			int Temp_a = 0,Temp_b = 0 ,Temp_c = 0;
			String temp = ex.equation_50[i];
			int size = temp.length();
		}
	}
	public static void main(String[] args) {
		//Exercises_2 ex = new Exercises_2();
		StrinfEquationToArray test = new StrinfEquationToArray();
		test.Trans();
	}
}