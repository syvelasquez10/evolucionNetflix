// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.Window$Callback;

class AppCompatDelegateImplV23$AppCompatWindowCallbackV23 extends AppCompatDelegateImplV14$AppCompatWindowCallbackV14
{
    final /* synthetic */ AppCompatDelegateImplV23 this$0;
    
    AppCompatDelegateImplV23$AppCompatWindowCallbackV23(final AppCompatDelegateImplV23 this$0, final Window$Callback window$Callback) {
        this.this$0 = this$0;
        super(this$0, window$Callback);
    }
    
    @Override
    public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
        return null;
    }
    
    @Override
    public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback, final int n) {
        if (this.this$0.isHandleNativeActionModesEnabled()) {
            switch (n) {
                case 0: {
                    return this.startAsSupportActionMode(actionMode$Callback);
                }
            }
        }
        return super.onWindowStartingActionMode(actionMode$Callback, n);
    }
}
