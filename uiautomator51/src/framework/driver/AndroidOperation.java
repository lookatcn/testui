package framework.driver;

import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;

import framework.data.ObjectType;
import framework.data.OperationType;

public class AndroidOperation 
{
	public static Object doOperation(int operation, Object androidObject, String... args)
	{
		int index = ObjectType.paraIndex;
		
		try
		{
			switch(operation)
			{
				
				//return object with no operation
				case OperationType.Operate_ReturnObject:
					return androidObject;
						
				
				//UiDevice method
				case OperationType.Operation_DeviceClick:
					return OperationUiDevice.deviceClick((UiDevice)androidObject, args[index], args[index + 1]);
				
				case OperationType.Operation_DiviceDrag:
					return OperationUiDevice.deviceDrag((UiDevice)androidObject, args[index], args[index + 1], args[index + 2], args[index + 3], args[index + 4]);
				
				case OperationType.Operation_IsScreenOn:
					return OperationUiDevice.isScreenOn((UiDevice)androidObject);
				
				case OperationType.Operation_PressBack:
					return OperationUiDevice.pressBack((UiDevice)androidObject);
				
				case OperationType.Operation_PressDelete:
					return OperationUiDevice.pressDelete((UiDevice)androidObject);
				
				case OperationType.Operation_PressEnter:
					return OperationUiDevice.pressEnter((UiDevice)androidObject);
				
				case OperationType.Operation_PressHome:
					return OperationUiDevice.pressHome((UiDevice)androidObject);
				
				case OperationType.Operation_PressKeyCode:
					return OperationUiDevice.pressKeyCode((UiDevice)androidObject, args[index]);
				
				case OperationType.Operation_PressMenu:
					return OperationUiDevice.pressMenu((UiDevice)androidObject);
				
				case OperationType.Operation_PressRecentApps:
					return OperationUiDevice.pressRecentApps((UiDevice)androidObject);
				
				case OperationType.Operation_Sleep:
					return OperationUiDevice.sleep((UiDevice)androidObject);
				
				case OperationType.Operation_WakeUp:
					return OperationUiDevice.wakeUp((UiDevice)androidObject);
				
				case OperationType.Operation_Swipe:
					return OperationUiDevice.swipe((UiDevice)androidObject, args[index], args[index + 1], args[index + 2], args[index + 3], args[index + 4]);
					

					
				
				//UiObject method
				case OperationType.Operation_ClickWait:
					return OperationUiObject.clickWait((UiObject)androidObject);
				
				///////public static final String Operation_DragToObject = "Operation_DragToObject"; //UiObject destObj, int steps
				
				case OperationType.Operation_DragToCoordinate:
					return OperationUiObject.dragToCoordinate((UiObject)androidObject, args[index], args[index + 1], args[index + 2]);
				
				case OperationType.Operation_LongClick:
					return OperationUiObject.longClick((UiObject)androidObject);
				
				case OperationType.Operation_PinchIn:
					return OperationUiObject.pinchIn((UiObject)androidObject, args[index], args[index + 1]);
				
				case OperationType.Operation_PinchOut:
					return OperationUiObject.pinchOut((UiObject)androidObject, args[index], args[index + 1]);
				
				case OperationType.Operation_SetText:
					return OperationUiObject.setText((UiObject)androidObject, args[index]);
				
				case OperationType.Operation_SwipeUp:
					return OperationUiObject.swipeUp((UiObject)androidObject, args[index]);
				
				case OperationType.Operation_SwipeDown:
					return OperationUiObject.swipeDown((UiObject)androidObject, args[index]);
				
				case OperationType.Operation_SwipeLeft:
					return OperationUiObject.swipeLeft((UiObject)androidObject, args[index]);
				
				case OperationType.Operation_SwipeRight:
					return OperationUiObject.swipeRight((UiObject)androidObject, args[index]);
				
				case OperationType.Operation_IsCheckable:
					return OperationUiObject.isCheckable((UiObject)androidObject);
				
				case OperationType.Operation_IsChecked:
					return OperationUiObject.isChecked((UiObject)androidObject);
				
				case OperationType.Operation_IsClickable:
					return OperationUiObject.isClickable((UiObject)androidObject);
				
				case OperationType.Operation_IsEnabled:
					return OperationUiObject.isEnabled((UiObject)androidObject);
				
				case OperationType.Operation_IsFocusable:
					return OperationUiObject.isFocusable((UiObject)androidObject);
				
				case OperationType.Operation_IsFocused:
					return OperationUiObject.isFocused((UiObject)androidObject);
				
				case OperationType.Operation_IsLongClickable:
					return OperationUiObject.isLongClickable((UiObject)androidObject);
				
				case OperationType.Operation_IsScrollable:
					return OperationUiObject.isScrollable((UiObject)androidObject);
				
				case OperationType.Operation_IsSelected:
					return OperationUiObject.isSelected((UiObject)androidObject);
				
				case OperationType.Operation_GetBounds:
					return OperationUiObject.getBounds((UiObject)androidObject);
				
				case OperationType.Operation_GetText:
					return OperationUiObject.getText((UiObject)androidObject);
					
				case OperationType.Operation_GetDesc:
					return OperationUiObject.getContentDescription((UiObject)androidObject);
					
					
					
				case OperationType.Operation_Exists:
					return OperationUiObject.exists((UiObject)androidObject);
					
				 case OperationType.Operation_WaitForExists:
	                    return OperationUiObject.WaitForExists((UiObject)androidObject, args[index]);
	                    
	             case OperationType.Operation_WaitUntilGone:
	                    return OperationUiObject.WaitUntilGone((UiObject)androidObject, args[index]);	
	
				//Scrollable method
				case OperationType.Operation_ScrollIntoText:
					return OperationScroll.scrollIntoText((UiScrollable) androidObject, args[index]);
			
					
					
				//Check method					
				case OperationType.Operation_CheckedTrue:
					return OperationCheck.checkedTrue((UiObject)androidObject);
					
				case OperationType.Operation_CheckedFalse:
					return OperationCheck.checkedFalse((UiObject)androidObject);
					
				case OperationType.Operation_EnabledTrue:
					return OperationCheck.enabledTrue((UiObject)androidObject);
					
				case OperationType.Operation_EnabledFalse:
					return OperationCheck.enabledFalse((UiObject)androidObject);
					
				case OperationType.Operation_SelectedTrue:
					return OperationCheck.selectedTrue((UiObject)androidObject);
					
				case OperationType.Operation_SelectedFalse:
					return OperationCheck.selectedFalse((UiObject)androidObject);
					
				case OperationType.Operation_TextContainsTrue:
					return OperationCheck.textContainsTrue((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_TextContainsFalse:
					return OperationCheck.textContainsFalse((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_TextEqualTrue:
					return OperationCheck.textEqualTrue((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_TextEqualFalse:
					return OperationCheck.textEqualFalse((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_DescEqualTrue:
					return OperationCheck.descEqualTrue((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_DescEqualFalse:
					return OperationCheck.descEqualFalse((UiObject)androidObject, args[index]);
					
				case OperationType.Operation_checkExist:
					return OperationCheck.checkObjectExist((UiObject)androidObject);
					
				case OperationType.Operation_checkNoExist:
					return OperationCheck.checkObjectNoExist((UiObject)androidObject);
					
				 case OperationType.Operation_GetChildCount:
		              return OperationUiObject.GetChildCount((UiObject)androidObject);
				
				
				
					
				default:
					Assert.fail("AndroidOperation--未定义的操作类型：" + Integer.toString(operation));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return null;
			
	}
	
	
		

}
