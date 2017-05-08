// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

interface AttachDetachListener
{
    public static final AttachDetachListener[] EMPTY_ARRAY = new AttachDetachListener[0];
    
    void onAttached(final FlatViewGroup$InvalidateCallback p0);
    
    void onDetached();
}
