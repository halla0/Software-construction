package sun_50_100;
public class story3 {
	static int judge_add = 0;/*判断式子中存在加法，出现了值为1*/
	static int judge_sub = 0;/*判断式子中存在减法，出现了值为1*/
	static int sum = 0;          
	static int []a_judge = new int[55];
	static int []b_judge = new int[55];
	static int []c_judge = new int[55];
	static int []d_judge = new int[55];/*	以上四个数组用来判断式子是否重复和结果是否大与100，小于0*/
	/*2（a） -（c） 3（b） = -1 （d）,表示数组的各个含义*/
	public static boolean judge(int a,int b,int c,int d){/*判断式子是否重复，相加是否大于100,相减是否小于0*/
		for(int i = 0 ; i < sum ; i++){
			if(d<0 || d>100) return false;/*如果小于0 或者 大于100，则不符合要求*/
			if(a ==a_judge[i] &&b ==b_judge[i] && c == c_judge[i]) return false;
			/*如果算式对应相等，则表示重复*/
			if(b ==a_judge[i] &&a ==b_judge[i] && c == c_judge[i]) return false;
			/*考虑1+2 和 2+1*/
		}
		return true;
	}
	
	public static int Produce_random_2(int temp){/*生成0-1的随机数字，0 + ，1 -*/
		temp = (int)(Math.random()*2);
		return temp;
	}
	
	public static int Produce_random_100(int temp){/*生成0-100的数字*/
		temp = (int)(Math.random()*101);/*生成0-100的数字*/
		return temp;
	}
	
	public static void produce(){/*生成式子,然后判断是否符合要求*/
		while(sum < 50){/*生成50个*/
			int a = 0,b = 0,c = 0,d = 0;
			a = Produce_random_100(a);
		    b = Produce_random_100(b);
		    c = Produce_random_2(c);/*以上生成式子*/
			if(c == 1){/*加*/
			    d = a + b;
			    judge_add = 1;/*使得判断出现加法的变量的值为1*/
		    }
			else{/*减*/
				d = a - b;
				judge_sub = 1;/*使得判断出现减法的变量的值为1*/
			}
			if(judge(a, b, c, d)){/*a的值，b的值，c的值，d的值*/
				a_judge[sum] = a;
				b_judge[sum] = b;
				c_judge[sum] = c;
				d_judge[sum++] = d;/*满足条件+1*/
			}
			if(sum == 50&&judge_add + judge_sub < 2 ){
			/*如果此时生成了50个式子，并且不同时存在加法和减法（小于2）*/
				sum = 1;/*此时将生成的式子销毁，重新生成*/
				judge_add = 0;
				judge_sub = 0;/*初始化两个变量的值*/
			}
		}		
	}
	
	public static void Print_For_50(){/*打印第几题 index,a的值,b的值，c表示加减号*/
		System.out.println("答案：");
		for(int i = 0 ; i < sum ;i++){
			if(i!=0 && i%5 == 0){/*控制换行*/
				System.out.print("\n");		
			}	
			System.out.print((i+1)+" : "+a_judge[i]);/*控制输出格式*/
			if(c_judge[i] == 1){
				System.out.print(" + ");
			}
			else{
				System.out.print(" - ");
			}
			System.out.print(b_judge[i]+" = "+d_judge[i]+"  ");/*控制输出格式*/
		}
	}
	
	public static void main(String[] args) {
		produce();/*生成算式*/
		Print_For_50();/*打印结果*/
	}
}
