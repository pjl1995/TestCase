package com.iqiyi.qixiu.base;

import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseTest {

	public static AndroidDriver<WebElement> session = null;
	protected static final int NETWORK_REQUEST_TIMEOUT = 20;
	public static final int LAUNCH_TIMEOUT = 10;
	public static FileOutputStream logFile = null;
	public static MultiOutputStream multi = null;


	//�������� �������� �����ֱ���driver�ͻ���ʱ��:ms
	public void swipeToDown(AndroidDriver<WebElement> driver, int during)
	{
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
	}
	public void swipeToUp(AndroidDriver<WebElement> driver, int during)
	{
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height / 4, width / 2, height * 3/ 4, during);
	}
	public void swipeToRight(AndroidDriver<WebElement> driver,int during){
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 5, height / 2, width * 4/ 5, height / 2, during);
	}
	public void swipeToLeft(AndroidDriver<WebElement> driver,int during){
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 4/ 5, height / 2, width / 5, height / 2, during);
	}

	public void cancelUpdate(){
		// ��ֹ��������ʾ��
		WebElement cancelText = session.findElementById("com.iqiyi.qixiu:id/dialog_cancel");
		if (cancelText != null) {
			cancelText.click();
		}
	}


	@BeforeClass
	public static void setup() {
		try {
			//serverlog�������
			logFile=new FileOutputStream("server.log");
//			D:\wenwen\test\appiumLog\
			final PrintStream console= System.out;
			multi=new MultiOutputStream(logFile,console);
			PrintStream p=new PrintStream(multi);
			System.setErr(p);
			System.setOut(p);

			System.out.println("BeforeTest start");

			//����app
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", "android");
			capabilities.setCapability("platformName", "Android");
			//capabilities.setCapability("deviceName", "DU2SSE1472015227");
			//capabilities.setCapability("deviceName", "GSLDU16824009146");
			capabilities.setCapability("deviceName", "acc44ed4");
			capabilities.setCapability("app",
					"E:/AndroidStudioProjects/qiyi-live/app/build/outputs/apk/app-debug.apk");
			session = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			session.manage().timeouts().implicitlyWait(LAUNCH_TIMEOUT, TimeUnit.SECONDS);

			session.findElementById("com.iqiyi.qixiu:id/tabLive");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void TearDown() {

		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(new File("Driver.log"));
//			D:\wenwen\test\appiumLog\
			PrintStream p = new PrintStream(fs);
			if (session!=null) {
				List<LogEntry> androidLog = session.manage().logs().get("logcat").getAll();
				for (LogEntry l : androidLog
						) {
					p.println(l.toString());
				}
				session.quit();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (session != null) {
			session.quit();
		}
		session = null;
	}
}
