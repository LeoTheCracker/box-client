package com.fangbian.box_client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.MobileTicket.CheckCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent service = new Intent(this, SocketClientService.class);
        startService(service);

//        String strDecheckcode = CheckCodeUtil.decheckcode("", "FTsLYXakruIXda+QIcxlUkxJFXo7AB15cTelnpjcWi1dRYriOhxhnSkIZzLndzA9Rk+bLvkxwxDKkz8YyOjYRKf4vVsQuqYKVqR2jEQcMd3WNpPK9nG8cFmEchTsVyaFawkk99taDN0YdAoXQebqYFzmIWP0gVtwkGcpCjKdL3X39T6D/WqdTJp7nDFRocS9cE+Ku9rB2y2pHNIaFaNvf+YWF7ukKklSpLdpEX9CNYM8P0JofERpB2nb/atyCiINg/j0PUi/XThlsmiD/68CCYCuA6709JHaltEerFeMU2dSQNiDv6q7Z34g5jGzMcRb3ITRVNk+55ZgAezhZN16hpOpCA6Kdgo1evBpzW80XGaS2hkK7kMbPDrd+z5dIpKCHB/cRqdzxYrVdx5wvHeQJaQcYSnBr7xMEiekqErZGEWFJVd+NfzpmbiC8c1pF3tkHynmr4ow+X5XKo1hJgE9/atAvBnImvNhajtnCj3BhqbM=");
//        Log.v("INJECT", "decheckcode1:" + strDecheckcode);
//        String strCheckCode = CheckCodeUtil.checkcode("", "[{\"baseDTO.os_type\":\"a\",\"baseDTO.device_no\":\"e20d58861dadb144\",\"baseDTO.mobile_no\":\"123444\",\"baseDTO.time_str\":\"20160713100652\",\"baseDTO.version_no\":\"1.1\",\"baseDTO.user_name\":\"15084821221\",\"password\":\"5416d7cd6ef195a0f7622a9c56b55e84\"}]");
////                String strDecheckcode = CheckCodeUtil.decheckcode("","FtlLDmxh51UjCaBx4agFPeVKi075LgNRddeVRHCzvw7cgUpebb6G0ayHUSD9nCJ5KKVHdA9ek3gU1jUxDsQEQjggZSKHfhofFgbfAomEt7ktZx/bQbtrrXrHeklmh50YdlB5MeVE1Rw5zPZ0x2JcmPUX+u4nzprXfmfdcz6939wHVpA3vnSxUpq73YEQOURp+riUjPMZnGaeSN3+3YGQzBmtbcqLUCGtME2wTsK1BpaI=");
//
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
