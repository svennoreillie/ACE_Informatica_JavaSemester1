package testing;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.V1.DateInt;

public class DateIntTest {
 
	DateInt ctorDateIII;
	Calendar sysDate;
	DateInt ctorDate;
	final int[] leapYears = new int [] {2000, 2004, 2008, 2012, 2016, 2020, 2400};
	final int[] notLeapYears = new int [] {1900, 2001, 2002, 2003, 2005, 2006, 2007};
	final int[] longMonths = new int[] { 1, 3, 5, 7, 8, 10, 12 };
	final String euString = new String ("01/01/2000");
	final String usString = new String ("2000/01/01");

	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception{
		ctorDateIII = new DateInt (1,1,2000);
		sysDate = Calendar.getInstance();
		ctorDate = new DateInt ();
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
	    thrown.expectMessage("Day is not in a valid range");
		//act
		ctorDate.setDate(0, 1, 2000);
	}
	
	@Test
	public void testSetDate_negativeAsDay() throws Exception {
		//arrange
	    thrown.expectMessage("Day is not in a valid range");
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
	
	@Test
	public void testSetDate_day29feb_notInLeapYear() throws Exception {
		//arrange
	    thrown.expectMessage("Day is not in a valid range");
		//act
		for (int i = 0; i < notLeapYears.length; i ++){
			ctorDate.setDate(29, 2, notLeapYears[i]);
		}   
	}
	
	@Test
	public void testSetDate_day30And31Feb() throws Exception {
		//arrange
	    thrown.expectMessage("Day is not in a valid range");
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
	    thrown.expectMessage("Day is not in a valid range");
		//act
		for(int i = 0; i< longMonths.length; i++){
			ctorDate.setDate(31, longMonths[i], 2000);
		}
	}
	
	//SetDate() | Month testing
	@Test
	public void testSetDate_zeroAsMonth() throws Exception {
		//arrange
	    thrown.expectMessage("Month is not in a valid range");
		//act
		ctorDate.setDate(1, 0, 2000);
	}
	
	@Test
	public void testSetDate_negativeAsMonth() throws Exception {
		//arrange
	    thrown.expectMessage("Month is not in a valid range");
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
	    thrown.expectMessage("Month is not in a valid range");
	    //act
	    ctorDate.setDate(1, 13, 2000);
	}
	
	//SetDate() | Year testing
	@Test
	public void testSetDate_zeroAsYear() throws Exception {
		//arrange
	    thrown.expectMessage("Year is not in a valid range");
		//act
		ctorDate.setDate(1, 1, 0);
	}
	
	@Test
	public void testSetDate_negativeAsYear() throws Exception {
		//arrange
	    thrown.expectMessage("Year is not in a valid range");
		//act
		ctorDate.setDate(1, 1, -1);
	}
	
	@Test
	public void testSetDate_positiveAsYear() throws Exception {
		ctorDate.setDate(1, 1, 2000);
	}
	
	@Test
	public void testGetFormatAmerican() {
		assertEquals(usString,ctorDateIII.getFormatAmerican());
	}

	@Test
	public void testGetFormatEuropean() {
		assertEquals(euString,ctorDateIII.getFormatEuropean());
	}

	@Test
	public void testSmallerThan() throws Exception {
		DateInt ctorPlusOneDay = new DateInt (2,1,2000);
		ctorDateIII.smallerThan(ctorPlusOneDay);
	}
	
	@Test
	public void testSmallerThan_sameDate() throws Exception {
		assertFalse(ctorDateIII.smallerThan(ctorDateIII));
	}
	

	@Test
	public void testDifferenceInYears() throws Exception {
		DateInt testDate = new DateInt(1,1,1999);
		assertEquals(1, ctorDateIII.differenceInYears(testDate));
		assertEquals(1, testDate.differenceInYears(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInYearsSameDate() throws Exception {
		DateInt testDate = new DateInt(1,1,2000);
		assertEquals(0, ctorDateIII.differenceInYears(testDate));
		assertEquals(0, testDate.differenceInYears(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInYearsTwoYears() throws Exception {
		DateInt testDate = new DateInt(1,1,2002);
		assertEquals(2, ctorDateIII.differenceInYears(testDate));
		assertEquals(2, testDate.differenceInYears(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInMonths() throws Exception {
		DateInt testDate = new DateInt(1,2,2000);
		assertEquals(1, ctorDateIII.differenceInMonths(testDate));
		assertEquals(1, testDate.differenceInMonths(ctorDate));
	}

	@Test
	public void testDifferenceInMonthsSameDate() throws Exception {
		DateInt testDate = new DateInt(1,1,2000);
		assertEquals(0, ctorDateIII.differenceInMonths(testDate));
		assertEquals(0, testDate.differenceInMonths(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInMonthsTwoMonths() throws Exception {
		DateInt testDate = new DateInt(1,3,2000);
		assertEquals(2, ctorDateIII.differenceInMonths(testDate));
		assertEquals(2, testDate.differenceInMonths(ctorDate));
	}
	
	@Test
	public void testDifferenceInDays() throws Exception {
		DateInt testDate = new DateInt(2,1,2000);
		assertEquals(1,ctorDateIII.differenceInDays(testDate));
		assertEquals(1,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysSameDate() throws Exception {
		DateInt testDate = new DateInt(1,1,2000);
		assertEquals(1,ctorDateIII.differenceInDays(testDate));
		assertEquals(1,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysLongMonth() throws Exception {
		DateInt testDate = new DateInt(1,2,2000);
		assertEquals(31,ctorDateIII.differenceInDays(testDate));
		assertEquals(31,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysshortMonth() throws Exception {
		ctorDateIII = new DateInt(1,4,2000);
		DateInt testDate = new DateInt(1,5,2000);
		assertEquals(30,ctorDateIII.differenceInDays(testDate));
		assertEquals(30,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysFebNotLeapYear() throws Exception {
		ctorDateIII = new DateInt(1,2,2001);
		DateInt testDate = new DateInt(1,3,2001);
		assertEquals(28,ctorDateIII.differenceInDays(testDate));
		assertEquals(28,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysFebNotYear() throws Exception {
		ctorDateIII = new DateInt(1,2,2001);
		DateInt testDate = new DateInt(1,3,2000);
		assertEquals(29,ctorDateIII.differenceInDays(testDate));
		assertEquals(29,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysMoreThenOneLeapYear() throws Exception {
		DateInt testDate = new DateInt(1,1,2001);
		assertEquals(366,ctorDateIII.differenceInDays(testDate));
		assertEquals(366,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysMoreThenOneNotLeapYear() throws Exception {
		ctorDateIII = new DateInt(1,1,2001);
		DateInt testDate = new DateInt(1,1,2002);
		assertEquals(365,ctorDateIII.differenceInDays(testDate));
		assertEquals(365,testDate.differenceInDays(ctorDateIII));
	}
	
	@Test
	public void testDifferenceInDaysFromJesusDate() throws Exception {
		ctorDateIII = new DateInt(1,1,1);
		DateInt testDate = new DateInt(2,1,1);
		assertEquals(1,ctorDateIII.differenceInDays(testDate));
		assertEquals(1,testDate.differenceInDays(ctorDateIII));
	}

	@Test
	public void testTotalDaysSinceJesusOneMoreDaY() throws Exception {
		ctorDateIII = new DateInt(2,1,1);
		assertEquals(1,ctorDateIII.totalDaysSinceJesus());
	}

	@Test
	public void testTotalDaysSinceJesusAfterFirstLeapYear() throws Exception {
		ctorDateIII = new DateInt(1,3,1752);
		assertEquals(639601,ctorDateIII.totalDaysSinceJesus());
	}
	
	@Test
	public void testTotalDaysSinceJesusAfterNoLeapYearBefore1752() throws Exception {
		ctorDateIII = new DateInt(1,3,1749);
		assertEquals(638505,ctorDateIII.totalDaysSinceJesus());
	}
	
	@Test
	public void testChangeDatePlusOneDay() throws Exception {
		DateInt testDate = new DateInt(2,1,2000);
		assertEquals(testDate,ctorDateIII.changeDate(1));
	}
	
	@Test
	public void testChangeDatePlusZeroDay() throws Exception {
		DateInt testDate = new DateInt(1,1,2000);
		assertEquals(testDate,ctorDateIII.changeDate(0));
	}
	
	@Test
	public void testChangeDateMinusOneDay() throws Exception {
		DateInt testDate = new DateInt(31,12,1999);
		assertEquals(testDate,ctorDateIII.changeDate(-1));
	}
	
	@Test
	public void testChangeDateOverLeapYear() throws Exception {
		DateInt testDate = new DateInt(1,3,2000);
		assertEquals(testDate,ctorDateIII.changeDate(60));
	}

	@Test
	public void testChangeDateNotOverLeapYear() throws Exception {
		ctorDateIII = new DateInt (1,1,2001);
		DateInt testDate = new DateInt(1,3,2001);
		assertEquals(testDate,ctorDateIII.changeDate(59));
	}
	
	@Test
	public void testAlterDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		assertEquals(euString,ctorDateIII.toString());
	}

	@Test
	public void testDateInt() {
		assertEquals(sysDate.get(Calendar.DATE),ctorDate.getDay());
		assertEquals(sysDate.get(Calendar.MONTH),ctorDate.getMonth());
		assertEquals(sysDate.get(Calendar.YEAR),ctorDate.getYear());
	}

	@Test
	public void testDateIntIntIntInt() throws Exception {
		assertEquals(1,ctorDateIII.getDay());
		assertEquals(1,ctorDateIII.getMonth());
		assertEquals(2000,ctorDateIII.getYear());
	}

	@Test
	public void testDateIntDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateIntString() throws Exception {
		ctorDate = new DateInt ("01/01/2000");
	}
	
	@Test
	public void testDateIntString_wrongSeperator() throws Exception {
		//arrange
	    thrown.expectMessage("Date does not contain the correct separator");
		//act
		ctorDate = new DateInt ("01:01:2000");
	}
	
	@Test
	public void testDateIntString_wrongLength() throws Exception {
		//arrange
	    thrown.expectMessage("Incorrect date length, you must supply date in following format DD/MM/YYYY");
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


	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}


}
