package postPackagePOJOs;

public class CreateUsers {
	
	private String name;
	private String job;
	private String place=null;

	public CreateUsers(String name, String job) {
		this.name = name;
		this.job = job;
	}
	public CreateUsers() {
	}
	public CreateUsers(String name, String job, String place) {
		this.name = name;
		this.job = job;
		this.place=place;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "CreateUsers [name=" + name + ", job=" + job + "]";
	}
	
	
	

}
