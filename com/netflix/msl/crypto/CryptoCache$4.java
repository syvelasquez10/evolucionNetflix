// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import javax.crypto.Mac;
import java.util.Map;

final class CryptoCache$4 extends ThreadLocal<Map<String, Mac>>
{
    @Override
    protected Map<String, Mac> initialValue() {
        return new HashMap<String, Mac>();
    }
}
