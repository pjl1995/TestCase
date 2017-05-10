package com.iqiyi.qixiu.cases;

import com.iqiyi.qixiu.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by raoyiwen_sx on 2016/12/8.
 * ��������¼
 */
public class ThirdpartyLoginTest extends BaseTest {

    @Test
    public void ThirdPartyLogin(){
        //cancelUpdate();

        //��ʽ�ȴ�
        session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {

            final WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
            tabMe.click();
            if (session.getPageSource().contains("�����¼")) {

                WebElement clickLogin = session.findElementById("com.iqiyi.qixiu:id/new_user_center_user_sign_in");
                clickLogin.click();

                List< WebElement> thirdParty=session.findElementsByClassName("android.widget.ImageView");
                int len = thirdParty.size();
                System.out.println(len);
                for(int i = 0; i < len; i ++)
                {
                	System.out.println(thirdParty.get(i));
                }
                //Ŀǰû�в����˺ž�û�����У�����д���ˣ��õ�ʱ��Ҫ�ֿ����ߵ���logout�������ǲ���apk��Ҫ�Ѱ�װ������������
                {
                    //weibo
                    thirdParty.get(2).click();
                    if(session.getPageSource().contains("΢����¼")){
                        WebElement weiboOk=session.findElementById("com.sina.weibo:id/bnLogin");
                        weiboOk.click();
                        new LogoutTest().Logout();
                    }
                }
                {
                    //wechat
                    thirdParty.get(3).click();
                    System.out.println(session.getPageSource());
                    if(session.getPageSource().contains("��¼")){
                    	System.out.println(111);
                        WebElement wechatOk=session.findElementByAndroidUIAutomator("new UiSelector().text(��¼)");
                    	//WebElement wechatOk=session.findElementByName("ȷ�ϵ�¼");
                        wechatOk.click();
                        new LogoutTest().Logout();
                    }
                }
                {
                    //qq
                    thirdParty.get(4).click();
                    if(session.getPageSource().contains("QQ��¼")){
                        WebElement qqOk=session.findElementByAndroidUIAutomator("new UiSelector().text(\"��Ȩ����¼\")");
                        qqOk.click();
                        new LogoutTest().Logout();
                    }
                }

                WebDriverWait wait = new WebDriverWait(session, NETWORK_REQUEST_TIMEOUT);
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        tabMe.click();
                        return driver.findElement(By.id("com.iqiyi.qixiu:id/new_user_center_user_nickname"));
                    }
                });

                Assert.assertNotNull("Login Failed", element);
                System.out.println("Login Success");
//			return 0;//login success
            } else {
                System.out.println("Already login");
//			return 1;//already login
            }
        }
        finally {
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//�Ƴ���ʽ�ȴ�
        }
    }
}
