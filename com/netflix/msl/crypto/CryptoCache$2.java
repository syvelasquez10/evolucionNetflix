// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import java.security.Signature;
import java.util.Map;

final class CryptoCache$2 extends ThreadLocal<Map<String, Signature>>
{
    @Override
    protected Map<String, Signature> initialValue() {
        return new HashMap<String, Signature>();
    }
}
