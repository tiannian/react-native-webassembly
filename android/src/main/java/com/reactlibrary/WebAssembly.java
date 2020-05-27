package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class WebAssembly extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public WebAssembly(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "WebAssembly";
    }

    @ReactMethod
    public void instantiate(byte[] bufferSource, Promise promise) {
        try {
            CWebAssemblyInstance cwai = CWebAssembly.instantiate(bufferSource);
            promise.resolve(cwai);
        } catch(Exception e) {
            Log.e("NativeMethod");
            promise.reject("NativeMethod", e);
        }
    }
}
