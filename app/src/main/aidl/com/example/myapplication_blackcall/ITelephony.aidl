// ITelephony.aidl
package com.example.myapplication_blackcall;

// Declare any non-default types here with import statements

interface ITelephony {
        //挂断电话
        boolean endCall();

        //接听电话
        void answerRingingCall();
}
