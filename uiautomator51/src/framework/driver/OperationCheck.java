package framework.driver;

import junit.framework.Assert;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class OperationCheck 
{
	
	
	public static boolean checkObjectExist(UiObject uiObject) throws NumberFormatException, UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object is not exist as expected", OperationUiObject.exists(uiObject));
		return true;
	}
		
	public static boolean checkObjectNoExist(UiObject uiObject) throws NumberFormatException, UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object should not exist as expected", !OperationUiObject.exists(uiObject));
		return true;
	}
	
	public static boolean checkedTrue(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object is not checked as expected", OperationUiObject.isChecked(uiObject));
		return true;
	}
		
	public static boolean checkedFalse(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object should not checked as expected", !OperationUiObject.isChecked(uiObject));
		return true;
	}
			
	public static boolean enabledTrue(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object is not enabled as expected", OperationUiObject.isEnabled(uiObject));
		return true;
	}
		
	public static boolean enabledFalse(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object should not enabled as expected", !OperationUiObject.isEnabled(uiObject));
		return true;
	}
	
	public static boolean selectedTrue(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object is not selected as expected", OperationUiObject.isSelected(uiObject));
		return true;
	}
		
	public static boolean selectedFalse(UiObject uiObject) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object should not selected as expected", !OperationUiObject.isSelected(uiObject));
		return true;
	}
	
	public static boolean textContainsTrue(UiObject uiObject, String text) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object does not contain text as expected", OperationUiObject.getText(uiObject).contains(text));
		return true;
	}
		
	public static boolean textContainsFalse(UiObject uiObject, String text) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: object should not contain text as expected", !OperationUiObject.getText(uiObject).contains(text));
		return true;
	}
	
	public static boolean textEqualTrue(UiObject uiObject, String text) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: text getting is not equal with text given", OperationUiObject.getText(uiObject).equals(text));
		return true;
	}
		
	public static boolean textEqualFalse(UiObject uiObject, String text) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: text getting should not equal with text given", !OperationUiObject.getText(uiObject).equals(text));
		return true;
	}
	
	public static boolean descEqualTrue(UiObject uiObject, String desc) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: desc getting is not equal with desc given", OperationUiObject.getContentDescription(uiObject).equals(desc));
		return true;
	}
		
	public static boolean descEqualFalse(UiObject uiObject, String desc) throws UiObjectNotFoundException
	{
		Assert.assertTrue("Fail: desc getting should not equal with desc given", !OperationUiObject.getContentDescription(uiObject).equals(desc));
		return true;
	}

	

		
			
			
}