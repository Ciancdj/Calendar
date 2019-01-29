package mainprogram;
/**
* @author Cianc
* @version 创建时间：2018年10月7日 下午1:28:09
* @ClassName 类名称
* @Description 类描述
*/



public class test {
	public static void main(String[] args) {
	for (int index = 1; index <= 31; index++) {
		CalDate cal = new CalDate(2018, 1, index);
		System.out.println(cal.yearName + " " + cal.zodiac + " " + cal.dataMouth + " " + cal.dateName);
		}
	}
}
