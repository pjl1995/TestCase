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
 * 个人中心里各个登录入口
 */
public class MultiLoginTest extends BaseTest {

    private void goBack(String string){
        Assert.assertNotNull("没有进入登录页面",session.findElementById("com.iqiyi.qixiu:id/bt_login"));
        if (session.getPageSource().contains("请输入爱奇艺账号")){
            System.out.println(string+"进入登录成功，返回！");
            WebElement goBack=session.findElementById("com.iqiyi.qixiu:id/back_icon");
            goBack.click();
        }
    }

    @Test
    public void MultiLogin(){
//        cancelUpdate();

        //隐式等待
        session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {

            final WebElement tabMe = session.findElementById("com.iqiyi.qixiu:id/tabMe");
            tabMe.click();
            if (session.getPageSource().contains("点击登录")) {
                WebElement follow = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follow");//关注
                follow.click();
                goBack("关注");
                WebElement follower = session.findElementById("com.iqiyi.qixiu:id/new_user_center_follower");//粉丝
                follower.click();
                goBack("粉丝");
                WebElement contri=session.findElementById("com.iqiyi.qixiu:id/new_user_center_contribute");//贡献榜
                contri.click();
                goBack("贡献榜");
                WebElement income=session.findElementById("com.iqiyi.qixiu:id/new_user_center_income");//收益
                income.click();
                goBack("收益");
                WebElement cz=session.findElementById("com.iqiyi.qixiu:id/new_user_center_possession_cz");//充值
                cz.click();
                goBack("充值");
                WebElement watch=session.findElementById("com.iqiyi.qixiu:id/me_watch_rl");//最近观看
                watch.click();
                goBack("最近观看");
                WebElement live=session.findElementById("com.iqiyi.qixiu:id/me_live_rl");//我的直播
                live.click();
                goBack("我的直播");
                WebElement smrz=session.findElementById("com.iqiyi.qixiu:id/me_smrz_rl");//实名认证
                smrz.click();
                goBack("实名认证");
                WebElement item=session.findElementById("com.iqiyi.qixiu:id/me_item_rl");//我的物品
                item.click();
                goBack("我的物品");
                WebElement vip=session.findElementById("com.iqiyi.qixiu:id/me_vip_rl");//贵族特权
                vip.click();
                goBack("贵族特权");
                swipeToDown(session,200);
                WebElement guard=session.findElementById("com.iqiyi.qixiu:id/me_guard_rl");//我的守护
                guard.click();
                goBack("我的守护");
                WebElement msg=session.findElementById("com.iqiyi.qixiu:id/me_msg_rl");//我的消息
                msg.click();
            }else {
                System.out.println("Already login");
                session.quit();
                session=null;
            }
        }
        finally {
            if(session!=null)
            session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//移除隐式等待
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
