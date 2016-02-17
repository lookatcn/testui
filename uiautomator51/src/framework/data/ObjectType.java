package framework.data;

import static framework.data.ObjectType.Object_Description;
import static framework.data.ObjectType.Object_ResIdText;
import static framework.data.ObjectType.Object_ResourceId;
import static framework.data.ObjectType.Object_Text;
import static framework.data.OperationType.Operation_ClickWait;
import static framework.data.OperationType.Operation_checkExist;
import static framework.data.OperationType.Operation_SetText;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;

public class ObjectType 
{
	
	public static int paraIndex = 0;
	
	public static void setParaIndex(int index)
	{
		paraIndex = index;
	}
	
	public static final int Object_InitValue = 10000;
	public static final int Object_Checkable_InitValue = Object_InitValue;
	public static final int Object_UnCheckable_InitValue = Object_InitValue + 5000;
	
	
	//Object_Checkable_InitValue
	public static final int Object_Text = Object_Checkable_InitValue + 2;
	public static final int Object_Description = Object_Checkable_InitValue + 3;
	public static final int Object_ResourceId = Object_Checkable_InitValue + 4;
	public static final int Object_ClassName = Object_Checkable_InitValue + 5;
	
	public static final int Object_TextScroll = Object_Checkable_InitValue + 6;
	public static final int Object_DescScroll = Object_Checkable_InitValue + 7;
	public static final int Object_TextScrollWithResId = Object_Checkable_InitValue + 8;//ResId为Scroll控件的
	public static final int Object_DescScrollWithResId = Object_Checkable_InitValue + 9;//ResId为Scroll控件的		
	
	public static final int Object_ResIdText = Object_Checkable_InitValue + 10;
	public static final int Object_ResIdDesc = Object_Checkable_InitValue + 11;
	public static final int Object_ResIdIndex = Object_Checkable_InitValue + 12;
	public static final int Object_ResIdInstance = Object_Checkable_InitValue + 13;
	public static final int Object_ResIdContainsText = Object_Checkable_InitValue + 14;
	
	public static final int Object_TextInstance = Object_Checkable_InitValue + 15;
	
	public static final int Object_ClassInstance = Object_Checkable_InitValue + 16;
	public static final int Object_ClassResId = Object_Checkable_InitValue + 17;
	public static final int Object_ClassIndex = Object_Checkable_InitValue + 18;
	public static final int Object_ClassDesc = Object_Checkable_InitValue + 19;
	public static final int Object_ClassText = Object_Checkable_InitValue + 20;
	public static final int Object_ClassContainsText = Object_Checkable_InitValue + 21;
	
	
	//Object_UnCheckable_InitValue
	public static final int Object_Device = Object_UnCheckable_InitValue + 1;
	public static final int Object_Scrollable = Object_UnCheckable_InitValue + 2;
	public static final int Object_ResIdScrollable = Object_UnCheckable_InitValue + 3;
	public static final int Object_waitForExists =Object_UnCheckable_InitValue+ 4;
	

}
