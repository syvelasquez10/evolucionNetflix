// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.widget.Toast;
import android.view.View;
import android.view.View$OnLongClickListener;

final class ViewUtils$3 implements View$OnLongClickListener
{
    final /* synthetic */ CharSequence val$desc;
    final /* synthetic */ View val$root;
    
    ViewUtils$3(final View val$root, final CharSequence val$desc) {
        this.val$root = val$root;
        this.val$desc = val$desc;
    }
    
    public boolean onLongClick(final View view) {
        Toast.makeText(this.val$root.getContext(), this.val$desc, 0).show();
        return true;
    }
}
