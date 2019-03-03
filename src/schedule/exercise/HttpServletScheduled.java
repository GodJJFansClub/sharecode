package schedule.exercise;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class HttpServletScheduled extends HttpServlet {
	

	private TimerTask task;
	private String scheduleTest = "";
	private Timer timer;
	
	
	@Override
	public void init() {
		
//		timer = new Timer();
		//	 ���Ƶ{�����檺���ȬҬ��I�������
		timer = new Timer(true);
		
		task = new TestTask();
		//	 ��o�{�b�t�ήɶ�	
		Calendar currentTime = Calendar.getInstance();
//		System.out.println(currentTime.getTime());
		//	 �]�w�ɶ���2018/12/13 0:00:00
		//	 �H�U������, �n�Q��o��Calendar���S�w���ɶ������ϥ�get(), �A�[�WCalendar�����������`��
		// 	�N�i�H���o�S�w���ɶ�, �o�Ǥ�k���N���ݩ�util.Date���Y�Ǥ�k
		
		// currentTime.get(Calendar.YEAR) : ���ocurrentTime�����~
//		System.out.println(currentTime.get(Calendar.YEAR));
//		System.out.println(currentTime.get(Calendar.MONTH));
		
		// currentTime.get(Calendar.Date) : ���ocurrentTime������
		// currentTime.get(Calendar.Calendar.DAY_OF_MONTH) �o�ӵ��G�W�����ӬۦP
//		System.out.println(currentTime.get(Calendar.DATE));
		
		// 	�]�w����Ѫ�00:00:00 
		Calendar cal = new GregorianCalendar( currentTime.get(Calendar.YEAR), 
				currentTime.get(Calendar.MONTH),  currentTime.get(Calendar.DATE), 0, 0, 0);
//		System.out.println(cal.getTime());
		// timer.schedule �N�Y��u�@�[�J�Ƶ{ �]�w�b����ɭԸӰ������, �W�u�Ϊ�
		//	���|�ɰ�, �]�N�O���]�{�b2018/12/13 20:46, �Ӷ}�l�ɶ��]��2018/12/13 00:00:00
		//	����{����, �ä��|��0~20:00:00���u�@�ɰ�
		// timer.schedule( TimeTask, util.Date, long period)
		// TimeTask �n���檺�u�@, ��@Runnable����, �@�ذ����
		// util.Date �}�l
		//timer.schedule(task, cal.getTime(), 1*60*60*1000);
		
		// �|�ɰ�
		timer.scheduleAtFixedRate(task, cal.getTime(), 1*60*60*1000); 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=Big5");
		PrintWriter respOut = resp.getWriter();
		
		respOut.println(scheduleTest);
		
	}
	
	@Override
	public void destroy(){
		// task.cancel()�i�H�����o�ӥ���
		task.cancel();
		// �����Ƶ{��, �Ҧ��Ƶ{���������ȥ�����
		// �p�G�S�����|�ɭP�L�k���`����Server
		// �ΤF�귽�N�n����
		timer.cancel();

		
	}
	
	public class TestTask extends TimerTask{
		int count = 0;
		@Override
		public void run() {
			
			StringBuilder scheduleLog = new StringBuilder();
			
			scheduleLog.append("This is Task" + count + "<br>").append("�u�@�Ʃw���ɶ�" +
			new Date(scheduledExecutionTime()) + "<br>").append("�u�@���檺�ɶ�" + new Date() + "<br>");
			
			scheduleTest += scheduleLog.toString(); // scheduleTest = scheduleTest + scheduleLog.toString();
			
			
			count++;
		}
		
	}
}
