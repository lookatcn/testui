package testcase;

import java.io.IOException;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.CallLogCommon;
import framework.common.SettingCommon;
import framework.common.EmailCommon;
import framework.common.CallCommon;
import framework.driver.ObjectFind;
import framework.driver.OperationUiDevice;
import framework.driver.OperationUiObject;
//插两张SIM卡，SD卡及手机内存中要有预存联系人
public class Settings extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Setting);
		

		
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	public void test_000() throws UiObjectNotFoundException, RemoteException 
	{
	DeviceCommon.removePermissions();
	}
	//设置VPN，
	public void test_001_001() throws UiObjectNotFoundException, RemoteException 
	{
			SettingCommon. EnterVPN();
			excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/vpn_create");
			if((Boolean) excute(Object_Text,Operation_Exists,"注意"))
				{
				excute(Object_Text,Operation_ClickWait,"确定");
				SettingCommon.SetPIN();
				SettingCommon.SetVPN("PPTP","vpn1.e2010.mobility-lab.com");
				}
			else{
				SettingCommon.SetVPN("PPTP","vpn1.e2010.mobility-lab.com");
			}	
			Wait(500);
			check(Object_Text,Operation_checkExist,"PPTP");
			
	}
	
	public void test_001_002() throws UiObjectNotFoundException, RemoteException 
	{
			SettingCommon. EnterVPN();
			SettingCommon.ConnectVPN("PPTP","vpnuser"," Password123");
	}
	//浏览Facebook
	public void test_001_003() throws UiObjectNotFoundException, RemoteException 
	{
		try{
			SettingCommon.ConnectBrowser("www.facebook.com");
		}finally{
			SettingCommon.RemovePIN();}
			
	}
	//添加L2TPVPN
	public void test_001_004() throws UiObjectNotFoundException, RemoteException 
	{
		SettingCommon. EnterVPN();
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/vpn_create");
		if((Boolean) excute(Object_Text,Operation_Exists,"注意"))
			{
			excute(Object_Text,Operation_ClickWait,"确定");
			SettingCommon.SetPIN();
			Wait(500);
			SettingCommon.SetL2TPVPN("L2TP","144.188.130.240","",""," test");
			}
		else{
				SettingCommon.SetL2TPVPN("L2TP","144.188.130.240","",""," test");
			}
		Wait(500);
		check(Object_Text,Operation_Exists,"L2TP");
			
	}
	//连接L2TPVPN，上网
	public void test_001_005() throws UiObjectNotFoundException, RemoteException 
	{
		try{
			SettingCommon. EnterVPN();
			SettingCommon.ConnectVPN("L2TP","test3","Password123");
			Wait(5000);
			SettingCommon.ConnectBrowser("www.facebook.com");
		}finally{
			SettingCommon.RemovePIN();}
	}
	//通过4G拨号
	public void test_001_006() throws UiObjectNotFoundException, RemoteException 
	{
			SettingCommon.EnterMove();
			Wait(500);
			SettingCommon.Select4date();
			Wait(5000);
			SettingCommon.endCall();
			//String failcase="test_001_006";
			//OperationUiDevice.takeScreenshot(getUiDevice(), failcase);
	}
	//通过4G上网
	public void test_001_007() throws UiObjectNotFoundException, RemoteException 
	{
			SettingCommon.EnterMove();
			Wait(500);
			SettingCommon.Select4date();
			Wait(1000);
			SettingCommon.ConnectBrowser("www.baidu.com");	
	}
	//通过3G拨号
		public void test_001_008() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.EnterMove();
			Wait(500);
			SettingCommon.Select3date();
			Wait(5000);
			SettingCommon.endCall();
		}
		//通过3G上网
		public void test_001_009() throws UiObjectNotFoundException, RemoteException 
		{
				SettingCommon.EnterMove();
				SettingCommon.Select3date();
				SettingCommon.ConnectBrowser("www.baidu.com");
		}	
		//通过2G拨号
		public void test_001_010() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.EnterMove();
			SettingCommon.Select2date();
			SettingCommon.endCall();
				}
		//2G上网
		public void test_001_011() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.EnterMove();
			SettingCommon.Select2date();
			SettingCommon.ConnectBrowser("www.baidu.com");
				}
		
		//查看流量使用,包含test__002_002
		public void test_002_001() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.EnterMoveData();
			Wait(500);
			SettingCommon.GetMoveData();
				}
		//显示WLAN流量
	public void test_002_003() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.EnterMoveData();
			SettingCommon.WifiData("显示WLAN流量");
			excute(Object_Device,Operation_PressMenu);
			check(Object_Text,Operation_checkExist,"隐藏WLAN流量");
				}
	//隐藏WLAN流量
	public void test_002_004() throws UiObjectNotFoundException, RemoteException 
	{
		SettingCommon.EnterMoveData();
		SettingCommon.WifiData("隐藏WLAN流量");
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"显示WLAN流量");
			}
	
	//打开，关闭，滑动流量限制
	public void test_002_005() throws UiObjectNotFoundException, RemoteException 
	{
		SettingCommon.OpenDaralimit();
		SettingCommon.CloseDaralimit();
		SettingCommon.DragDaralimit();
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/sweep_warning");
		Wait(500);
		check(Object_ResourceId,Operation_TextEqualTrue,"android:id/numberpicker_input","5120");
			}
	
	//PIN屏幕锁
	public void test_005_001() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		SettingCommon.Entersecurity();
		SettingCommon.SetPIN();
		SettingCommon.unLock();
		check(Object_Text,Operation_checkExist,"屏幕锁定方式");
		}finally{
			SettingCommon.RemovePIN();
			}
		}
	//修改SIM名称为特殊字符
	public void test_013_001() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1","@#$");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMName("SIM 卡插槽 2","*&^");
		Wait(500);
	}
	//修改SIM1名称为数字
		public void test_013_003() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			Wait(500);
			SettingCommon.SIMsettings("SIM 卡插槽 1");
			SettingCommon.SIMName("SIM 卡插槽 1","1234567890");
		}
	//修改SIM2名称为数字
		public void test_013_004() throws UiObjectNotFoundException, RemoteException 
			{
				excute(Object_Text,Operation_ClickWait,"SIM 卡");
				Wait(500);
				SettingCommon.SIMsettings("SIM 卡插槽 2");
				SettingCommon.SIMName("SIM 卡插槽 2","0987654321");
				}
				
	//设置SIM1为主卡
	public void test_013_005() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			Wait(500);
			SettingCommon.SIMsettings("SIM 卡插槽 1");
			SettingCommon.SIMName("SIM 卡插槽 1","SIM1");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"主卡选择");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM1");
			Wait(500);
			if((Boolean) excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
				Wait(30000);
			}
			else
				Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","5","SIM1");
			}	
	//设置SIM2为主卡
	public void test_013_006() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			Wait(500);
			SettingCommon.SIMsettings("SIM 卡插槽 2");
			SettingCommon.SIMName("SIM 卡插槽 2","SIM2");
			excute(Object_Text,Operation_ClickWait,"主卡选择");
			Wait(1000);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			Wait(500);	
			if((Boolean) excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
				Wait(40000);
			}
			else
				Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","5","SIM2");
			}
	//主卡在SIM1设置SIM1拨号
	public void test_013_007() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_005();
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM1");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
			SettingCommon.endCall();
			}
	//主卡在SIM1设置SIM2拨号
	public void test_013_008() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_005();
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
			SettingCommon.endCall();
			}
	//主卡在SIM2设置SIM1拨号
	public void test_013_009() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM1");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
			SettingCommon.endCall();
			}
	//主卡在SIM2设置SIM2拨号
	public void test_013_010() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
			SettingCommon.endCall();
			}
	//主卡在SIM1设置SIM1拨号
	public void test_013_011() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_005();
		excute(Object_Text,Operation_ClickWait,"通话");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"SIM1");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
		SettingCommon.endCall();
		}
	//主卡在SIM1设置SIM2拨号
	public void test_013_012() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_005();
		excute(Object_Text,Operation_ClickWait,"通话");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"SIM2");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
		SettingCommon.endCall();
				}
	//主卡在SIM1拨号每次询问选SIM1
	public void test_013_013() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_005();
		excute(Object_Text,Operation_ClickWait,"通话");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"每次都询问");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","每次都询问");
		SettingCommon.SelectendCall(1);
				}
	
	//主卡在SIM1拨号每次都询问选SIM2
	public void test_013_014() throws UiObjectNotFoundException, RemoteException 
	{
		SettingCommon.SelectendCall(2);
			}
	//主卡在SIM2拨号选SIM1
		public void test_013_015() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			SettingCommon.SIMsettings("SIM 卡插槽 1");
			SettingCommon.SIMName("SIM 卡插槽 1","SIM1");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM1");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
			SettingCommon.endCall();
				}
	//主卡在SIM2拨号选SIM2
	public void test_013_016() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			excute(Object_Text,Operation_ClickWait,"通话");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
			SettingCommon.endCall();
			}
	//主卡在SIM2拨号每次都询问选SIM1
	public void test_013_017() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_006();
		excute(Object_Text,Operation_ClickWait,"通话");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"每次都询问");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","每次都询问");
		SettingCommon.SelectendCall(1);
			}
		
	//主卡在SIM2拨号每次都询问选SIM2
	public void test_013_018() throws UiObjectNotFoundException, RemoteException 
		{
			SettingCommon.SelectendCall(2);
			}
	//主卡在SIM1信息选SIM1
	public void test_013_019() throws UiObjectNotFoundException, RemoteException 
		{
		test_013_005();
		excute(Object_Text,Operation_ClickWait,"信息");
		Wait(500);
		excute(Object_Text,Operation_ClickWait,"SIM1");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
		Wait(500);
			}
	//主卡在SIM1信息选SIM2
		public void test_013_020() throws UiObjectNotFoundException, RemoteException 
			{
			test_013_005();
			excute(Object_Text,Operation_ClickWait,"信息");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
			Wait(500);
				}
	//主卡在SIM2信息选SIM1
	public void test_013_021() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			excute(Object_Text,Operation_ClickWait,"信息");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM1");
			}
	//主卡在SIM2信息选SIM2
	public void test_013_022() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_006();
			excute(Object_Text,Operation_ClickWait,"信息");
			Wait(500);
			excute(Object_Text,Operation_ClickWait,"SIM2");
			}
	//主卡在SIM1信息选SIM1发送短信
	public void test_013_023() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_019();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_ResIdDesc,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM1，SIM 卡选择器");
		
		}
	//主卡在SIM1信息选SIM1发送彩信
	public void test_013_024() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_019();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_ResIdDesc,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM1，SIM 卡选择器");
			}
	//主卡在SIM1信息选SIM2发送短信
	public void test_013_025() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_020();
			SettingCommon.SendMS("10010");
			Wait(500);
			check(Object_ResIdDesc,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM2，SIM 卡选择器");
			}
	//主卡在SIM1信息选SIM2发送彩信
	public void test_013_026() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_020();
			SettingCommon.SendMMS("10010");
			Wait(500);
			check(Object_ResIdDesc,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM2，SIM 卡选择器");
			}
	//主卡在SIM1信息选SIM1发送短信
	public void test_013_027() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_020();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_Description,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM1，SIM 卡选择器");
		}
	//主卡在SIM2信息选SIM1发送彩信
	public void test_013_028() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_020();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_Description,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM1，SIM 卡选择器");
			}
	//主卡在SIM2信息选SIM2发送短信
	public void test_013_029() throws UiObjectNotFoundException, RemoteException 
		{
			test_013_020();
			SettingCommon.SendMS("10010");
			Wait(500);
			check(Object_Description,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM2，SIM 卡选择器");
			}
	//主卡在SIM2信息选SIM2发送彩信
	public void test_013_030() throws UiObjectNotFoundException, RemoteException 
	{
		test_013_020();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_Description,Operation_Exists,"com.android.messaging:id/self_send_icon","已选择SIM2，SIM 卡选择器");
		}
	
	//主卡在SIM1信息选SIM1发送禁用SIM1发送短信
		public void test_013_045() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_019();
			SettingCommon.enableCard("开启");
			Wait(500);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
			SettingCommon.SendMS("10010");
		}finally{
				SettingCommon.enableCard("关闭");}
			
			}
	//主卡在SIM1信息选SIM2发送禁用SIM1发送短信
	public void test_013_046() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		test_013_020();
		SettingCommon.enableCard("开启");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
		SettingCommon.SendMS("10010");
		}finally{
		SettingCommon.enableCard("关闭");}
		}
	//主卡在SIM1信息选SIM1发送禁用SIM2发送短信
	public void test_013_047() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		test_013_019();
		SettingCommon.enableCard2("开启");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
		SettingCommon.SendMS("10010");
		}finally{
			SettingCommon.enableCard2("关闭");}
		
		}
	
	//主卡在SIM1信息选SIM1发送禁用SIM2发送短信
	public void test_013_048() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		test_013_020();
		SettingCommon.enableCard2("开启");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
		SettingCommon.SendMS("10010");
		}finally{
			SettingCommon.enableCard2("关闭");}
			}
	//主卡在SIM2信息选SIM1发送禁用SIM1发送短信
	public void test_013_049() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		test_013_021();
		SettingCommon.enableCard("开启");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
		SettingCommon.SendMS("10010");
		}finally{
			SettingCommon.enableCard("关闭");}
			}
		
	//主卡在SIM2信息选SIM2发送禁用SIM1发送短信
	public void test_013_050() throws UiObjectNotFoundException, RemoteException 
	{
		try{
		test_013_022();
		SettingCommon.enableCard("开启");
		Wait(500);
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
		SettingCommon.SendMS("10010");
		}finally{
		SettingCommon.enableCard("关闭");}
		}
	//主卡在SIM2信息选SIM2发送禁用SIM2发送短信
		public void test_013_051() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_021();
			SettingCommon.enableCard2("开启");
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
			SettingCommon.SendMS("10010");
			}finally{
				SettingCommon.enableCard2("关闭");}
				}
		//主卡在SIM2信息选SIM1发送禁用SIM2发送短信
		public void test_013_052() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_022();
			SettingCommon.enableCard2("开启");
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
			SettingCommon.SendMS("10010");
			}finally{
				SettingCommon.enableCard2("关闭");}
			}
		//主卡在SIM2信息选SIM1发送禁用SIM2发送短信
		public void test_013_053() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			ClearBackgroundApp();
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			Wait(500);
			}finally{
			SettingCommon.enableCard("开启");
			Wait(1000);
			SettingCommon.enableCard2("关闭");}
			}
	//发送信息
	public void test_013_054() throws UiObjectNotFoundException, RemoteException 
	{
		SettingCommon.SendMS("10010");
		}
	//拨打紧急拨号
	public void test_013_055() throws UiObjectNotFoundException, RemoteException 
		{
		try{
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		 int WideArea = ModArea.width();
		 int x = ModArea.centerX();
		 int y = ModArea.centerY();
		 CallCommon.makeCallByDailer("112");
		 Wait(10000);
		 UiDevice.getInstance().click(x, y);
		 }finally{
			SettingCommon.enableCard("关闭");
			Wait(1000);
			SettingCommon.enableCard2("关闭");}
			}
		
	public void test_006_001() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.set_check_date("2016","10");
		SettingCommon.set_check_time("上午", "6","30");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
	}

	public void test_006_002() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.set_check_time_zone();
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}

	public void test_006_003() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
		SettingCommon.set_time("100111592015.50",10000);
 		//检测时间设置是否正确
 		SettingCommon.check_am_pm("pm","12","00");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}

	public void test_006_004() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
		SettingCommon.set_time("100123592015.50",10000);
 		//检测时间设置是否正确
 		SettingCommon.check_am_pm("am", "12","00");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}

	public void test_006_005() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
 		SettingCommon.set_time("123123592037.50",10000);
 		SettingCommon.check_biggest_date();
 		//检测时间设置是否正确
 		SettingCommon.check_am_pm("am", "12", "00");
	}

	public void test_006_006() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"日期和时间","vertical");
		SettingCommon.open_close_auto_date("关闭");
		SettingCommon.open_close_auto_time_zone("关闭");
		SettingCommon.check_auto();
	}

	public void test_005_002() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_Device, Operation_PressHome);
		ClearBackgroundApp();
		SettingCommon.install_apk("GPS.apk");
		check(Object_Text,Operation_checkExist,"应用安装完成。");
	}
	
	public void test_007_001() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"应用","vertical");
		SettingCommon.uninstall_apk("GPS Test");
		check(Object_Text,Operation_checkNoExist,"GPS Test");
	}

	public void test_004_001() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//测试时需要提供账户跟密码
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
//		SettingCommon.add_mail_account(mail_address,"个人",password,name);
//		SettingCommon.check_mail_account("个人",mail_address);
	}

	public static void test_004_002() throws UiObjectNotFoundException 
	{
		//测试时需要提供账户跟密码，并且修改Exchange设置
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
//		SettingCommon.add_mail_account(mail_address, "Exchange", password, name);
//		SettingCommon.check_mail_account("Exchange", mail_address);
	}

	public static void test_005_003() 
	{
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"滑动");
		excute(Object_Device, Operation_Sleep);
		Wait(2000);
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();
		check(Object_Text,Operation_checkExist,"滑动");
	}

	public static void test_003_001() throws UiObjectNotFoundException 
	{
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen","HomeScreen_new",0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}

	public static void test_003_002()
	{
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		check(Object_Text,Operation_checkExist,"选择壁纸来源");
	}

	public static void test_003_003() throws UiObjectNotFoundException
	{
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("图库");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen","HomeScreen_new",0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}

	public static void test_003_004() throws UiObjectNotFoundException, IOException
	{
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"显示","vertical");
		SettingCommon.set_pic("文件管理器");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen","HomeScreen_new",0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}
	
	//以下为石亚辉*************************************************************************************
	//设置-主菜单界面最上角点击搜索
		public static void test_009_001() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/search");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/search");
		}
				
			//输入字符/数字等进行搜索操作
	  public void test_009_002() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/search");
			excute(Object_Text,Operation_SetText,"搜索…","WLAN");
			excute(Object_ResIdText,Operation_Exists,"com.android.settings:id/title", "WLAN");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","WLAN");
		}
			 
			//添加无线网络
		public void test_010_001() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"WLAN");
			if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
			{	
				excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			}
			else
			{
				excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
			}
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_Text,Operation_ClickWait,"添加网络");
			SettingCommon.addWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
			Wait(15000);
			SettingCommon.checkWifiConnect("Testteam");
		}
			
			//用无线网浏览网页
		public void test_010_002() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"WLAN");
			if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
			{	
				excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			}
			else
			{
				excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
			}
			if((Boolean) excute(Object_ResourceId,Operation_TextEqualTrue,"android:id/summary","已连接"))
			{
				SettingCommon.ConnectBrowser("www.baidu.com");
			}
			else
			{
				excute(Object_Description,Operation_ClickWait,"更多选项");
				excute(Object_Text,Operation_ClickWait,"添加网络");
				SettingCommon.addWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
				Wait(8000);
				SettingCommon.checkWifiConnect("Testteam");
				SettingCommon.ConnectBrowser("www.baidu.com");	
				Wait(2000);
			}
		}
			
			//查看已保存网络
		public void test_010_003() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"WLAN");
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"已保存的网络");
			Wait(1000);
			SettingCommon.checkSaveWifi("Testteam");
		}
			
			//取消已保存网络
		public void test_010_004() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"WLAN");
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"已保存的网络");
			SettingCommon.checkSaveWifi("Testteam");
			excute(Object_Text,Operation_ClickWait,"Testteam");
			excute(Object_ResourceId,Operation_ClickWait,"android:id/button3","取消保存");
			Wait(1000);
			check(Object_Text,Operation_checkNoExist,"android:id/title","Testteam");
			excute(Object_Device,Operation_PressBack);
			SettingCommon.closeWifi();
		}  
			
			//蓝牙搜索,重用名更改
		public void test_011_001() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"蓝牙");
		    if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
			{	
			    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			}
			else
			{
				excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
			}
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"重命名此设备");
			excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/edittext","test");
			excute(Object_ResourceId,Operation_ClickWait,"android:id/button1");
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"重命名此设备");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/edittext","test");
			excute(Object_ResourceId,Operation_ClickWait,"android:id/button2");	
			}
		
		//与蓝牙设备配对
		public void test_011_002() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"蓝牙");
			if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
			{	
			    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			}
			else
			{
				excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
			}
			Wait(15000);
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"刷新");
			Wait(15000);
			check(Object_ResIdText,Operation_checkExist,"android:id/title","1");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","1");
			Wait(6000);
			if((Boolean)excute(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","要与1配对吗？"))
			{
				excute(Object_Text,Operation_ClickWait,"android:id/button1","配对");
			}
			else
			{
				Wait(5000);
				excute(Object_Text,Operation_ClickWait,"android:id/button1","配对");
			}	
			
		}
		
		//主卡为SIM1，SIM1作为移动数据浏览百度
		public void test_013_031() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","主卡选择");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM1");
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
			}
			Wait(40000);
			SettingCommon.Selectdate();
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","移动数据网络");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM1");
			Wait(2000);
			SettingCommon.ConnectBrowser("www.baidu.com");	
			Wait(2000);
		}

		
		
	   //主卡为SIM1，SIM2作为移动数据浏览百度
		public void test_013_032() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","主卡选择");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM1");
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
			}
			Wait(20000);
			SettingCommon.Selectdate();
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","移动数据网络");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM2");
			Wait(2000);
			SettingCommon.ConnectBrowser("www.baidu.com");	
			Wait(2000);
		}
		
		//主卡为SIM2，SIM1作为移动数据浏览百度
		public void test_013_033() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","主卡选择");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM2");
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
			}
			Wait(40000);
			SettingCommon.Selectdate();
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","移动数据网络");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM1");
			Wait(2000);
			SettingCommon.ConnectBrowser("www.baidu.com");	
			Wait(2000);
		}
			
			//主卡为SIM2，SIM2作为移动数据浏览百度
		public void test_013_034() throws UiObjectNotFoundException, RemoteException 
		{
			excute(Object_Text,Operation_ClickWait,"SIM 卡");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","主卡选择");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM2");
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			{
				excute(Object_Text,Operation_ClickWait,"确定");
			}
			Wait(20000);
			SettingCommon.Selectdate();
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title","移动数据网络");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/title","SIM2");
			Wait(2000);
			SettingCommon.ConnectBrowser("www.baidu.com");	
			Wait(2000);
		}
		
			//主卡为SIM1，禁用SIM1
		public void test_013_035() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_005();
			SettingCommon.enableCard("开启");
			}finally{
			SettingCommon.enableCard("关闭");}
			
		}

		   //主卡为SIM1，禁用SIM2
		public void test_013_036() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_005();
			SettingCommon.enableCard2("开启");
			}finally{
			SettingCommon.enableCard2("关闭");}
			
		}
			
			//主卡为SIM1，禁用SIM1和SIM2
		public void test_013_037() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_005();
			SettingCommon.enableCard("开启");
			SettingCommon.enableCard2("开启");
			}finally{
			SettingCommon.enableCard("关闭");
			SettingCommon.enableCard2("关闭");}
		}
		
			//主卡为SIM2,禁用SIM1
		public void test_013_038() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_006();
			SettingCommon.enableCard("开启");
			Wait(500);
			}finally{
			SettingCommon.enableCard("关闭");}
			
		}
			
			//主卡为SIM2,禁用SIM2
		public void test_013_039() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_006();
			SettingCommon.enableCard2("开启");
			}finally{
			SettingCommon.enableCard2("关闭");}
			
		}
			
			//主卡为SIM2，禁用SIM1和SIM2
		public void test_013_040() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_006();
			SettingCommon.enableCard("开启");
			SettingCommon.enableCard2("开启");
			}finally{
			SettingCommon.enableCard("关闭");
			SettingCommon.enableCard2("关闭");}
			
		}
			
			//主卡为SIM1，禁用SIM1，通信、短信等无法设置
		public void test_013_041() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_005();
			SettingCommon.enableCard("开启");
			Wait(20000);
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
		      {
		       excute(Object_Text,Operation_ClickWait,"确定");
		      }
		       Wait(2000);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
			}finally{
			SettingCommon.enableCard("关闭");}
		}
			
			//主卡为SIM1，禁用SIM2，通信、短信等无法设置
		public void test_013_042() throws UiObjectNotFoundException, RemoteException 
		{
			try{
			test_013_005();
			SettingCommon.enableCard2("开启");
			Wait(20000);
			if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
		      {
		       excute(Object_Text,Operation_ClickWait,"确定");
		      }
		       Wait(2000);
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
			check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
			}finally{
			SettingCommon.enableCard2("关闭");}
		}
			

			//主卡为SIM2，禁用SIM1，通信、短信等无法设置
			public void test_013_043() throws UiObjectNotFoundException, RemoteException 
			{
				try{
				test_013_006();
				SettingCommon.enableCard("开启");
				Wait(20000);
				if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			      {
			       excute(Object_Text,Operation_ClickWait,"确定");
			      }
			       Wait(2000);
				check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
				check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
				}finally{
				SettingCommon.enableCard("关闭");}
			}
			

			//主卡为SIM2，禁用SIM2，通信、短信等无法设置
			public void test_013_044() throws UiObjectNotFoundException, RemoteException 
			{
				try{
				test_013_006();
				SettingCommon.enableCard2("开启");
				Wait(20000);
				if((Boolean)excute(Object_Text,Operation_Exists,"注意"))
			      {
			       excute(Object_Text,Operation_ClickWait,"确定");
			      }
			       Wait(2000);
				check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
				check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
				}finally{
				SettingCommon.enableCard2("关闭");}
			}
			
}