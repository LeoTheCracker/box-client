package com.fangbian.box_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.MobileTicket.CheckCodeUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent service = new Intent(this, SocketClientService.class);
        startService(service);

//        String strDecheckcode = CheckCodeUtil.decheckcode("", "FjnGasds5Z0elvMIzgoixBXE4zLAX9sVCke3FPgDscOXR36ZdHhLZMpJrKncmjroglYnngkwi7Cf60pu0PT8ZcvdBSQRc6c6IDxOSoq6Sr50EnipaeIxcOPaY701EVi0e8QA8q8bGuhqPvTsBkAO7LbI8gmppayR4fxxg9iPR151o5o6qU5c3nSz+HUprm0wHYTT5T0eUnjpWyiQTQtI1kZLLZ838NAlDKyb8Cmcj2o6ilrpjdMpfFKNTMCuj5gX5cmcGa5jBXALwry93mdiJedmUETD9RzJfDDKGdJoh/vl812WREfOsYeBEoUqe/5RT5AHtmmfo3CDxl0mOcOG2EBom2mom3W+hQT/fBIdgOxn69dupA6LFyt30YM+eFb3BvngehnP/Txq9dQKZqxK84zDwDe7GJfOESSHtQYhnO1fvbdQUUPlzgOdYFrWMT6SXhKBYwfARydRvywQr2HY5zlWL9vU4luIiPVdC9wOIePqoFjLfN2CUVa0T6wjcws+T5rSMwziiHNI5Zc+MJXj1zLRep81W06IIjyN2cUX+7WD7D8AdMLcp4Y5Q40rfvkwScxhNzJSJ2lJGZ9h200uGBuoJEr4j+A6iiHFyjX0NWXYGJXuxjKx58hTi9/kPnaVK");
//        Log.v("INJECT", "decheckcode1:" + strDecheckcode);
//        String strCheckCode = CheckCodeUtil.checkcode("", "{\"baseDTO.os_type\":\"a\",\"baseDTO.device_no\":\"0bf58b71d198d371\",\"baseDTO.mobile_no\":\"123444\",\"baseDTO.time_str\":\"20160111103122\",\"baseDTO.version_no\":\"1.1\",\"baseDTO.user_name\":\"15201169346\",\"password\":\"e781a68d744b55f06db794471868ef7c\",\"ostype\":\"and\",\"imei\":\"868038021072575\",\"mac\":\"84:73:03:44:6c:40\",\"model\":\"X800+\",\"sdk\":\"21\"}");
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
        Intent service = new Intent(this, SocketClientService.class);
        stopService(service);
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
