package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class CallLogCommon {
	
	public static void deleteAllLog(String place) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteAllLog======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"确定");
		
		if((Boolean) excute(Object_Text,Operation_Exists,"查看"))
		{
			excute(Object_Device, Operation_PressBack);	
		}	
	}
	
	public static void deleteLog(String place, String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteLog======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");		
		DeviceCommon.selectMore(name);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Device, Operation_PressBack);
	}
	
	public static void deleteLogWithCancel(String place, String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteLogWithCancel======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");		
		DeviceCommon.selectMore(name);
		excute(Object_Device, Operation_PressBack);
	}

	public static void checkCallLog(String type) throws UiObjectNotFoundException{
		DeviceCommon.enterApp(Devices_Desc_CallLog);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,type);
	}
}
