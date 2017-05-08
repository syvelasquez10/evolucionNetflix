// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import javax.crypto.Cipher;
import java.util.Map;

final class CryptoCache$1 extends ThreadLocal<Map<String, Cipher>>
{
    @Override
    protected Map<String, Cipher> initialValue() {
        return new HashMap<String, Cipher>();
    }
}
