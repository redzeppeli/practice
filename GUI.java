package com.practice;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GUI {
	public static void main(String args[]) {
		MyFrame frame = new MyFrame("電卓");
		frame.setVisible(true);
	}
}

class MyFrame extends JFrame {

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField("");//計算結果
	double stackedValue = 0.0;
	boolean isStacked = false;
	boolean afterCalc = false;
	String currentOp = "";

	public MyFrame(String title) {

		contentPane.setLayout((borderLayout1));
		setTitle(title);
		setSize(250, 300);
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//×ボタンで終了するメソッド。

		contentPane.add(result, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 4));
		//ボタンを配置するパネル
		contentPane.add(p, BorderLayout.CENTER);

		p.add(new NumberButton("7"), 0);
		p.add(new NumberButton("8"), 1);
		p.add(new NumberButton("9"), 2);
		p.add(new CalcButton("÷"), 3);
		p.add(new NumberButton("4"), 4);
		p.add(new NumberButton("5"), 5);
		p.add(new NumberButton("6"), 6);
		p.add(new CalcButton("×"), 7);
		p.add(new NumberButton("1"), 8);
		p.add(new NumberButton("2"), 9);
		p.add(new NumberButton("3"), 10);
		p.add(new CalcButton("－"), 11);
		p.add(new NumberButton("0"), 12);
		p.add(new JButton("."), 13);
		p.add(new CalcButton("＋"), 14);
		p.add(new CalcButton("＝"), 15);

		contentPane.add(new ClearButton(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	//テキストフィールド(答)
	public void appendResult(String c) {
		if(!afterCalc) {
			result.setText(result.getText() + c);
		}else {
		result.setText(c);
		afterCalc = false;
	}
	}
	//数字を入力するボタン
	public class NumberButton extends JButton implements ActionListener {
		public NumberButton(String keyTop) {
			super(keyTop);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText();
			appendResult(keyNumber);
		}
	}
	//演算子ボタン
		public class CalcButton extends JButton implements ActionListener {
			public CalcButton(String op) {
				super(op);
				this.addActionListener(this);
			}
			
			public void actionPerformed(ActionEvent e) {
				if (isStacked) {
					double resultValue = (Double.valueOf(result.getText())).doubleValue();
					
					if (currentOp.equals("＋")) {
						stackedValue += resultValue;
					} else if (currentOp.equals("－")) {
						stackedValue -= resultValue;
					} else if (currentOp.equals("×")) {
						stackedValue *= resultValue;
					} else if (currentOp.equals("÷")) {
						stackedValue /= resultValue;
					}
						result.setText(String.valueOf(stackedValue));
				}
					currentOp = this.getText();
					stackedValue = (Double.valueOf(result.getText())).doubleValue();
					afterCalc = true;
					if (currentOp.equals("＝")) {
						isStacked = false;
					} else {
						isStacked = true;
					}
				}
		}
			//クリアボタン
			public class ClearButton extends JButton implements ActionListener {
				public ClearButton() {
					super("C");
					this.addActionListener(this);
				}

				public void actionPerformed(ActionEvent evt) {
					stackedValue = 0.0;
					afterCalc = false;
					isStacked = false;
					result.setText("");
				}
			}
		}

