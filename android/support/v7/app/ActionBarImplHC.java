// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ActionMode$Callback;
import android.support.v7.appcompat.R;
import android.view.ActionMode;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;

class ActionBarImplHC extends ActionBarImplBase implements OnActionModeForChildListener
{
    private ActionMode mCurActionMode;
    final NativeActionModeAwareLayout mNativeActionModeAwareLayout;
    
    public ActionBarImplHC(final ActionBarActivity actionBarActivity, final Callback callback) {
        super(actionBarActivity, callback);
        this.mNativeActionModeAwareLayout = (NativeActionModeAwareLayout)actionBarActivity.findViewById(R.id.action_bar_root);
        if (this.mNativeActionModeAwareLayout != null) {
            this.mNativeActionModeAwareLayout.setActionModeForChildListener((NativeActionModeAwareLayout.OnActionModeForChildListener)this);
        }
    }
    
    @Override
    public void hide() {
        super.hide();
        if (this.mCurActionMode != null) {
            this.mCurActionMode.finish();
        }
    }
    
    @Override
    boolean isShowHideAnimationEnabled() {
        return this.mCurActionMode == null && super.isShowHideAnimationEnabled();
    }
    
    @Override
    public ActionMode$Callback onActionModeForChild(final ActionMode$Callback actionMode$Callback) {
        return (ActionMode$Callback)new CallbackWrapper(actionMode$Callback);
    }
    
    @Override
    public void show() {
        super.show();
        if (this.mCurActionMode != null) {
            this.mCurActionMode.finish();
        }
    }
    
    private class CallbackWrapper implements ActionMode$Callback
    {
        private final ActionMode$Callback mWrappedCallback;
        
        CallbackWrapper(final ActionMode$Callback mWrappedCallback) {
            this.mWrappedCallback = mWrappedCallback;
        }
        
        public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
            return this.mWrappedCallback.onActionItemClicked(actionMode, menuItem);
        }
        
        public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
            final boolean onCreateActionMode = this.mWrappedCallback.onCreateActionMode(actionMode, menu);
            if (onCreateActionMode) {
                ActionBarImplHC.this.mCurActionMode = actionMode;
                ActionBarImplHC.this.showForActionMode();
            }
            return onCreateActionMode;
        }
        
        public void onDestroyActionMode(final ActionMode actionMode) {
            this.mWrappedCallback.onDestroyActionMode(actionMode);
            ActionBarImplHC.this.hideForActionMode();
            ActionBarImplHC.this.mCurActionMode = null;
        }
        
        public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
            return this.mWrappedCallback.onPrepareActionMode(actionMode, menu);
        }
    }
}
