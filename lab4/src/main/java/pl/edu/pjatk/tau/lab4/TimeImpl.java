package pl.edu.pjatk.tau.lab4;

public class TimeImpl implements Time {
	
	private String time;
	
	public TimeImpl(){
		
	}
	
	public TimeImpl(String time){
		this.time = time;
	}

	@Override
	public int getHour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setTime(String time){
			this.time = time;
	}

}
