package com.iqiyi.qixiu.cases;

import com.iqiyi.qixiu.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by raoyiwen_sx on 2016/12/8.
 */
public class LogoutTest extends BaseTest{



    @Test
    public void Logout() {
//        cancelUpdate();

        try {

            //ÒþÊ½µÈ´ý
            session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            final WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
            tabMe.click();
            if (!session.getPageSource().contains("µã»÷µÇÂ¼")) {

                swipeToDown(session,200);

                WebElement clickSeting = session.findElementById("com.iqiyi.qixiu:id/me_setting_rl");
                clickSeting.click();

                WebElement clickLogout=session.findElementById("com.iqiyi.qixiu:id/user_center_setting_logout");
                clickLogout.click();

                WebElement logoutOk=session.findElementById("com.iqiyi.qixiu:id/dialog_ok");
                logoutOk.click();

                WebDriverWait wait = new WebDriverWait(session, NETWORK_REQUEST_TIMEOUT);
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        tabMe.click();
                        swipeToUp(session,200);
                        return driver.findElement(By.id("com.iqiyi.qixiu:id/new_user_center_user_sign_in"));
                    }
                });

                Assert.assertNotNull("Logout Failed", element);
                System.out.println("Logout Success");
//			return 0;//login success
            } else {
                System.out.println("No login account");
//			return 1;//already login
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//ÒÆ³ýÒþÊ½µÈ´ý
        }
    }
}
