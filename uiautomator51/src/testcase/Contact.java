package testcase;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.CallLogCommon;
import framework.common.SettingCommon;
import framework.common.EmailCommon;
import framework.common.CallCommon;
import framework.driver.ObjectFind;
//插两张SIM卡，SD卡及手机内存中要有预存联系人
public class Contact extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	//001信息、email接口，收藏功能均有包含
	
	public void test_002() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		ContactCommon.modifyContact("姓名", "all23%",Devices_Text_Operator_Cancel);
		ContactCommon.checkNameAndTel(PhoneName, TelNumber);
		ContactCommon.modifyContact("姓名", "all23%",Devices_Text_Operator_Determine);
		ContactCommon.checkNameAndTel("all23%", TelNumber);
		//ContactCommon.modifyContact("电话", "9876543210");
	}
	
	//收藏功能已实现，现补充黑名单功能
	//保证有SIM卡
	//没有黑名单
//	public void test_004() throws UiObjectNotFoundException, RemoteException 
//	{
//		ContactCommon.BatchDelete("所有联系人");
//		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
//		excute(Object_Device, Operation_PressBack);
//		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
//		excute(Object_Device, Operation_PressBack);
//		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", SIM1Name); 
//		excute(Object_Description,Operation_ClickWait,"更多选项");
//		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "添加至黑名单"); 
//		Wait(1000);
//		excute(Object_Description,Operation_ClickWait,"更多选项");
//		check(Object_Text, Operation_checkExist,"从黑名单中删除");
//	}
	//姓名、电话、邮箱
	public void test_005() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.selectContactPosition("本机");
		excute(Object_Text,Operation_SetText,"姓名",PhoneName);
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text,Operation_SetText,"电话",TelNumber);
		excute(Object_TextScroll,Operation_SetText,"电子邮件","vertical","888888888@qq.com");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "放在主屏幕上");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		
		DeviceCommon.lookForWidgetByObject((UiObject) excute(Object_Text, Operate_ReturnObject,PhoneName),"Click");
		ContactCommon.checkNameAndTel(PhoneName,TelNumber);
		check(Object_Text, Operation_checkExist,"888888888@qq.com");
		
		ContactCommon.modifyContact("姓名", "modify");
		ContactCommon.modifyContact("电话", "1111");
		ContactCommon.modifyContact("电子邮件", "1111@qq.com");
		ContactCommon.checkNameAndTel("modify","1111");
		check(Object_Text, Operation_checkExist,"1111@qq.com");
		
		DeviceCommon.lookForWidgetByObject((UiObject) excute(Object_Text, Operate_ReturnObject,PhoneName),"Delete");
	}
	
	//013被108包含
	
	//仅保证发送 --!
	//验证邮箱接口
	public void test_006() throws UiObjectNotFoundException, RemoteException 
	{
		String emailName = "18202542932@139.com";
		String password = "12abAB";
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.selectContactPosition("本机");
		excute(Object_TextScroll,Operation_SetText,"姓名","vertical",PhoneName);
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",TelNumber);
		excute(Object_TextScroll,Operation_SetText,"电子邮件","vertical",emailName);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "放在主屏幕上");
		
		SettingCommon.addAccount(emailName,password);
		DeviceCommon.lookForWidgetByObject((UiObject) excute(Object_Text, Operate_ReturnObject,PhoneName),"Click");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/header", emailName);
		excute(Object_Text, Operation_SetText,"主题","Contact-email(006)");
		excute(Object_Text, Operation_SetText,"邮件内容","Test");
		excute(Object_ResourceId, Operation_ClickWait,"com.android.email:id/send");
		
		DeviceCommon.lookForWidgetByObject((UiObject) excute(Object_Text, Operate_ReturnObject,PhoneName),"Delete");
	}
	
	public void test_014() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		ContactCommon.deleteContact(PhoneName,Devices_Text_Operator_Cancel);
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text,Operation_Exists,PhoneName);
		ContactCommon.deleteContact(PhoneName,Devices_Text_Operator_Determine);
		excute(Object_Text,Operation_Exists,PhoneName);
	}
	
	public void test_021() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, "11111111112222222222333333333344444444445555555555" +
				"66666666667777777777888888888899999999990000000000");
		ContactCommon.checkNameAndTel(PhoneName, "11111111112222222222333333333344444444445555555555" +
				"66666666667777777777888888888899999999990000000000");
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("本机",PhoneName1, "123*2#7+");
		ContactCommon.checkNameAndTel(PhoneName1, "123*2#7+");
	}
	
	//1.将联系人加入快捷拨号，验证加入成功，然后将快捷拨号删除，验证删除成功；
	//2.从通讯录中找到最近通话记录，将联系人保存，然后加入快捷拨号，长按打通电话，并验证打通成功；
	public void test_022() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		CallCommon.addFastDail(PhoneName,5);
		CallCommon.deleteFastDail(5);
		CallCommon.makeCallByDailer(CMCCNum);
		Wait(5000);
		CallCommon.endCall();
		DeviceCommon.enterApp(Devices_Desc_CallLog);
		excute(Object_Text, Operation_ClickWait,"全部");
		excute(Object_ResourceId, Operation_SetText,"com.android.dialer:id/name",PhoneName);
		excute(Object_ResourceId, Operation_SetText,"com.android.dialer:id/details_action","详情");
		excute(Object_ResourceId, Operation_ClickWait,"com.android.dialer:id/add_contact_icon");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "创建新联系人");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text2", "本机");
		excute(Object_TextScroll,Operation_SetText,"姓名","vertical","CMCC");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		CallCommon.addFastDail("CMCC",5);
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.dialer:id/floating_action_button", "拨号键盘");
		excute(Object_Text,Operation_LongClick,"5");
		CallCommon.makeCall();
		Wait(5000);
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.dialer:id/name","CMCC");
		CallCommon.endCall();
	}
	
	//包括023
	public void test_024() throws UiObjectNotFoundException, RemoteException 
	{
		String name[] = {"abc","da!","","1*&","b#d"};
		String number[] = {"","789","134","256",""};
		String matcher[] = {"a","b","d","1","134"};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<name.length;i++)
		{
			ContactCommon.addNameAndTel("本机",name[i], number[i]);
			excute(Object_Device, Operation_PressBack);
		}
		for(int i=0;i<matcher.length;i++)
		{
			int result[] = ContactCommon.matchResult(name,number,matcher[i]);
			excute(Object_ResourceId, Operation_SetText,"com.android.contacts:id/menu_search",matcher[i]);
			for(Integer j=0;j<result.length;j++)
			{
				String text = name[result[j]].equals("") ? number[result[j]]:name[result[j]];
				check(Object_Text, Operation_checkExist,text);
			}
			ContactCommon.backContactHome();
		}
	}
	
	// 1.新建10个人到手机上；
	// 2.首先pb list中点击搜索，输入文本，然后将文本删除之后，就可以回到pb list首页，
	// 3.滑屏找到联系人10，然后进入联系人10的界面，返回到pb list后能够记录原来的位置；
	// 4.按Home键返回，然后重新进入到pb list之后能够记录原来的位置；
	public void test_025() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<10;i++)
		{
			ContactCommon.addNameAndTel("本机",PhoneName+i, TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		
		excute(Object_ResourceId, Operation_SetText,"com.android.contacts:id/menu_search",PhoneName+9);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/search_close_button","清除搜索内容");
		check(Object_Text, Operation_checkExist,PhoneName+0);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/search_back_button","停止搜索");
		excute(Object_TextScroll,Operation_ClickWait,PhoneName+9,"vertical");
		excute(Object_Device, Operation_PressBack);
		check(Object_Text, Operation_checkExist,PhoneName+9);
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		check(Object_TextScroll, Operation_checkExist,PhoneName+9,"vertical");
	}
	
	//包含27
	public void test_026() throws UiObjectNotFoundException, RemoteException 
	{
		String regex;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		String destn="导出到外部存储设备";
		regex = ContactCommon.exportContact(PhoneName,destn,Devices_Text_Operator_Cancel);
		ContactCommon.judgeExport(regex,destn,Devices_Text_Operator_Cancel);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		regex = ContactCommon.exportContact(PhoneName,destn,Devices_Text_Operator_Determine);
		ContactCommon.judgeExport(regex,destn,Devices_Text_Operator_Determine);
	}
	
	//被031包含
	//包含008、010
	public void test_028() throws UiObjectNotFoundException, RemoteException 
	{
		String destn;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		//Phone->SIM1
		destn="SIM1";
		ContactCommon.copyContactTo(PhoneName,destn);
		ContactCommon.judgeCopy(PhoneName,destn);
		//Phone->SIM2
		destn="SIM2";
		ContactCommon.copyContactTo(PhoneName,destn);
		ContactCommon.judgeCopy(PhoneName,destn);
	}
	
	//必须保证SD卡里预装有联系人
	public void test_029() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.importContact("one", "从外部存储设备导入");
		ContactCommon.judgeImport();
	}
	
	//必须保证SD卡里预装至少2个联系人
	public void test_030() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.importContact("one", "从外部存储设备导入",Devices_Text_Operator_Cancel);
		ContactCommon.judgeImport(Devices_Text_Operator_Cancel);
		ContactCommon.importContact("one", "从外部存储设备导入",Devices_Text_Operator_Determine);
		ContactCommon.judgeImport(Devices_Text_Operator_Determine);
	}
	
	//包含028
	public void test_031() throws UiObjectNotFoundException, RemoteException 
	{
		String destn;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		destn="SIM1";
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Cancel);
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Determine,Devices_Text_Operator_Cancel);
		ContactCommon.judgeCopy(PhoneName,destn,Devices_Text_Operator_Cancel);
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Determine,"完成");
		ContactCommon.judgeCopy(PhoneName,destn,Devices_Text_Operator_Determine);
		
		destn="SIM2";
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Cancel);
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Determine,Devices_Text_Operator_Cancel);
		ContactCommon.judgeCopy(PhoneName,destn,Devices_Text_Operator_Cancel);
		ContactCommon.copyContactTo(PhoneName,destn,Devices_Text_Operator_Determine,"完成");
		ContactCommon.judgeCopy(PhoneName,destn,Devices_Text_Operator_Determine);
	}
	
	//复制功能在031有本机到SIM1/2复制，因此补充SIM1/2到本机复制
	public void test_032() throws UiObjectNotFoundException, RemoteException 
	{
		String destn;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		destn = "本机";
		ContactCommon.copyContactTo(SIM1Name,destn);
		ContactCommon.judgeCopy(SIM1Name,destn);
		ContactCommon.copyContactTo(SIM2Name,destn);
		ContactCommon.judgeCopy(SIM2Name,destn);
	}
	
	//复制到账户（031/032）；导入（029、031）;导出（026）。
	//补充从内部导入、导出，导入需要手机内存中有联系人
	public void test_034() throws UiObjectNotFoundException, RemoteException 
	{
		String destn,regex;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		//Phone->内部
		destn="导出到内部存储设备";	
		regex = ContactCommon.exportContact(PhoneName,destn);
		ContactCommon.judgeExport(regex,destn);
		
		//内部->Phone
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.importContact("one", "从内部存储设备导入");
		ContactCommon.judgeImport();
	}
	
	//仅保证发送 --!
	//1.新建四个联系人，其中联系人1，2到SIM1上，联系人3，4到SIM2上；
	//2.将这四条记录通过SIM1,SIM2发送到10086上
	public void test_036() throws UiObjectNotFoundException, RemoteException 
	{
		String positon[] = {"SIM1","SIM2"}; 
		String name[] = {SIM1Name,SIM2Name};
		String number[] = {"12345","467899"};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<4;i++)
		{
			ContactCommon.addNameAndTel(positon[i/2],name[i/2], number[i/2]);
			excute(Object_Device, Operation_PressBack);
		}
		
		for(int i=0;i<2;i++)
		{
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "通过短信/彩信发送");
			excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button", "完成");
			
			excute(Object_ResourceId, Operation_SetText,Message_ResId_Receiver_Editor, CMCCNum);
			excute(Object_ResourceId, Operation_ClickWait,Message_ResId_Message_Send);
			if((Boolean) excute(Object_ResIdText,Operation_Exists,"android:id/alertTitle","请选择 SIM 卡"))
			{
				excute(Object_ClassInstance,Operation_ClickWait,Class_LinearLayout, Integer.toString(i));
			}
			excute(Object_Device, Operation_PressBack);
		}
	}
	
	//仅保证发送 --!(邮件)
	public void test_037() throws UiObjectNotFoundException, RemoteException 
	{
		String emailName = "18202542932@139.com";
		String password = "12abAB";
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		SettingCommon.addAccount(emailName,password);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", PhoneName);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "分享");
		if((Boolean) excute(Object_Text,Operation_Exists,"使用电子邮件分享"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button_once", "仅此一次");
		}
		else
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "电子邮件");
		}
		
		excute(Object_ResourceId, Operation_SetText,"com.android.email:id/to",emailName);
		excute(Object_Text, Operation_SetText,"主题","Contact-email(037)");
		excute(Object_Text, Operation_SetText,"邮件内容","Test");
		excute(Object_ResourceId, Operation_ClickWait,"com.android.email:id/send");
	}
	
	//仅保证发送 --!(彩信)
	public void test_039() throws UiObjectNotFoundException, RemoteException 
	{
		int SIMNum = 0;
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "分享");
		if((Boolean) excute(Object_ResIdText,Operation_Exists,"使用信息分享"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button_once", "仅此一次");
		}
		else
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "信息");
		}
		
		excute(Object_ResourceId, Operation_SetText,Message_ResId_Receiver_Editor, CMCCNum);
		excute(Object_ResourceId, Operation_ClickWait,Multimedia_ResId_Message_Send);
		if((Boolean) excute(Object_ResIdText,Operation_Exists,"android:id/alertTitle","请选择 SIM 卡"))
		{
			excute(Object_ClassInstance,Operation_ClickWait,Class_LinearLayout, Integer.toString(SIMNum));
		}
		Wait(20000);
	}
	
	public void test_041() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.selectContactPosition("本机");
		excute(Object_TextScroll,Operation_SetText,"姓名","vertical",PhoneName);
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",TelNumber);
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",TelNumber+0);
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",TelNumber+1);
		excute(Object_TextScroll,Operation_SetText,"电子邮件","vertical","888888888@qq.com");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		excute(Object_Device, Operation_PressBack);
		
		//Phone->SIM1
		String destn="SIM1";
		ContactCommon.copyContactTo(PhoneName,destn);
		ContactCommon.selectDisplayContact(destn);
		UiObject contactCount=(UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.contacts:id/contacts_count");
		int num = ContactCommon.getNum(contactCount.getText());
		Assert.assertTrue("Error: There aren't so many contacts in SIM1 !!!",num>1);
	}
	
	//保证有两张SIM卡
	public void test_042() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameAndTel("本机","", TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addGroup("family");
		String[] contactToGroup = {TelNumber};
		ContactCommon.addContactToGroup(contactToGroup);
		
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", TelNumber);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "复制");
		excute(Object_ResIdContainsText, Operation_ClickWait,"android:id/text2", "SIM1");
		//new FindByClassInstanceAndClick("android.widget.ImageView","0");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		Wait(1000);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.selectDisplayContact("SIM1");
		excute(Object_TextScroll,Operation_Exists,TelNumber,"vertical");
	}
	
	public void test_043() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		String SIMName[] = {SIM1Name,SIM2Name};
		for(int i=0;i<2;i++)
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", SIMName[i]); 
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "复制");
			excute(Object_ResIdContainsText, Operation_ClickWait,"android:id/text2", "本机");
			excute(Object_Device, Operation_PressBack);
			ContactCommon.selectDisplayContact("本机");
			excute(Object_TextScroll,Operation_Exists,SIMName[i],"vertical");
			ContactCommon.selectDisplayContact("所有联系人");
		}
	}
	
	public void test_044() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机","", CMCCNum);
		ContactCommon.checkNameAndTel(CMCCNum, CMCCNum);
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
		CallCommon.makeCall();
		Wait(2000);
		boolean flag = (Boolean) excute(Object_ResourceId,Operation_TextEqualTrue,"com.android.dialer:id/name",CMCCNum);
		Assert.assertTrue("Error: call name is not 10086!!!",flag);
		CallCommon.endCall();
	}
	
	public void test_052() throws UiObjectNotFoundException, RemoteException 
	{
		String name[] = {"2b*s4ee","abcd","cdef","bcde"};
		String orderName[] = {"abcd","bcde","cdef","2b*s4ee"};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<name.length;i++)
		{
			ContactCommon.addNameAndTel("本机",name[i],TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		
		for(int i=0;i<orderName.length;i++)
		{
			boolean flag=(Boolean) excute(Object_ResIdInstance, Operation_TextEqualTrue,"com.android.contacts:id/cliv_name_textview", Integer.toString(i),orderName[i]);
			Assert.assertTrue("Error: contacts are in a wrong order !!!",flag);
		}
	}
	
	public void test_053() throws UiObjectNotFoundException, RemoteException 
	{
		String name[] = {"bcde","abcd","",""};
		String number[] = {TelNumber,TelNumber,"124","123"};
		String orderName[] = {"abcd","bcde","123","124"};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<name.length;i++)
		{
			ContactCommon.addNameAndTel("本机",name[i],number[i]);
			excute(Object_Device, Operation_PressBack);
		}
		
		excute(Object_ClassText,Operation_Exists,"android.widget.TextView","#");
		
		for(int i=0;i<orderName.length;i++)
		{
			boolean flag=(Boolean) excute(Object_ResIdInstance, Operation_TextEqualTrue,"com.android.contacts:id/cliv_name_textview", Integer.toString(i),orderName[i]);
			Assert.assertTrue("Error: contacts are in a wrong order !!!",flag);
		}
	}
	
	//新建五个联系人到手机，其中联系人2没有名字，联系人3的名字以符号开头，联系人4没有电话号码,联系人5正常
	//在pb list中会出现a-z的字母列表，符号开头的名字放在最前面，无名字的联系人放在#区内，且放置在最后面；
	public void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		String name[] = {"aaee","","+aaaa","zzbb","zzabb"};
		String number[] = {"1234","123","12345","","467899"};
		String orderName[] = {"+aaaa","aaee","zzabb","zzbb","123"};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<name.length;i++)
		{
			ContactCommon.addNameAndTel("本机",name[i], number[i]);
			excute(Object_Device, Operation_PressBack);
		}
		
		excute(Object_ClassText,Operation_Exists,"android.widget.TextView","A");
		excute(Object_ClassText,Operation_Exists,"android.widget.TextView","Z");
		excute(Object_ClassText,Operation_Exists,"android.widget.TextView","#");
		
		for(int i=0;i<orderName.length;i++)
		{
			boolean flag=(Boolean) excute(Object_ResIdInstance, Operation_TextEqualTrue,"com.android.contacts:id/cliv_name_textview", Integer.toString(i),orderName[i]);
			Assert.assertTrue("Error: contacts are in a wrong order !!!",flag);
		}
	}
	
	public void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		String number1 = "123"; 
		String number2 = "234";
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机","", number1);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("本机","", number2);
		excute(Object_Device, Operation_PressBack);
		
		check(Object_ResIdText,Operation_checkExist,"com.android.contacts:id/cliv_name_textview",number1);
		check(Object_ResIdText,Operation_checkExist,"com.android.contacts:id/cliv_name_textview",number2);
		
		
		
		UiObject uiObject = (UiObject) excute(Object_ResIdText, Operate_ReturnObject, "com.android.contacts:id/cliv_name_textview",number1);
		
		Rect bounds = (Rect) excute(Object_ClassText, Operation_GetBounds, "android.widget.TextView","#");
		Rect bounds1 = (Rect) excute(Object_ResIdText, Operation_GetBounds, "com.android.contacts:id/cliv_name_textview",number1);
		Rect bounds2 = (Rect) excute(Object_Text, Operation_GetBounds, number2);
		int y = bounds.centerY();
		int y1 = bounds1.centerY();
		int y2 = bounds2.centerY();
		
		int flag = number1.compareTo(number2);
		if(flag < 0)
			assertTrue("Error: contacts are in a wrong order !!!",y==y1 && y1<y2);
		else if(flag > 0)
			assertTrue("Error: contacts are in a wrong order !!!",y==y2 && y1>y2);
	}
	
	public void test_056() throws UiObjectNotFoundException, RemoteException 
	{
		int i=0;
		ContactCommon.BatchDelete("所有联系人");
		for(;i<10;i++)
		{
			ContactCommon.addNameAndTel("本机",PhoneName+i, TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		Wait(2000);
		DeviceCommon.swipe("up", 10, 2);
		excute(Object_TextScroll, Operation_ClickWait,PhoneName+Integer.toString(i-1),"vertical");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"删除");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		
		check(Object_ResIdText,Operation_checkExist,"com.android.contacts:id/cliv_name_textview",PhoneName+Integer.toString(i-2));
	}
	
	public void test_058() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		String[] contactToGroup = {PhoneName+0,PhoneName+1};
		for(int i=0;i<2;i++)
		{
			ContactCommon.addNameAndTel("本机",contactToGroup[i], TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		ContactCommon.addGroup("本机","family");
		ContactCommon.addContactToGroup(contactToGroup);
	}
	
	//保证插了SIM1（为啥只有SIM1？？？！！！）
	public void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		String position[] = {"SIM1","SIM2"};
		String name[] = {SIM1Name,SIM2Name};
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		for(int i=0;i<position.length;i++)
		{
			ContactCommon.addNameAndTel(position[i],name[i], TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		ContactCommon.addGroup("SIM 卡","family");
		ContactCommon.addALLContactToGroup();
	}
	
	//仅保证发送 --!
	//1.新建10个人到手机上；其中前8个联系人为移动号码，第9个为联通号码，第10个为座机号码
	//2.新建群组，将这十个联系人加入群组中；
	//3.打开群组，给群组中的10个联系人发送短信；
	public void test_061() throws UiObjectNotFoundException, RemoteException 
	{
		String groupName = "family";
		String[] number = {"18202537224","15122775392","13622197715",
				"18202536740","18202610780","18202542932","18202650217",CMCCNum,"0222104"};
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		for(int i=0;i<number.length;i++)
		{
			ContactCommon.addNameAndTel("本机",PhoneName+i, number[i]);
			excute(Object_Device, Operation_PressBack);
		}
		ContactCommon.addGroup("本机",groupName);
		ContactCommon.addALLContactToGroup();
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/label", groupName);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群发信息");
		excute(Object_ResourceId, Operation_SetText,"com.android.mms:id/embedded_text_editor","Test!!!");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.mms:id/send_button_sms","发送");
		if((Boolean) excute(Object_ResIdText,Operation_Exists,"android:id/alertTitle","请选择 SIM 卡"))
		{
			excute(Object_ResIdInstance, Operation_ClickWait,"com.android.mms:id/sim_icon","0");
		}
	}
	
	public void test_062() throws UiObjectNotFoundException, RemoteException 
	{
		String emailName = "18202542932@139.com";
		String password = "12abAB";
		String groupName = "family";
		String[] email = {"18202537224@139.com","15122775392@139.com","13622197715@139.com"};
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		for(int i=0;i<3;i++)
		{
			ContactCommon.selectContactPosition("本机");
			excute(Object_TextScroll,Operation_SetText,"姓名","vertical",PhoneName+i);
			excute(Object_Device, Operation_PressBack);
			excute(Object_TextScroll,Operation_SetText,"电子邮件","vertical",email[i]);
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
			excute(Object_Device, Operation_PressBack);
		}
		SettingCommon.addAccount(emailName,password);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.addGroup("本机",groupName);
		ContactCommon.addALLContactToGroup();
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/label", groupName);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群发邮件");
		Wait(2000);
		excute(Object_Text, Operation_SetText,"主题","Contact-email(062)");
		excute(Object_Text, Operation_SetText,"邮件内容","Test");
		excute(Object_ResourceId, Operation_ClickWait,"com.android.email:id/send");
	}
	
	public void test_064() throws UiObjectNotFoundException, RemoteException 
	{
		String position[] = {"本机","SIM1","SIM2"};
		String name[] = {PhoneName,SIM1Name,SIM2Name};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<position.length;i++)
		{
			ContactCommon.addNameAndTel(position[i],name[i], TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		
		UiObject contactCount=(UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.contacts:id/contacts_count");
		
		ContactCommon.selectDisplayContact("SIM1");
		Wait(1000);
		int SIM1Num = ContactCommon.getNum(contactCount.getText());
		ContactCommon.selectDisplayContact("SIM2");
		Wait(1000);
		int SIM2Num = ContactCommon.getNum(contactCount.getText());
		ContactCommon.selectDisplayContact("本机");
		Wait(1000);
		int PhoneNum = ContactCommon.getNum(contactCount.getText());
		
		ContactCommon.deleteContact(PhoneName);
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",PhoneNum > ContactCommon.getNum(contactCount.getText()));
		ContactCommon.selectDisplayContact("SIM1");
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",SIM1Num == ContactCommon.getNum(contactCount.getText()));
		ContactCommon.selectDisplayContact("SIM2");
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",SIM2Num == ContactCommon.getNum(contactCount.getText()));
	}
	
	public void test_065() throws UiObjectNotFoundException, RemoteException 
	{
		String position[] = {"本机","SIM1","SIM2"};
		String name[] = {PhoneName,SIM1Name,SIM2Name};
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<position.length;i++)
		{
			ContactCommon.addNameAndTel(position[i],name[i], TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		
		UiObject contactCount=(UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.contacts:id/contacts_count");
		
		ContactCommon.selectDisplayContact("SIM1");
		Wait(1000);
		int SIM1Num = ContactCommon.getNum(contactCount.getText());
		ContactCommon.selectDisplayContact("SIM2");
		Wait(1000);
		int SIM2Num = ContactCommon.getNum(contactCount.getText());
		ContactCommon.selectDisplayContact("本机");
		Wait(1000);
		int PhoneNum = ContactCommon.getNum(contactCount.getText());
		
		ContactCommon.addNameAndTel("本机",PhoneName+1, TelNumber);
		excute(Object_Device, Operation_PressBack);
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",PhoneNum < ContactCommon.getNum(contactCount.getText()));
		ContactCommon.selectDisplayContact("SIM1");
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",SIM1Num == ContactCommon.getNum(contactCount.getText()));
		ContactCommon.selectDisplayContact("SIM2");
		Wait(1000);
		Assert.assertTrue("Error: Contacts in phone hasn't decrease!!!",SIM2Num == ContactCommon.getNum(contactCount.getText()));
	}
	
	public void test_066() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"批量删除");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_search","搜索");
		excute(Object_ResourceId, Operation_SetText,"android:id/search_src_text", PhoneName);
		check(Object_Text, Operation_checkExist,"匹配到 1个条目");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview",PhoneName);
		excute(Object_Description,Operation_ClickWait,"向上导航");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		check(Object_TextScroll,Operation_checkNoExist,PhoneName,"vertical");
		//check(Object_Text, Operation_checkNoExist,PhoneName);
	}
	public void test_067() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		ContactCommon.deleteContact(SIM1Name,Devices_Text_Operator_Determine);
		excute(Object_TextScroll,Operation_checkNoExist,SIM1Name,"vertical");
	}
	
	public void test_068() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.loopCopy(10, 200);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"批量删除");
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		
		// 下拉状态栏
		getUiDevice().openNotification();
		// 点击正在删除
		Wait(1000);
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","正在删除"); 
		// 取消删除
		excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		Wait(5000);
		
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		if((Boolean) excute(Object_ResIdText,Operation_checkNoExist,"所有联系人"))
		{
			excute(Object_Text, Operation_ClickWait,"所有联系人");
		}
		excute(Object_ResourceId,Operation_Exists,"com.android.contacts:id/cliv_name_textview");
		ContactCommon.BatchDelete("所有联系人");
		Wait(10000);//保证全部删除完成
	}
	
	public void test_071() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("本机","NoNumver", "");
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		boolean flag = (Boolean) excute(Object_TextScroll,Operation_Exists,"NoNumver", "vertical");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"要显示的联系人");
		boolean flagControl = (Boolean) excute(Object_ResourceId,Operation_IsChecked,"com.android.contacts:id/checkbox");
		Assert.assertEquals("sadgfasdgasdgasd",flag,!flagControl);
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/title","有电话号码的联系人");
		excute(Object_Device, Operation_PressBack);
		if(flagControl)
		check(Object_TextScroll,Operation_checkExist,"NoNumver","vertical");
		else
		check(Object_TextScroll,Operation_checkNoExist,"NoNumver","vertical");
		
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"要显示的联系人");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/title","有电话号码的联系人");
		excute(Object_Device, Operation_PressBack);
		if(!flagControl)
		excute(Object_TextScroll,Operation_Exists,"NoNumver","vertical");
		else
		excute(Object_TextScroll,Operation_checkNoExist,"NoNumver","vertical");
	}
	
	public void test_072() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		ContactCommon.selectDisplayContact("本机");
		excute(Object_TextScroll,Operation_Exists,PhoneName,"vertical");
		ContactCommon.selectDisplayContact("SIM1");
		excute(Object_TextScroll,Operation_Exists,SIM1Name,"vertical");
		ContactCommon.selectDisplayContact("SIM2");
		excute(Object_TextScroll,Operation_Exists,SIM2Name,"vertical");
	}
	
	//联系人添加账户类型需为：Exchange
	public void test_075() throws UiObjectNotFoundException, RemoteException 
	{
		String username = "18202537224@139.com";
		String password = "zhanxun123";
		ContactCommon.BatchDelete("所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "帐户");
		SettingCommon.deleteAction("Exchange",username);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "帐户");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","添加帐户");
		EmailCommon.addEmail("Exchange",username,password,"Text");
		excute(Object_Device, Operation_PressBack);
		ContactCommon.selectContactPosition(username);
		excute(Object_TextScroll,Operation_SetText,"姓氏","vertical","zhan");
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll,Operation_SetText,"名字","vertical","xun");
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",TelNumber);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		excute(Object_Device, Operation_PressBack);
		check(Object_Text, Operation_checkExist,"xun zhan");
	}
	
	public void test_076() throws UiObjectNotFoundException, RemoteException 
	{
		String accountName = "18202537224@139.com";
		test_075();
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "帐户");
		Assert.assertTrue("Error: the return is false!!!",SettingCommon.deleteAction("Exchange",accountName));
	}
	
	//必须插有SIM卡
	//验证CC和SMS接口
	public void test_077() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, CMCCNum);
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
		CallCommon.makeCall();
		Wait(2000);
		String get=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/name");
		boolean flag = get.equals(PhoneName)? true : false;
		Assert.assertTrue("Error: call fail!!!",flag);
		CallCommon.endCall();
		
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon_alternate");
		excute(Object_ResourceId, Operation_SetText,"com.android.mms:id/embedded_text_editor","Hi ~.~");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.mms:id/send_button_sms","发送");
		if((Boolean) excute(Object_ResIdText,Operation_Exists,"android:id/alertTitle","请选择 SIM 卡"))
		{
			excute(Object_ResIdInstance, Operation_ClickWait,"com.android.mms:id/sim_icon","0");
		}
	}
	
	//手机需预置照片
	public void test_079() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addPhoneWithPic(PhoneName, CMCCNum, "选择照片");
		ContactCommon.checkNameAndTel(PhoneName, CMCCNum);
	}
	
	//1.设置显示SIM1；
	//2.新建联系人到SIM2，然后在pb list中查找是否含有新建立的联系人；正常应该是不显示的；
	//3.将设置重新改为全部显示；
	public void test_080() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.selectDisplayContact("SIM2");
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/contacts_count","SIM2中没有联系人");
		ContactCommon.selectDisplayContact("所有联系人");
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/cliv_name_textview", SIM1Name);
	}
	
	//1.新建立联系人到手机上,SIM1上
	// 2.设置仅显示SIM1;
	// 3.批量删除中删除联系人1，但是正常应该不会在pb list上显示；
	// 4.将设置改为全部可见选项；
	public void test_081() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		ContactCommon.selectDisplayContact("SIM1");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"批量删除");
		excute(Object_ResIdText,Operation_checkNoExist,"com.android.contacts:id/cliv_name_textview",PhoneName);
		excute(Object_Device, Operation_PressBack);
		
		ContactCommon.selectDisplayContact("所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"批量删除");
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/cliv_name_textview",PhoneName);
	}
	
	public void test_082() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.selectDisplayContact("SIM1");
		excute(Object_ResIdText,Operation_checkNoExist,"com.android.contacts:id/cliv_name_textview",PhoneName);
		ContactCommon.selectDisplayContact("SIM2");
		excute(Object_ResIdText,Operation_checkNoExist,"com.android.contacts:id/cliv_name_textview",PhoneName);
		ContactCommon.selectDisplayContact("本机");
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/cliv_name_textview",PhoneName);
	}
	
	public void test_083() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("SIM1",SIM1Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("SIM2",SIM2Name, TelNumber);
		excute(Object_Device, Operation_PressBack);
		
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/cliv_name_textview",SIM1Name);
		excute(Object_ResIdText,Operation_Exists,"com.android.contacts:id/cliv_name_textview",SIM2Name);
	}
	
	//84/94完全相同，邮箱接口在006实现，+/-电话号码在097实现
	
	public void test_086() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addPhoneWithPic(PhoneName, CMCCNum, "拍照");
		ContactCommon.checkNameAndTel(PhoneName, CMCCNum);
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
		CallCommon.makeCall();
		Wait(5000);
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.dialer:id/name",PhoneName);
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.dialer:id/phoneNumber",CMCCNum);
		CallCommon.endCall();
	}
	
	//包含105
	public void test_093() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机", "all23%", "1#*pw24");
		ContactCommon.checkNameAndTel("all23%", "1#*pw24");
	}
	
	//1.新建立联系人1到手机中
	//2.编辑联系人1，添加住宅，单位和其他电话，并查看是否添加成功，然后将这些电话一一删除，并查看删除是否成功；
	public void test_097() throws UiObjectNotFoundException, RemoteException 
	{
		String type[] = {"住宅","单位"};
		String numbers[] = {"123","456"};
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
		for(int i=0;i<type.length;i++)
		{
			excute(Object_TextScroll,Operation_Exists,type[i],"vertical");
			excute(Object_TextScroll,Operation_SetText,"电话","vertical",numbers[i]);
		}
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		for(int i=0;i<type.length;i++)
		{
			check(Object_Text, Operation_checkExist,numbers[i]);
		}
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
		while((Boolean) excute(Object_ResIdDesc,Operation_Exists,"com.android.contacts:id/delete_button", "删除"))
		{
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/delete_button", "删除");
		}
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		check(Object_Text, Operation_checkNoExist,TelNumber);
		for(int i=0;i<type.length;i++)
		{
			check(Object_Text, Operation_checkNoExist,numbers[i]);
		}
	}
	
	//保证两张SIM卡,进入通话及信息界面
	public void test_098() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview",PhoneName);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");

		excute(Object_TextScroll,Operation_Exists,"住宅","vertical");
		
		excute(Object_Text, Operation_SetText,"电话","022");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		Wait(1000);
		check(Object_Text, Operation_checkExist,"022");
		check(Object_Text, Operation_checkExist,"住宅");
		
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
		excute(Object_TextScroll,Operation_SetText,"022", "vertical",""); 
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		check(Object_Text, Operation_checkNoExist,"022");
		check(Object_Text, Operation_checkNoExist,"住宅");
		
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/floating_end_call_action_button");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon_alternate");
		check(Object_Text, Operation_checkExist,"键入信息");
		excute(Object_Device, Operation_PressBack);
	}
	
	public void test_101() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName+21+"~", TelNumber);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
		excute(Object_TextScroll,Operation_Exists,PhoneName+21+"~","vertical");
		excute(Object_TextScroll,Operation_Exists,TelNumber,"vertical");
	}
	
	//编辑修改
	public void test_103() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机",PhoneName, TelNumber);
		ContactCommon.modifyContact("姓名", "all23%");
		ContactCommon.checkNameAndTel("all23%", TelNumber);
	}
	
	public void test_104() throws UiObjectNotFoundException, RemoteException 
	{
		String location[] = {"SIM1","SIM2"};
		String simName[] = {SIM1Name,SIM2Name};
		UiObject ContactTitle = (UiObject) excute(Object_ClassText, Operate_ReturnObject,"android.widget.TextView","通讯录");
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<location.length;i++)
		{
			ContactCommon.addNameAndTel(location[i],(simName[i]+"~"), TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		for(int i=0;i<simName.length;i++)
		{
			excute(Object_Text, Operation_ClickWait,simName[i]+"~");
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
			check(Object_Text, Operation_checkExist,simName[i]+"~");
			check(Object_Text, Operation_checkExist,TelNumber);
			while(!ContactTitle.exists())
			{
				excute(Object_Device, Operation_PressBack);
			}
		}
	}
	
	//包含013/78/109
	public void test_108() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机", PhoneName, TelNumber);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_star","添加到收藏");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait,"收藏");
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.contacts:id/contact_tile_name", PhoneName );
		
		excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/contact_tile_name");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_star","从收藏中移除");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait,"收藏");
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.contacts:id/contact_tile_list_empty", "没有收藏。" );
	}
	
	public void test_110() throws UiObjectNotFoundException, RemoteException 
	{
		String[] number = {CMCCNum,CMCCNum+11};
		ContactCommon.BatchDelete("所有联系人");
		DeviceCommon.enterApp(Devices_Desc_CallLog);
		CallLogCommon.deleteAllLog("全部");
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		for(int i=0;i<2;i++)
		{
			ContactCommon.addNameAndTel("本机", PhoneName+i, number[i]);
			excute(Object_Device, Operation_PressBack);
		}
		for(int i=0;i<4;i++)
		{
			excute(Object_TextScroll,Operation_ClickWait,PhoneName+0,"vertical");
			excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
			CallCommon.makeCall();
			Wait(2000);
			CallCommon.endCall();
			excute(Object_Device, Operation_PressBack);
		}
		for(int i=0;i<5;i++)
		{
			excute(Object_TextScroll,Operation_ClickWait,PhoneName+1,"vertical");
			excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
			CallCommon.makeCall();
			Wait(2000);
			CallCommon.endCall();
			excute(Object_Device, Operation_PressBack);
		}
		DeviceCommon.enterApp(Devices_Desc_CallLog);
		excute(Object_Text, Operation_ClickWait,"全部");
		boolean flag1 = (Boolean) excute(Object_ResIdInstance, Operation_TextEqualTrue,"com.android.dialer:id/name","0",PhoneName+1);
		boolean flag2 = (Boolean) excute(Object_ResIdInstance, Operation_TextEqualTrue,"com.android.dialer:id/name","1",PhoneName+0);
		Assert.assertTrue("Error: the call sequence is error!!!",flag1 && flag2);
	}
	
	//1.新建20个人到手机上；
	//2.利用前10个联系人拨打电话，后十个联系人发送短信；
	//3.打开收藏夹观察常用联系人，看是否有这20个联系人；
	public void test_111() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		for(int i=0;i<20;i++)
		{
			ContactCommon.addNameAndTel("本机", PhoneName+(char)('a'+i), CMCCNum);
			excute(Object_Device, Operation_PressBack);
		}
		for(int i=0;i<20;i++)
		{
			excute(Object_TextScroll,Operation_ClickWait,PhoneName+(char)('a'+i),"vertical");
			if(i<10)
			{
				excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon");
				CallCommon.makeCall();
				Wait(2000);
				//boolean flag = new FindAppByResIdAndGetText("com.android.dialer:id/name").getText().equals(PhoneName+(char)('a'+i));
				//Assert.assertTrue("Error: call fail!!!",flag);
				CallCommon.endCall();
				excute(Object_Device, Operation_PressBack);
			}
			else
			{
				excute(Object_ResourceId, Operation_ClickWait,"com.android.contacts:id/icon_alternate");
				excute(Object_ResourceId, Operation_SetText,"com.android.mms:id/embedded_text_editor","Hi ~.~");
				excute(Object_ResIdDesc,Operation_ClickWait,"com.android.mms:id/send_button_sms","发送");
					if((Boolean) excute(Object_ResIdText,Operation_Exists,"android:id/alertTitle","请选择 SIM 卡"))
				{
					excute(Object_ResIdInstance, Operation_ClickWait,"com.android.mms:id/sim_icon","0");
				}
				excute(Object_Description,Operation_ClickWait,"向上导航");
			}
		}
		ContactCommon.backContactHome();
		excute(Object_Text, Operation_ClickWait,"收藏");
		for(int i=0;i<20;i++)
		{
			excute(Object_TextScroll,Operation_Exists,PhoneName+(char)('a'+i),"vertical");
		}
	}
	
	//必须插入SIM卡
	//新建立联系人1,未输入姓名和名字信息即按Home键退出，点击拨号图标，进入拨号盘，显示正常；
	public void test_113() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.selectContactPosition("本机");
		excute(Object_Device, Operation_PressHome);
		CallCommon.makeCallByDailer("654138674");
		Wait(1000);
		CallCommon.endCall();
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.dialer:id/name","654138674");
	}
	
	public void test_114() throws UiObjectNotFoundException, RemoteException 
	{
		String name[] = {"a","b"};
		UiObject groupName = (UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.contacts:id/group_name");
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.deleteGroup("all");
		for(int i=0;i<name.length;i++)
		{
			ContactCommon.addNameAndTel("本机",name[i], TelNumber);
			excute(Object_Device, Operation_PressBack);
		}
		ContactCommon.addGroup("本机","b");
		ContactCommon.addALLContactToGroup();
		excute(Object_ResourceId, Operation_SetText,"com.android.contacts:id/menu_search", "b");
		check(Object_Text, Operation_checkExist,"a");
		check(Object_Text, Operation_checkExist,"b");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/search_back_button","停止搜索");
		ContactCommon.modifyGroup("b");
		groupName.clearTextField();
		groupName.setText("a");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item","完成");
		ContactCommon.backContactHome();
		excute(Object_ResourceId, Operation_SetText,"com.android.contacts:id/menu_search", "b");
		check(Object_Text, Operation_checkNoExist,"a");
		check(Object_Text, Operation_checkExist,"b");
	}
	
	public void test_115() throws UiObjectNotFoundException, RemoteException 
	{
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机", "aa?", TelNumber);
		excute(Object_Device, Operation_PressBack);
		ContactCommon.addNameAndTel("本机", PhoneName, TelNumber);
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_SetText,"com.android.contacts:id/menu_search","aa");
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.contacts:id/count_overlay","匹配到 1个条目");
		excute(Object_ResIdText,Operation_TextEqualTrue,"com.android.contacts:id/cliv_name_textview","aa?");
	}
}