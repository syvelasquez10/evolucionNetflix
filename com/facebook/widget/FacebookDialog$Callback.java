// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Bundle;

public interface FacebookDialog$Callback
{
    void onComplete(final FacebookDialog$PendingCall p0, final Bundle p1);
    
    void onError(final FacebookDialog$PendingCall p0, final Exception p1, final Bundle p2);
}
