package com.reactlibrary;

public class CWebAssemblyInstance {
    private final int instIndex;

    CWebAssemblyInstance(int instIndex) {
        this.instIndex = instIndex;
    }

    static {
        System.loadLibrary("wasm");
    }
}

