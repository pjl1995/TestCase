package com.iqiyi.qixiu.base;

import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static com.iqiyi.qixiu.base.BaseTest.multi;

/**
 * Created by raoyiwen_sx on 2016/12/22.
 */
public class LogtryTest {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        String fileName = "output.txt";
        File file = new File(fileName);

        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos  = new FileOutputStream(fileName);
//        PrintStream ps = new PrintStream(fos);
//        System.setErr(ps);
//		System.setOut(ps);


        PrintStream console= System.out;
        multi=new MultiOutputStream(fos,console);
        PrintStream p=new PrintStream(multi);
        System.setErr(p);
        System.setOut(p);

        System.out.println("this is a message");

        int b  = 5;
        Assert.assertTrue(b+"",b==4);
    }
}
