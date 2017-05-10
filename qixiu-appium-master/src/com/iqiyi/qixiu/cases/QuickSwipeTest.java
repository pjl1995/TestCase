package com.iqiyi.qixiu.cases;

import com.iqiyi.qixiu.base.BaseTest;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by raoyiwen_sx on 2016/12/21.
 * �컮 ��������
 */
public class QuickSwipeTest extends BaseTest{

    @Test
    public void QuickSwipe(){


        try {
            //��ʽ�ȴ�
            session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            session.findElementById("com.iqiyi.qixiu:id/tabLive");

            //�Ƽ��컮  �������
            for (int i = 0; i < 10; i++) {
                swipeToDown(session, 200);
                new WebDriverWait(session, 1);
            }
            for (int j = 0; j < 10; j++) {
                swipeToUp(session, 200);
                new WebDriverWait(session, 1);
            }
            for (int k = 0; k < 4; k++) {
                swipeToRight(session, 200);
                new WebDriverWait(session, 1);
            }
            for (int l = 0; l < 4; l++) {
                swipeToLeft(session, 200);
                new WebDriverWait(session, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);//�Ƴ���ʽ�ȴ�
        }
    }
}
