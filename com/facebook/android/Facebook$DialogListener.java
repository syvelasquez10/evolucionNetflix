// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.os.Bundle;

public interface Facebook$DialogListener
{
    void onCancel();
    
    void onComplete(final Bundle p0);
    
    void onError(final DialogError p0);
    
    void onFacebookError(final FacebookError p0);
}
