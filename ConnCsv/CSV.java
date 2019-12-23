package ConnCsv;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import Experiment3.Exercises_2;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
public class CSV {
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
	//创建文件
	//把先计算出正确的算式名字，然后生成文件，再把产生的算式依次放到练习文件里面
	public String TranDataExe(Exercises_2 ex , int judge) throws IOException{
		//用来存放真正的后缀
		String lastIndex = "";
		//加法算式
		if( judge == 0 ){
			lastIndex = "//" + EX_INDEXNAME_ADD + new CSV().SumExerciseFile( new File(SOURSE_E ) );
		}
		//减法算式
		else if( judge == 1){
			lastIndex = "//" + EX_INDEXNAME_SUB + new CSV().SumExerciseFile( new File(SOURSE_E ) );
		}
		//混合加减法算式
		else{
			lastIndex = "//" + EX_INDEXNAME_ADD_SUB + new CSV().SumExerciseFile( new File(SOURSE_E ) );
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
		//System.out.println( file.getPath() );
		return file.getPath();
	}
	
	//将批改文件和练习文件的数据进行保存
	public String TranDataPra(Exercises_2 ex , int judge ,String BelongToFile) throws IOException{
		//用来存放真正的后缀
		String lastIndex = "";
		//加法算式
		if( judge == 0 ){
			lastIndex = "//" + "practise_add_50_" + new CSV().SumExerciseFile( new File(SOURSE_P) );
		}
		//减法算式
		else if( judge == 1){
			lastIndex = "//" + "practise_sub_50_" + new CSV().SumExerciseFile( new File(SOURSE_P) );
		}
		//混合加减法算式
		else{
			lastIndex = "//" + "practise_add_sub_50_" + new CSV().SumExerciseFile( new File(SOURSE_P) );
		}
		//使用csv里面的方法创建文件
		//新建一个csv对象来使用它的方法
		CSV c = new CSV();
		//新建获得正确名字的文件,并且将file对象留给下面的存取操作使用
		File file = new File( SOURSE_P + lastIndex );
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
		//将所依赖的习题文件保存在表头
		String []temp0 = new String[2];
		temp0[0] = BelongToFile;
		c.CsvWrite( temp0, SOURSE_P + lastIndex );
		
		//采用特殊字符串将答案和式子进行分割
		temp[50] = "---";
		//将数据存储
		c.CsvWrite( temp, SOURSE_P + lastIndex );
		//将答案存储
		for( int i = 0 ; i < 50 ; i++ ){
			//-1表示没写入答案
			if( ex.value_d[i] == -1 ){
				//给每个答案加上序号  12@58  表示12题答案为58
				temp[i] = String.valueOf(i+1) + "@";
			}
			else{
				temp[i] = String.valueOf(i+1) + "@" + String.valueOf( ex.value_d[i] ); 
			}
			
		}
		temp[50] = "end";
		c.CsvWrite( temp, SOURSE_P + lastIndex );
		return file.getPath();
	}
	//将批改文件和练习文件的数据进行保存
	public String TranDataAlt(Exercises_2 ex , int judge,String BelongToFile ) throws IOException{
		//用来存放真正的后缀
		String lastIndex = "";
		//加法算式
		if( judge == 0 ){
			lastIndex = "//" + "alt_add_50_" + new CSV().SumExerciseFile( new File( SOURSE_A ) );
		}
		//减法算式
		else if( judge == 1){
			lastIndex = "//" + "alt_sub_50_" + new CSV().SumExerciseFile( new File( SOURSE_A ) );
		}
		//混合加减法算式
		else{
			lastIndex = "//" + "alt_add_sub_50_" + new CSV().SumExerciseFile( new File( SOURSE_A ) );
		}
		//使用csv里面的方法创建文件
		//新建一个csv对象来使用它的方法
		CSV c = new CSV();
		//新建获得正确名字的文件,并且将file对象留给下面的存取操作使用
		File file = new File( SOURSE_A + lastIndex );
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
		//将所依赖的习题文件保存在表头
		String []temp0 = new String[2];
		temp0[0] = BelongToFile;
		c.CsvWrite( temp0, SOURSE_A + lastIndex );
		//采用特殊字符串将答案和式子进行分割
		temp[50] = "<!-- -->";
		//将数据存储
		c.CsvWrite( temp, SOURSE_A + lastIndex );
		//将答案存储
		for( int i = 0 ; i < 50 ; i++ ){
			if( ex.value_d[i] == 1 ){
				temp[i] = "T";
			}
			else{
				temp[i] = "F";
			}
		}
		c.CsvWrite( temp, SOURSE_A + lastIndex );
		return file.getPath();
	}
	
	//计算习题文件的后缀，比如005
	private String SumExerciseFile(File file){
		//测试得到文件下文件的数量
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
	//创建文件，
	public void CreateFile(File file) throws IOException{
		//创建文件
		file.createNewFile();
		//获得文件的名字
		System.out.println( file.getName() );
	}
	//文件读入部分
	public String[] CsvRead( String pathCSV ){//变量为文件的路径+名字
		//用来存取式子和答案
		String []tempSaveEquAns = new String[105];
		//用来当作tempSaveEquAns的下标使用
		int sum = 0 ;
		
		ArrayList<String[]> lstFile = new ArrayList<String[]>();
		//cvs文件读入部分
		try {
			CsvReader reader = new CsvReader(pathCSV,',',Charset.forName("gb2312"));
			//读取表头 ,加上这一句是不算表头数据从第二行开始取
			reader.readHeaders();
			
			//逐行读取直至读取完
			while(reader.readRecord()){
				//System.out.println(reader.getRawRecord());
				lstFile.add( reader.getValues() );
			}
			//资源关闭
			reader.close();
			//输出大小
			//System.out.println(lstFile.size());
			//System.out.println(lstFile.get(5)[0].toString());
			for(int row = 0 ; row < lstFile.size() ; row ++ ){
				for(int col=0 ; col < lstFile.get( row ).length ; col++){
					String cell = lstFile.get(row)[col];
					
					//将提取的数据用数组存起来
					tempSaveEquAns[ sum++ ] = cell ;
					
					//System.out.print(cell + ",");
				}
				//System.out.println();
			}
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			//输出异常
			e.printStackTrace();
		}
		
		return tempSaveEquAns;
	}
	//cvs文件写入部分,实现了文件追加内容
	public void CsvWrite( String[] str, String file ){//生成习题不用写入头
		File f = new File( file );
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter(f,true) );
			//以逗号的格式分割开
			CsvWriter cwriter = new CsvWriter(writer,'\n');
			
			cwriter.writeRecord(str,false);
			//关闭资源
			cwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
