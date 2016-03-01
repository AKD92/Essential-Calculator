package org.ash.calc.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import org.ash.calc.engine.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private JLabel lblDisplay;
	private JLabel lblOp;
	
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JButton btnFive;
	private JButton btnSix;
	private JButton btnSeven;
	private JButton btnEight;
	private JButton btnNine;
	private JButton btnZero;
	
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnMultiplication;
	private JButton btnDivide;
	
	private JButton btnPoint;
	private JButton btnEquals;
	private JButton btnLog;
	private JButton btnLon;
	private JButton btnClear;
	private JButton btnPower;
	private JButton btnSqrt;
	
	private Action numberAction;
	private Action commandAction;
	private Action clearAllAction;
	private Action equalsAction;
	
	private ImageIcon applicationIcon;
	
	public MainWindow() {
		
		super("Essential Calculator");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		addWindowListener(new MyWindowListener());
		
		java.net.URL imgurl = getClass().getResource("calculator.png");
		applicationIcon = new ImageIcon(imgurl);
		setIconImage(applicationIcon.getImage());
		
		numberAction = new NumberAction();
		commandAction = new CommandAction();
		clearAllAction = new ClearAllAction();
		equalsAction = new EqualsAction();
		
		JPanel content = new JPanel(new BorderLayout(5,5));
		content.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(content);
		
		JPanel panel = createDisplayPanel();
		add(panel, BorderLayout.PAGE_START);
		
		panel = createKeyboardPanel();
		add(panel, BorderLayout.CENTER);
		
		panel = createBottomPanel();
		add(panel, BorderLayout.PAGE_END);
		
		pack();
		//clarifyDisplay();
		setSize(310, 500);
		setLocationRelativeTo(null);
	}
	
	private class NumberAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {
			
			String cmd = evt.getActionCommand();
			
			MathManager mng = MathManager.getManager();
			
			mng.addNumber(cmd, false);
			
		}
	}
	
	private class CommandAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {
			
			String cmd = evt.getActionCommand();
			
			MathManager mng = MathManager.getManager();
			MathOp op = MathOp.getMathOp(cmd);
			
			mng.setOperation(op);
			
		}
	}
	
	private class ClearAllAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {
			
			Memory mem = Memory.getMemory();
			mem.clearMemory();
			MathManager mng = MathManager.getManager();
			mng.wholeClear();
			updateDisplay(0.0);
			updateOp("");
		}
	}
	
	public class EqualsAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) {
			
			MathManager mng = MathManager.getManager();
			mng.doCalculation();
			
		}
	}
	
	private class MyWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent evt) {
			Application.closeApplication();
		}
	}
	
	private final JPanel createDisplayPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5,5));
		panel.setBorder(new EmptyBorder(0,0,10,0));
		
		Memory mem = Memory.getMemory();
		String dis = String.valueOf(mem.getData());
		
		lblOp = new JLabel("Operations");
		lblOp.setHorizontalAlignment(SwingConstants.LEADING);
		lblOp.setMinimumSize(lblOp.getPreferredSize());
		
		Font lblFont = new Font(Font.MONOSPACED, Font.PLAIN, 13);
		lblOp.setFont(lblFont);
		
		JPanel opPanel = new JPanel(new GridLayout(1,1));
		opPanel.add(lblOp);
		
		opPanel.setMinimumSize(lblOp.getMinimumSize());
		opPanel.setPreferredSize(lblOp.getPreferredSize());
		panel.add(opPanel, BorderLayout.PAGE_START);
		
		lblDisplay = new JLabel(dis);
		lblDisplay.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDisplay.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDisplay.setOpaque(true);
		
		JScrollPane scrDisplay = new JScrollPane(lblDisplay);
		Border innerBorder = new EmptyBorder(0,3,0,3);
		Border outerBorder = new EtchedBorder(EtchedBorder.RAISED);
		
		scrDisplay.setBorder(new CompoundBorder(outerBorder, innerBorder));
		Color back = scrDisplay.getBackground().brighter();
		lblDisplay.setBackground(back);
		scrDisplay.setBackground(back);
		
		JScrollBar horiScr = scrDisplay.getHorizontalScrollBar();
		horiScr.setPreferredSize(new Dimension(10, 10));
		
		Font f = new Font(Font.DIALOG, Font.PLAIN, 25);
		lblDisplay.setFont(f);
		
		panel.add(scrDisplay, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(20, 100));
		lblOp.setText(null);
		
		return panel;
	}
	
	private final JPanel createBottomPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel info = new JLabel("Programmed by : Ashis Kumar Das");
		info.setAlignmentX(0.5f);
		info.setAlignmentX(0.5f);
		
		Cursor handCur = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		info.setCursor(handCur);
		
		panel.add(Box.createVerticalStrut(5));
		panel.add(info);
		
		return panel;
	}
	
	private final JPanel createKeyboardPanel() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		
		Insets insTop = new Insets(0, 0, 3, 3);
		Insets insTopRight = new Insets(0, 0, 3, 0);
		Insets insBottom = new Insets(0, 0, 0, 3);
		Insets insBottomRight = new Insets(0, 0, 0, 0);
		
		Font f = new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 14);
		
		btnClear = new JButton("clr");
		btnClear.setFocusPainted(false);
		btnClear.addActionListener(clearAllAction);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.insets = insTop;
		panel.add(btnClear, gc);
		
		btnPower = new JButton("x^y");
		btnPower.setFocusPainted(false);
		btnPower.addActionListener(commandAction);
		btnPower.setActionCommand("^");
		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridwidth = 1;
		panel.add(btnPower, gc);
		
		btnSqrt = new JButton("sqrt");
		btnSqrt.setFocusPainted(false);
		btnSqrt.addActionListener(commandAction);
		btnSqrt.setActionCommand("q");
		gc.gridx = 3;
		gc.gridy = 0;
		gc.insets = insTopRight;
		panel.add(btnSqrt, gc);
		
		btnLon = new JButton("lon");
		btnLon.setFocusPainted(false);
		btnLon.addActionListener(commandAction);
		btnLon.setActionCommand("n");
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = insTop;
		panel.add(btnLon, gc);
		
		btnLog = new JButton("log");
		btnLog.setFocusPainted(false);
		btnLog.addActionListener(commandAction);
		btnLog.setActionCommand("g");
		gc.gridx = 1;
		gc.gridy = 1;
		panel.add(btnLog, gc);
		
		btnMultiplication = new JButton("X");
		btnMultiplication.setFocusPainted(false);
		btnMultiplication.addActionListener(commandAction);
		btnMultiplication.setActionCommand("*");
		gc.gridx = 2;
		gc.gridy = 1;
		panel.add(btnMultiplication, gc);
		
		btnDivide = new JButton("/");
		btnDivide.setFocusPainted(false);
		btnDivide.addActionListener(commandAction);
		btnDivide.setActionCommand("/");
		gc.gridx = 3;
		gc.gridy = 1;
		gc.insets = insTopRight;
		panel.add(btnDivide, gc);
		
		btnSeven = new JButton("7");
		btnSeven.setFocusPainted(false);
		btnSeven.addActionListener(numberAction);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = insTop;
		panel.add(btnSeven, gc);
		
		btnEight = new JButton("8");
		btnEight.setFocusPainted(false);
		btnEight.addActionListener(numberAction);
		gc.gridx = 1;
		gc.gridy = 2;
		panel.add(btnEight, gc);
		
		btnNine = new JButton("9");
		btnNine.setFocusPainted(false);
		btnNine.addActionListener(numberAction);
		gc.gridx = 2;
		gc.gridy = 2;
		panel.add(btnNine, gc);
		
		btnPlus = new JButton("+");
		btnPlus.setFocusPainted(false);
		btnPlus.addActionListener(commandAction);
		btnPlus.setActionCommand("+");
		gc.gridx = 3;
		gc.gridy = 2;
		gc.insets = insTopRight;
		panel.add(btnPlus, gc);
		
		btnFour = new JButton("4");
		btnFour.setFocusPainted(false);
		btnFour.addActionListener(numberAction);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets = insTop;
		panel.add(btnFour, gc);
		
		btnFive = new JButton("5");
		btnFive.setFocusPainted(false);
		btnFive.addActionListener(numberAction);
		gc.gridx = 1;
		gc.gridy = 3;
		panel.add(btnFive, gc);
		
		btnSix = new JButton("6");
		btnSix.setFocusPainted(false);
		btnSix.addActionListener(numberAction);
		gc.gridx = 2;
		gc.gridy = 3;
		panel.add(btnSix, gc);
		
		btnMinus = new JButton("-");
		btnMinus.setFocusPainted(false);
		btnMinus.addActionListener(commandAction);
		btnMinus.setActionCommand("-");
		gc.gridx = 3;
		gc.gridy = 3;
		gc.insets = insTopRight;
		panel.add(btnMinus, gc);
		
		btnOne = new JButton("1");
		btnOne.setFocusPainted(false);
		btnOne.addActionListener(numberAction);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.insets = insTop;
		panel.add(btnOne, gc);
		
		btnTwo = new JButton("2");
		btnTwo.setFocusPainted(false);
		btnTwo.addActionListener(numberAction);
		gc.gridx = 1;
		gc.gridy = 4;
		panel.add(btnTwo, gc);
		
		btnThree = new JButton("3");
		btnThree.setFocusPainted(false);
		btnThree.addActionListener(numberAction);
		gc.gridx = 2;
		gc.gridy = 4;
		panel.add(btnThree, gc);
		
		btnEquals = new JButton("=");
		btnEquals.setFocusPainted(false);
		btnEquals.addActionListener(equalsAction);
		gc.gridx = 3;
		gc.gridy = 4;
		gc.gridheight = 2;
		gc.insets = insBottomRight;
		panel.add(btnEquals, gc);
		
		btnZero = new JButton("0");
		btnZero.setFocusPainted(false);
		btnZero.addActionListener(numberAction);
		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridheight = 1;
		gc.gridwidth = 2;
		gc.insets = insBottom;
		panel.add(btnZero, gc);
		
		btnPoint = new JButton(".");
		btnPoint.setFocusPainted(false);
		btnPoint.addActionListener(numberAction);
		gc.gridx = 2;
		gc.gridy = 5;
		gc.gridwidth = 1;
		panel.add(btnPoint, gc);
		
		Component[] allButtons = panel.getComponents();
		for (Component x : allButtons) x.setFont(f);
		
		btnSqrt.setPreferredSize(btnLon.getPreferredSize());
		
		InputMap imap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = panel.getActionMap();
		
		KeyStroke zero = KeyStroke.getKeyStroke('0');
		KeyStroke one = KeyStroke.getKeyStroke('1');
		KeyStroke two = KeyStroke.getKeyStroke('2');
		KeyStroke three = KeyStroke.getKeyStroke('3');
		KeyStroke four = KeyStroke.getKeyStroke('4');
		KeyStroke five = KeyStroke.getKeyStroke('5');
		KeyStroke six = KeyStroke.getKeyStroke('6');
		KeyStroke seven = KeyStroke.getKeyStroke('7');
		KeyStroke eight = KeyStroke.getKeyStroke('8');
		KeyStroke nine = KeyStroke.getKeyStroke('9');
		KeyStroke point = KeyStroke.getKeyStroke('.');
		
		KeyStroke plus = KeyStroke.getKeyStroke('+');
		KeyStroke minus = KeyStroke.getKeyStroke('-');
		KeyStroke multi = KeyStroke.getKeyStroke('*');
		KeyStroke divide = KeyStroke.getKeyStroke('/');
		
		KeyStroke lon = KeyStroke.getKeyStroke('n');
		KeyStroke log = KeyStroke.getKeyStroke('g');
		KeyStroke power = KeyStroke.getKeyStroke('^');
		KeyStroke sqrt = KeyStroke.getKeyStroke('q');
		
		KeyStroke clear = KeyStroke.getKeyStroke('c');
		KeyStroke equals = KeyStroke.getKeyStroke('=');
		KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		
		imap.put(zero, "zero");
		imap.put(one, "one");
		imap.put(two, "two");
		imap.put(three, "three");
		imap.put(four, "four");
		imap.put(five, "five");
		imap.put(six, "six");
		imap.put(seven, "seven");
		imap.put(eight, "eight");
		imap.put(nine, "nine");
		imap.put(point, "point");
		
		imap.put(plus, "plus");
		imap.put(minus, "minus");
		imap.put(multi, "multi");
		imap.put(divide, "divide");
		imap.put(lon, "lon");
		imap.put(log, "log");
		imap.put(power, "power");
		imap.put(sqrt, "sqrt");
		
		imap.put(clear, "clear");
		imap.put(equals, "equals");
		imap.put(enterKey, "equals");
		
		amap.put("zero", numberAction);
		amap.put("one", numberAction);
		amap.put("two", numberAction);
		amap.put("three", numberAction);
		amap.put("four", numberAction);
		amap.put("five", numberAction);
		amap.put("six", numberAction);
		amap.put("seven", numberAction);
		amap.put("eight", numberAction);
		amap.put("nine", numberAction);
		amap.put("point", numberAction);
		
		amap.put("plus", commandAction);
		amap.put("minus", commandAction);
		amap.put("multi", commandAction);
		amap.put("divide", commandAction);
		amap.put("lon", commandAction);
		amap.put("log", commandAction);
		amap.put("power", commandAction);
		amap.put("sqrt", commandAction);
		
		amap.put("clear", clearAllAction);
		amap.put("equals", equalsAction);
		
		return panel;
	}
	
	public void updateDisplay(double val) {
		String text = String.valueOf(val);
		lblDisplay.setText(text);
	}
	public void updateDisplay(String text) {
		lblDisplay.setText(text);
	}
	
	public void updateOp(String text) {
		lblOp.setText(text);
	}
	public void addTextToOp(String text) {
		String str = lblOp.getText();
		str = str + text;
		lblOp.setText(str);
	}
}
