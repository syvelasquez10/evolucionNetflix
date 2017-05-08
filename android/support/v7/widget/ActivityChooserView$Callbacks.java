// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.AdapterView;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;

class ActivityChooserView$Callbacks implements View$OnClickListener, View$OnLongClickListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener
{
    final /* synthetic */ ActivityChooserView this$0;
    
    private void notifyOnDismissListener() {
        if (this.this$0.mOnDismissListener != null) {
            this.this$0.mOnDismissListener.onDismiss();
        }
    }
    
    public void onClick(final View view) {
        if (view == this.this$0.mDefaultActivityButton) {
            this.this$0.dismissPopup();
            final Intent chooseActivity = this.this$0.mAdapter.getDataModel().chooseActivity(this.this$0.mAdapter.getDataModel().getActivityIndex(this.this$0.mAdapter.getDefaultActivity()));
            if (chooseActivity != null) {
                chooseActivity.addFlags(524288);
                this.this$0.getContext().startActivity(chooseActivity);
            }
            return;
        }
        if (view == this.this$0.mExpandActivityOverflowButton) {
            this.this$0.mIsSelectingDefaultActivity = false;
            this.this$0.showPopupUnchecked(this.this$0.mInitialActivityCount);
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void onDismiss() {
        this.notifyOnDismissListener();
        if (this.this$0.mProvider != null) {
            this.this$0.mProvider.subUiVisibilityChanged(false);
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, int defaultActivity, final long n) {
        switch (((ActivityChooserView$ActivityChooserViewAdapter)adapterView.getAdapter()).getItemViewType(defaultActivity)) {
            default: {
                throw new IllegalArgumentException();
            }
            case 1: {
                this.this$0.showPopupUnchecked(Integer.MAX_VALUE);
                break;
            }
            case 0: {
                this.this$0.dismissPopup();
                if (this.this$0.mIsSelectingDefaultActivity) {
                    if (defaultActivity > 0) {
                        this.this$0.mAdapter.getDataModel().setDefaultActivity(defaultActivity);
                        return;
                    }
                    break;
                }
                else {
                    if (!this.this$0.mAdapter.getShowDefaultActivity()) {
                        ++defaultActivity;
                    }
                    final Intent chooseActivity = this.this$0.mAdapter.getDataModel().chooseActivity(defaultActivity);
                    if (chooseActivity != null) {
                        chooseActivity.addFlags(524288);
                        this.this$0.getContext().startActivity(chooseActivity);
                        return;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public boolean onLongClick(final View view) {
        if (view == this.this$0.mDefaultActivityButton) {
            if (this.this$0.mAdapter.getCount() > 0) {
                this.this$0.mIsSelectingDefaultActivity = true;
                this.this$0.showPopupUnchecked(this.this$0.mInitialActivityCount);
            }
            return true;
        }
        throw new IllegalArgumentException();
    }
}
