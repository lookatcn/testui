package framework.common;

import static framework.data.ResIdTextAndDesc.*;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class NoteCommon {
	
//	public static void createFolder(String name) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: createFolder======");
//		
//		new FindAppByDescAndClick("更多选项").action();
//		new FindAppByTextAndClick("新建文件夹").action();	
//		new FindAppByResIdAndSetText(Note_ResId_New_Folder, name).action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//
//	public static void deleteAll() throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteAll======");
//		new FindAppByDescAndClick("更多选项").action();
//		new FindAppByTextAndClick("删除全部").action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//	
//	public static void deleteNote(String name) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteNote======");
//		new FindAppByTextAndLongClick(name).action();
//		new FindAppByTextAndClick("删除").action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//
//	public static void deleteMoreWord(int wordNum) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteMoreWord======");
//		
//		for(int i = 0; i < wordNum; i++)
//		{
//			new PressDelete().action();
//		}
//	}
//	
//	public static void saveNote(String noteName) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: saveNote======");
//		new FindAppByDescAndClick("更多选项").action();
//		if(!noteName.equals("None"))
//		{
//			new FindAppByTextAndClick("编辑标题").action();
//			new FindAppByResIdAndSetText(Note_ResId_Edit_Title, noteName).action();
//			new FindAppByTextAndClick("确定").action();
//			new FindAppByDescAndClick("更多选项").action();
//		}
//		new FindAppByTextAndClick("保存").action();
//		new Wait(2000).action();
//	}
	
}
