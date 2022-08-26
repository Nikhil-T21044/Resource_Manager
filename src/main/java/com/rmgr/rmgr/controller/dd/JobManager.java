
public class JobManager implements Runnable {

	JobManager()
	{

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t=new Thread();
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ephemeralNode en=new ephemeralNode("Description", 10, 100);
		en.startJob();

		while(true)
		{


		}
	}

}
