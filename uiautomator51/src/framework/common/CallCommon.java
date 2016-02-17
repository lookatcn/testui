package framework.common;
import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class CallCommon {
	public static void makeCallByDailer(String number) throws UiObjectNotFoundException{
		makeCallByDailer(number,1);
	}
	
	public static void makeCallByDailer(String number,int simNum) throws UiObjectNotFoundException{
		 DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.dialer:id/floating_action_button", "拨号键盘");
		excute(Object_ResourceId,Operation_SetText,"com.android.dialer:id/digits",number);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/dialpad_floating_action_button");
		 if((Boolean) excute(Object_Text,Operation_Exists,"用于外拨电话的帐户"))
		 {
			 makeCallByDualcard(simNum);
		 }
	}
	 
	public static void makeCallByDualcard(int simNum) throws UiObjectNotFoundException {
		 excute(Object_ClassInstance,Operation_ClickWait,"android.widget.ImageView",Integer.toString(--simNum));
	}
	 
	public static void makeCall() throws UiObjectNotFoundException {
		if((Boolean) excute(Object_Text,Operation_Exists,"用于外拨电话的帐户"))
		{
			makeCallByDualcard(1);
		}
	}
	 
	public static void endCall() throws UiObjectNotFoundException {
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.dialer:id/floating_end_call_action_button", "挂断");
	}
	
	public static void addFastDail(String name,int index) throws UiObjectNotFoundException {
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","设置");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.dialer:id/title","常规");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","快速拨号设置");
       excute(Object_ResIdInstance,Operation_ClickWait,"com.android.phone:id/img_back",Integer.toString(index-1));
	    if((Boolean) excute(Object_Text,Operation_Exists,"替换快速拨号"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text1","替换快速拨号");
		}
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.phone:id/contacts","选择联系人");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview",name);
		Assert.assertTrue("Error: FastDail add failed!!!",
				(Boolean) check(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.phone:id/contacts_cell_name",Integer.toString(index-1),name));
	 }
	 
	 public static void deleteFastDail(int index) throws UiObjectNotFoundException {
		 DeviceCommon.enterApp(Devices_Desc_Call);
		 excute(Object_Description,Operation_ClickWait,"更多选项");
		 excute(Object_ResIdText,Operation_ClickWait,"android:id/title","设置");
		 excute(Object_ResIdText,Operation_ClickWait,"com.android.dialer:id/title","常规");
		 excute(Object_ResIdText,Operation_ClickWait,"android:id/title","快速拨号设置");
		 if((Boolean) excute(Object_ResIdInstance,Operation_TextEqualFalse,"com.android.phone:id/contacts_cell_name",Integer.toString(index-1),"添加联系人"))
		 {
			 excute(Object_ResIdInstance,Operation_ClickWait,"com.android.phone:id/img_back",Integer.toString(index-1));
			 excute(Object_ResIdText,Operation_ClickWait,"android:id/text1","删除快速拨号");
			 excute(Object_ResIdText,Operation_ClickWait,"android:id/button1","是");
		 }
		 else
		 {
			 System.out.println("The FastDail has already deleted!!!");
		 }
		 Assert.assertTrue("Error: FastDail delete failed!!!",
				 (Boolean) excute(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.phone:id/contacts_cell_name",Integer.toString(index-1),"添加联系人"));
	 }
}
