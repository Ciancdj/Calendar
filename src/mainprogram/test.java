package mainprogram;
/**
* @author Cianc
* @version ����ʱ�䣺2018��10��7�� ����1:28:09
* @ClassName ������
* @Description ������
*/



public class test {
	public static void main(String[] args) {
	for (int index = 1; index <= 31; index++) {
		CalDate cal = new CalDate(2018, 1, index);
		System.out.println(cal.yearName + " " + cal.zodiac + " " + cal.dataMouth + " " + cal.dateName);
		}
	}
}
