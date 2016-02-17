package framework.driver;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;

public class OperationUiDevice 
{
	public static boolean deviceClick(UiDevice device, String x, String y)
	{
		return device.click(Integer.parseInt(x), Integer.parseInt(y));
	}
		
	public static boolean deviceDrag(UiDevice device, String startX, String startY, String endX, String endY, String steps)
	{
		return device.drag(Integer.parseInt(startX), 
						Integer.parseInt(startY), 
						Integer.parseInt(endX), 
						Integer.parseInt(endY), 
						Integer.parseInt(steps));
	}
	
	public static boolean isScreenOn(UiDevice device) throws RemoteException
	{
		return device.isScreenOn();
	}
		
	public static boolean pressBack(UiDevice device)
	{
		return device.pressBack();
	}
	
	public static boolean pressDelete(UiDevice device)
	{
		return device.pressDelete();
	}
	
	public static boolean pressEnter(UiDevice device)
	{
		return device.pressEnter();
	}
	
	public static boolean pressHome(UiDevice device)
	{
		return device.pressHome();
	}
			
	public static boolean pressKeyCode(UiDevice device, String keyCode)
	{
		return device.pressKeyCode(Integer.parseInt(keyCode));
	}
	
	public static boolean pressMenu(UiDevice device)
	{
		return device.pressMenu();
	}
		
	public static boolean pressRecentApps(UiDevice device) throws RemoteException
	{
		return device.pressRecentApps();
	}
	
	public static boolean sleep(UiDevice device) throws RemoteException
	{
		device.sleep();
		return true;
	}
			
	public static boolean wakeUp(UiDevice device) throws RemoteException
	{
		device.wakeUp();
		return true;
	}
	
	public static boolean swipe(UiDevice device, String startX, String startY, String endX, String endY, String steps) throws RemoteException
	{
		return device.swipe(Integer.parseInt(startX), 
				Integer.parseInt(startY), 
				Integer.parseInt(endX), 
				Integer.parseInt(endY), 
				Integer.parseInt(steps));
	}
	
	//截图
	public static void takeScreenshot (UiDevice uiDevice, String failcase) 
	{
		System.out.println("Enter takeScreenshot!!!");
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = format.format(time);
		File Dir = new File("/sdcard/Pictures");
		if(!Dir.exists()){
			Dir.mkdir();
		}
		File screen = new File("/sdcard/Pictures/"+timeStr+"_"+failcase+".png") ;
		uiDevice.takeScreenshot(screen);
		//UiDevice.getInstance().takeScreenshot(screen);
		//return uiDevice.takeScreenshot(storePath);
	}	
	
			
//	public static boolean unLock(UiObject uiObject) throws UiObjectNotFoundException
//	{
//		int x = uiObject.getBounds().centerX();
//		int y = uiObject.getBounds().centerY();
//		return UiDevice.getInstance().drag(x, y, x, 0, 10);
//	}
//		
//	public void clearBackGround()
//	{
//		UiDevice.getInstance().pressRecentApps();
//		new Wait(4000).action();
//		if (new GetObjectStatusByResId(Devices_ResId_ClearButton).getObjectStatus("isExist")) {
//			AndroidCommon.findAppByResourceId(Devices_ResId_ClearButton).clickAndWaitForNewWindow();
//		}else {
//			while (new GetObjectStatusByResId("com.android.systemui:id/task_view_thumbnail").getObjectStatus("isExist")) {
//				new FindAppByResIdAndClick("com.android.systemui:id/dismiss_task").action();
//			}
//		}
//	}
	
	
			
}