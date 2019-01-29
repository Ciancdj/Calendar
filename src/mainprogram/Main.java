package mainprogram;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * @author Cianc
 * @version ����ʱ�䣺2018��10��6�� ����7:56:20
 * @ClassName Frame
 * @Description ��������
 */
class Frame extends JFrame {
	// ������
	private JPanel mainPanel;
	// ����ͼ����
	private JPanel mainView;
	// ��������
	private Calendar date = null;
	// ����������
	JPanel nav = new JPanel();
	// ��������
	JPanel calendar = new JPanel();
	// ��ǰ����
	private Calendar now = null;
	// ���ڽ���
	String holiDay = "";
	// ������Ϣ
	private String[] WEEK = {"����һ", "���ڶ�", "������", "������", "������", "������", "������"};
	public Frame() {
		super("Calendar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(350, 100, 1200, 800);
		Container c = this.getContentPane();
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(245,222,179));
		init();
		c.add(mainPanel);
		this.setVisible(true);
	}
	
	/**
	 * �����ʼ������
	 */
	private void init() {
		// ��ȡ����ʱ��
		now = Calendar.getInstance();
		date = Calendar.getInstance();
		date.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1);
		buttonAdd();
		mainViewInit();
	}
	
	/**
	 * ��ʼ������ѡ��ť
	 */
	private void buttonAdd() {
		JButton aboutButton = new JButton("����");
		aboutButton.setBackground(new Color(245,222,179));
		aboutButton.setBounds(0, 0, 1200, 40);
		aboutButton.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		aboutButton.setHorizontalAlignment(JLabel.LEFT);
		mainPanel.add(aboutButton);
		JButton returnNowButton = new JButton("��ת������");
		returnNowButton.setBounds(460, 58, 150, 35);
		returnNowButton.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		mainPanel.add(returnNowButton);
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				JOptionPane.showMessageDialog(null, "����");
			}
		});
		JTextField[] textField = {
				new JTextField("���"), new JTextField("�·�")
		};
		for (int index = 0; index < textField.length; index++) {
			textField[index].setBounds(140 + index*150, 60, 40, 30);
			textField[index].setEditable(false);
			textField[index].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			textField[index].setBackground(new Color(245,222,179));
			mainPanel.add(textField[index]);
		}
		String[] year = new String[50];
		for (int index = date.get(Calendar.YEAR) - 30, i = 0; index < date.get(Calendar.YEAR) + 20; index++, i++)
			year[i] = Integer.toString(index);
		String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		JComboBox[] list = {
				new JComboBox(year), new JComboBox(month)
		};
		list[0].setSelectedIndex(30);
		list[1].setSelectedIndex(date.get(Calendar.MONTH));
		for (int index = 0; index < list.length; index++) {
			list[index].setBounds(180 + index*150, 60, 100, 30);
			list[index].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			mainPanel.add(list[index]);
		}
		// ���ı�����ݵ�ֵ
		list[0].addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO �Զ����ɵķ������
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String year = (String)e.getItem();
					date.set(Integer.parseInt(year), date.get(Calendar.MONTH) - 1, 1);
					calenderView(date);
				}
			}
		});
		// ���ı����·ݵ�ֵ
		list[1].addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO �Զ����ɵķ������
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String month = (String)e.getItem();
					date.set(date.get(Calendar.YEAR), Integer.parseInt(month) - 1, 1);
					calenderView(date);
				}
			}
		});
		returnNowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				// ��date�ص���ԭ����ʱ��
				date.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1);
				// �������������Ϊ�������
				list[0].setSelectedIndex(30);
				// ���·���������Ϊ�����·�
				list[1].setSelectedIndex(date.get(Calendar.MONTH));
				calenderView(now);
			}
		});
	}
	
	/**
	 * �м����������
	 * ���÷������г�ʼ��
	 */
	private void mainViewInit() {
		mainView = new JPanel();
		mainView.setBackground(Color.white);
		mainView.setBounds(0, 100, 1200, 600);
		mainView.setLayout(null);
		calenderView(now);
		mainPanel.add(mainView);
	}
	
	/**
	 *  ����ϵͳ�Ľ��ռ���
	 * @param month ���뵱���·�
	 * @param day ���뵱������
	 * @return
	 */
	private String holiday(int month, int day) {
		switch(month) {
		case 1:
			if(day == 1) { return "Ԫ����";}
			else return "";
		case 3:
			if(day == 1) { return "ֲ����";}
			else if(day == 8) {return "��Ů��";}
			else return "";
		case 5:
			if(day == 1) { return "�Ͷ���";}
			else if(day == 4) {return "�����";}
			else return "";
		case 6:
			if(day == 1) { return "��ͯ��";}
			else return "";
		case 7:
			if(day == 1) { return "������";}
			else return "";
		case 8:
			if(day == 1) { return "������";}
			else return "";
		case 10:
			if(day == 1) { return "�����";}
			else return "";
		case 11:
			if(day == 1) { return "��ʥ��";}
			else return "";
		case 12:
			if(day == 25) { return "ʥ����";}
			else return "";
		default:
			return "";
		}
		
	}
	
	/**
	 * ����UI��
	 * @param cale ����Calendar����
	 */
	private void calenderView(Calendar cale) {
		CalDate cal = new CalDate(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH) + 1, cale.get(Calendar.DAY_OF_MONTH));
		NavView(cale);
		calendar.removeAll();
		int calendarH = 600;
		int calendarL = 850;
		calendar.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		calendar.setBounds(0,0,calendarL,calendarH);
		calendar.setLayout(null);
		calendar.setBackground(Color.white);
		for (int index = 0; index < WEEK.length; index++) {
			JLabel weekLabel = new JLabel(WEEK[index]);
			weekLabel.setBounds((calendarL/WEEK.length)*index, 0, (calendarL/WEEK.length), 50);
			weekLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			weekLabel.setHorizontalAlignment(JLabel.CENTER);
			calendar.add(weekLabel);
		}
		// ��¼������ж�����
		int num ;
		// ������������
		int numRow;
		// �ڼ���
		int row = 0;
		// �ڼ���
		int col = 0;
		if (CalDate.isYear(date.get(Calendar.YEAR))) { num = CalDate.leafYear[date.get(Calendar.MONTH)];}
		else { num = CalDate.unLeafYear[date.get(Calendar.MONTH)];}
		numRow = num / 7;
		calendarH -= 50;
		if (num % 7 != 0) { numRow++;}
		for (int index = 0; index < num; index++) {
			if (index == 0) {
				for (int i = 0; i < dayOfWeek(date) - 1; i++) {
					JLabel label = new JLabel();
					label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					label.setBounds((calendarL/WEEK.length)*i, 50, (calendarL/WEEK.length), (calendarH/numRow));
					calendar.add(label);
				}
				col = dayOfWeek(date) - 1;
			}
			holiDay = holiday(date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));
			cal = new CalDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));
			String html = "<html><h2>"+date.get(Calendar.DAY_OF_MONTH) + " " + holiDay +"</h2><h4>"+cal.dateName+"</h4></html>";
			JLabel label = new JLabel(html);
			label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			label.setBounds((calendarL/WEEK.length)*(col), 50 + (calendarH/numRow) * row, (calendarL/WEEK.length), (calendarH/numRow));
			Calendar c = Calendar.getInstance();
			c.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					NavView(c);
				}
			});
			calendar.add(label);
			col++;
			if (col > 6) {
				col = 0;
				row++;
			}
			date.add(Calendar.DAY_OF_MONTH, 1);
		}
		mainView.add(calendar);
		calendar.updateUI();
	}
	
	private void NavView(Calendar calendar) {
		nav.removeAll();
		CalDate cal = new CalDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		holiDay = holiday(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		nav.setBounds(850,0,350,600);
		nav.setBackground(Color.WHITE);
		nav.setLayout(null);
		JTextField[] textField = {
			new JTextField("��Ԫ" + calendar.get(Calendar.YEAR) + "��" + (calendar.get(Calendar.MONTH) + 1) + "��" + calendar.get(Calendar.DAY_OF_MONTH) + " " + WEEK[dayOfWeek(calendar) - 1]),
			new JTextField(""+calendar.get(Calendar.DAY_OF_MONTH)), new JTextField(cal.dataMouth + cal.dateName),
			new JTextField(cal.yearName + "�� �� " + cal.zodiac + " �꡿"),new JTextField(holiDay)
		};
		textField[0].setBounds(0, 10, 350, 20);
		textField[0].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		textField[1].setBounds(0, 50, 350, 200);
		textField[1].setFont(new Font("����", Font.BOLD, 200));
		textField[1].setForeground(Color.GRAY);
		textField[2].setBounds(0, 260, 350, 20);
		textField[2].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		textField[2].setForeground(Color.orange);
		textField[3].setBounds(0, 290, 350, 20);
		textField[3].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		textField[3].setForeground(Color.orange);
		textField[4].setBounds(0, 320, 350, 20);
		textField[4].setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		textField[4].setForeground(Color.orange);
		for (int index = 0; index < textField.length; index++) {
			textField[index].setBorder(null);
			textField[index].setHorizontalAlignment(JTextField.CENTER);
			textField[index].setEditable(false);
			textField[index].setBackground(Color.WHITE);
			nav.add(textField[index]);
		}
		nav.updateUI();
		mainView.add(nav);
	} 
	
	/**
	 * ������ʽ������������
	 * @return date�����Ӧ��������
	 */
	private int dayOfWeek(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) { return 7;}
		else { return calendar.get(Calendar.DAY_OF_WEEK) - 1;}
	}
}

/**
* @author Cianc
* @version ����ʱ�䣺2018��10��6�� ����7:56:20
* @ClassName Main
* @Description ����
*/
public class Main {
	public static void main(String[] args) {
		new Frame();
	}
}
