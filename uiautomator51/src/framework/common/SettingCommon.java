package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.widget.TextView;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import framework.data.OperationType;
import framework.driver.OperationUiObject;

public class SettingCommon {
	
	public static void addContextualModel(String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: addContextualModel======");
		
		if((Boolean) excute(Object_Text,Operation_Exists,name))
		{
			deleteContextualModel(name);	
		}
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加");		
		excute(Object_ClassIndex,Operation_SetText,Class_EditText, "0", name);
		excute(Object_Text,Operation_ClickWait,"确定");			
		excute(Object_Text,Operation_ClickWait,name);			
		excute(Object_Text,Operation_ClickWait,"启用");	
	}
	
	public static void deleteContextualModel(String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: deleteContextualModel======");
		
		excute(Object_Text,Operation_ClickWait,name);		
		excute(Object_Text,Operation_ClickWait,"删除");	
		excute(Object_Text,Operation_ClickWait,"确定");	
	}
	
	public static void addAccount(String userName,String password) throws UiObjectNotFoundException{
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
		if(!(Boolean) excute(Object_Text,Operation_Exists,"个人"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","添加帐户");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","个人");
			EmailCommon.addEmail(userName,password);
		}
		DeviceCommon.exitApp();
	}
	
	public static void delAllAccountsBySetting() throws UiObjectNotFoundException{
		delAllAccountsBySetting("个人");
	}
	
	public static void delAllAccountsBySetting(String index) throws UiObjectNotFoundException{
		UiObject emailAccount = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/title","index");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"帐户");
		do{
			deleteAction(index, "@");
			System.out.println("<<<<<<<<<<<<"+emailAccount.exists());
		}while(emailAccount.exists());
		DeviceCommon.exitApp();
	}
	
	public static void delAccountBySetting() throws UiObjectNotFoundException{
		delAccountBySetting("个人", "@");
	}
	
	public static void delAccountBySetting(String userName) throws UiObjectNotFoundException{
		delAccountBySetting("个人", userName);
	}
	
	public static void delAccountBySetting(String index,String userName) throws UiObjectNotFoundException{
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"帐户");
		deleteAction(index, userName);
	}
	
	public static boolean deleteAction(String index, String userName) throws UiObjectNotFoundException{
		UiObject addAccount = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/title", "添加帐户");
		if((Boolean) excute(Object_Text,Operation_Exists,index))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title",index);
		}
		else
		{
			System.out.println("The needed account has already empety!!!");
			return false;
		}
		if(userName.equals("@"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"android:id/title",userName);
		}
		else
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title",userName);
		}
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","移除帐户");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1","移除帐户");
		Wait(2000);
		System.out.println(">>>>>>>>>>>"+addAccount.exists());
		while(!addAccount.exists())
		{
			excute(Object_Device, Operation_PressBack);
		}
		//new CheckAppIsExistByText(index,false);
		return true;
	}
	public static void SIMsetting(String SIMName) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: SIMsettings======");
		
		excute(Object_Text,Operation_ClickWait,SIMName);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");	
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");	
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");
		Wait(500);
	}
	//修改SIM卡名称
	public static void SIMNames(String SIMCard,String SIMName) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: SIMName======");
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name",SIMName);
		excute(Object_Text,Operation_ClickWait,"确定");
		Wait(1000);
		excute(Object_Text,Operation_ClickWait,SIMCard);
		Wait(1000);
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.settings:id/sim_name",SIMName);
		Wait(1000);
		excute(Object_Text,Operation_ClickWait,"确定");
		Wait(1000);
	}
	//开关SIM1 
	 public static void enableCard(String Turno) throws UiObjectNotFoundException
	   {
		 try {
			ClearBackgroundApp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
	    UiObject index0=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(0));
	    UiObject index = index0.getChild(new UiSelector().resourceId("com.android.settings:id/universal_switch"));
	    String Turnon=(String) index.getText();
	    if (Turnon.equals(Turno))
	    	index.clickAndWaitForNewWindow();
	    if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
	    	{
	    	excute(Object_Text,Operation_ClickWait,"确定");
	    	Wait(15000);
	    	}
	    if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
	    excute(Object_Text,Operation_ClickWait,"确定");
	    Wait(20000);
	   }
	//开关SIM2
	public static void enableCard2(String Turno) throws UiObjectNotFoundException
	{
		try {
			ClearBackgroundApp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		UiObject index0=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(1));
		UiObject index = index0.getChild(new UiSelector().resourceId("com.android.settings:id/universal_switch"));
		String Turnon=(String) index.getText();
		if (Turnon.equals(Turno))
		    {
		    	index.clickAndWaitForNewWindow();
		    }
		if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
		   {
		    excute(Object_Text,Operation_ClickWait,"确定");
		   }
		    Wait(15000);
		if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
		   {
		    excute(Object_Text,Operation_ClickWait,"确定");
		   }
		 Wait(20000);
		   }
	  
	public static void EnterVPN() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: EnterVPN======");
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"VPN");
	}
	
	public static void SetPIN() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: SetPIN======");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","PIN码");
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/password_entry","1234");
		Wait(500);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/next_button");
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/password_entry","1234");
		Wait(500);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/next_button");
		Wait(500);
		if((Boolean) excute(Object_Text,Operation_Exists,"完成"))
		{
			excute(Object_Text,Operation_ClickWait,"完成");
		}
		else
			System.out.println("============");
			Wait(500);
	}
	
	public static void SetVPN(String VPNName, String Servicer) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: AddVPN======");
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/name",VPNName);
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/server",Servicer);
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"保存");	
		
	}
	
	public static void SetL2TPVPN(String VPNName, String Servicer, String secret, String identifier, String ipsec) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: SetL2TPVPN======");
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/name",VPNName);
		Wait(500);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/type");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"L2TP/IPSec PSK");
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/server",Servicer);
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/l2tp_secret",secret);
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/ipsec_identifier",identifier);
		Wait(500);
		excute(Object_Device,Operation_PressEnter);
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/ipsec_secret",ipsec);
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"保存");	
		
	}
	
	public static void ConnectVPN(String type,String VPNName, String Password) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: ConnectVPN======");
		excute(Object_Text,Operation_ClickWait,type);
		Wait(500);
		excute(Object_Text,Operation_SetText,"连接",VPNName);
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/password",Password);
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"连接");
		Wait(5000);
	}
	public static void ConnectBrowser(String URL) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: ConnectBroswer======");
		try {
			ClearBackgroundApp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Wait(3000);
		DeviceCommon.enterApp("浏览器");
		UiObject index2=new UiObject(new UiSelector().resourceId("com.android.browser:id/url"));
		Wait(500);
		excute(Object_ResourceId, Operation_SetText,"com.android.browser:id/url",URL);
		Wait(500);	
		UiDevice.getInstance().pressEnter();
		UiObject stop=new UiObject(new UiSelector().resourceId("com.android.browser:id/stop"));
		OperationUiObject.WaitUntilGone(stop,"100000");
		//Wait(20000);
		if((Boolean) excute(Object_Text,Operation_Exists,"继续"))
		{
			excute(Object_Text,Operation_ClickWait,"继续");
		}
		else
			System.out.println("======Enter webpage======");
		Wait(2000);
		UiDevice.getInstance(). pressMenu ();
		Wait(500);
		excute(Object_Text, Operation_ClickWait,"在网页上查找");
		excute(Object_ResourceId, Operation_SetText,"android:id/edit","net::ERR_NAME_NOT_RESOLVED");
		check(Object_ResourceId,Operation_TextEqualTrue,"android:id/matches","0/0");
		
	}
	
	public static void EnterMove() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: EnterMove======");
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"移动网络");
	}
	
	//public static void Selectdate() throws UiObjectNotFoundException
	//{
		//System.out.println("======Start to excute CallContactsCommon: Selectdate======");
		//UiObject index4=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(4));
		//UiObject index2=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(2));
		//if(index4.exists()){
			//index2.clickAndWaitForNewWindow();
			//}else{
				//excute(Object_Text,Operation_ClickWait,"SIM2");
				//Wait(500);
				//index2.clickAndWaitForNewWindow();
	        //}
	//}
	
	public static void Select4date() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: Selectdate======");
		UiObject index2=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(2));
		Wait(500);
		index2.clickAndWaitForNewWindow();
		//Selectdate();
		UiObject index0=new UiObject(new UiSelector().resourceId("android:id/select_dialog_listview")).getChild(new UiSelector().index(0));
		index0.click();
		Wait(10000);
	}
	
	
	public static void Select3date() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: Selectdate======");
		UiObject index2=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(2));
		Wait(500);
		index2.clickAndWaitForNewWindow();
		UiObject index0=new UiObject(new UiSelector().resourceId("android:id/select_dialog_listview")).getChild(new UiSelector().index(1));
		index0.clickAndWaitForNewWindow();
		Wait(10000);
	}
	
	public static void Select2date() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: Selectdate======");
		UiObject index2=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(2));
		Wait(500);
		index2.clickAndWaitForNewWindow();
		UiObject index0=new UiObject(new UiSelector().resourceId("android:id/select_dialog_listview")).getChild(new UiSelector().index(2));
		index0.clickAndWaitForNewWindow();
		Wait(10000);
	}
	//发送短信
		public static void SendMS(String Num) throws UiObjectNotFoundException
		{
			try {
				ClearBackgroundApp();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Wait(1000);
			DeviceCommon.enterApp("信息");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/start_new_conversation_button");
			Wait(500);
			excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/recipient_text_view",Num);
			Wait(500);
			excute(Object_Device,Operation_PressEnter);
			Wait(500);
			if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.messaging:id/compose_message_text"))
			{
				excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/compose_message_text","MS");
				Wait(500);
				excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/self_send_icon");
			}
			else{
				excute(Object_Device,Operation_PressEnter);
				Wait(500);
				excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/compose_message_text","MS");
				Wait(500);
				excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/self_send_icon");
				}
			
		}
		
	//发送彩信
	public static void SendMMS(String Num) throws UiObjectNotFoundException
	{
		try {
			ClearBackgroundApp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeviceCommon.enterApp("信息");
		Wait(500);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/start_new_conversation_button");
		Wait(500);
		excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/recipient_text_view",Num);
		Wait(500);
		excute(Object_Device,Operation_PressEnter);
		Wait(500);
		if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.messaging:id/compose_message_text"))
		{
			excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/compose_message_text","MMS");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/attach_media_button");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/camera_capture_button");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/self_send_icon");
		}
		else{
			excute(Object_Device,Operation_PressEnter);
			Wait(500);
			excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/compose_message_text","MMS");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/attach_media_button");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/camera_capture_button");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/self_send_icon");
			}	
		
		}
		
	public static void EnterMoveData() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: EnterMoveData======");
		try {
			ClearBackgroundApp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeviceCommon.enterApp(Devices_Desc_Setting);
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
	}
	
	public static void GetMoveData() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: GetMoveData======");
		String Data=(String) excute(Object_ResourceId,Operation_GetText,"com.android.settings:id/cycle_summary");
		Wait(500);
		ConnectBrowser("www.baidu.com");
		EnterMoveData();
		Wait(5000);
		check(Object_Text,Operation_checkNoExist,Data);
		
	}
	//开启关闭WLAN流量显示
	public static void WifiData(String Wifid) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: OpenWifiData======");
		excute(Object_Device,Operation_PressMenu);
		Wait(500);
		excute(Object_Text,Operation_ClickWait,Wifid);
		Wait(500);
		
	}
	//开启流量限制
	public static void OpenDaralimit() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: OpenDaralimit======");
		EnterMoveData();
		excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
		Wait(500);
		if((Boolean) excute(Object_Text,Operation_Exists,"确定"))
			excute(Object_Text,Operation_ClickWait,"确定");
		 else
			 {
			 excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"确定");
			}
	}
	//关闭流量限制
	public static void CloseDaralimit() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallContactsCommon: CloseDaralimit======");
		EnterMoveData();
		excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
		Wait(500);
		if((Boolean) excute(Object_Text,Operation_Exists,"确定"))
			{
			excute(Object_Text,Operation_ClickWait,"确定");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
			}
		 else
			 System.out.println("======Daralimit have been Closed======");
	}
	
	
	//滑动流量限制
		public static void DragDaralimit() throws UiObjectNotFoundException
		{
			System.out.println("======Start to excute CallContactsCommon: OpenDaralimit======");
			EnterMoveData();
			excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
			Wait(500);
			if((Boolean) excute(Object_Text,Operation_Exists,"确定"))
				excute(Object_Text,Operation_ClickWait,"确定");
			 else
				 {
				 excute(Object_Text,Operation_ClickWait,"设置移动数据流量上限");
				Wait(500);
				excute(Object_Text,Operation_ClickWait,"确定");
				}
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/sweep_limit");
			excute(Object_ResourceId, Operation_SetText,"android:id/numberpicker_input","10240");
			excute(Object_Text,Operation_ClickWait,"确定");
			Wait(500);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/sweep_warning");
			excute(Object_ResourceId, Operation_SetText,"android:id/numberpicker_input","5120");
			excute(Object_Text,Operation_ClickWait,"确定"); 
		}
		
		//拨号
		public static void endCall() throws UiObjectNotFoundException
		{
			try {
				ClearBackgroundApp();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DeviceCommon.enterApp(Devices_Desc_Call);
			Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
			 int WideArea = ModArea.width();
			 int x = ModArea.centerX();
			 int y = ModArea.centerY();
			 CallCommon.makeCallByDailer(CMCCNum);
			 Wait(10000);
			 UiDevice.getInstance().click(x, y);
		}
		
		public static void SelectendCall(int simNum) throws UiObjectNotFoundException
		{
			try {
				ClearBackgroundApp();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DeviceCommon.enterApp(Devices_Desc_Call);
			Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
			 int WideArea = ModArea.width();
			 int x = ModArea.centerX();
			 int y = ModArea.centerY();
			 CallCommon.makeCallByDailer(CMCCNum,simNum);
			 Wait(10000);
			 UiDevice.getInstance().click(x, y);
		}
		//进入锁屏
		public static void Entersecurity() throws UiObjectNotFoundException
		{
			excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
			if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.settings:id/password_entry"))
			{
				excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/password_entry","1234");
				Wait(500);
				excute(Object_Device,Operation_PressEnter);
			}
			else
			System.out.println("======Entersecurity======");
		}
		//解锁
		public static void unLock() throws UiObjectNotFoundException
		{
			excute(Object_Device,Operation_Sleep);
			Wait(500);
			DeviceCommon.unLock();
			Wait(500);
			if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.systemui:id/key_enter"))
			{
				excute(Object_ResourceId, Operation_SetText,"com.android.systemui:id/pinEntry","1234");
				Wait(500);
				excute(Object_ResourceId,Operation_ClickWait,"com.android.systemui:id/key_enter");
			}
			else
			System.out.println("======unlock======");
		}
		//移除PIN锁
				public static void RemovePIN() throws UiObjectNotFoundException
				{
					try {
						ClearBackgroundApp();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DeviceCommon.enterApp(Devices_Desc_Setting);
					Wait(500);
					excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
					Wait(500);
					excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
					if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.settings:id/password_entry"))
					{
						excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/password_entry","1234");
						Wait(500);
						excute(Object_Device,Operation_PressEnter);
					}
					else
					{
						System.out.println("======Entersecurity======");
						}
					Wait(500);
					excute(Object_Text,Operation_ClickWait,"滑动");
					if((Boolean) excute(Object_Text,Operation_Exists,"是，移除"))
					{
						excute(Object_Text, Operation_ClickWait,"是，移除");
					}
					else
					{
						System.out.println("======RemovePIN Success======");
						}
					
				}
				
//以下为孔皓*******************************************************************************************************					
				public static void open_close_auto_date(String open_close) throws UiObjectNotFoundException 
				{
					UiObject set_button = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/switchWidget","0");
					if(set_button.getText().equals(open_close))
						excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
				}
				
				public static void set_check_date(String year,String day) throws UiObjectNotFoundException
				{
					//设置日期
					excute(Object_Text,Operation_ClickWait,"设置日期");
					excute(Object_ResourceId,Operation_ClickWait,"android:id/date_picker_header_year");
					excute(Object_ResIdText,Operation_ClickWait,"android:id/text1",year);
					excute(Object_ResourceId,Operation_ClickWait,"android:id/next");
					excute(Object_Text,Operation_ClickWait,day);
					UiObject year_old = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/date_picker_header_year");
					UiObject date_old = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/date_picker_header_date");
					excute(Object_Text,Operation_ClickWait,"确定");
					excute(Object_Text,Operation_ClickWait,"设置日期");
					//检查日期设置是否正确
					UiObject year_new = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/date_picker_header_year");
					UiObject date_new = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/date_picker_header_date");
					check(Object_Text,Operation_TextEqualTrue,year_old.getText(),year_new.getText());
					check(Object_Text,Operation_TextEqualTrue,date_old.getText(),date_new.getText());
					excute(Object_Device, Operation_PressBack);
				}
				
				public static void set_check_time(String info,String hour,String minute) throws UiObjectNotFoundException 
				{
					//设置时间
					excute(Object_Text,Operation_ClickWait,"设置时间");
					excute(Object_Text,Operation_ClickWait,info);
					excute(Object_Description,Operation_ClickWait,hour);
					excute(Object_Description,Operation_ClickWait,minute);
					excute(Object_Text,Operation_ClickWait,"确定");
					//检测时间设置是否正确
					excute(Object_Text,Operation_ClickWait,"设置时间");
					UiObject hours = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/hours");
					UiObject minutes = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/minutes");
					if(info.equals("上午"))
						check(Object_ResourceId,Operation_IsChecked,"android:id/am_label");
					if(info.equals("下午"))
						check(Object_ResourceId,Operation_IsChecked,"android:id/pm_label");
					check(Object_Text,Operation_TextEqualTrue,hours.getText(),hour);
					check(Object_Text,Operation_TextEqualTrue,minutes.getText(),minute);
					excute(Object_Device, Operation_PressBack);
				}
				
				public static void open_close_auto_time_zone(String open_close) throws UiObjectNotFoundException 
				{
					UiObject set_button = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/switchWidget","1");
					if(set_button.getText().equals(open_close))
						excute(Object_Text,Operation_ClickWait,"自动确定时区");
				}
				
				public static void set_check_time_zone() throws UiObjectNotFoundException 
				{
					//设置时区
					excute(Object_Text,Operation_ClickWait,"选择时区");
					excute(Object_Text,Operation_ClickWait,"中国标准时间");
					UiObject set_button2 = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/summary","4");
					//检查时区
					check(Object_Text,Operation_TextEqualTrue,set_button2.getText(),"GMT+08:00 中国标准时间");
				}
				
				public static void open_close_24_time(String open_close) throws UiObjectNotFoundException 
				{
					UiObject set_button3 = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/switchWidget","2");
					if(set_button3.getText().equals(open_close))
						excute(Object_Text,Operation_ClickWait,"使用24小时制");
				}
				
				public static void check_am_pm(String m,String hour,String min) throws UiObjectNotFoundException
				{
			 		excute(Object_Text,Operation_ClickWait,"设置时间");
					UiObject hours = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/hours");
					UiObject minutes = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"android:id/minutes");
					if(m.equals("am"))
					{	
						check(Object_ResourceId,Operation_IsChecked,"android:id/am_label");
						check(Object_Text,Operation_TextEqualTrue,hours.getText(),hour);
						check(Object_Text,Operation_TextEqualTrue,minutes.getText(),min);
					}
					if(m.equals("pm"))
					{
						check(Object_ResourceId,Operation_IsChecked,"android:id/pm_label");
						check(Object_Text,Operation_TextEqualTrue,hours.getText(),hour);
						check(Object_Text,Operation_TextEqualTrue,minutes.getText(),min);
					}
					excute(Object_Device, Operation_PressBack);
				}
				
				public static void check_biggest_date() throws UiObjectNotFoundException 
				{
					//检测日期设置是否正确
					UiObject set_button3 = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/summary","2");
					check(Object_Text,Operation_TextEqualTrue,set_button3.getText(),"2038年1月1日");
				}
				
				public static void check_auto() throws UiObjectNotFoundException 
				{
					check(Object_Text,Operation_IsEnabled,"设置日期");
					check(Object_Text,Operation_IsEnabled,"设置时间");
					check(Object_Text,Operation_IsEnabled,"选择时区");
				}
				
				public static void install_apk(String name) throws UiObjectNotFoundException 
				{
					DeviceCommon.enterApp(Devices_Desc_FileManage);
					excute(Object_Text,Operation_ClickWait,"APK安装文件");
					Wait(2000);
					excute(Object_Text,Operation_ClickWait,name);
					 if((Boolean) excute(Object_Text,Operation_Exists,"禁止安装"))
						{
							excute(Object_Text,Operation_ClickWait,"设置");
							excute(Object_TextScroll,Operation_ClickWait,"未知来源","vertical");
							excute(Object_Text,Operation_ClickWait,"确定");
							excute(Object_Device, Operation_PressBack);
							excute(Object_Text,Operation_ClickWait,name);
						}
					Wait(2000);
					excute(Object_Text,Operation_ClickWait,"安装");
//					UiObject ins=new UiObject(new UiSelector().text("正在安装..."));
//					OperationUiObject.waitUntilGone(ins,100000);
					Wait(30000);
				}
				
				public static void uninstall_apk(String name) throws UiObjectNotFoundException 
				{
					excute(Object_TextScroll,Operation_ClickWait,name,"vertical");
					excute(Object_Text,Operation_ClickWait,"卸载");
					excute(Object_Text,Operation_ClickWait,"确定");
					UiObject unins=new UiObject(new UiSelector().text("正在卸载..."));
					OperationUiObject.WaitUntilGone(unins,"30000");
				}
				
				public static void change_user_name(String name) 
				{
					excute(Object_ResIdText,Operation_ClickWait,"android:id/summary","机主");
					excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/user_name",name);
					excute(Object_Device,Operation_PressBack);
				}
				
				public static void change_user_photo() 
				{
					excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/user_photo");
					excute(Object_Text,Operation_ClickWait,"拍照");
//					Wait(5000);
					excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
//					Wait(5000);
					excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/done_button");
					excute(Object_Text,Operation_ClickWait,"保存");
//					Wait(5000);
				}
				
				public static void check_user_name(String name) throws UiObjectNotFoundException 
				{
					UiObject set_button = (UiObject)excute(Object_ResIdInstance,Operate_ReturnObject,"android:id/title","1");
					check(Object_Text,Operation_TextEqualTrue,set_button.getText(),name);
				}
				
				public static void add_user() 
				{
					excute(Object_Text,Operation_ClickWait,"添加用户");
					UiObject add = (UiObject) excute(Object_Text,Operate_ReturnObject,"要添加新用户吗？");
					if (add.exists())
						{
							System.out.println("Appear hint when add new user");
							excute(Object_Text,Operation_ClickWait,"取消");
						}
					else
						System.out.println("No hint when add new user");
					excute(Object_Text,Operation_ClickWait,"添加用户");
					UiObject add2 = (UiObject) excute(Object_Text,Operate_ReturnObject,"要添加新用户吗？");
					if (add2.exists())
						{
							System.out.println("Appear hint when add new user");
							excute(Object_Text,Operation_ClickWait,"确定");
						}
					else
						System.out.println("No hint when add new user");
					excute(Object_Text,Operation_ClickWait,"以后再说");
				}
				
				public static void add_user_with_enter() 
				{
					System.out.println("Add new user:"+excute(Object_Text,Operation_checkExist,"新用户"));
					excute(Object_Text,Operation_ClickWait,"添加用户");
					excute(Object_Text,Operation_ClickWait,"确定");
					excute(Object_Text,Operation_ClickWait,"立即设置");
					Wait(15000);
				}
				
				public static void add_mail_account(String mail,String info,String password,String name) throws UiObjectNotFoundException 
				{
					excute(Object_Text,Operation_ClickWait,"添加帐户");
					excute(Object_Text,Operation_ClickWait,info);
					UiObject tm=new UiObject(new UiSelector().text("添加账户"));
					OperationUiObject.WaitUntilGone(tm,"10000");
					excute(Object_Text,Operation_ClickWait,"网易163");
					UiObject mail2 = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.email:id/account_email");
					mail2.setText(mail);
					excute(Object_Text,Operation_ClickWait,"下一步");
					UiObject password2 = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.email:id/regular_password");
					password2.setText(password);
					excute(Object_Text,Operation_ClickWait,"下一步");
					UiObject tm1=new UiObject(new UiSelector().text("正在验证服务器设置…"));
					OperationUiObject.WaitUntilGone(tm1,"60000");
					excute(Object_Text,Operation_ClickWait,"下一步");
					UiObject tm2=new UiObject(new UiSelector().text("正在创建账户…"));
					OperationUiObject.WaitUntilGone(tm2,"60000");
					UiObject name2 = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.email:id/account_name");
					name2.setText(name);
					excute(Object_Text,Operation_ClickWait,"下一步");
				}
				
				public static void check_mail_account(String info,String mail) 
				{
					excute(Object_Text,Operation_ClickWait,info);
					check(Object_Text,Operation_checkExist,mail);
				}
				
				public static void set_time(String set_time, int time) throws IOException 
				{
					Process pro = Runtime.getRuntime().exec("date " + set_time);
					Wait(time);
					excute(Object_Device, Operation_PressBack);
					excute(Object_Text,Operation_ClickWait,"日期和时间");
				}
				
				public static void set_pic(String info) throws UiObjectNotFoundException 
				{
					int x = UiDevice.getInstance().getDisplayWidth();
					int y = UiDevice.getInstance().getDisplayHeight();
					excute(Object_Text,Operation_ClickWait,"壁纸");
					excute(Object_Text,Operation_ClickWait,info);
					Wait(2000);
					if(info.equals("动态壁纸"))
						{
							excute(Object_Text,Operation_ClickWait,"黑洞");
							excute(Object_Text,Operation_ClickWait,"设置壁纸");
						}
					if(info.equals("图库"))
						{
							UiDevice.getInstance().click(x/2, y/10*6);
							Wait(2000);
							UiDevice.getInstance().click(x/2, y/2);
							Wait(2000);
							excute(Object_Text,Operation_ClickWait,"设置壁纸");
						}
					if(info.equals("壁纸"))
						{
							excute(Object_Description,Operation_ClickWait,"第1张壁纸，共1张");
							Wait(2000);
							excute(Object_Text,Operation_ClickWait,"设置壁纸");
						}
					if(info.equals("文件管理器"))
						{
							excute(Object_TextScroll,Operation_ClickWait,"picture.jpg","vertical");
							Wait(2000);
							excute(Object_Text,Operation_ClickWait,"保存");
						}
					excute(Object_Device, Operation_PressBack);
				}
				
				public static void take_temp_pic(UiDevice device,String name) 
				{
					File Dir = new File("/sdcard/Temp");
					if(!Dir.exists())
					{
						Dir.mkdir();
					}
					File screen = new File("/sdcard/Temp/"+name+".png") ;
					device.takeScreenshot(screen);
				}

			    public static void check_pic(String name,String name_new,double percent) 
			    {
			    	String path1 = "/sdcard/Temp/"+name+".png";
			    	String path2 = "/sdcard/Temp/"+name_new+".png";
			        //创建两个bitmap
			        Bitmap m1=BitmapFactory.decodeFile(path1);
			        Bitmap m2=BitmapFactory.decodeFile(path2);
			        //声明变量
			        int width=m2.getWidth();
			        int height=m2.getHeight();
			        int numDiffPixels=0;
			        //横纵对比，涉及到两个循环
			        for(int y=0;y<height;y++)
			        {
			            for(int x=0;x<width;x++)
			            {
			                //取不相等的像素值
			                if(m2.getPixel(x, y)!=m1.getPixel(x, y))
			                {
			                    numDiffPixels++;
			                }
			            }
			        }
			        double totalPices=height*width;//总像素值
			        double diffPercent=numDiffPixels/totalPices;//不相等的百分比
//			        return percent<=1.0-diffPercent;//返回相似度
			        Assert.assertFalse(percent<=1.0-diffPercent);//比较相似度
				}
			    
			    public static void delete_pic(String name,String name_new) 
			    {
			    	String path1 = "/sdcard/Temp/"+name+".png";
			    	String path2 = "/sdcard/Temp/"+name_new+".png";
			        File file1=new File(path1);
			        File file2=new File(path2);
			        file1.delete();
			        file2.delete();
				}
//以上为石亚辉*******************************************************************************************************
			    public static void addWifi(String wifiname, String type,String password) throws UiObjectNotFoundException{
					excute(Object_Text,Operation_SetText,"输入SSID",wifiname);
					excute(Object_ResourceId, Operation_ClickWait,"android:id/text1",type);
					excute(Object_ResIdText,Operation_ClickWait,"android:id/text1",type);
					excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/password",password);
					excute(Object_ResourceId,Operation_ClickWait,"android:id/button1", "保存");
				}
				
				public static void checkWifiConnect(String wifiname) throws UiObjectNotFoundException{
					check(Object_ResourceId,Operation_TextEqualTrue,"android:id/title",wifiname);
					check(Object_ResourceId,Operation_TextEqualTrue,"android:id/summary","已连接");
					System.out.println(wifiname);
				}
				public static void checkSaveWifi(String wifiname) throws UiObjectNotFoundException{
					check(Object_Text,Operation_checkExist,wifiname);
					System.out.println(wifiname);
				}
		//
					public static void Display(String simcard) throws UiObjectNotFoundException
					 {
						String text = "SIM2";
						excute(Object_ResIdInstance,Operation_Exists,"android:id/summary","4",simcard);
						if(simcard == text)
						{
							System.out.println(simcard);
						}
					 }
					
					public static void Selectdate() throws UiObjectNotFoundException
					 {
					  UiObject index3=new UiObject(new UiSelector().resourceId("android:id/list")).getChild(new UiSelector().index(3));
					  UiObject index1 = index3.getChild(new UiSelector().resourceId("com.android.settings:id/universal_switch"));  
					  if (index1.isChecked()==false)
					  {
						  index1 = index3.getChild(new UiSelector().resourceId("com.android.settings:id/universal_switch"));
						  index1.click();
					  }
					  Wait(2000);
					 }
					
					public static void SIMsettings(String SIMName) throws UiObjectNotFoundException
					{
						System.out.println("======Start to excute CallContactsCommon: SIMsettings======");
						
						excute(Object_Text,Operation_ClickWait,SIMName);
						excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");	
						excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");	
						excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name","");
						Wait(500);
					}
					//修改SIM卡名称
					public static void SIMName(String SIMCard,String SIMName) throws UiObjectNotFoundException
					{
						System.out.println("======Start to excute CallContactsCommon: SIMName======");
						excute(Object_ResourceId, Operation_SetText,"com.android.settings:id/sim_name",SIMName);
						excute(Object_Device,Operation_PressBack);
						excute(Object_Text,Operation_ClickWait,"确定");
						Wait(1000);
						excute(Object_Text,Operation_ClickWait,SIMCard);
						Wait(1000);
						check(Object_ResourceId,Operation_TextEqualTrue,"com.android.settings:id/sim_name",SIMName);
						Wait(1000);
						excute(Object_Text,Operation_ClickWait,"确定");
						Wait(1000);
					}
					
					public static void closeWifi() throws UiObjectNotFoundException, RemoteException 
					{
						if((Boolean) excute(Object_Text,Operation_Exists,"开启"))
						{	
							excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
						}
						else
						{
							excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
						}
						
					}
					
}


