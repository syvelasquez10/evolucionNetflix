// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphObject;

interface TestSession$TestAccount extends GraphObject
{
    String getAccessToken();
    
    String getId();
    
    String getName();
    
    void setName(final String p0);
}
