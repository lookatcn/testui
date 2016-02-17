package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.data.DeviceParameter.*;
import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

import android.graphics.Rect;
import android.os.RemoteException;

import static framework.excute.Excute.*;

public class DeviceCommon 
{
	
	public static void unLock()
	{
		excute(Object_Device, Operation_WakeUp);
		if((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_unlock))
		{
			Rect bounds = (Rect) excute(Object_ResourceId, Operation_GetBounds, Devices_ResId_unlock);
			String x = Integer.toString(bounds.centerX());
			String y = Integer.toString(bounds.centerY());
			excute(Object_Device, Operation_DiviceDrag, x, y, x, "0", "10");	
		}
	}
	
	public static void clearBackGround()
	{
		excute(Object_Device, Operation_PressRecentApps);		
		Wait(4000);
		if((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_ClearButton))
		{
			excute(Object_ResourceId, Operation_ClickWait, Devices_ResId_ClearButton);
		}
		else 
		{
			while ((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_TaskView)) 
			{
				excute(Object_ResourceId, Operation_ClickWait, Devices_ResId_DismissTask);
			}
		}
	}
	
	 public static void enterApp(String appName) throws UiObjectNotFoundException
	 {
	 System.out.println("======Start to excute DeviceCommon: enterApp======");
	 excute(Object_Device, Operation_PressHome);
	 if(!(Boolean) excute(Object_Description, Operation_Exists, appName))
	 {
	 excute(Object_Description, Operation_ClickWait, Devices_Desc_Applycation);
	 UiScrollable applists = new UiScrollable(new UiSelector().scrollable(true));
	         while(!(Boolean) excute(Object_Description, Operation_Exists, appName)){
	         applists.scrollTextIntoView(appName);
	 }
	 }
	 excute(Object_Description, Operation_ClickWait, appName);
	 }
	
	public static void exitApp() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute DeviceCommon: exitApp======");
		while(!(Boolean) excute(Object_Description,Operation_Exists,"应用"))
		{
			excute(Object_Device, Operation_PressBack);
		}
	}
	
	public static void swipe(String direction,int step,int loop) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute DeviceCommon: swipe======");
		int x = UiDevice.getInstance().getDisplayWidth();
		int y = UiDevice.getInstance().getDisplayHeight();
		while(loop > 0)
		{
			if(direction.equals("Left"))
				UiDevice.getInstance().swipe(x/2, y/2,0,y/2,step);
			else if(direction.equals("Right"))
				UiDevice.getInstance().swipe(x/2, y/2,x,y/2,step);
			else if(direction.equals("Up"))
				UiDevice.getInstance().swipe(x/2, y/2,x/2,0,step);
			else if(direction.equals("Down"))
				UiDevice.getInstance().swipe(x/2, y/2,x/2,y,step);
			loop--;
		}
	}
//	
//	public static void exitApp(String appName) throws UiObjectNotFoundException
//	{
//		
//	}
//	
//	public static void pressKeySerial(String keySerial) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressKeySerial======");
//		
//		for (int i = 0; i < keySerial.length(); i++) 
//		{
//			char  item =  keySerial.charAt(i);
//			new PressKeyCode(String.valueOf(item)).action();
//        }
//	}
//	
//	public static void pressKeySerialAsNumber(String key, int number) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressKeySerialAsNumber======");
//		
//		for (int i = 0; i < number; i++) 
//		{
//			pressKeySerial(key);
//        }
//	}
//	
//	public static void pressDirectionSerialAsNumber(String direction, int number) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressDirectionSerialAsNumber======");
//		
//		for (int i = 0; i < number; i++) 
//		{
//			new PressDirection(direction).action();
//        }
//	}
//	
	public static void isTextExistAndClick(String text) throws UiObjectNotFoundException
	{
		if((Boolean) excute(Object_Text,Operation_Exists,text))
		{
			excute(Object_Text,Operation_ClickWait,text);	
		}
	}
	
	public static void isDescExistAndClick(String text) throws UiObjectNotFoundException
	{
		if((Boolean) excute(Object_Text,Operation_Exists,text))
		{
			excute(Object_Description,Operation_ClickWait,text);	
		}
	}
//	
//	public static void isResIdExistAndClick(String text) throws UiObjectNotFoundException
//	{
//		if(new GetObjectStatusByResId(text).getObjectStatus("isExist"))
//		{
//			new FindAppByResIdAndClick(text).action();	
//		}
//	}
//	
	public static void selectMore(String name) throws UiObjectNotFoundException
	{
		String[] nameList = name.split(",");
		for(int i = 0; i < nameList.length; i++)
		{
			excute(Object_TextScroll,Operation_ClickWait,nameList[i]);
		}
	}
	
	 public static void closeSwitch(int num) throws UiObjectNotFoundException{
	        for(int i=0; i<num; i++){
	            UiObject index = (UiObject)excute(Object_ResIdInstance, Operate_ReturnObject, "com.android.packageinstaller:id/switchWidget",String.valueOf(i));
	            if(index.getText().equals("OFF")){
	                index.clickAndWaitForNewWindow();
	            }
	        }
	    }
	 
	 public static void removePermissions() throws UiObjectNotFoundException{
	        excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
	        Wait(500);
	        String[] appList = {"信息","浏览器","相机","文件管理器","电话","通讯录","日历","Launcher3"};
	        
	        for(int i=0; i<appList.length; i++){
	            excute(Object_TextScroll, Operation_ClickWait, appList[i], "vertical");
	            excute(Object_Text, Operation_ClickWait, "权限");
	            int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.android.packageinstaller:id/list"))).intValue();
	            if((Boolean)excute(Object_Text, Operation_Exists, "其他权限")){
	                Num = Num-1;
	                closeSwitch(Num);
	                excute(Object_ResIdText, Operation_ClickWait, "android:id/title","其他权限");
	                int secondNum = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.android.packageinstaller:id/list"))).intValue();
	                closeSwitch(secondNum);        
	            }
	            else{
	            	
	            closeSwitch(Num);
	            }
	            try {
					ClearBackgroundApp();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		Wait(1000);
	    		DeviceCommon.enterApp(Devices_Desc_Setting);
	            excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
	        }
	    }

	
 public static void waittingFor(UiObject uiobject,int N) throws UiObjectNotFoundException {
	int i = 1;
	while(!uiobject.exists()){
		Wait(1000);
		System.out.println("Waiting for...");
		if(i>N){
			System.out.println("Timeout...but I still can't find her...");
			break;
		}
		i++;
	}
}
//
//等待一段时间，但不进行点击操作，直到uiobject消失
   public static void waitUntilDisapper(UiObject uiobject,int N) throws UiObjectNotFoundException {
	   int i = 1;
	   while(uiobject.exists()){
		  Wait(1000);
		  System.out.println("Waiting Disapper...");
		   if(i>N){
			System.out.println("Timeout...but She still did not leave...");
			break;
		}
		i++;
	}
}
//寻找桌面小部件并执行相应操作
	public static void lookForWidgetByObject(UiObject object, String text) throws UiObjectNotFoundException 
	{
		int i=1;
		UiObject uiscrollable = (UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.launcher3:id/workspace");
		while(!(Boolean) excute(Object_ClassDesc,Operation_Exists,"android.widget.TextView","应用"))
		{
			excute(Object_Device, Operation_PressBack);
		}
		excute(Object_Device, Operation_PressHome);
		
		while(!object.exists())
		{
			if(i == 1)
			{
				UiDevice.getInstance().pressHome();//按home键进入第一屏（前提是在退出所有应用到IDEL界面）
				Wait(1000);
			}
			uiscrollable.swipeLeft(10);
			if(i>5)
			{
				break;
			}
			i++;
		}
		
		if(text.equals("Click"))
		{
			object.clickAndWaitForNewWindow();
		}
		else if(text.equals("Delete"))
		{
			Rect data = (Rect) excute(Object_ResourceId, Operation_GetBounds,"com.android.quicksearchbox:id/search_widget_text");
			
			int x = data.centerX();
			int y = data.centerY();
			object.dragTo(x, y, 50);
		}
	}

	public static UiObject ScrollIntoView(UiObject object) throws UiObjectNotFoundException
	{
		return ScrollIntoView(object, "vertical");
	}
	
	public static UiObject ScrollIntoView(UiObject object, String scrollDirection) throws UiObjectNotFoundException 
	{
		UiScrollable applist = new UiScrollable(new UiSelector().scrollable(true));
		if(scrollDirection == "horizontal")
			applist.setAsHorizontalList();
		
		if(applist.scrollIntoView(object))
		{
			return object;
		}
		else
		{
			Assert.assertTrue("Error: the needed object can't find!!!",false);
			return null;
		}
	}
  
}