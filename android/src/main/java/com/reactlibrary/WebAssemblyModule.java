package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class WebAssemblyModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public WebAssemblyModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "WebAssembly";
    }

    @ReactMethod
    public String addTest(String stringArgument) {
        // TODO: Implement some actually useful functionality
        return stringArgument + " test";
    }
}
