package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import java.util.Base64;

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
    public void instantiate(String stringSource, Promise promise) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] buffer = decoder.decode(stringSource);
            WritableMap map = Arguments.createMap();
            int index = CWebAssembly.instantiate(buffer);
            map.pushInt("_instant_index", index);
            promise.resolve(map);
        } catch(Exception e) {
            promise.reject("NativeMethod", e);
        }
    }
}
