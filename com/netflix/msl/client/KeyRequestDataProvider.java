// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.keyx.KeyRequestData;

public interface KeyRequestDataProvider<DATA extends KeyRequestData>
{
    DATA get();
}
