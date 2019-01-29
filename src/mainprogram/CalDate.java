package mainprogram;
/**
* @author Cianc
* @version 创建时间：2018年10月7日 上午8:51:23
* @ClassName CalDate
* @Description 该类目的为找出特定年份的大年初一对应阳历的日期
*/
class CalDate {
	// 从1900-2050年的阴历对应表
	private long[] INFO = new long[]  
		    {
		    		 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,  
				     0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,  
				     0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,  
				     0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,  
				     0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,  
				     0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,  
				     0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,  
				     0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,  
				     0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,  
				     0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,  
				     0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,  
				     0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,  
				     0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,  
				     0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,  
				     0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0
		     };  
	// 0000 0101 0010 1011 0000
	// 天干地支纪年法中的天干
	private String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
	
	// 天干地支纪年法中的地支
	private String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
	
	// 生肖序列
	private String[] ZODIAC = {"鼠", "牛", "虎" ,"兔" ,"龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
	
	// 农历每天的名字法则
	private String[] DATE = {
			"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
			"十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
			"甘一", "甘二", "甘三", "甘四", "甘五", "甘六", "甘七", "甘八", "甘九", "三十",
			};
	
	// 月份的数组
	private String[] MONTH = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "腊月"};
	
    // 闰年每月的天数
	static int[] leafYear = {31,29,31,30,31,30,31,31,30,31,30,31};
	
	// 非闰年每月的天数
	static int[] unLeafYear = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	// 该年份天干地支纪年法中的名称
	public String yearName = "";
	
	// 该天中的名称
	public String dateName = "";
	
	// 该天在农历的月份
	public String dataMouth = "";
	
	// 该年的生肖属性
	public String zodiac = "";
	
	// 天干下标
	int heavenlyIndex = 6;
	
	// 地支下标
	int earthlyIndex = 0;
	
	/**
	 * 计算年份对应的大年初一的日期
	 * 调用引用方法
	 * @param year
	 * @param mouth
	 */
	public CalDate(int year, int mouth, int day) {
		calInfoDate(year - 1900, mouth, day);
	}
	
	/**
	 * 调用方法计算天干地支以及生肖
	 * @param year
	 * @param mouth
	 */
	private void calInfoDate(int year, int mouth, int day) {
		int num = day;
		int i = 0;
		int yearIndex = 0;
		if (isYear(year + 1900)) {
			for (int index = 0; index < mouth - 1; index++) 
				num += leafYear[index];
		}
		else {
			for (int index = 0; index < mouth - 1; index++) 
				num += unLeafYear[index];
		}
		for (int index = 1900; index < (1900 + year); index++) {
			num += calYearNum(index);
		}
		num -= 30;
		while ((num - calDayNum(i)) > 0) {
			num -= calDayNum(i++);
			yearIndex++;
			heavenlyIndex++;
			if (heavenlyIndex > 9) {
				heavenlyIndex -= 10;
			}
			earthlyIndex++;
			if (earthlyIndex > 11) {
				earthlyIndex -= 12;
			}
		}
		yearName = HEAVENLY_STEMS[heavenlyIndex] + EARTHLY_BRANCHES[earthlyIndex];
		zodiac = ZODIAC[earthlyIndex];
		dateName = DATE[calDateNum(yearIndex, num) - 1];
	}
	
	/**
	 * 判断在该年是否为闰年
	 * @param year 传入年份
	 * @return
	 */
	public static boolean isYear(int year) {
		if (year % 100 == 0) {
			if (year % 400 == 0) 
				return true;
		}
		else {
			if (year % 4 == 0) 
				return true;
		}
		return false;
	}
	
	/**
	 * 计算在新历中该年的总天数
	 * @param year 传入年份
	 * @return
	 */
	private int calYearNum(int year) {
		if (year % 100 == 0) {
			if (year % 400 == 0) 
				return 366;
		}
		else {
			if (year % 4 == 0) 
				return 366;
		}
		return 365;
	}
	
	/**
	 * 计算该年份阴历有多少天
	 * @param year 传入年份
	 * @return
	 */
	private int calDayNum(int year) {
		int num = 0;
		if (calLeapMouthIndex(year) != 0) 
			num += calLeapMouthNum(year);
		for (int index = 1; index <= 12; index++) {
			num += calMouthNum(year, index);
		}
		return num;
	}
	
	/**
	 * 计算该月的第一天的农历的日子
	 * @param year 传入农历这是第几年
	 * @param num  传入今天是该年的第几天
	 * @return	返回今天是该月的第几天
	 */
	private int calDateNum(int year, int num) {
		int month = 1;
		int index = 0;
		int leapMouth = 0;
		int temp = 0;
		leapMouth = (int)calLeapMouthIndex(year);
		if (leapMouth == 0) {
			while ((temp = (num - calMouthNum(year,month))) > 0) {
				num = temp;
				month++;
			}
		}
		else {
			boolean usedLeap = false;
			while (true) {
				if (month == leapMouth + 1 && (!usedLeap)) {
					temp = num - calLeapMouthNum(year);
					usedLeap = true;
					if (temp <= 0) { 
						dataMouth = "闰";
						break;
					}
				}
				else {
					temp = num - calMouthNum(year,month);
					if (temp <= 0) { break;}
					month++;
				}
				num = temp;
			}
		}
		dataMouth += MONTH[month - 1];
		return num;
	}
	
	/**
	 * 计算闰月的月份，若是没有则返回0
	 * @return
	 */
	private long calLeapMouthIndex(int year) {
		return (INFO[year] & 0xF);
	}
	
	/**
	 * 计算闰月是否为大月
	 * @return 若闰月为大月则返回1
	 */
	private int calLeapMouthNum(int year) {
		if ((INFO[year] & 0xF0000) == 0) 
			return 29;
		else 
			return 30;
	}
	
	/**
	 * 计算月份的的天数
	 * @param num 传入月份
	 * @return
	 */
	private int calMouthNum(int year, int num) {
		if (((INFO[year]>>((12 - num) + 4)) & 0x1) == 0) 
			return 29;
		else 
			return 30;
	}
}
