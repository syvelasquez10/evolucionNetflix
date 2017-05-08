// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.util.HashMap;
import javax.crypto.KeyAgreement;
import java.util.Map;

final class CryptoCache$6 extends ThreadLocal<Map<String, KeyAgreement>>
{
    @Override
    protected Map<String, KeyAgreement> initialValue() {
        return new HashMap<String, KeyAgreement>();
    }
}
