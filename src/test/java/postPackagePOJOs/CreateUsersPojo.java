package postPackagePOJOs;

import javax.annotation.meta.Exclusive;

import com.google.gson.annotations.Expose;

public class CreateUsersPojo {
	
	private String job="TL1";
	private String place=null;
	
	@Expose(serialize = false)
	private String name="Brij";
	
	private transient String country="India";

}
