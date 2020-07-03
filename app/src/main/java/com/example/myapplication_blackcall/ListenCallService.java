package com.example.myapplication_blackcall;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Method;

public class ListenCallService extends Service {
    private TelephonyManager tm;
    private PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("TAG", "空闲(挂断电话/未来电之前)");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e("TAG", "响铃:来电号码"+phoneNumber);
                    if ("18375791396".equals(phoneNumber)){
                        endCall();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e("TAG", "接通");
                    break;
                default:
                    break;
            }
        }
    };

    private void endCall() {
        try {
            Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
            ITelephony telephony = ITelephony.Stub.asInterface(binder);
            telephony.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "Service onCreate()");
        tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "Service onDestroy()");
        tm.listen(listener, PhoneStateListener.LISTEN_NONE);
    }
}
