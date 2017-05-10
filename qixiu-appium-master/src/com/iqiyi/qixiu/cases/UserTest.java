package com.iqiyi.qixiu.cases;

import com.iqiyi.qixiu.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by raoyiwen_sx on 2016/12/7.
 * ��¼��ҳ�����ݼ��
 */
public class UserTest extends BaseTest{

    private void goBackLogged(final String string){
        try {
            Thread.sleep(1000);
            if (!string.equals("��ֵ")) {
                Assert.assertTrue("û�н���" + string + "ҳ��"+ session.getPageSource(),
                        session.getPageSource().contains(string));
//                    session.findElementById("com.iqiyi.qixiu:id/label_title").getText().contains(string));
                System.out.println(string + "����ɹ������أ�");
                WebElement goBack = session.findElementById("com.iqiyi.qixiu:id/left_button");
                goBack.click();
                WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
                tabMe.click();
            } else {

                Assert.assertTrue("û�н���" + string + "ҳ��" + session.getPageSource(),
                        session.getPageSource().contains(string));
//                session.findElementById("com.iqiyi.qixiu:id/phoneTitle").toString().contains(string));
                System.out.println(string + "����ɹ������أ�");
                WebElement goBackcz = session.findElementById("com.iqiyi.qixiu:id/phoneTopBack");
                goBackcz.click();
                WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
                tabMe.click();
            }
        }catch (AssertionError e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void UserAction(){

        new LoginTest().Login();
        try {
            session.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
            tabMe.click();
            WebElement user=session.findElementById("com.iqiyi.qixiu:id/new_user_center_user_rl");//�ҵ�����
            user.click();
            goBackLogged("����");
            WebElement follow = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follow");//��ע
            follow.click();
            goBackLogged("��ע");
            WebElement follower = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follower");//��˿
            follower.click();
            goBackLogged("��˿");
            WebElement contri = session.findElementById("com.iqiyi.qixiu:id/new_user_center_contribute");//���װ�
            contri.click();
            goBackLogged("���װ�");
            WebElement income = session.findElementById("com.iqiyi.qixiu:id/new_user_center_income");//����
            income.click();
            goBackLogged("����");
            WebElement cz = session.findElementById("com.iqiyi.qixiu:id/new_user_center_possession_cz");//��ֵ
            cz.click();
            goBackLogged("��ֵ");
            WebElement watch = session.findElementById("com.iqiyi.qixiu:id/me_watch_rl");//����ۿ�
            watch.click();
            goBackLogged("����ۿ�");
            WebElement live = session.findElementById("com.iqiyi.qixiu:id/me_live_rl");//�ҵ�ֱ��
            live.click();
            goBackLogged("�ҵ�ֱ��");
            WebElement smrz = session.findElementById("com.iqiyi.qixiu:id/me_smrz_rl");//ʵ����֤
            smrz.click();
            goBackLogged("ʵ����֤");
            WebElement item = session.findElementById("com.iqiyi.qixiu:id/me_item_rl");//�ҵ���Ʒ
            item.click();
            goBackLogged("�ҵ���Ʒ");
            WebElement vip = session.findElementById("com.iqiyi.qixiu:id/me_vip_rl");//������Ȩ
            vip.click();
            goBackLogged("������Ȩ");
            swipeToDown(session, 200);
            WebElement guard = session.findElementById("com.iqiyi.qixiu:id/me_guard_rl");//�ҵ��ػ�
            guard.click();
            goBackLogged("�ҵ��ػ�");
            WebElement msg = session.findElementById("com.iqiyi.qixiu:id/me_msg_rl");//�ҵ���Ϣ
            msg.click();
            goBackLogged("�ҵ���Ϣ");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//�Ƴ���ʽ�ȴ�
        }
    }
}
