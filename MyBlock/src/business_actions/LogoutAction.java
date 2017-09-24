package business_actions;

import pageObject.ObjDashboard;
import pageObject.ObjLogin;

public class LogoutAction {

	
MyblockActions mblock;
	

	public LogoutAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}
	
	public void LgoutFrmPrfle() throws Exception{
		try {
			mblock.ElementExists(ObjDashboard.lnk_profile);
			mblock.Element(ObjDashboard.lnk_profile).ufxFocus();
			mblock.Element(ObjDashboard.lnk_signout).ufxFocus();
			mblock.Element(ObjDashboard.lnk_signout).ufxClick();
			boolean login = mblock.ElementExists(ObjLogin.txt_username);
			if(login){
				mblock.ValidateTest(true, true,"successfully Logout");
			}
			
			else{
				mblock.ValidateTest(false, true,"Logout is not success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
