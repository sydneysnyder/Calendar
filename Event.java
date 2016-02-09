import java.util.Calendar;


/**
 * Events to be put into a date on the Calendar
 * @author sydneysnyder
 *
 */
public class Event 
{
	private Date date;
	private String title;
	private String startTime;
	private String endTime;
	/**
	 * Initiates all variables
	 * @param c - calendar
	 * @param day - a Date the event is on
	 * @param name - name of event
	 * @param stime - start time
	 * @param etime - end time
	 */
	public Event(Calendar c, Date day, String name, String stime, String etime)
	{
		date = day;
		title = name;
		startTime = stime;
		endTime = etime;
	}
	/**
	 * Returns date
	 * @return date - the date of event
	 */
	public Date getDate()
	{
		return date;
	}
	/**
	 * Returns event name
	 * @return title - name of event
	 */
	public String getEventName()
	{
		return title;
	}
	/**
	 * Returns numbers in start time as int
	 * @return int startTime
	 */
	public int getStartTime()
	{
		if(startTime.length() == 5)
			return Integer.parseInt(startTime.substring(0,2));
		else
			return Integer.parseInt(startTime.substring(0,1));
	}
	/**
	 * Returns the end time
	 * @return endTime - string
	 */
	public String getEndTime()
	{
		return endTime;
	}
	public int getETime()
	{
		if(endTime.length() == 5)
			return Integer.parseInt(endTime.substring(0,2));
		else
			return Integer.parseInt(endTime.substring(0,1));
	}
	/**
	 * Returns the start time
	 * @return startTime - string
	 */
	public String getStTime()
	{
		return startTime;
	}
	/**
	 * Returns the times together
	 * @return the times linked together in a string
	 */
	public String getTimes()
	{
		if(Integer.parseInt(endTime.substring(0,2)) >= 0 || Integer.parseInt(endTime.substring(3)) >= 0)
		return startTime + "-" + endTime;
		else
		return startTime;
	}
}
