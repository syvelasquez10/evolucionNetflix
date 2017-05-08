// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Window$Callback;
import android.view.Window;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(24)
class AppCompatDelegateImplN extends AppCompatDelegateImplV23
{
    AppCompatDelegateImplN(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }
    
    @Override
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatDelegateImplN$AppCompatWindowCallbackN(this, window$Callback);
    }
}
