package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class WebAssemblyInstance extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public WebAssemblyInstance(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "WebAssemblyInstance";
    }

    static {
        System.loadLibrary("wasm");
    }

 /*   @ReactMethod
    public void instantiate(String bufferSource, Promise promise) {

    }*/
}
