package framework.data;

public class OperationType 
{
	public static final int Operate_ReturnObject = 10000;
	
	public static final int DeviceOperate_InitValue = 20000;
	public static final int DeviceOperate_Checkable_InitValue = DeviceOperate_InitValue;
	public static final int DeviceOperate_UnCheckable_InitValue = DeviceOperate_InitValue + 5000;
	
	public static final int ObjectOperate_InitValue = 30000;
	public static final int ObjectOperate_Checkable_InitValue = ObjectOperate_InitValue;
	public static final int ObjectOperate_UnCheckable_InitValue = ObjectOperate_InitValue + 5000;	
	
	public static final int ScrollOperate_InitValue = 40000;
	public static final int ScrollOperate_Checkable_InitValue = ScrollOperate_InitValue;
	public static final int ScrollOperate_UnCheckable_InitValue = ScrollOperate_InitValue + 5000;	

	public static final int CheckOperate_InitValue = 90000;
	public static final int CheckOperate_Checkable_InitValue = CheckOperate_InitValue;
	public static final int CheckOperate_UnCheckable_InitValue = CheckOperate_InitValue + 5000;	
	
	//UiDevice method
	public static final int Operation_DeviceClick = DeviceOperate_UnCheckable_InitValue + 1; //int x, int y
	
	public static final int Operation_DiviceDrag = DeviceOperate_Checkable_InitValue + 1; //int startX, int startY, int endX, int endY, int steps
	public static final int Operation_IsScreenOn = DeviceOperate_Checkable_InitValue + 2;
	public static final int Operation_PressBack = DeviceOperate_Checkable_InitValue + 3;
	public static final int Operation_PressDelete = DeviceOperate_Checkable_InitValue + 4;
	public static final int Operation_PressEnter = DeviceOperate_Checkable_InitValue + 5;
	public static final int Operation_PressHome = DeviceOperate_Checkable_InitValue + 6;
	public static final int Operation_PressKeyCode = DeviceOperate_Checkable_InitValue + 7; //int keyCode
	public static final int Operation_PressMenu = DeviceOperate_Checkable_InitValue + 8;
	public static final int Operation_PressRecentApps = DeviceOperate_Checkable_InitValue + 9;
	public static final int Operation_Sleep = DeviceOperate_Checkable_InitValue + 10;
	public static final int Operation_WakeUp = DeviceOperate_Checkable_InitValue + 11;
	public static final int Operation_UnLock = DeviceOperate_Checkable_InitValue + 12;
	public static final int Operation_ClearBackGround = DeviceOperate_Checkable_InitValue + 13;
	public static final int Operation_Swipe = DeviceOperate_Checkable_InitValue + 14;
	public static final int Operation_takeScreenshot = DeviceOperate_Checkable_InitValue + 15;
	
	//UiObject method
	public static final int Operation_ClickWait = ObjectOperate_Checkable_InitValue + 1;
	public static final int Operation_DragToObject = ObjectOperate_Checkable_InitValue + 2; //UiObject destObj, int steps
	public static final int Operation_DragToCoordinate = ObjectOperate_Checkable_InitValue + 3; //int destX, int destY, int steps
	public static final int Operation_LongClick = ObjectOperate_Checkable_InitValue + 4;	
	public static final int Operation_PinchIn = ObjectOperate_Checkable_InitValue + 5; //int percent, int steps
	public static final int Operation_PinchOut = ObjectOperate_Checkable_InitValue + 6; //int percent, int steps
	public static final int Operation_SetText = ObjectOperate_Checkable_InitValue + 7; //int text
	public static final int Operation_SwipeDown = ObjectOperate_Checkable_InitValue + 8; //int steps
	public static final int Operation_SwipeLeft = ObjectOperate_Checkable_InitValue + 9; //int steps
	public static final int Operation_SwipeRight = ObjectOperate_Checkable_InitValue + 10; //int steps
	public static final int Operation_SwipeUp = ObjectOperate_Checkable_InitValue + 11; //int steps		
	public static final int Operation_IsCheckable = ObjectOperate_Checkable_InitValue + 12;
	public static final int Operation_IsChecked = ObjectOperate_Checkable_InitValue + 13;
	public static final int Operation_IsClickable = ObjectOperate_Checkable_InitValue + 14;
	public static final int Operation_IsEnabled = ObjectOperate_Checkable_InitValue + 15;	
	public static final int Operation_IsFocusable = ObjectOperate_Checkable_InitValue + 16;
	public static final int Operation_IsFocused = ObjectOperate_Checkable_InitValue + 17;
	public static final int Operation_IsLongClickable = ObjectOperate_Checkable_InitValue + 18;
	public static final int Operation_IsScrollable = ObjectOperate_Checkable_InitValue + 19;	
	public static final int Operation_IsSelected = ObjectOperate_Checkable_InitValue + 20;	
	public static final int Operation_GetBounds = ObjectOperate_Checkable_InitValue + 21;
	public static final int Operation_GetText = ObjectOperate_Checkable_InitValue + 22;	
	public static final int Operation_GetDesc = ObjectOperate_Checkable_InitValue + 23;
	
	//以下未实现
	public static final int Operation_ClearTextField = ObjectOperate_Checkable_InitValue + 24;
	public static final int Operation_Click = ObjectOperate_Checkable_InitValue + 25;
	public static final int Operation_ClickWaitWithTime = ObjectOperate_Checkable_InitValue + 26; //long timeout	
	public static final int Operation_ClickBottomRight = ObjectOperate_Checkable_InitValue + 27;
	public static final int Operation_ClickTopLeft = ObjectOperate_Checkable_InitValue + 28;		
	public static final int Operation_LongClickBottomRight = ObjectOperate_Checkable_InitValue + 29;
	public static final int Operation_LongClickTopLeft = ObjectOperate_Checkable_InitValue + 30;
	public static final int Operation_PerformMultiPointerGesture = ObjectOperate_Checkable_InitValue + 31; //PointerCoords... touches
	public static final int Operation_PerformTwoPointerGesture = ObjectOperate_Checkable_InitValue + 32; //Point startPoint1, Point startPoint2, Point endPoint1, Point endPoint2, int steps
		
	public static final int Operation_GetChild = ObjectOperate_Checkable_InitValue + 35; //UiSelector selector	
	public static final int Operation_GetChildCount = ObjectOperate_Checkable_InitValue + 36;
	public static final int Operation_GetClassName = ObjectOperate_Checkable_InitValue + 37;
	public static final int Operation_GetContentDescription = ObjectOperate_Checkable_InitValue + 38;
	public static final int Operation_GetFromParent = ObjectOperate_Checkable_InitValue + 39; //UiSelector selector
	public static final int Operation_GetPackageName = ObjectOperate_Checkable_InitValue + 40;
	public static final int Operation_GetSelector = ObjectOperate_Checkable_InitValue + 41;
	public static final int Operation_GetVisibleBounds = ObjectOperate_Checkable_InitValue + 42;
	
	//以上未实现
	
	public static final int Operation_WaitForExists = ObjectOperate_Checkable_InitValue + 33; //long timeout
	public static final int Operation_WaitUntilGone = ObjectOperate_Checkable_InitValue + 34; //long timeout
	//public static final int Operation_IsExists = ObjectOperate_Checkable_InitValue + 43;
	
	public static final int Operation_Exists = ObjectOperate_UnCheckable_InitValue + 1;
	
	
	//Scroll
	public static final int Operation_ScrollIntoText = ScrollOperate_Checkable_InitValue + 1;
	public static final int Operation_ScrollIntoView = ScrollOperate_Checkable_InitValue + 2;
	
	
	
	//Check method	
	public static final int Operation_CheckedTrue = CheckOperate_Checkable_InitValue + 1; 
	public static final int Operation_CheckedFalse = CheckOperate_Checkable_InitValue + 2; 
	
	public static final int Operation_EnabledTrue = CheckOperate_Checkable_InitValue + 3; 
	public static final int Operation_EnabledFalse = CheckOperate_Checkable_InitValue + 4; 
	
	public static final int Operation_SelectedTrue = CheckOperate_Checkable_InitValue + 5; 
	public static final int Operation_SelectedFalse = CheckOperate_Checkable_InitValue + 6; 
	
	public static final int Operation_TextContainsTrue = CheckOperate_Checkable_InitValue + 7; 
	public static final int Operation_TextContainsFalse = CheckOperate_Checkable_InitValue + 8; 
	
	public static final int Operation_TextEqualTrue = CheckOperate_Checkable_InitValue + 9; 
	public static final int Operation_TextEqualFalse = CheckOperate_Checkable_InitValue + 10; 
	
	public static final int Operation_DescEqualTrue = CheckOperate_Checkable_InitValue + 11; 
	public static final int Operation_DescEqualFalse = CheckOperate_Checkable_InitValue + 12; 
	
	
	
	public static final int Operation_checkExist = CheckOperate_UnCheckable_InitValue + 1; 
	public static final int Operation_checkNoExist = CheckOperate_UnCheckable_InitValue + 2;
	
	
	
	
}
