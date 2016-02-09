import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Model class which stores all dates and events for each date
 * @author sydneysnyder
 *
 */

public class Model 
{
	private static ArrayList<Date> d = new ArrayList<Date>();
	private Controller c;
	private File events = new File("events.txt");
	/**
	 * Creates model
	 * @param cont - controller
	 */
	public Model(Controller cont)
	{
		c = cont;
	}
	/**
	 * Adds a date to the model
	 * @param date - date added
	 */
	public void add(Date date)
	{
		d.add(date);
	}
	/**
	 * Returns arraylist of dates
	 * @return dates
	 */
	public ArrayList<Date> getDates()
	{
		return d;
	}
	/**
	 * Adds event to a date
	 * @param e - event to add
	 */
	public void addEvent(Event e)
	{
		findDate(e.getDate().getDateString()).addEvent(e);
	}
	/**
	 * Returns events for all dates
	 * @return all events
	 */
	public String getEvents()
	{
		String ev = new String();
		for(int i = 0; i < d.size(); i++)
		{
			ev = d.get(i).getEvents();
		}
		return ev;
	}
	/**
	 * Returns events for a date
	 * @param d - date to get events from
	 * @return events
	 */
	public String getEvents(Date d)
	{
		Date da = findDate(d.getDateString());
		String es = da.getEvents();
		return es;
	}
	/**
	 * Finds a specific date, if not found it is created
	 * @param date - date to find
	 * @return date object
	 */
	public Date findDate(String date)
	{
		if(d.size() != 0)
		{
			for(int i = 0; i < d.size(); i++)
			{
				if(d.get(i).getDateString().equals(date))
				{
					sortDates();
					return d.get(i);
				}
			}
		}
		Date da = new Date(date);
		add(da);
		sortDates();
		return da;
	}
	/**
	 * Writes file with all events
	 */
	public void quit()
	{
		try
	      {
			events.createNewFile();
	        FileWriter p = new FileWriter(events);
	        BufferedWriter b = new BufferedWriter(p);
	        int i = 0;
	        while(i < d.size())
	        {
	        	b.write("DATE" + "\n");
	        	if(d.get(i).hasEvents())
	        	{
	        		b.write(d.get(i).getDateString() + "\n");
	        		b.write("EVENTS" + "\n");
	        		int j = 0;
	        		while(j < d.get(i).getNumberOfEvents())
	        		{
	        			b.write(d.get(i).getEvent(j).getEventName() + "\n");
	        			b.write(d.get(i).getEvent(j).getStTime() + "\n");
	        			b.write(d.get(i).getEvent(j).getEndTime() + "\n");
	        			j++;
	        		}
	        	}
	        	i++;
	        }
	        //JOptionPane.showMessageDialog(new JFrame(), "File written successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        System.out.print("File written successfully");
	        b.flush();
	        b.close();
	      }
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(new JFrame(), "Error: File not printed.", "File Error", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error");
		}
	}
	/**
	 * Sorts dates in order
	 */
	public void sortDates()
	{
		Collections.sort(d, new Comparator<Date>() { public int compare(Date d1, Date d2){
			String[] delim1 = new String[2];
			delim1 = d1.getDateString().split("/");
			int month1 = Integer.parseInt(delim1[0]);
			int day1 = Integer.parseInt(delim1[1]);
			int year1 = Integer.parseInt(delim1[2]);
			String[] delim2 = new String[2];
			delim2 = d2.getDateString().split("/");
			int month2 = Integer.parseInt(delim2[0]);
			int day2 = Integer.parseInt(delim2[1]);
			int year2 = Integer.parseInt(delim2[2]);
			if(year1 == year2)
			{
				if(month1 == month2)
				{
					if(day1 == day2)
						return 0;
					else if(day1 > day2)
						return 1;
					else return -1;
				}
				else if(month1 > month2)
				{
					return 1;
				}
				else return -1;
			}
			else if(year1 > year2)
			{
				return 1;
			}
			else return -1;
		}
		});
	}
}
