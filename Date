import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Date holds an arraylist of events
 * @author sydneysnyder
 *
 */

public class Date 
{
	private ArrayList<Event> events;
	private String date;
	private boolean yes = false;
	public Date(String day)
	{
		date = day;
		events = new ArrayList<Event>();
	}
	/**
	 * Adds event to the date
	 * @param event - event to be added to the date
	 */
	public void addEvent(Event event)
	{
		if(checkEvent(event))
			events.add(event);
		sortEvents(events);
	}
	/**
	 * The number of events on this day
	 * @return amount of events in events ArrayList
	 */
	public int getNumberOfEvents()
	{
		return events.size();
	}
	/**
	 * Prints all events or a message representing no events on this date
	 */
	public String getEvents()
	{
		if(events.size() == 0)
		{
			return "No events today.";
		}
		else if(events.size() == 1)
		{
			Event e = events.get(0);
			return e.getEventName() + " at " + e.getTimes();
		}
		else
		{
			String es = new String();
			for(int i = 0; i < events.size(); i++)
			{
				Event e = events.get(i);
				es = es + "\n" + e.getEventName() + " at " + e.getTimes();
			}
			return es;
		}
	}
	/**
	 * Gives access to events in the events ArrayList
	 * @param i - the location of the event within the ArrayList
	 * @return the event located at i in events ArrayList
	 */
	public Event getEvent(int i)
	{
		if(i > events.size() || events.size() == 0) return null;
		else return events.get(i);
	} 
	/**
	 * Returns boolean value representing if there are events on this Date
	 * @return true or false depending on if there are vents
	 */
	public boolean hasEvents()
	{
		if(events.size() != 0)
			return true;
		return false;
	}
	/**
	 * Sorts events in ArrayList e
	 * @param e - an ArrayList of events
	 */
	public void sortEvents(ArrayList<Event> e)
	{
		if(events.size() > 0)
		{
		Collections.sort(e, new Comparator<Event>() { public int compare(Event e1, Event e2){
			if(e1.getStartTime() > e2.getStartTime())
					return 1;
			else if((e2.getStartTime()) > (e1.getStartTime()))
				return -1;
			else
				return 0;
		}
		});}
	}
	public boolean checkEvent(Event e)
	{
		int start = e.getStartTime();
		int end = e.getETime();
		for(int i = 0; i < events.size(); i++)
		{
			if(events.get(i).getStartTime() <= events.get(i).getETime())
			{
				if(start >= events.get(i).getStartTime() && end >= events.get(i).getETime() 
					|| start >= events.get(i).getETime() && start <= events.get(i).getStartTime())
				{
					JOptionPane.showMessageDialog(new JFrame(), "Error: Event overlaps with previous event:" + events.get(i).getEventName()
						+ ". \n Please create events that do not overlap.");
					System.out.println("Events should not overlap.");
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Returns the original string used to make Date 
	 * @return date - the date written in MM/DD/YYYY
	 */
	public String getDateString()
	{
		return date;
	}
	
}
