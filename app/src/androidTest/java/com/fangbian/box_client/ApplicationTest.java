package com.fangbian.box_client;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void TestReadFile(){
        String server = readFile("/sdcard/server").replace("\\n", "").replace("\\r","").replace("\\r\\n","");
        Log.d("TestReadFile",String.format("http://%s/terminal",server));
    }


    public String readFile(String fileName) {
        String res="";
        try{
            File file = new File(fileName);
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            //res = EncodingUtils.getString(buffer, "UTF-8");
            res=new String(buffer,0,buffer.length,"UTF-8");
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}