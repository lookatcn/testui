package framework.driver;


import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;

public class OperationScroll 
{
	//如果可以滑动找到控件，进入控件并返回true；否则返回false
	public static boolean scrollIntoText(UiScrollable uiScrollable, String text) throws UiObjectNotFoundException  
	{
		return uiScrollable.scrollTextIntoView(text);
	}	
	
	//如果可以滑动找到控件，进入控件并返回true；否则返回false
	public static boolean scrollIntoView(UiScrollable uiScrollable,UiObject uiObject) throws UiObjectNotFoundException  
	{
		return uiScrollable.scrollIntoView(uiObject);
	}	
}