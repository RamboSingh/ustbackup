package TestCases;

import business_actions.MyblockActions;
import utility.Config;

public class CardProofingFrmPod {

	
	public static void main(String[] args) {
		try {
			
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			
			// Browser navigation
			mblock.NavigateToURL();

			// Create new account
			mblock.actCtracnt.crtacnt();
			
		
			mblock.actEmrldCard.cardPrfActn();
			
			mblock.actLogout.LgoutFrmPrfle();

			mblock.Finalize(true);

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
