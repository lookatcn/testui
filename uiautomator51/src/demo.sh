#!/bin/bash
#创建打包测试代码的Build脚本
#set -x
cd /home/apuser/k/sdk/tools
./android create uitest-project -n Android51 -t 2 -p /home/apuser/workspace/AndroidTest

#打包
cd /home/apuser/workspace/AndroidTest
ant build 

#上传文件到手机
adb push /home/apuser/workspace/AndroidTest/bin/Android51.jar data/local/tmp

#运行测试程序
adb root

#adb shell uiautomator runtest Android51.jar -c uitest.testcase.ShortMessage#test_001
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.MultimediaMessage#test_001
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.FileManage#test_001
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.Note#test_001
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.CallLog#test_001
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.Search#test_001


adb shell uiautomator runtest Android51.jar -c testcase.Contact


#adb shell uiautomator runtest Android51.jar -c uitest.testcase.FileManage
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.Note
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.ShortMessage
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.MultimediaMessage
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.CallLog
#adb shell uiautomator runtest Android51.jar -c uitest.testcase.Search

#adb shell uiautomator runtest Android51.jar -c uitest.testcase.temp
#adb pull data/local/tmp /home/likewise-open/SPREADTRUM/bingxing.liu/AndroidM/workspace/AndroidUiTest/result 
