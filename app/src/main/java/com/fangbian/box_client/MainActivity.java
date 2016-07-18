package com.fangbian.box_client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.MobileTicket.CheckCodeUtil;
import com.worklight.common.security.AppAuthenticityToken;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Intent service = new Intent(this, SocketClientService.class);
//        startService(service);


        Log.d("test", AppAuthenticityToken.a1(this,
                "273524C193809N563130X8A56324FS683373X254F09D8S412578XAA4FF4DBS864272C873638XBAE5B5DFS093096C174687N704272N313431C890733N133879X70669515S"));

//        String strDecheckcode = CheckCodeUtil.decheckcode("", "aUR5zckh0agBxJ3JmSEUrA0QtPm9wWHcteXMOb2kGdShdFgNCUVJvAlEmL1lbTTw0EzwyMzxDJgIyIjphSEQkNjcwIlY6QUE0OzA3QDlpJDg3FiJAHTYFQiI7QCw4ZjIaHxZBRQEwRG8EBFNCWl1dYHFRXURNRHNYeHleQnxMAVhNW1N8YQNdUlNXXm98HgQCfVsHUX8CeFRtfVJXDHlBX2RnXXEWW1cMR197cAt6BHZVQwJxDVdQcUEDXEVDQRp5TWpRbncHAVhPYRkBXXMJBl5cdXJtDGtme1J5W39keHF2YXd0bVxSXg==");
//        Log.v("INJECT", "decheckcode1:" + strDecheckcode);
//        String strCheckCode = CheckCodeUtil.checkcode("", "[{\"baseDTO.os_type\":\"a\",\"baseDTO.device_no\":\"e20d58861dadb144\",\"baseDTO.mobile_no\":\"123444\",\"baseDTO.time_str\":\"20160713100652\",\"baseDTO.version_no\":\"1.1\",\"baseDTO.user_name\":\"15084821221\",\"password\":\"5416d7cd6ef195a0f7622a9c56b55e84\"}]");
////                String strDecheckcode = CheckCodeUtil.decheckcode("","FtlLDmxh51UjCaBx4agFPeVKi075LgNRddeVRHCzvw7cgUpebb6G0ayHUSD9nCJ5KKVHdA9ek3gU1jUxDsQEQjggZSKHfhofFgbfAomEt7ktZx/bQbtrrXrHeklmh50YdlB5MeVE1Rw5zPZ0x2JcmPUX+u4nzprXfmfdcz6939wHVpA3vnSxUpq73YEQOURp+riUjPMZnGaeSN3+3YGQzBmtbcqLUCGtME2wTsK1BpaI=");
////
////
////                strDecheckcode = CheckCodeUtil.decheckcode("","FtlLDmxh51UjCaBx4agFPeVKi075LgNRddeVRHCzvw7e4RlyieGrtVpJXHjm2Jfw61vcpkeo+3SgEsvLAxDIobloT7ECps909wMLYWJvdgUlhCXq3VMoqND51iv5UejwZ3PZswTTtPg/MhP3i0B39VqIsbVmK270fNWVOG3HOpF4l38oLnL+bYTYhRdqSkEFGNG7wJKUwvb3yPZUg4KDPmKMen9/JURDQqI97UEiZeKw=");
////                Log.v("INJECT", "decheckcode2:"+strDecheckcode);
//        Log.v("INJECT", "checkcode:" + strCheckCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
//        Intent service = new Intent(this, SocketClientService.class);
//        stopService(service);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
