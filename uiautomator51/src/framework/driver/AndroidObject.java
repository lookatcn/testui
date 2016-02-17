package framework.driver;

import junit.framework.Assert;
import framework.data.ObjectType;

public class AndroidObject 
{
	public static final int objectIndex = 0;
	
	public static Object getObject(int objectType, String... args)
	{
		try
		{
			switch(objectType)
			{
				case ObjectType.Object_Device:
					ObjectType.setParaIndex(objectIndex);
					return ObjectFind.device();
					
				case ObjectType.Object_Text:
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.byText(args[objectIndex]);
					
				case ObjectType.Object_Description:
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.byDesciption(args[objectIndex]);
				
				case ObjectType.Object_ResourceId:
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.byResourceId(args[objectIndex]);
				
				case ObjectType.Object_ClassName:
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.byClassName(args[objectIndex]);
				
				//通过垂直或水平滑动页面，找到控件返回
				case ObjectType.Object_TextScroll:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byTextScroll(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_DescScroll:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byDescScroll(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_TextScrollWithResId:
					ObjectType.setParaIndex(objectIndex + 3);
					return ObjectFind.byTextScrollWithResId(args[objectIndex], args[objectIndex + 1], args[objectIndex + 2]);
				
				case ObjectType.Object_DescScrollWithResId:
					ObjectType.setParaIndex(objectIndex + 3);
					return ObjectFind.byDescScrollWithResId(args[objectIndex], args[objectIndex + 1], args[objectIndex + 2]);
							
				//通过resourceId + 属性，找到控件返回
				case ObjectType.Object_ResIdText:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdText(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ResIdDesc:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdDesc(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ResIdIndex:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdIndex(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ResIdInstance:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdInstance(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ResIdContainsText:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdContainsText(args[objectIndex], args[objectIndex + 1]);
				
				//通过text + 属性，找到控件返回
				case ObjectType.Object_TextInstance:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byTextInstance(args[objectIndex], args[objectIndex + 1]);
				
				//通过className + 属性，找到控件返回
				case ObjectType.Object_ClassInstance:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassInstance(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ClassResId:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassResId(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ClassIndex:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassIndex(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ClassDesc:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassDesc(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ClassText:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassText(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_ClassContainsText:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byClassContainsText(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_Scrollable:
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.byScroll(args[objectIndex]);
					
				case ObjectType.Object_ResIdScrollable:
					ObjectType.setParaIndex(objectIndex + 2);
					return ObjectFind.byResIdScroll(args[objectIndex], args[objectIndex + 1]);
				
				case ObjectType.Object_waitForExists:
					
					ObjectType.setParaIndex(objectIndex + 1);
					return ObjectFind.Object_waitForExists(args[objectIndex]);
				
					
				default:
					
					Assert.fail("AndroidObject--未定义的设备查找类型：" + Integer.toString(objectType));
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
