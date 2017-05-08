// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import android.util.Base64;
import com.netflix.msl.util.Base64$Base64Impl;

public class AndroidBase64Impl implements Base64$Base64Impl
{
    private static final int DECODE_FLAGS = 2;
    private static final int ENCODE_FLAGS = 2;
    
    @Override
    public byte[] decode(final String s) {
        return Base64.decode(s, 2);
    }
    
    @Override
    public String encode(final byte[] array) {
        return Base64.encodeToString(array, 2);
    }
}
