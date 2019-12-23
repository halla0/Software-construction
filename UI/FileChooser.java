package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ConnCsv.CSV;
public class FileChooser extends JFrame implements ActionListener{
	public File file;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser( new CSV().SOURSE_P );
		
		//提供文件选择的对话框,只能看到目录
		//JFileChooser.FILES_AND_DIRECTORIES 目录文件都能选择
		//jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
		
		jfc.showDialog( new JLabel(), "选择" );
		
		File file0 = jfc.getSelectedFile();
		//输出它的路径
		//System.out.println( file0.getPath() );
		file = file0;
		//return file.getPath();
	}
 
}