// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import java.security.KeyPairGenerator;
import java.util.Map;

final class CryptoCache$7 extends ThreadLocal<Map<String, KeyPairGenerator>>
{
    @Override
    protected Map<String, KeyPairGenerator> initialValue() {
        return new HashMap<String, KeyPairGenerator>();
    }
}
