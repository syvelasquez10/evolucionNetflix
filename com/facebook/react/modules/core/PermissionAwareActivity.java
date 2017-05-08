// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

public interface PermissionAwareActivity
{
    void requestPermissions(final String[] p0, final int p1, final PermissionListener p2);
    
    boolean shouldShowRequestPermissionRationale(final String p0);
}
