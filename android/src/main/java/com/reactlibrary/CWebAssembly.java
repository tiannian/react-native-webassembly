package com.reactlibrary;

public class CWebAssembly {
    public static native int instantiate(byte[] bufferSource);

    static {
        System.loadLibrary("wasm");
    }
}

