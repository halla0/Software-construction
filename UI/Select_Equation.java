package UI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import Enity.Helper;
import Enity.TransData;
public class Select_Equation extends JDialog{
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	//构造函数
	public Select_Equation(final MyFrame f){
		super(f);
		//设置标题
		this.setTitle("选择产生的式子的类型");
		//置关闭窗体时执行的操作
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		//设置位置和大小
		this.setBounds(0,0,460,200);
		//设置居中
		this.setLocationRelativeTo( null );
		//设置可见
		this.setVisible( true );
		//取消窗体布局管理器设置
		this.setLayout( null );
		
		//以下为班按钮前面的文字
		
		//加法算式
		JTextField jt1 = initSelect( 90, 10, 160, 30, "产生50道加法算式" );
		//减法算式
		JTextField jt2 = initSelect( 90, 60, 160, 30, "产生50道减法算式" );
		//加减法算式
		JTextField jt3 = initSelect( 90, 110, 180, 30, "产生50道加减法算式" );
		
		//以下为按钮设置
		
		//点击生成加法算式
		JButton jButton1 = setButton( 320, 10, 40, 30, "点击");
		//添加鼠标点击事件
		jButton1.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	TransData t = new TransData();
	        	t.setNum( 0 );
				Helper help = new Helper();
				help.transData = t;
				//在dialog中调用主类方法
				f.SetAllText();
				//退出
	        	dispose();
	        }
	    });
		//点击生成减法算式
		JButton jButton2 = setButton( 320, 60, 40, 30, "点击");
		//添加鼠标点击事件
		jButton2.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	TransData t = new TransData();
	        	t.setNum(1);
				Helper help = new Helper();
				help.transData = t;
				//在dialog中调用主类方法
				f.SetAllText();
				//退出
	        	dispose();	
	        }
	    });
		//点击生成加法减法算式
		JButton jButton3 = setButton( 320, 110, 40, 30, "点击");		
		//添加鼠标点击事件
		jButton3.addMouseListener(new java.awt.event.MouseAdapter(){
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	TransData t = new TransData();
	        	t.setNum(2);
				Helper help = new Helper();
				help.transData = t;
				//在dialog中调用主类方法
				f.SetAllText();
				//退出
	        	dispose();	        	
	        	//System.out.println("3");
	        }
	    });
		
	}
	//设置文本的位置大小等
	public JTextField initSelect(int x,int y,int width,int height, String Name){
		
		Container c = getContentPane();
        //设置其想，放哪儿，放哪儿
        c.setLayout( null );
        // 创建文本框，指定可见列数为8列
        JTextField textField = new JTextField(  );
        //设置字体
        textField.setFont( new Font("微软雅黑", Font.PLAIN, 18 ) );
        //设置内容
        textField.setText( Name );
        //设置位置和大小
        textField.setBounds( x, y, width, height);
        //设置其不可编辑
        textField.setEditable( false );
        //设置背景颜色
        textField.setBackground( new Color(230, 230, 250) );
        //加入
        c.add(textField);
		//应用面板
		this.setContentPane( c );
		
		return textField;
	}

	//按钮设置，位置，大小，名字
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
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyFrame dialog = new MyFrame();
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
}