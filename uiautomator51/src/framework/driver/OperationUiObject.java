package framework.driver;

import android.graphics.Rect;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class OperationUiObject 
{
	public static boolean clickWait(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.clickAndWaitForNewWindow();
	}
		
	public static boolean dragToObject(UiObject uiObject, UiObject destObject, String steps) throws NumberFormatException, UiObjectNotFoundException
	{
		return uiObject.dragTo(destObject, Integer.parseInt(steps));
	}
	
	public static boolean dragToCoordinate(UiObject uiObject, String destX, String destY, String steps) throws NumberFormatException, UiObjectNotFoundException
	{
		return uiObject.dragTo(Integer.parseInt(destX), Integer.parseInt(destY), Integer.parseInt(steps));
	}
	
	public static boolean exists(UiObject uiObject)
	{
		try{
			return uiObject.exists();
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public static boolean isCheckable(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isCheckable();
	}
	
	public static boolean isChecked(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isChecked();
	}
	
	public static boolean isClickable(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isClickable();
	}
	
	public static boolean isEnabled(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isEnabled();
	}
	
	public static boolean isFocusable(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isFocusable();
	}
	
	public static boolean isFocused(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isFocused();
	}
	
	public static boolean isLongClickable(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isLongClickable();
	}
	
	public static boolean isScrollable(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isScrollable();
	}
	
	public static boolean isSelected(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.isSelected();
	}
	
	public static boolean longClick(UiObject uiObject) throws UiObjectNotFoundException
	{
		int one_x = uiObject.getBounds().centerX();
		int one_y = uiObject.getBounds().centerY();
		
		return UiDevice.getInstance().drag(one_x, one_y, one_x, one_y, 10);
	}
	
	public static boolean pinchIn(UiObject uiObject, String percent, String steps) throws UiObjectNotFoundException
	{
		return uiObject.pinchIn(Integer.parseInt(percent), Integer.parseInt(steps));
	}
	
	public static boolean pinchOut(UiObject uiObject, String percent, String steps) throws UiObjectNotFoundException
	{
		return uiObject.pinchOut(Integer.parseInt(percent), Integer.parseInt(steps));
	}
	
	public static boolean setText(UiObject uiObject, String text) throws UiObjectNotFoundException
	{
		return uiObject.setText(text);
	}
	
	public static boolean swipeDown(UiObject uiObject, String steps) throws UiObjectNotFoundException
	{
		return uiObject.swipeDown(Integer.parseInt(steps));
	}
	
	public static boolean swipeLeft(UiObject uiObject, String steps) throws UiObjectNotFoundException
	{
		return uiObject.swipeLeft(Integer.parseInt(steps));
	}
	
	public static boolean swipeRight(UiObject uiObject, String steps) throws UiObjectNotFoundException
	{
		return uiObject.swipeRight(Integer.parseInt(steps));
	}
	
	public static boolean swipeUp(UiObject uiObject, String steps) throws UiObjectNotFoundException
	{
		return uiObject.swipeUp(Integer.parseInt(steps));
	}
	
	
	
	public static Rect getBounds(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.getBounds();
	}
	
	public static String getText(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.getText();
	}
	
	public static String getContentDescription(UiObject uiObject) throws UiObjectNotFoundException
	{
		return uiObject.getContentDescription();
	}
	
	public static boolean WaitForExists(UiObject uiObject, String timeout) throws UiObjectNotFoundException
	{
		return uiObject.waitForExists(Long.parseLong(timeout));
	}
	
	public static boolean WaitUntilGone(UiObject uiObject, String timeout) throws UiObjectNotFoundException
	{
		return uiObject.waitUntilGone(Long.parseLong(timeout));
	}
	
	 public static int GetChildCount(UiObject uiObject) throws UiObjectNotFoundException
	    {
	        return uiObject.getChildCount();
	    }
			
}