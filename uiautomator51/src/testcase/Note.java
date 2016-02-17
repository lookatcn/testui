package testcase;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import framework.common.DeviceCommon;

public class Note extends UiAutomatorTestCase
{
	
	protected void setUp() 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
	}
	
	protected void tearDown() 
    {
		System.out.println("Enter the tearDown!!!");		
		DeviceCommon.clearBackGround();
    }
	public void test_001() 
	{		
		DeviceCommon.enterApp(Devices_Desc_Note);
		excute(Object_Description, Operation_ClickWait, "新建便签");
		check(Object_Text, Operation_checkExist, "0/1000");
		
		excute(Object_ResourceId, Operation_SetText, Note_ResId_Edit_Content, "new");
		check(Object_Text, Operation_checkExist, "3/1000");
		
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressHome);
		
		
		
	}
	
	
	
//	
//	//无法输入中文
//	public void test_001() throws RemoteException, UiObjectNotFoundException
//	{		
//		DeviceCommon.enterApp(Devices_Desc_Note);
//		new FindAppByDescAndClick("新建便签").action();	
//		new CheckAppIsExistByText("0/1000", true).action();
//		new FindAppByResIdAndSetText(Note_ResId_Edit_Content,"new").action();
//		new CheckAppIsExistByText("3/1000", true).action();
////		new FindAppByResIdAndSetText(Note_ResId_Edit_Content,"你好").action();
////		new CheckAppIsExistByText("2/1000", true).action();
//		
//		
//		new PressBack().action();
//		new PressBack().action();
//		
//		NoteCommon.deleteNote("new");		
//		
//		new CheckAppIsExistByText("new", false).action();
//	}
	
	
	
	
}







