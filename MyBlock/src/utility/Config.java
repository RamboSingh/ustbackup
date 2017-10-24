package utility;

public class Config {

	public String[] GetConfig(){
		String[] sConf={"chrome"};
		utility.Constant_Class.browserName=sConf[0];
		return sConf;
					
	}
}
