package TestCases;
import business_actions.MyblockActions;
import utility.Config;
public class ForgotFlows_PISearch_CardUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			

			// Browser navigation
			mblock.NavigateToURL();

			// Create new account
			mblock.actff.PISrchECU();
			
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
