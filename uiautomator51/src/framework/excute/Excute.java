package framework.excute;

import junit.framework.Assert;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.Devices_ResId_ClearButton;
import framework.driver.AndroidObject;
import framework.driver.AndroidOperation;

public class Excute 
{
	public static void Wait(int ms) 
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void ClearBackgroundApp() throws UiObjectNotFoundException, RemoteException
	{
		System.out.println("Clear Background");
		UiDevice.getInstance().pressRecentApps();
		Wait(4000);
		if ((Boolean) excute(Object_ResourceId, Operation_Exists,Devices_ResId_ClearButton)) {
			excute(Object_ResourceId, Operation_ClickWait,Devices_ResId_ClearButton);
		}else {
			while ((Boolean) excute(Object_ResourceId, Operation_Exists,"com.android.systemui:id/task_view_thumbnail")) {
				excute(Object_ResourceId, Operation_ClickWait,"com.android.systemui:id/dismiss_task");
			}
		}
	}
	
	public static Object check(int objectType, int operation, String... args)
	{	
		return excute(objectType, operation, args);
	}
	
	public static Object excute(int objectType, int operation, String... args)
	{		
		Object uiObject = AndroidObject.getObject(objectType, args);			
		checkObject(objectType, operation, uiObject);
		Object result = AndroidOperation.doOperation(operation, uiObject, args);		

		return result;
	}
	
	private static void checkObject(int objectType, int operation, Object uiObject)
	{
		if (isNeedCheckObject(objectType, operation)) 
		{			
			Assert.assertTrue("Not find the object with object type: " + objectType + ", opration type: " + operation, ((UiObject) uiObject).exists());
		}
	}
	
	private static boolean isNeedCheckObject(int objectType, int operation)
	{
		boolean objectStatus = objectType > Object_Checkable_InitValue
						           && objectType < Object_UnCheckable_InitValue;
		boolean deviceOprationStatus = operation > DeviceOperate_Checkable_InitValue
						                   && operation < DeviceOperate_UnCheckable_InitValue;
		boolean objectOprationStatus = operation > ObjectOperate_Checkable_InitValue
						                   && operation < ObjectOperate_UnCheckable_InitValue;
		boolean scrollOprationStatus = operation > ScrollOperate_Checkable_InitValue
						                   && operation < ScrollOperate_UnCheckable_InitValue;
		boolean checkOprationStatus = operation > CheckOperate_Checkable_InitValue
						                  && operation < CheckOperate_UnCheckable_InitValue;
		
		if (  (objectStatus && deviceOprationStatus)
			|| (objectStatus && objectOprationStatus)
			|| (objectStatus && scrollOprationStatus)
			|| (objectStatus && checkOprationStatus)  )
		{
			return true;
		}
		return false;
	}
}
