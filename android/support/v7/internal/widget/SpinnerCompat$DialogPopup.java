// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.app.AlertDialog$Builder;
import android.view.View;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.ListAdapter;
import android.content.DialogInterface$OnClickListener;

class SpinnerCompat$DialogPopup implements DialogInterface$OnClickListener, SpinnerCompat$SpinnerPopup
{
    private ListAdapter mListAdapter;
    private AlertDialog mPopup;
    private CharSequence mPrompt;
    final /* synthetic */ SpinnerCompat this$0;
    
    private SpinnerCompat$DialogPopup(final SpinnerCompat this$0) {
        this.this$0 = this$0;
    }
    
    public void dismiss() {
        if (this.mPopup != null) {
            this.mPopup.dismiss();
            this.mPopup = null;
        }
    }
    
    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }
    
    public void onClick(final DialogInterface dialogInterface, final int selection) {
        this.this$0.setSelection(selection);
        if (this.this$0.mOnItemClickListener != null) {
            this.this$0.performItemClick(null, selection, this.mListAdapter.getItemId(selection));
        }
        this.dismiss();
    }
    
    public void setAdapter(final ListAdapter mListAdapter) {
        this.mListAdapter = mListAdapter;
    }
    
    public void setPromptText(final CharSequence mPrompt) {
        this.mPrompt = mPrompt;
    }
    
    public void show() {
        if (this.mListAdapter == null) {
            return;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.this$0.getContext());
        if (this.mPrompt != null) {
            alertDialog$Builder.setTitle(this.mPrompt);
        }
        (this.mPopup = alertDialog$Builder.setSingleChoiceItems(this.mListAdapter, this.this$0.getSelectedItemPosition(), (DialogInterface$OnClickListener)this).create()).show();
    }
}