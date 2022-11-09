// 로그인 유저 정보만 포함하기때문에 암호는 없음
public class LoginManager {
	private String seperator;
	private String logComNum;
	private boolean loginStatus;
	
	private LoginManager() {
		seperator = null;
		logComNum = null;
		loginStatus = false;
	}
	
	private static class LoginManagerHolder {
		private static final LoginManager member = new LoginManager();
	}
	
	public static LoginManager getInstance() {
		return LoginManagerHolder.member;
	}
	
	public String getSeperator() {
		return seperator;
	}
	
	public String getLogComNum() {
		return logComNum;
	}
	
	public boolean getLoginStatus() { 
		return loginStatus;
	}
	
	public boolean login(String seperator, String logComNum) {
		if(LoginManagerHolder.member.seperator == null && LoginManagerHolder.member.logComNum == null) {
			LoginManagerHolder.member.seperator = seperator;
			LoginManagerHolder.member.logComNum = logComNum;
			LoginManagerHolder.member.loginStatus = true;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean logout() {
		if(seperator != null && logComNum != null) {
			seperator = null;
			logComNum = null;
			loginStatus = false;
			return true;
		} else {
			return false;
		}
	}
}
