package com.iqiyi.qixiu.cases;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iqiyi.qixiu.base.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EnterLiveRoom extends BaseTest {

	private void enterLiveRoom(String string){
		try {
			final WebElement nearby_tab = session.findElementByName(string);
			nearby_tab.click();
			
			String stringid = null;
			if(string == "附近" || string == "热门") stringid = "com.iqiyi.qixiu:id/feed_iv";
			if(string == "推荐" || string == "关注") stringid = "com.iqiyi.qixiu:id/vImageRoot";
			final String stringid_final = stringid;
			
			for(int i = 0; i < 2; i ++)
			{
				WebDriverWait wait = new WebDriverWait(session, NETWORK_REQUEST_TIMEOUT);
				WebElement element = wait.until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						return driver.findElement(By.id(stringid_final));
					}
				});
				int randomNumber = (int)(1 + Math.random() * 5);
				swipeToDown(session, 100 * randomNumber);
				Thread.sleep(1 * 1000);	
				final WebElement feed_iv = session.findElementById(stringid_final);
				feed_iv.click();
				Thread.sleep(3 * 1000);
				final WebElement roomCloseBtn = session.findElementById("com.iqiyi.qixiu:id/roomCloseBtn");
				roomCloseBtn.click();
				Thread.sleep(1 * 1000);			
			}
			System.out.println(string + "模块测试成功");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//移除隐式等待
		}
    }
    
	
	@Test
	public void Test() {
		try {
			enterLiveRoom("附近");
			enterLiveRoom("热门");
			enterLiveRoom("推荐");
			enterLiveRoom("关注");
			System.out.println("全部模块测试成功");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//移除隐式等待
		}
	}
}