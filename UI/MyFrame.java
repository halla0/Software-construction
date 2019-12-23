package UI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import ConnCsv.CSV;
import Enity.Helper;
import Experiment3.Exercises_2;
public class MyFrame extends JFrame{
	private JTextField [] jt1;
	private JTextField [] jt2;
	//面板
	private JPanel contentPane;
	//用来生成算式的对象
	private Exercises_2 ex = new Exercises_2();
	//习题文件名字
	public String exercise_Equation_File_Name;
	//习题文件的名字及路径
	private String Exercise_Name;
	//批改文件的名字及路径
	private String Alter_Name;
	//练习文件的名字及路径
	private String Practise_Name;
	//
	//构造
	public MyFrame(){
		//设置字体大小
		//this.setFont( new Font("微软雅黑", Font.PLAIN, 18 ) );
		//设置标题
		this.setTitle( "练习系统" );
		//设置关闭窗体时执行的操作
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//设置窗体显示位置及大小
		this.setBounds( 0, 0, 1640, 800 );
		//居中
		this.setLocationRelativeTo( null );
		//设置可见
		this.setVisible( true );
		//取消窗体布局管理器设置
		this.setLayout( null );
		/* 以上为主界面的设置*/
	
		//继续上次的题， 按钮，位置，大小，名字
		JButton jButton1 = setButton( 25, 5, 130, 40, "继续上次的题");
		//添加鼠标点击事件
		jButton1.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	FileChooser f = new FileChooser();
	        	f.actionPerformed( null );
	        	//尝试输出
	        	CSV c = new CSV();
	        	//System.out.println( f.file.getPath() );
	    		String [] t = new String[101];
	    		//将指定路径的文件内容读取出来
	    		t = c.CsvRead( String.valueOf( f.file.getPath() ) );
	    		
	    		/*
	    		//罗列出来式子和结果
	    		for( int i =0 ; i < 101 ; i++ ){
	    			System.out.println( t[i] );
	    		}
	    		*/
	    		//
	    		SetPraFileToMain( t );
	        }
	    });
		final MyFrame myFrame=this;
		//产生习题， 按钮
		JButton jButton2 = setButton( 175, 5, 90, 40, "产生习题");
		//添加鼠标点击事件
		jButton2.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	Select_Equation s = new Select_Equation( myFrame );
	        	s.setVisible( true );
	        	//System.out.println(s.getName());
	        	//System.out.println( Helper.transData.getNum() );	
	        }
	    });
		//打分，自动保存文件
		JButton jButton3 = setButton( 285, 5, 50, 40, "打分");
		//添加鼠标点击事件
		jButton3.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	    		////设置下方数据，答对几题//将答案设置成好处理的格式
	    		int wrong = Mark();
        		try {
					String temp = new CSV().TranDataAlt( ex, Helper.transData.getNum(),Exercise_Name );
					//JOptionPane.showMessageDialog(this, "添加成功");
					JOptionPane.showMessageDialog( null,"打分好了，文件已经保存！" );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	    		
	    		//显示信息在界面中
	    		initBottomData( wrong );
	    		
	        }
	    });		
		//保存，只保存练习题
		final JButton jButton4 = setButton( 355, 5, 50, 40, "保存");
		jButton4.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	//保存之前，先处理文件,2019/12/21改，考虑到读取结果的时候会
	        	///出现空空空空空，11，12，13，而读取的为11，12，13，没有了之间空
	        	//故在存储答案时把顺序也存上，就能精确答案对应
	        	SaveAns();
	        	//只要有习题，则可以保存
	        	if( !ex.equation_50[0].equals( "" ) ){
	        		System.out.println("有对应的习题，可以保存");
	        		try {
						String temp = new CSV().TranDataPra( ex, Helper.transData.getNum() , Exercise_Name );
						//JOptionPane.showMessageDialog(this, "添加成功");
						JOptionPane.showMessageDialog( null,"保存成功！" );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	    });			
		/*以上为按钮事件的设定*/
	}
	//将练习文件的数据读取出来
	public void SetPraFileToMain( String [] temp ){
		//用循环来实现设定
		//式子
		jt1 = new JTextField[50];
		jt2 = new JTextField[50];
		jt1[0] = SetEqau( 50, 60, temp[0] );
		//答案框
		jt2[0] = SetAns( 152, 60 );
		jt2[0].setText( temp[51].substring( temp[51].indexOf('@') + 1, temp[51].length() ) );
		for( int i = 1 ; i < 50 ; i++ ){
			//System.out.print(i+"@");
			if( i % 10 != 0 ){
			    jt1[i] = SetEqau( jt2[i-1].getX() + 50, jt2[i-1].getY(), temp[i] );
			    jt2[i] = SetAns( jt1[i].getX() + 102, jt2[i-1].getY() );
			    //将结果12@24  的24提取出来
			    jt2[i].setText(  temp[i+51].substring( temp[i+51].indexOf('@') + 1, temp[i+51].length() )  );
			    //System.out.println( jt1[i].getX() +"--" +jt1[i].getY() + "+++++" +jt2[i].getX() +"--" + jt2[i].getY());
			}
			//System.out.println("ha");
			else{
				jt1[i] = SetEqau( 50, jt1[i-1].getY()+40, temp[i] );
				jt2[i] = SetAns( 152, jt1[i].getY() );
			    //将结果12@24  的24提取出来
			    jt2[i].setText(  temp[i+51].substring( temp[i+51].indexOf('@') + 1, temp[i+51].length() )  );
				//System.out.println( jt1[i].getX() +"--" +jt1[i].getY() + "+++++" +jt2[i].getX() +"--" + jt2[i].getY());
			}
		}		
	}
	//设置所有的文本布局，已经固定式子框100，30长度  答案框---
	public void SetAllText(){
		//产生相应习题
		if( Helper.transData.getNum() == 0 ){
			ex.Substract_50();
		}
		else if( Helper.transData.getNum() == 1 ){
			ex.Addtion_50();
		}
		else{
			ex.Addtion_Substract_50();
		}
		//使用csv对象方法来存放文件
		CSV csv = new CSV();
		try {
			//往文件里面写入数据,并保存路径
			Exercise_Name = csv.TranDataExe( ex, Helper.transData.getNum() );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//用循环来实现设定
		//式子
		jt1 = new JTextField[50];
		jt2 = new JTextField[50];
		jt1[0] = SetEqau( 50, 60, ex.equation_50[0] );
		//答案框
		jt2[0] = SetAns( 152, 60 );
		for( int i = 1 ; i < 50 ; i++ ){
			//System.out.print(i+"@");
			if( i % 10 != 0 ){
			    jt1[i] = SetEqau( jt2[i-1].getX() + 50, jt2[i-1].getY(), ex.equation_50[i] );
			    jt2[i] = SetAns( jt1[i].getX() + 102, jt2[i-1].getY() );
			    //System.out.println( jt1[i].getX() +"--" +jt1[i].getY() + "+++++" +jt2[i].getX() +"--" + jt2[i].getY());
			}
			//System.out.println("ha");
			else{
				jt1[i] = SetEqau( 50, jt1[i-1].getY()+40, ex.equation_50[i] );
				jt2[i] = SetAns( 152, jt1[i].getY() );
				//System.out.println( jt1[i].getX() +"--" +jt1[i].getY() + "+++++" +jt2[i].getX() +"--" + jt2[i].getY());
			}
		}
	}
	//位置，大小，名字
	public JButton setButton(int x,int y,int width,int height, String Name){
		//创建容器对象
		Container c = getContentPane();
		//新建按钮，并且设置名字
		JButton b2 = new JButton(Name);
		//让蚊子铺满按钮
		b2.setBorder( BorderFactory.createEtchedBorder() );
		//b2.setText(Name);
		b2.setFont( new Font("微软雅黑", Font.PLAIN, 18 ) );
		//b2.setSize(width, height)
		b2.setBounds(x,y,width,height);
		//设置颜色
		b2.setBackground( new Color(230, 230, 250) );
		//添加进容器
		c.add( b2 );
		//返回按钮
		return b2;
	}
	//设置文本的位置大小等
	public JTextField SetEqau(int x,int y, String Name){
		Container c = getContentPane();
        //设置其想，放哪儿，放哪儿
        c.setLayout( null );
        // 创建文本框，指定可见列数为8列
        JTextField textField = new JTextField(  );
        //设置字体21
        textField.setFont( new Font("微软雅黑", Font.PLAIN, 18 ) );
        //设置内容
        textField.setText( Name );
        //设置位置和大小,固定长度 100，30
        textField.setBounds( x, y, 100, 30);
        //设置其不可编辑
        textField.setEditable( false );
        //设置其右对齐
        textField.setHorizontalAlignment(JTextField.RIGHT);
        //设置背景颜色
        textField.setBackground( new Color(230, 230, 250) );
        //加入
        c.add(textField);
		//应用面板
		this.setContentPane( c );
		return textField;
	}
	//答案放置入口,只设定位置，大小固定
	public JTextField SetAns(int x,int y){
		Container c = getContentPane();
        //设置其想，放哪儿，放哪儿
        c.setLayout( null );
        // 创建文本框，指定可见列数为8列
        JTextField textField = new JTextField(  );
        //设置字体
        textField.setFont( new Font("微软雅黑", Font.PLAIN, 18 ) );
        //设置位置和大小
        textField.setBounds( x, y, 40, 31 );
        //设置背景颜色
        textField.setBackground( new Color(230, 230, 250) );
        //加入
        c.add(textField);
		//应用面板
		this.setContentPane( c );
		return textField;		
	}
	//设置下方做题的信息
	public void initBottomData( int wrong ){
		Container c = getContentPane();
		JTextArea t = new JTextArea();
		//设置背景颜色
		t.setBackground( new Color(	119,136,153 ) );
		//设置位置，大小
		t.setBounds( 380,500,900,200);
		//设置文字
		String temp = "所属习题文件：" + Exercise_Name + "\n";
		String temp2 = "已成功保存文件至：" + "\n";
		//正确的题目数量
		int correct = 50 - wrong;
		//正确率
		int Rate =  correct * 2;
		System.out.println( Rate );
		String temp3 = "正确：" + String.valueOf( correct ) + "\n" + "错误：" + String.valueOf( wrong ) 
		+ "\n" + "正确率：" + String.valueOf( correct ) +"%" + "\n" + "得分：" + String.valueOf( 2*correct ) + "分" +"\n";
		temp3 = temp + temp3 + temp2;
		
		t.setText( temp3 );
		//设置字体
		t.setFont(  new Font("微软雅黑", Font.PLAIN, 18 )  );
		//设置其不可编辑
		t.setEditable( false );
		//设置可见
		t.setVisible( true );
		//应用
		this.setContentPane( c );
		c.add(t);
	}
	//打分,
	private int Mark(){
		int sum = 0 ;
		for( int i = 0 ; i < 50 ; i++ ){
			//统计错的
			if( jt2[i].getText().equals("") || Integer.parseInt( jt2[i].getText() ) != ex.value_d[i] ) {
				jt2[i].setBackground( Color.red );
				//0表示错误
				ex.value_d[i] = 0;
				sum++;
			}
			else{
				//1表示答案正确
				ex.value_d[i] = 1;
			}
		}
		return sum;
	}
	//保存文件，用value[]的结果存
	public void SaveAns(){
		for( int i = 0 ; i < 50 ; i++ ){
			//如果没有输入答案
			if( jt2[i].getText().equals("") ){
				//-1表示没有输入答案
				ex.value_d[i] = -1;
			}
			else{
				//如果有输入答案，就记录
				ex.value_d[i] = Integer.parseInt( jt2[i].getText() );
			}
		}
		for( int i = 0 ;  i < 50 ; i++ ){
			System.out.println( ex.value_d[i] );
		}
		System.out.println("测试能出来");
	}
	//main
	public static void main(String[] args) {
	    MyFrame t = new MyFrame();
	}
}