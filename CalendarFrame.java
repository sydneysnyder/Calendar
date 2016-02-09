import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

enum MONTHS
{
	January, February, March, April, May, June, July, August, September, October, November, December;
}
enum DAYS
{
	Sun, Mon, Tue, Wed, Thur, Fri, Sat ;
}
public class CalendarFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static GregorianCalendar cal;
	private ArrayList<JButton> days;
	private JButton today;
	private JButton prev;
	private JButton next;
	private JLabel month;
	private JLabel year;
	private JPanel title = new JPanel();
	private JPanel dates = new JPanel(new FlowLayout());
	private JPanel everything = new JPanel();
	private JFrame frame = new JFrame();
	private Date d;
	private final MONTHS[] arrayOfMonths = MONTHS.values();
	private Controller c;
	private boolean set;
	private DayViewPanel dv;
	public CalendarFrame()
	{
		
		/**
		 * Initialize components
		 */
		days = new ArrayList<JButton>();
		cal = new GregorianCalendar();
	    c = new Controller(cal);
	    GregorianCalendar temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
	    int firstDay = temp.get(Calendar.DAY_OF_WEEK) - 1;
	    int j = 0;
	    month = new JLabel(arrayOfMonths[temp.get(Calendar.MONTH)].toString());
	    year = new JLabel(Integer.toString(temp.get(Calendar.YEAR)));
	    /**
	     * Set up JLabel for month and add to JPanel title
	     */
	    JButton quit = new JButton("Quit");
	    quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				c.quit();
				dispose();
			}
	    	
	    });
	    prev = new JButton("<");
	    next = new JButton(">");
	    prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				c.changeMonth(-1);
				cal = c.getCal();
				d = c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
				dv.changeDate(d);
				dv.setText(c.getEvents(d));
				update();
			}
	    	
	    });
	    next.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) 
			{
	    		c.changeMonth(1);
				cal = c.getCal();
				Date d =c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
				dv.changeDate(d);
				dv.setText(c.getEvents(d));
				update();
			}
	    });
	    
	    /**
	     * Create buttons for each date and add to JPanl dates
	     */
	    dates.setLayout(new GridLayout(5, 7));
	    for(int i = 1; i < daysInMonth; i++)
	    {
	    	int numOfBlanks = firstDay + 1;
	    	while(j < numOfBlanks)
	    	{
	    		dates.add(new JLabel(""));
	    		j++;
	    	}
	    	if(i == cal.get(Calendar.DAY_OF_MONTH))
	    	{
	    		today = new JButton(Integer.toString(i));
	    		today.setForeground(Color.MAGENTA);
	    		int k = i;
	    		today.addActionListener(new ActionListener(){
	    			public void actionPerformed(ActionEvent e) 
					{
						d = c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + k + "/" + cal.get(Calendar.YEAR));
						dv.changeDate(d);
						dv.setText(c.getEvents(d));
						if(set != true)
    						set = true;
    					else
    						checkforSet();
    					today.setForeground(Color.CYAN);
					}
		    	});
	    		dates.add(today);
	    		days.add(today);
	    	}	
	    	else
	    	{
	    		JButton q = new JButton(Integer.toString(i));
	    		q.setPreferredSize(new Dimension(40, 40));
	    		dates.add(q);
	    		days.add(q);
	    		int k = i;
	    		q.addActionListener(new ActionListener(){
	    			public void actionPerformed(ActionEvent e) 
	    				{
	    					if(set != true)
	    						set = true;
	    					else
	    						checkforSet();
	    					q.setForeground(Color.CYAN);
	    					d = c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + k + "/" + cal.get(Calendar.YEAR));
	    					dv.changeDate(d);
	    					dv.setText(c.getEvents(d));
	    					dv.revalidate();
	    				}
	    		});
	    	}
	    }
	    

	    /**
	     * Add all to main panel then to JFrame
	     */
		title.add(prev);
	    title.add(month);
	    title.add(year);
	    title.add(next);
	    String today = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
	    dv = new DayViewPanel(c.findDate(today), cal, c);
	    dv.setText(c.findDate(today).getEvents());
	    add(dv, BorderLayout.EAST);
	    add(dates, BorderLayout.CENTER);
	    add(quit, BorderLayout.SOUTH);
		add(title, BorderLayout.NORTH);
		add(everything, BorderLayout.WEST);
		pack();
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void checkforSet()
	{
		for(int i = 0; i < days.size(); i++)
		{
			if(days.get(i).getForeground().equals(Color.CYAN))
			{
				if(days.get(i).equals(today))
				{
					days.get(i).setForeground(Color.MAGENTA);
				}
				else
					days.get(i).setForeground(Color.BLACK);
				
			}
		}
	}
	
	private void update()
	{
		GregorianCalendar temp = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
	    int firstDay = temp.get(Calendar.DAY_OF_WEEK) - 1;
	    int j = 0;
	    days = new ArrayList<JButton>();
		dates.removeAll();
		dates.revalidate();
		title.removeAll();
	    month = new JLabel(arrayOfMonths[temp.get(Calendar.MONTH)].toString());
	    year = new JLabel(Integer.toString(temp.get(Calendar.YEAR)));
	    title.add(prev);
	    title.add(month);
	    title.add(year);
	    title.add(next);
		/**
	     * Create buttons for each date
	     */
	    dates.setLayout(new GridLayout(5, 7));
	    for(int i = 1; i < daysInMonth; i++)
	    {
	    	int numOfBlanks = firstDay + 1;
	    	while(j < numOfBlanks)
	    	{
	    		dates.add(new JLabel(""));
	    		j++;
	    	}
	    	if(i == cal.get(Calendar.DAY_OF_MONTH))
	    	{
	    		today = new JButton(Integer.toString(i));
	    		today.setForeground(Color.MAGENTA);
	    		int k = i;
	    		today.addActionListener(new ActionListener(){
	    			public void actionPerformed(ActionEvent e) 
					{
						d = c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + k + "/" + cal.get(Calendar.YEAR));
						dv.changeDate(d);
						dv.setText(c.getEvents(d));
						if(set != true)
    						set = true;
    					else
    						checkforSet();
    					today.setForeground(Color.CYAN);
						dv.revalidate();
					}
		    	});
	    		days.add(today);
	    		dates.add(today);
	    	}	
	    	else
	    	{
	    		JButton q = new JButton(Integer.toString(i));
	    		q.setPreferredSize(new Dimension(40, 40));
	    		dates.add(q);
	    		days.add(q);
	    		dates.revalidate();
	    		dates.repaint();
	    		int k = i;
	    		q.addActionListener(new ActionListener(){
	    			public void actionPerformed(ActionEvent e) 
	    				{
	    					d = c.findDate(cal.get(Calendar.MONTH) + 1 + "/" + k + "/" + cal.get(Calendar.YEAR));
	    					dv.changeDate(d);
	    					dv.setText(c.getEvents(d));
	    					dv.revalidate();
	    					if(set != true)
	    						set = true;
	    					else
	    						checkforSet();
	    					q.setForeground(Color.CYAN);
	    				}
	    		});
	    	}
	    }
	    revalidate();
	}
}

