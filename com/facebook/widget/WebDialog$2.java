// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.android.R$string;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.R$drawable;
import android.view.View$OnClickListener;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;

class WebDialog$2 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ WebDialog this$0;
    
    WebDialog$2(final WebDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.this$0.sendCancelToListener();
        this.this$0.dismiss();
    }
}
