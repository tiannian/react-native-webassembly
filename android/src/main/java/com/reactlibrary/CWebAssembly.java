package com.reactlibrary;

public class CWebAssembly {
    public static native CWebAssemblyInstance instantiate(byte[] bufferSource);

    static {
        System.loadLibrary("wasm");
    }
}

