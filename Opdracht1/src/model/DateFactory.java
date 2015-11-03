package model;

import model.V1.DateInt;
import model.V2.DateGreg;


public class DateFactory {

	private static DateType _type = DateType.DateInt;
	
	public static void setType(DateType type) {
		_type = type;
	}
	
	public static Date generateDate() throws Exception {
		switch (_type) {
			case DateInt:
				return new DateInt();
			case DateGreg:
				return new DateGreg();
			default:
				return new DateInt();
			}
	}
	
	public static Date generateDate(String date) throws Exception {
		switch (_type) {
		case DateInt:
			return new DateInt(date);
		case DateGreg:
			return new DateGreg(date);
		default:
			return new DateInt(date);
		}
	}
	
	public static Date generateDate(Date date) throws Exception {
		switch (_type) {
		case DateInt:
			return new DateInt(date);
		case DateGreg:
			return new DateGreg(date);
		default:
			return new DateInt(date);
		}
	}
	
	public static Date generateDate(int day, int month, int year) throws Exception {
		switch (_type) { 
		case DateInt:
			return new DateInt(day, month, year);
		case DateGreg:
			return new DateGreg(day, month, year);
		default:
			return new DateInt(day, month, year);
		}
	}
	
	
}
