// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import java.security.KeyFactory;
import java.util.Map;

final class CryptoCache$5 extends ThreadLocal<Map<String, KeyFactory>>
{
    @Override
    protected Map<String, KeyFactory> initialValue() {
        return new HashMap<String, KeyFactory>();
    }
}
