// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import java.security.MessageDigest;
import java.util.Map;

final class CryptoCache$3 extends ThreadLocal<Map<String, MessageDigest>>
{
    @Override
    protected Map<String, MessageDigest> initialValue() {
        return new HashMap<String, MessageDigest>();
    }
}
