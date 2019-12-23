package Experiment3;
import java.util.Random;
public class Substract extends BinaryOperation_3_2{
	public void Produce() {
		Random r = new Random();
		// 生成式子等号前面的各个部分
		int leftTemp = r.nextInt(101);
		int rightTemp = r.nextInt(101);
		int valueTemp = leftTemp - rightTemp;
		if ( judge ( valueTemp ) ){
			char optionTemp2 = '-';
			valueTemp = Compute(leftTemp, rightTemp, '-');
			setOption('-');
			setLeft(leftTemp);
			setRight(rightTemp);
			setValue(valueTemp);
			Construct();			
		}
		else{
			Produce();
		}
	}
}