package Experiment3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
public class CSV {
	//创建文件
	public void CreateFile(File file) throws IOException{
		//创建文件
		file.createNewFile();
		//获得文件的名字
		System.out.println( file.getName() );
	}
	//文件读入部分
	public void CsvRead( String pathCSV ){//变量为文件的路径+名字
		ArrayList<String[]> lstFile = new ArrayList<String[]>();
		//cvs文件读入部分
		try {
			CsvReader reader = new CsvReader(pathCSV,',',Charset.forName("gb2312"));
			//读取表头 ,加上这一句是不算表头数据从第二行开始取
			//reader.readHeaders();
			//逐行读取直至读取完
			while(reader.readRecord()){
				//System.out.println(reader.getRawRecord());
				lstFile.add(reader.getValues());
			}
			//资源关闭
			reader.close();
			//输出大小
			//System.out.println(lstFile.size());
			//System.out.println(lstFile.get(5)[0].toString());
			for(int row = 0 ; row < lstFile.size() ; row ++ ){
				for(int col=0 ; col < lstFile.get( row ).length ; col++){
					String cell = lstFile.get(row)[col];
					System.out.print(cell + ",");
				}
				System.out.println();
			}
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			//输出异常
			e.printStackTrace();
		}		
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
	public static void main(String[] args){
		
		//测试读取包装好的csv
		CSV test = new CSV();
		test.CsvRead( "D://test.csv" );
		//String [][]s = new String[][];
		//test.CsvWrite();
	}
}
