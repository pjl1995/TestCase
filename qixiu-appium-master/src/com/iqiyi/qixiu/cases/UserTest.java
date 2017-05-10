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
 * 登录后页面内容检查
 */
public class UserTest extends BaseTest{

    private void goBackLogged(final String string){
        try {
            Thread.sleep(1000);
            if (!string.equals("充值")) {
                Assert.assertTrue("没有进入" + string + "页面"+ session.getPageSource(),
                        session.getPageSource().contains(string));
//                    session.findElementById("com.iqiyi.qixiu:id/label_title").getText().contains(string));
                System.out.println(string + "进入成功，返回！");
                WebElement goBack = session.findElementById("com.iqiyi.qixiu:id/left_button");
                goBack.click();
                WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
                tabMe.click();
            } else {

                Assert.assertTrue("没有进入" + string + "页面" + session.getPageSource(),
                        session.getPageSource().contains(string));
//                session.findElementById("com.iqiyi.qixiu:id/phoneTitle").toString().contains(string));
                System.out.println(string + "进入成功，返回！");
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
            WebElement user=session.findElementById("com.iqiyi.qixiu:id/new_user_center_user_rl");//我的资料
            user.click();
            goBackLogged("资料");
            WebElement follow = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follow");//关注
            follow.click();
            goBackLogged("关注");
            WebElement follower = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follower");//粉丝
            follower.click();
            goBackLogged("粉丝");
            WebElement contri = session.findElementById("com.iqiyi.qixiu:id/new_user_center_contribute");//贡献榜
            contri.click();
            goBackLogged("贡献榜");
            WebElement income = session.findElementById("com.iqiyi.qixiu:id/new_user_center_income");//收益
            income.click();
            goBackLogged("收益");
            WebElement cz = session.findElementById("com.iqiyi.qixiu:id/new_user_center_possession_cz");//充值
            cz.click();
            goBackLogged("充值");
            WebElement watch = session.findElementById("com.iqiyi.qixiu:id/me_watch_rl");//最近观看
            watch.click();
            goBackLogged("最近观看");
            WebElement live = session.findElementById("com.iqiyi.qixiu:id/me_live_rl");//我的直播
            live.click();
            goBackLogged("我的直播");
            WebElement smrz = session.findElementById("com.iqiyi.qixiu:id/me_smrz_rl");//实名认证
            smrz.click();
            goBackLogged("实名认证");
            WebElement item = session.findElementById("com.iqiyi.qixiu:id/me_item_rl");//我的物品
            item.click();
            goBackLogged("我的物品");
            WebElement vip = session.findElementById("com.iqiyi.qixiu:id/me_vip_rl");//贵族特权
            vip.click();
            goBackLogged("贵族特权");
            swipeToDown(session, 200);
            WebElement guard = session.findElementById("com.iqiyi.qixiu:id/me_guard_rl");//我的守护
            guard.click();
            goBackLogged("我的守护");
            WebElement msg = session.findElementById("com.iqiyi.qixiu:id/me_msg_rl");//我的消息
            msg.click();
            goBackLogged("我的消息");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//移除隐式等待
        }
    }
}
