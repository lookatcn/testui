package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.driver.AndroidObject;

public class EmailCommon extends UiAutomatorTestCase{
	
	//个人用户信息
	public static String emailName = "18202542932@139.com";
	public static String password = "12abAB";
	
	public static void inatialEmail() throws UiObjectNotFoundException{
		if(!(Boolean) excute(Object_ResourceId,Operation_checkExist,"com.android.email:id/compose_button"))
		{
			addEmail(emailName,password);
			Wait(5000);
		}
	}
	
	public static void addEmail(String userName,String password) throws UiObjectNotFoundException{
		addEmail("个人", userName,password,"Test");
	}
	
	public static void addEmail(String userName,String password,String accountName) throws UiObjectNotFoundException{
		addEmail("个人", userName,password,accountName);
	}
	
	//index = 个人/Exchange
	public static void addEmail(String index, String userName,String password,String accountName) throws UiObjectNotFoundException{
		//String userName = "18202542932@139.com";
		//String password = "12abAB";
		String server = "";
		if(index.equals("个人"))
		{
			server = "外发服务器设置";
		}
		else if(index.equals("Exchange"))
		{
			server = "接收服务器设置";
		}
		UiObject next = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"com.android.email:id/next","下一步");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.email:id/mail_type_name","139邮箱");
		excute(Object_ResourceId,Operate_ReturnObject,"com.android.email:id/account_email");
		excute(Object_ResourceId,Operation_SetText,"com.android.email:id/account_email",userName);
		next.clickAndWaitForNewWindow();
		//添加Exchange账户会用到
		if((Boolean) excute(Object_Text,Operation_Exists,"确认帐户类型"))
		{
			excute(Object_Text,Operation_ClickWait,"Exchange");
		}
		excute(Object_ResourceId,Operation_SetText,"com.android.email:id/regular_password",password);
		Wait(2000);
		next.clickAndWaitForNewWindow();
		UiObject emailConfig = new UiObject(new UiSelector().textContains("正在"));
		DeviceCommon.waitUntilDisapper(emailConfig,120);
		
		UiObject noConnect = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/alertTitle","无法完成");
		UiObject modify = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/button1","修改详细信息");
		while(noConnect.exists())
		{
			modify.clickAndWaitForNewWindow();
			next.clickAndWaitForNewWindow();
			DeviceCommon.waitUntilDisapper(emailConfig,60);		
		}
		if((Boolean) excute(Object_ResIdText,Operation_Exists,"com.android.email:id/headline",server))
		{
			next.clickAndWaitForNewWindow();
			DeviceCommon.waitUntilDisapper(emailConfig,60);		
		}
		Wait(2000);
		if(index.equals("Exchange"))
		{
			if((Boolean) excute(Object_Text,Operation_Exists,"远程安全管理"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1","确定");
			}
			Wait(2000);
			next.clickAndWaitForNewWindow();
			DeviceCommon.waittingFor((UiObject) excute(Object_ClassText,Operate_ReturnObject,"android.widget.TextView", "要激活设备管理器吗？"),30);
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/action_button","激活");
			next.clickAndWaitForNewWindow();
			Wait(1000);
			check(Object_Text,Operation_checkExist,"Exchange");
			return;
		}
	
		//为了提高测试效率，特做以下操作
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1","每隔15分钟");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1","永不");
		if((Boolean) excute(Object_Text,Operation_CheckedTrue,"收到电子邮件时通知我"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.email:id/account_notify","收到电子邮件时通知我");
		}
		if((Boolean) excute(Object_Text,Operation_CheckedTrue,"同步此帐户中的电子邮件"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.email:id/account_sync_email","同步此帐户中的电子邮件");
		}
		if((Boolean) excute(Object_Text,Operation_CheckedTrue,"连接到WLAN后自动下载附件"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.email:id/account_background_attachments","连接到WLAN后自动下载附件");
		}
		next.clickAndWaitForNewWindow();
		excute(Object_ResourceId,Operation_SetText,"com.android.email:id/account_name",accountName);
		next.clickAndWaitForNewWindow();
		Wait(1000);
       excute(Object_Text,Operation_checkExist,"个人");
	}
	
	public static void deleteEmail(String emailName) throws UiObjectNotFoundException{
		excute(Object_TextInstance,Operate_ReturnObject,"删除帐户", "1");
		excute(Object_Description,Operation_ClickWait,"转到上一层级");
		excute(Object_TextScrollWithResId,Operation_ClickWait,"android:id/list", "设置");
		if(emailName.equals("@"))
		{
			excute(Object_ResIdContainsText,Operation_ClickWait,"android:id/title",emailName);
		}
		else
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title",emailName);
		}
		DeviceCommon.ScrollIntoView((UiObject)AndroidObject.getObject(Object_TextInstance, "删除帐户", "1"));
		excute(Object_TextInstance,Operation_ClickWait,"删除帐户", "1");
		//new ScrollFindByObject(AndroidCommon.findByTextInstance("删除帐户", 1)).func().clickAndWaitForNewWindow();
		excute(Object_ResourceId,Operation_SetText,"android:id/button1","确定");
		excute(Object_ResourceId,Operation_SetText,"android:id/button1","确定");
		DeviceCommon.waitUntilDisapper((UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/message","删除中，请等待..."),120);
	}
	
	public static void deleteEmail() throws UiObjectNotFoundException{
		deleteEmail("@");
	}
	
	public static void changeEmailRing(String ringName) throws UiObjectNotFoundException{
		excute(Object_TextScroll,Operation_ClickWait,"选择铃声");
		if((Boolean) excute(Object_Text,Operation_checkExist,"使用媒体存储完成操作"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button_once", "仅此一次");
		}
		else
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "媒体存储");
		}
		excute(Object_TextScrollWithResId,Operation_ClickWait,"android:id/select_dialog_listview", ringName);
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1",Devices_Text_Operator_Determine);
	}
}
