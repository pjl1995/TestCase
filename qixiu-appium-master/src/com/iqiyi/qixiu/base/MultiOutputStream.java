package com.iqiyi.qixiu.base;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by raoyiwen_sx on 2016/12/21.
 * 双输出，可以同时输出到文件和控制台
 */
public class MultiOutputStream extends OutputStream {

    OutputStream output1;
    OutputStream output2;

    public MultiOutputStream(OutputStream output1,OutputStream output2){
        this.output1 = output1;
        this.output2 = output2;
    }

    @Override
    public void write(int b) throws IOException {
        // TODO Auto-generated method stub
        output1.write(b);
        output2.write(b);
    }

}