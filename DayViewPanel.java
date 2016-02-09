import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;
/**
 * Creates a Day View Panel which shows events for the day
 * @author sydneysnyder
 *
 */
public class DayViewPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextArea events;
	private Date date;
	/**
	 * Creates a panel for the day view which shows events for the day and the date
	 * @param d - date
	 * @param cal - calendar
	 * @param con - controller
	 */
	public DayViewPanel(Date d, GregorianCalendar cal, Controller con)
	{
		date = d;
		events = new JTextArea();
		events.setBounds(20, 50, 20 ,50);
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				CreateFrame c = new CreateFrame(date, con, cal);
				revalidate();
			}
			
		});
		add(events);
		add(create, BorderLayout.NORTH);
	}
	/**
	 * Changes the date
	 * @param d - date to change to
	 */
	public void changeDate(Date d)
	{
		date = d;
	}
	/**
	 * Sets the TextArea
	 * @param t - text to use
	 */
	public void setText(String t)
	{ 
		events.setText(date.getDateString());
		events.append("\n" + t);
		events.repaint();
	}
}
