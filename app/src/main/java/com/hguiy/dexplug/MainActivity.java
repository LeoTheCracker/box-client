package com.hguiy.dexplug;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fangbian.box_client.ZTContext;
import com.fangbian.box_client.ZTPackageInfo;
import com.fangbian.box_client.ZtPackageManager;
import com.worklight.common.security.AppAuthenticityToken;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //动态添加标识
//        Marker marker = Markers.append("appname", "192.168.1.13");
//        logger.info(marker, "log message");

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                throw new RuntimeException("test");
//            }
//        });

        ZTContext tokenContext = new ZTContext();
        ZTPackageInfo localSuanyaPackageInfo = new ZTPackageInfo();
        Signature[] arrayOfSignature = new Signature[1];
        arrayOfSignature[0] = new Signature("30820237308201a0a0030201020204520c9714300d06092a864886f70d0101050500305f310b30090603550406130230313110300e060355040813076265696a696e673110300e060355040713076265696a696e67310c300a060355040a13037a7463310e300c060355040b13057261696c73310e300c0603550403130531323330363020170d3133303831353038353334305a180f32313133303732323038353334305a305f310b30090603550406130230313110300e060355040813076265696a696e673110300e060355040713076265696a696e67310c300a060355040a13037a7463310e300c060355040b13057261696c73310e300c06035504031305313233303630819f300d06092a864886f70d010101050003818d00308189028181009fea9d56277fc27c68a3836173d286791af2be38e7384afce32f38b164d83a6c6ec7656f7881c444c2e677e2195415a92bb6a06638886d132d26ce47895fa96076085813ee7b264d6017b21a64c75ae4ba63496906fe77fda68305d8ee426ece06e1b683bf78eccedf8bcd9817376d26c50ffb745f378a50834fd6522db3a6150203010001300d06092a864886f70d0101050500038181003e646d1dea5763f12008d36023a0812bc4452b15d3f8cbf189e2f6b43b89a373e4cc4ec7197f31e9c765821d6c8499cd0a71e49fac114b0b90bf2db8f7520d5ab922c04b602f7e81cd7f4dcb9e94118691bf2dd6277bc404bdfc6906fab2145c3426a0624a549cd85e83fe4822e686abc4119f21dbddb8cb71c5ebdb5f042688");
        localSuanyaPackageInfo.setSignatures(arrayOfSignature);
        ZtPackageManager localSuanyaPackageManager = new ZtPackageManager();
        localSuanyaPackageManager.setPackageInfo(localSuanyaPackageInfo);
        tokenContext.setPackageManager(localSuanyaPackageManager);
        tokenContext.setPackageName("com.MobileTicket");
        String str = AppAuthenticityToken.a1(tokenContext, "453213N732764N497344XC3E579CFS346841X7CB3B26ES054795N589013N493792C361215C000924C974831N336628N571271X0427B7C5S");
        Log.d("test",str);

//        try {
//            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.MobileTicket", PackageManager.GET_SIGNATURES);
//            Signature[] signatures = packageInfo.signatures;
//            for (Signature signature : signatures) {
//                Log.d("test",signature.toCharsString());
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

//        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//        Log.d("imei",tm.getDeviceId());

//        String strDecheckcode = CheckCodeUtil.decheckcode("", "FTsLYXakruIXda+QIcxlUkxJFXo7AB15cTelnpjcWi1dRYriOhxhnSkIZzLndzA9Rk+bLvkxwxDKkz8YyOjYRKf4vVsQuqYKVqR2jEQcMd3WNpPK9nG8cFmEchTsVyaFawkk99taDN0YdAoXQebqYFzmIWP0gVtwkGcpCjKdL3X39T6D/WqdTJp7nDFRocS9cE+Ku9rB2y2pHNIaFaNvf+SzRm8ItIhwTBTC0QtXPvoIualY7hNXBTqcDURsza3tt+O/FlShmwkwWdVj2/ZGxT+pjhjB8teM9VUCBtT1XJgcdHik2MOyKid3J7fxvwF14lrMpahbkC1lmOzFM0d3vGqxeKHZ80m32wPx0XIBp8ZsoAw3YwOUY2byjbjXqa5yvAJMhEvBBGALiItn32AE74BFXcrowyiRHicKrujmw0U4L8IGxApicFQCX/zsnxo5Ps0Guz+/EL30wn1xlHcNzyb/4sDwlzYd24Olkt331qxiRUcZ2cWRrKaTRqyhq2ReE");
//        Log.v("INJECT", "decheckcode1:" + strDecheckcode);
//        String strCheckCode = CheckCodeUtil.checkcode("", "[{\"baseDTO.os_type\":\"a\",\"baseDTO.device_no\":\"e20d58861dadb144\",\"baseDTO.mobile_no\":\"123444\",\"baseDTO.time_str\":\"20160713100652\",\"baseDTO.version_no\":\"1.1\",\"baseDTO.username\":\"15084821221\",\"password\":\"5416d7cd6ef195a0f7622a9c56b55e84\"}]");
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