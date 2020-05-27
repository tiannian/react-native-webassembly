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
    public void instantiate(String stringSource, Promise promise) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            bytes[] buffer = decoder.decode(stringSource);
            CWebAssemblyInstance cwai = CWebAssembly.instantiate(buffer);
            promise.resolve(cwai);
        } catch(Exception e) {
            promise.reject("NativeMethod", e);
        }
    }
}
