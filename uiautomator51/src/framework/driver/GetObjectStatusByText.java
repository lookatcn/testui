package framework.driver;


import static framework.data.ObjectType.Object_Scrollable;
import static framework.data.OperationType.Operate_ReturnObject;
import static framework.excute.Excute.excute;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class GetObjectStatusByText
{
	String className;
	String text;
	
	public GetObjectStatusByText(String findText)
	{
		className = "GetObjectStatusByText";
		text = findText;		
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public void action() 
	{
		
	}
	
	public boolean getObjectStatus(String status) throws UiObjectNotFoundException 
	{
		if(status.equals("isExist"))
		{
			return (Boolean) excute(Object_Text,Operation_Exists,text);
		}
		
		else if(status.equals("isChecked"))
		{
			return (Boolean) excute(Object_Text,Operation_IsChecked,text);
		}
		
		else if(status.equals("isEnabled"))
		{
			return (Boolean) excute(Object_Text,Operation_IsEnabled,text);
		}
		else if(status.equals("isSelected"))
		{
			return (Boolean) excute(Object_Text,Operation_IsSelected,text);
		}
		
		return true;
	}

}
