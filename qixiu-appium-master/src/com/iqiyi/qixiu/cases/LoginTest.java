package com.iqiyi.qixiu.cases;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iqiyi.qixiu.base.BaseTest;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {

	@Test
	public void Login() {
//		cancelUpdate();

		try {
			//ÒþÊ½µÈ´ý
			session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			final WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
			System.out.println("111");
			tabMe.click();
			if (session.getPageSource().contains("µÇÂ¼")) {

				WebElement clickLogin = session.findElementById("com.iqiyi.qixiu:id/new_user_center_user_sign_in");
				clickLogin.click();


				WebElement etName = session.findElementById("com.iqiyi.qixiu:id/et_name");
				WebElement etPassword = session.findElementById("com.iqiyi.qixiu:id/et_password");

				etName.sendKeys("show_test_029@163.com");
				etPassword.sendKeys("123456");

				WebElement btLogin = session.findElementById("com.iqiyi.qixiu:id/bt_login");
				btLogin.click();

				WebDriverWait wait = new WebDriverWait(session, NETWORK_REQUEST_TIMEOUT);
				WebElement element = wait.until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						tabMe.click();
						return driver.findElement(By.id("com.iqiyi.qixiu:id/new_user_center_user_nickname"));
					}
				});

				Assert.assertNotNull("Login Failed", element);
				System.out.println("Login Success"+element);
			} else { 
				System.out.println("Already login");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//ÒÆ³ýÒþÊ½µÈ´ý
		}
	}
}