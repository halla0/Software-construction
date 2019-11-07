package sun_50_100;
public class sum1 {
	
	static int sum = 0;
	static int []a_judge = new int[55];
	static int []b_judge = new int[55];
	static int []c_judge = new int[55];
	static int []d_judge = new int[55];/*	以上四个数组用来判断式子是否重复和结果是否大与100，小于0*/
	
	public static int Produce_random_2(int temp){/*生成0-1的随机数字，0 + ，1 -*/
		temp = (int)(Math.random()*2);
		return temp;
	}
	
	public static int Produce_random_100(int temp){/*生成0-100的数字*/
		temp = (int)(Math.random()*101);/*生成0-100的数字*/
		return temp;
	}
	
	public static void produce(){/*生成式子,然后判断是否符合要求*/
		while(sum < 50){
			int a = 0,b = 0,c = 0,d = 0;
			a = Produce_random_100(a);
		    b = Produce_random_100(b);
		    c = Produce_random_2(c);/*以上生成式子*/
			if(c == 1){/*加*/
			    d = a + b;
		    }
			else{/*减*/
				d = a - b;
			}
			a_judge[sum] = a;
			b_judge[sum] = b;
			c_judge[sum] = c;
			d_judge[sum++] = d;/*满足条件+1*//*a的值，b的值，c的值，d的值*/
		}		
	}
	
	public static void Print_For_50(){/*打印第几题 index,a的值,b的值，c表示加减号*/
		System.out.println("题目：");
		for(int i = 0 ; i < sum ;i++){
			System.out.print((i+1)+" : "+a_judge[i]);/*控制输出格式*/
			if(c_judge[i] == 1){
				System.out.print(" + ");
			}
			else{
				System.out.print(" - ");
			}
			System.out.print(b_judge[i]+" = "+"\n");/*控制输出格式*/
		}
	}
	
	public static void Print_Ans(){/*打印结果*/
		System.out.println("答案：");
		for(int i = 0 ; i < sum ;i++){
			System.out.println(i+"： "+d_judge[i]);//控制格式
		}		
	}
	
	public static void main(String[] args) {
		produce();/*生成题目*/
		Print_For_50();/*打印题目*/
		Print_Ans();
	} 
}
