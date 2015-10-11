package testing;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import model.V1.DateInt;
import model.V2.DateGreg;

public class DateIntTestGeert extends DateInt {
	DateInt ctorDateIII;
	Calendar sysDate;
	DateInt ctorDate;
	String europeString, usString;
	@Test
	public void test() {
		fail("Not yet implemented");
	}
//junit.pdf pagina 13
	public class DateTest {
		@Test
		public void test_addDays_withinSameMonth_1() {
		Date actual = new Date(2050, 2, 15);
		actual.addDays(4);
		Date expected = new Date(2050, 2, 19);
		assertEquals("date after +4 days", expected, actual);
		}
		// give test case methods really long descriptive names
		!
		@Test
		public void test_addDays_wrapToNextMonth_2() {
		Date actual = new Date(2050, 2, 15);
		actual.addDays(14);
		Date expected = new Date(2050, 3, 1);
		assertEquals("date after +14 days", expected, actual);
		}
		// give descriptive names to expected/actual values
		}
}
