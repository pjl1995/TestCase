package com.iqiyi.qixiu.cases;

import com.iqiyi.qixiu.base.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * Created by raoyiwen_sx on 2016/12/8.
 * ���������������¼���
 */
public class MultiLoginTest extends BaseTest {

    private void goBack(String string){
        Assert.assertNotNull("û�н����¼ҳ��",session.findElementById("com.iqiyi.qixiu:id/bt_login"));
        if (session.getPageSource().contains("�����밮�����˺�")){
            System.out.println(string+"�����¼�ɹ������أ�");
            WebElement goBack=session.findElementById("com.iqiyi.qixiu:id/back_icon");
            goBack.click();
        }
    }

    @Test
    public void MultiLogin(){
//        cancelUpdate();

        //��ʽ�ȴ�
        session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {

            final WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
            tabMe.click();
            if (session.getPageSource().contains("�����¼")) {
                WebElement follow = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follow");//��ע
                follow.click();
                goBack("��ע");
                WebElement follower = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follower");//��˿
                follower.click();
                goBack("��˿");
                WebElement contri=session.findElementById("com.iqiyi.qixiu:id/new_user_center_contribute");//���װ�
                contri.click();
                goBack("���װ�");
                WebElement income=session.findElementById("com.iqiyi.qixiu:id/new_user_center_income");//����
                income.click();
                goBack("����");
                WebElement cz=session.findElementById("com.iqiyi.qixiu:id/new_user_center_possession_cz");//��ֵ
                cz.click();
                goBack("��ֵ");
                WebElement watch=session.findElementById("com.iqiyi.qixiu:id/me_watch_rl");//����ۿ�
                watch.click();
                goBack("����ۿ�");
                WebElement live=session.findElementById("com.iqiyi.qixiu:id/me_live_rl");//�ҵ�ֱ��
                live.click();
                goBack("�ҵ�ֱ��");
                WebElement smrz=session.findElementById("com.iqiyi.qixiu:id/me_smrz_rl");//ʵ����֤
                smrz.click();
                goBack("ʵ����֤");
                WebElement item=session.findElementById("com.iqiyi.qixiu:id/me_item_rl");//�ҵ���Ʒ
                item.click();
                goBack("�ҵ���Ʒ");
                WebElement vip=session.findElementById("com.iqiyi.qixiu:id/me_vip_rl");//������Ȩ
                vip.click();
                goBack("������Ȩ");
                swipeToDown(session,200);
                WebElement guard=session.findElementById("com.iqiyi.qixiu:id/me_guard_rl");//�ҵ��ػ�
                guard.click();
                goBack("�ҵ��ػ�");
                WebElement msg=session.findElementById("com.iqiyi.qixiu:id/me_msg_rl");//�ҵ���Ϣ
                msg.click();
            }else {
                System.out.println("Already login");
                session.quit();
                session=null;
            }
        }
        finally {
            if(session!=null)
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//�Ƴ���ʽ�ȴ�
        }
    }

    @After
    public void Login() {
        if (session != null) {

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
                    WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
                    tabMe.click();
                    swipeToUp(session,200);
                    return driver.findElement(By.id("com.iqiyi.qixiu:id/new_user_center_user_nickname"));
                }
            });

            Assert.assertNotNull("Login Failed", element);
            System.out.println("Login Success");
            session.quit();
        }
        session=null;
    }

}
