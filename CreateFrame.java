import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class CreateFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private String title = new String();
	private String sTime = new String();
	private String eTime = new String();
	private Date da;
	private Event ev;
	/**
	 * Creates a forum where user inputs event. Event is added to the date. Window closes.
	 * @param d - the dat
	 * @param con - the Controller
	 * @param cal - the Calendar
	 */
	public CreateFrame(Date d, Controller con, GregorianCalendar cal)
	{
		JTextArea titleTxt = new JTextArea(1, 20);
		JTextArea startTime = new JTextArea(1, 20);
		JTextArea endTime = new JTextArea(1, 20);
		JTextArea dateTxt = new JTextArea(1, 20);
		JLabel start = new JLabel("Start time:");
		JLabel end = new JLabel("End time:");
		JLabel titles = new JLabel("Title:");
		JLabel dat = new JLabel("Date:");
		dateTxt.setText(d.getDateString());
		dateTxt.setEditable(false);
		titleTxt.setEditable(true);
		startTime.setEditable(true);
		endTime.setEditable(true);
		JButton create = new JButton("Finalize");
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				title = titleTxt.getText();
				sTime = startTime.getText();
				eTime = endTime.getText();
				System.out.println(d.getDateString() + " " + title + " " + sTime + " " + eTime);
				da = con.findDate(d.getDateString());
				ev = new Event(cal, da, title, sTime, eTime);
				System.out.println(da.getDateString() + " " + ev.getEventName() + " " + ev.getStTime() + " " + ev.getEndTime());
				con.addEvent(ev);
				System.out.println(con.getEvents(d));
				dispose();
			}
			
		});
		JPanel boxes = new JPanel();
		boxes.setBounds(5, 20, 5, 20);
		boxes.add(dat);
		boxes.add(dateTxt);
		JPanel boxes2 = new JPanel();
		boxes.setBounds(5, 20, 5, 20);
		boxes2.add(start);
		boxes2.add(startTime);
		JPanel boxes3 = new JPanel();
		boxes.setBounds(5, 20, 5, 20);
		boxes3.add(end);
		boxes3.add(endTime);
		JPanel boxes4 = new JPanel();
		boxes.setBounds(5, 20, 5, 20);
		boxes4.add(titles);
		boxes4.add(titleTxt);
		setBounds(30, 30, 30, 30);
		JPanel duo = new JPanel();
		JPanel duo2 = new JPanel();
		duo.add(boxes, BorderLayout.NORTH);
		duo.add(boxes2, BorderLayout.SOUTH);
		duo2.add(boxes3,BorderLayout.NORTH);
		duo2.add(boxes4, BorderLayout.SOUTH);
		add(duo, BorderLayout.NORTH);
		add(duo2, BorderLayout.CENTER);
		add(create, BorderLayout.SOUTH);
		setBackground(Color.DARK_GRAY);
		pack();
	    setVisible(true);
	}
	
}
