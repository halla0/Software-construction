package Experiment3;
import java.util.Scanner;
public class Test {
	void write(){
		Scanner sc = new Scanner( System.in );
		String str = "";
		String temp = "";
		while( true ){
			temp = sc.next();
			//System.out.println( temp );
			if( temp.equals( "save" ) ){
				break;
			}
			str += temp;
		}
		System.out.println(str);
	}
	void read(){
		//String str = 
	}
	public static void main(String[] args) {
		Test t = new Test();
		t.write();
//		CSV c = new CSV();
//		c.CsvRead( "D:\\tt.csv" );
	}
}