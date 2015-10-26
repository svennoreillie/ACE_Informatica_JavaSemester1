package testing;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import common.MagicStrings;
import model.Date;
import model.DateBase;
import model.DateFactory;
import model.DateType;
import model.V1.DateInt;

@RunWith(Parameterized.class)
public class DateTest {
	
	//Privates
	Date ctorDateIII;
	Calendar sysDate;
	Date ctorDate;
	MagicStrings ms = new MagicStrings();
	final int[] leapYears = new int [] {2000, 2004, 2008, 2012, 2016, 2020, 2400};
	final int[] notLeapYears = new int [] {1900, 2001, 2002, 2003, 2005, 2006, 2007};
	final int[] longMonths = new int[] { 1, 3, 5, 7, 8, 10, 12 };
	final int[] shortMonths = new int[] { 2, 4, 6, 9, 11 };
	final String euString = new String ("01/01/2000");
	final String usString = new String ("2000/01/01");

	
	//Parameters for the test
	@Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                    new Object[]{DateType.DateInt},
                    new Object[]{DateType.DateGreg}
        );
    }
    
    //Parameter of constructor will get filled with each of the above defined DateTypes
    public DateTest(DateType type) {
    	DateFactory.setType(type);
    }

	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception{
		ctorDateIII = DateFactory.generateDate(1,1,2000);
		sysDate = Calendar.getInstance();
		ctorDate = DateFactory.generateDate();
	}
	
	
	
	//SetDate()
	@Test
	public void testSetDate() throws Exception {
		ctorDate.setDate(1, 1, 2000);
	}
	
	//SetDate() | Day testing
	@Test
	public void testSetDate_zeroAsDay() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDayRangeWrong());
		//act
		ctorDate.setDate(0, 1, 2000);
	}
	
	@Test
	public void testSetDate_negativeAsDay() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDayRangeWrong());
		//act
		ctorDate.setDate(-1, 1, 2000);
	}

	@Test
	public void testSetDate_day1Till28_allMonths() throws Exception {
		for(int i = 1; i < 29; i++){
			for( int j = 1; j < 13; j++) {
				ctorDate.setDate(i, j, 2000);
			}
		}
	}
	
	@Test
	public void testSetDate_day29feb_inLeapYear() throws Exception {
		for (int i = 0; i < leapYears.length; i ++){
			ctorDate.setDate(29, 2, leapYears[i]);
		}
	}
	
	//try catch blok
	@Test
	public void testSetDate_day29feb_notInLeapYear() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDayRangeWrong());
		//act
		for (int i = 0; i < notLeapYears.length; i ++){
			ctorDate.setDate(29, 2, notLeapYears[i]);
		}   
	}
	
	//try catch blok
	@Test
	public void testSetDate_day30And31Feb() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDayRangeWrong());
		//act
		for (int i = 1999; i < 2006; i++){
			for (int j = 30; j < 32; j++){
				ctorDate.setDate(j, 2, i);
			}	
		}   
	}

	@Test
	public void testSetDate_day29And30InAllMonthsExceptFeb() throws Exception {
		for (int i = 1; i < 13;i++){
			if( i!= 2){
				for(int j = 29; j <= 30; j++){
					ctorDate.setDate(j, i, 2000);
				}
			}	
		}
	}

	@Test
	public void testSetDate_day31InAllLongMonths() throws Exception {
		for(int i = 0; i< longMonths.length; i++){
			ctorDate.setDate(31, longMonths[i], 2000);
		}
	}

	@Test
	public void testSetDate_day31InAllShortMonths() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDayRangeWrong());
		//act
		for(int i = 0; i< shortMonths.length; i++){
			ctorDate.setDate(31, shortMonths[i], 2000);
		}
	}
	
	//SetDate() | Month testing
	@Test
	public void testSetDate_zeroAsMonth() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getMonthRangeWrong());
		//act
		ctorDate.setDate(1, 0, 2000);
	}
	
	@Test
	public void testSetDate_negativeAsMonth() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getMonthRangeWrong());
		//act
		ctorDate.setDate(1, -1, 2000);
	}
	
	@Test
	public void testSetDate_month1Till12() throws Exception {
		for(int i = 1; i < 13; i++){
			ctorDate.setDate(1, i, 2000);
		}
	}
	
	@Test
	public void testSetDate_month13() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getMonthRangeWrong());
	    //act
	    ctorDate.setDate(1, 13, 2000);
	}
	
	//SetDate() | Year testing
	@Test
	public void testSetDate_zeroAsYear() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getYearRangeWrong());
		//act
		ctorDate.setDate(1, 1, 0);
	}
	
	@Test
	public void testSetDate_negativeAsYear() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getYearRangeWrong());
		//act
		ctorDate.setDate(1, 1, -1);
	}
	
	@Test
	public void testSetDate_positiveAsYear() throws Exception {
		ctorDate.setDate(1, 1, 2000);
	}
	
	
	//GetFormatAmerican()
	@Test
	public void testGetFormatAmerican() {
		assertEquals(usString,ctorDateIII.getFormatAmerican());
	}

	//GetFormatEuropean()
	@Test
	public void testGetFormatEuropean() {
		assertEquals(euString,ctorDateIII.getFormatEuropean());
	}

	@Test
	public void testSmallerThan() throws Exception {
		DateInt ctorPlusOneDay = new DateInt (2,1,2000);
		ctorDateIII.smallerThan(ctorPlusOneDay);
	}
	
	//must fail ??? how to expect a fail test?
	@Test
	public void testSmallerThan_sameDate() throws Exception {
		ctorDateIII.smallerThan(ctorDateIII);
	}

	@Test
	public void testDifferenceInYearsOneYear() throws Exception {
		DateInt testDate = new DateInt(1,1,2001);
		assertEquals(1,ctorDateIII.differenceInYears(testDate));
		assertEquals(1,testDate.differenceInYears(ctorDateIII));
	}

	@Test
	public void testDifferenceInYearsSameYear() throws Exception {
		assertEquals(0,ctorDateIII.differenceInYears(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInMonthsOneMonth() throws Exception {
		DateInt testDate = new DateInt(1,2,2000);
		assertEquals(1,ctorDateIII.differenceInMonths(testDate));
		assertEquals(1,testDate.differenceInMonths(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInMonthsSameMonth() throws Exception {
		assertEquals(0,ctorDateIII.differenceInMonths(ctorDateIII));
	}

	@Test
	public void testDifferenceInDaysOneDay() throws Exception {
		DateInt testDate = new DateInt(2,1,2000);
		assertEquals(1,ctorDateIII.differenceInDays(testDate));
		assertEquals(1,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysSameDay() throws Exception {
		assertEquals(0,ctorDateIII.differenceInDays(ctorDateIII));
	}

	@Test
	public void testTotalDaysSinceJesus() throws Exception {
		ctorDate = new DateInt(ctorDateIII);
		assertEquals(ctorDate,ctorDateIII);
		assertEquals(ctorDateIII,ctorDate);
	}
	
	@Test
	public void testTotalDaysSinceJesusNumbered() throws Exception {
		Date d1 = new DateInt(5, 2, 1);
		assertEquals(36,d1.totalDaysSinceJesus());
		Date d2 = new DateInt(5, 2, 2);
		assertEquals(401,d2.totalDaysSinceJesus());
		Date d3 = new DateInt(25, 4, 5);
		assertEquals(1576,d3.totalDaysSinceJesus());
		Date d4 = new DateInt(25, 2, 5);
		assertEquals(1517,d4.totalDaysSinceJesus());
		Date d5 = new DateInt(25, 4, 1999);
		assertEquals(729869,d5.totalDaysSinceJesus());
		Date d6 = new DateInt(25, 2, 1999);
		assertEquals(729810,d6.totalDaysSinceJesus());
	}


	@Test
	public void testChangeDateOneDay() throws Exception {
		DateBase date = (DateBase)ctorDateIII.changeDate(1);
		assertEquals(2,date.getDay());
	}
	
	@Test
	public void testChangeDateMinusOneDay() throws Exception {
		DateBase date = (DateBase)ctorDateIII.changeDate(-1);
		assertEquals(31,date.getDay());
		assertEquals(12,date.getMonth());
		assertEquals(1999,date.getYear());
	}
	
	@Test
	public void testChangeDateZeroDay() throws Exception {
		DateBase date = (DateBase)ctorDateIII.changeDate(0);
		assertEquals(1,date.getDay());
	}
	
	@Test
	public void testChangeDateOverLeapYear() throws Exception {
		DateBase date = (DateBase)ctorDateIII.changeDate(61);
		assertEquals(2,date.getDay());
		assertEquals(3,date.getMonth());
	}

	@Test
	public void testChangeDateNotOverLeapYear() throws Exception {
		DateBase date = (DateBase)ctorDateIII.changeDate(426);
		assertEquals(2,date.getDay());
		assertEquals(3,date.getMonth());
		assertEquals(2001,date.getYear());
		DateBase date2 = (DateBase)date.changeDate(365);
		assertEquals(2,date2.getDay());
		assertEquals(3,date2.getMonth());
		assertEquals(2002,date2.getYear());
	}
	
	@Test
	public void testAlterDateOneDay() throws Exception {
		ctorDateIII.alterDate(1);
		assertEquals(2,ctorDateIII.getDay());
	}
	
	@Test
	public void testAlterDateMinusOneDay() throws Exception {
		ctorDateIII.alterDate(-1);
		assertEquals(31,ctorDateIII.getDay());
		assertEquals(12,ctorDateIII.getMonth());
		assertEquals(1999,ctorDateIII.getYear());
	}
	
	@Test
	public void testAlterDateMinusOneDay2() throws Exception {
		DateInt date = new DateInt(31,12,1999);
		date.alterDate(-1);
		assertEquals(30,date.getDay());
		assertEquals(12,date.getMonth());
		assertEquals(1999,date.getYear());
	}
	
	@Test
	public void testAlterDateZeroDay() throws Exception {
		ctorDateIII.alterDate(0);
		assertEquals(1,ctorDateIII.getDay());
	}
	
	@Test
	public void testAlterDateOverLeapYear() throws Exception {
		ctorDateIII.alterDate(61);
		assertEquals(2,ctorDateIII.getDay());
		assertEquals(3,ctorDateIII.getMonth());
	}

	@Test
	public void testToStringNotOverLeapYear() throws Exception {
		ctorDateIII.alterDate(426);
		assertEquals(2,ctorDateIII.getDay());
		assertEquals(3,ctorDateIII.getMonth());
		assertEquals(2001,ctorDateIII.getYear());
		ctorDateIII.alterDate(365);
		assertEquals(2,ctorDateIII.getDay());
		assertEquals(3,ctorDateIII.getMonth());
		assertEquals(2002,ctorDateIII.getYear());
	}

	@Test
	public void testDateInt() throws Exception {
		assertEquals(sysDate.get(Calendar.DATE),ctorDate.getDay());
		assertEquals(sysDate.get(Calendar.MONTH) + 1,ctorDate.getMonth());
		assertEquals(sysDate.get(Calendar.YEAR),ctorDate.getYear());
	}

	@Test
	public void testDateIntIntIntInt() throws Exception {
		assertEquals(1,ctorDateIII.getDay());
		assertEquals(1,ctorDateIII.getMonth());
		assertEquals(2000,ctorDateIII.getYear());
	}

	@Test
	public void testDateIntDate() throws Exception {
		ctorDate = new DateInt(ctorDateIII);
		assertEquals(ctorDate,ctorDateIII);
		assertEquals(ctorDateIII,ctorDate);
	}

	@Test
	public void testDateIntString() throws Exception {
		ctorDate = new DateInt ("01/01/2000");
	}
	
	@Test
	public void testDateIntString_wrongSeperator() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDateSeperatorWrong());
		//act
		ctorDate = new DateInt ("01:01:2000");
	}
	
	@Test
	public void testDateIntString_wrongLength() throws Exception {
		//arrange
	    thrown.expectMessage(ms.getDateLengthWrong());
	    //act
	    ctorDate = new DateInt ("1:01:2000");
	    ctorDate = new DateInt ("01:1:2000");  
	}
	
	@Test
	public void testGetDay() throws Exception {
		assertEquals(1,ctorDateIII.getDay());
	}

	@Test
	public void testGetMonth() throws Exception {
		assertEquals(1,ctorDateIII.getMonth());
	}

	@Test
	public void testGetYear() throws Exception {	
		assertEquals(2000,ctorDateIII.getYear());
	}

	@Test
	public void testEqualsObject() throws Exception {
		DateInt date1 = new DateInt (1,1,2000);
		date1.equals(ctorDate);
		ctorDate.equals(date1);
	}

}
