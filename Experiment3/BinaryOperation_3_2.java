package Experiment3;
import java.util.Random;
public abstract class BinaryOperation_3_2 {
	/**
	 * 实现计算结果，生成式子，
	 */
	//分别表示a - b = c的各个部分
	private int left;
	private int right;
	private char option;
	private int value;
	private String equation;
	//实现生成式子的各个部分
	abstract void Produce();
	//实现计算结果
	public int Compute(int leftTemp,int rightTemp,char optionTemp){
		if(optionTemp == '-'){
			return leftTemp - rightTemp;
		}
		else{
			return leftTemp + rightTemp;
		}
	}
	//实现构造式子
	public void Construct(){
		String temp = String.valueOf(getLeft()) + " " + String.valueOf(getOption()) + " ";
		temp += String.valueOf(getRight()) + " = ";
		setEquation(temp);
	}
	//判断结果是否符合要求
	public boolean judge( int valueTemp ){
		if( valueTemp > 100 || valueTemp < 0){
			return false;
		}
		return true;
	}
	//一以下为各个元素的set和get方法
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public char getOption() {
		return option;
	}

	public void setOption(char option) {
		this.option = option;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getEquation() {
		return equation;
	}
	public void setEquation(String equation) {
		this.equation = equation;
	}
	
}