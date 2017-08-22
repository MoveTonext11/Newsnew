package zhangxuelei1506d.headline;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/8/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=597e812b");
    }
}
