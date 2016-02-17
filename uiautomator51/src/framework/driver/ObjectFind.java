package framework.driver;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

import framework.data.DeviceParameter;

public class ObjectFind
{
	//只供类内调用的私有方法
	private static UiScrollable getAppList(String direction)
	{
		UiScrollable applist = new UiScrollable(new UiSelector().scrollable(true));
		setScrollDirection(direction, applist);
		return applist;
	}
	
	private static void setScrollDirection(String direction, UiScrollable applist)
	{
		if(direction == DeviceParameter.Scroll_Horizontal)
			applist.setAsHorizontalList();
	}
	
	
	//返回设备对象
	public static UiDevice device() 
	{
		return UiDevice.getInstance();
	}
	
	//在当前页面直接找到控件返回
	public static UiObject byText(String text) 
	{
		return new UiObject(new UiSelector().text(text));
	}
	
	public static UiObject byDesciption(String desc)
	{
		return new UiObject(new UiSelector().description(desc));
	}
	
	public static UiObject byResourceId(String resId)
	{
		return new UiObject(new UiSelector().resourceId(resId));
	}
	
	public static UiObject byClassName(String className) 
	{
		return new UiObject(new UiSelector().className(className));
	}
	
	
	//通过垂直或水平滑动页面，找到控件返回
	public static UiObject byTextScroll(String text, String direction) throws UiObjectNotFoundException  
	{
		UiScrollable applist = getAppList(direction);
		return applist.getChildByText(new UiSelector().text(text), text, true); 
	}

	public static UiObject byDescScroll(String desc, String direction) throws UiObjectNotFoundException  
	{
		UiScrollable applist = getAppList(direction);
		return applist.getChildByDescription(new UiSelector().description(desc), desc, true); 
	}
	
	//ResId为Scroll控件的
	public static UiObject byTextScrollWithResId(String resId, String text, String direction) throws UiObjectNotFoundException
	{
		UiScrollable applist = new UiScrollable(new UiSelector().resourceId(resId).scrollable(true));
		setScrollDirection(direction, applist);
		return applist.getChildByText(new UiSelector().text(text), text, true);
	}
	
	//ResId为Scroll控件的
	public static UiObject byDescScrollWithResId(String resId, String desc, String direction) throws UiObjectNotFoundException  
	{
		UiScrollable applist = new UiScrollable(new UiSelector().resourceId(resId).scrollable(true));
		setScrollDirection(direction, applist);
		return applist.getChildByDescription(new UiSelector().description(desc), desc, true); 
	}
	
	
	
	//通过resourceId + 属性，找到控件返回
	public static UiObject byResIdText(String resId, String text)
	{
		return new UiObject(new UiSelector().resourceId(resId).text(text));
	}
	
	public static UiObject byResIdDesc(String resId, String desc){
		return new UiObject(new UiSelector().resourceId(resId).description(desc));
	}
	
	public static UiObject byResIdIndex(String resId, String index) 
	{
		return new UiObject(new UiSelector().resourceId(resId).index(Integer.parseInt(index)));
	}
			
	public static UiObject byResIdInstance(String resId, String instanceNum) {
		return new UiObject(new UiSelector().resourceId(resId).instance(Integer.parseInt(instanceNum)));
	}
	
	public static UiObject byResIdContainsText(String resId, String textContain){
		return new UiObject(new UiSelector().resourceId(resId).textContains(textContain));
	}
		
	
	
	//通过text + 属性，找到控件返回
	public static UiObject byTextInstance(String text, String instanceNum) 
	{
		return new UiObject(new UiSelector().text(text).instance(Integer.parseInt(instanceNum)));
	}
		
	
	
	//通过className + 属性，找到控件返回
	public static UiObject byClassInstance(String className, String instanceNum) 
	{
		return new UiObject(new UiSelector().className(className).instance(Integer.parseInt(instanceNum)));
	}
	
	public static UiObject byClassResId(String className, String resId) 
	{
		return new UiObject(new UiSelector().className(className).resourceId(resId));
	}
	
	public static UiObject byClassIndex(String className, String index) 
	{
		return new UiObject(new UiSelector().className(className).index(Integer.parseInt(index)));
	}
	
	public static UiObject byClassDesc(String className, String desc) 
	{
		return new UiObject(new UiSelector().className(className).description(desc));
	}

	public static UiObject byClassText(String className, String text) 
	{
		return new UiObject(new UiSelector().className(className).text(text));
	}
	
	public static UiObject byClassContainsText(String className, String textContain) 
	{
		return new UiObject(new UiSelector().className(className).textContains(textContain));
	}
	
		
	public static UiScrollable byScroll(String direction) throws UiObjectNotFoundException  
	{
		return getAppList(direction);
	}
	
	public static UiScrollable byResIdScroll(String resId, String direction) throws UiObjectNotFoundException
	{
		UiScrollable applist = new UiScrollable(new UiSelector().resourceId(resId).scrollable(true));
		setScrollDirection(direction, applist);
		return applist;
	}

	public static UiObject Object_waitForExists(String ID) {
		return new UiObject(new UiSelector().resourceId(ID));
	}
		
	
	
	
	
	
	
}