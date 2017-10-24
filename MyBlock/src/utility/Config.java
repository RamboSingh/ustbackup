package utility;

import business_actions.ExcelAction;

public class Config {

	public String[] GetConfig() throws Exception{
		ExcelAction.setExcelfile1();
		ExcelAction.getbrowserData();
		ExcelAction.getEnvData();
		String[] sConf={ExcelAction.brwsr};
		return sConf;
	}
}
