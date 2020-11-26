package postPackagePOJOs;

public class HeadersHostsPojo {
	private String contentType;
	private String tokenKey;
	public String host="https://reqres.in";
	public String createUserPath="/api/users";
	
	public HeadersHostsPojo(String contentType, String tokenKey) {
		this.contentType = contentType;
		this.tokenKey = tokenKey;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	

}
