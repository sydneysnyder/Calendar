import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Communicates between the views and the models
 * @author sydneysnyder
 *
 */

public class Controller 
{
	private GregorianCalendar cal;
	private Model m;
	/**
	 * Constructs a controller
	 * @param c - calendar
	 */
	public Controller(GregorianCalendar c)
	{
		m = new Model(this);
		cal = c;
	}
	/**
	 * Changes the month
	 * @param amount - amount to change month by
	 */
	public void changeMonth(int amount)
	{
		if(Calendar.MONTH + amount < Calendar.DECEMBER)
			cal.add(Calendar.MONTH,  amount);
		else
		{
			cal.add(Calendar.YEAR, 1);
			cal.add(Calendar.MONTH + amount, -Calendar.DECEMBER);
		}
	}
	/**
	 * Gets events from the model for a specific date
	 * @param d - date
	 * @return list of events for date
	 */
	public String getEvents(Date d)
	{
		return m.getEvents(d);
	}
	/**
	 * Returns the calendar
	 * @return cal - calendar
	 */
	public GregorianCalendar getCal()
	{
		return cal;
	}
	/**
	 * Add event to the model
	 * @param e - event to add
	 */
	public void addEvent(Event e)
	{
		m.addEvent(e);
	}
	/**
	 * Verifies whether date exists with model if not date is created
	 * @param date - date to verify
	 * @return the date
	 */
	public Date findDate(String date)
	{
		Date d = m.findDate(date);
		return d;
	}
	/**
	 * Saves events into text file
	 */
	public void quit()
	{
		m.quit();
	}
}
