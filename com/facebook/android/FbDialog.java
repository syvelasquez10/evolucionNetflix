// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.widget.WebDialog$OnCompleteListener;
import android.os.Bundle;
import android.content.Context;
import com.facebook.widget.WebDialog;

@Deprecated
public class FbDialog extends WebDialog
{
    private Facebook$DialogListener mListener;
    
    public FbDialog(final Context context, final String s, final Bundle bundle, final Facebook$DialogListener dialogListener) {
        super(context, s, bundle, 16973840, null);
        this.setDialogListener(dialogListener);
    }
    
    public FbDialog(final Context context, final String s, final Bundle bundle, final Facebook$DialogListener dialogListener, final int n) {
        super(context, s, bundle, n, null);
        this.setDialogListener(dialogListener);
    }
    
    public FbDialog(final Context context, final String s, final Facebook$DialogListener facebook$DialogListener) {
        this(context, s, facebook$DialogListener, 16973840);
    }
    
    public FbDialog(final Context context, final String s, final Facebook$DialogListener dialogListener, final int n) {
        super(context, s, n);
        this.setDialogListener(dialogListener);
    }
    
    private void callDialogListener(final Bundle bundle, final FacebookException ex) {
        if (this.mListener == null) {
            return;
        }
        if (bundle != null) {
            this.mListener.onComplete(bundle);
            return;
        }
        if (ex instanceof FacebookDialogException) {
            final FacebookDialogException ex2 = (FacebookDialogException)ex;
            this.mListener.onError(new DialogError(ex2.getMessage(), ex2.getErrorCode(), ex2.getFailingUrl()));
            return;
        }
        if (ex instanceof FacebookOperationCanceledException) {
            this.mListener.onCancel();
            return;
        }
        this.mListener.onFacebookError(new FacebookError(ex.getMessage()));
    }
    
    private void setDialogListener(final Facebook$DialogListener mListener) {
        this.mListener = mListener;
        this.setOnCompleteListener(new FbDialog$1(this));
    }
}
