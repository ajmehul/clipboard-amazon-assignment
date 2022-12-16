package Utils;

public class detectPlatform {
	public String detectOS() {
		System.out.println("Detecting platform...");
		String os = System.getProperty("os.name");
		System.out.println(os);
		return os;
	}
	
	public String initialiseDriver(String os) {
		if(os.contains("Windows")) {
			return "chromedriver_win.exe";
		} else if(os.contains("linux")) {
			return "chromedriver_linux";
		} else if(os.contains("linux")) {
			return "chromedriver_mac";
		}
		return "Null";
	}
}
