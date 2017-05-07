// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode$Callback;

class ActionBarActivityDelegateBase$ActionModeCallbackWrapper implements ActionMode$Callback
{
    private ActionMode$Callback mWrapped;
    final /* synthetic */ ActionBarActivityDelegateBase this$0;
    
    public ActionBarActivityDelegateBase$ActionModeCallbackWrapper(final ActionBarActivityDelegateBase this$0, final ActionMode$Callback mWrapped) {
        this.this$0 = this$0;
        this.mWrapped = mWrapped;
    }
    
    @Override
    public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
        return this.mWrapped.onActionItemClicked(actionMode, menuItem);
    }
    
    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrapped.onCreateActionMode(actionMode, menu);
    }
    
    @Override
    public void onDestroyActionMode(final ActionMode actionMode) {
        this.mWrapped.onDestroyActionMode(actionMode);
        Label_0110: {
            if (this.this$0.mActionModePopup == null) {
                break Label_0110;
            }
            this.this$0.mActivity.getWindow().getDecorView().removeCallbacks(this.this$0.mShowActionModePopup);
            this.this$0.mActionModePopup.dismiss();
        Label_0101_Outer:
            while (true) {
                if (this.this$0.mActionModeView != null) {
                    this.this$0.mActionModeView.removeAllViews();
                }
                while (true) {
                    if (this.this$0.mActivity == null) {
                        break Label_0101;
                    }
                    try {
                        this.this$0.mActivity.onSupportActionModeFinished(this.this$0.mActionMode);
                        this.this$0.mActionMode = null;
                        return;
                        while (true) {
                            this.this$0.mActionModeView.setVisibility(8);
                            ViewCompat.requestApplyInsets((View)this.this$0.mActionModeView.getParent());
                            continue Label_0101_Outer;
                            continue;
                        }
                    }
                    // iftrue(Label_0054:, this.this$0.mActionModeView.getParent() == null)
                    // iftrue(Label_0054:, this.this$0.mActionModeView == null)
                    catch (AbstractMethodError abstractMethodError) {
                        continue;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        return this.mWrapped.onPrepareActionMode(actionMode, menu);
    }
}
