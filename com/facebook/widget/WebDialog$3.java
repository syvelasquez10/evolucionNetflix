// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.android.R$string;
import android.content.DialogInterface$OnCancelListener;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.android.R$drawable;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;
import android.view.View;
import android.view.View$OnClickListener;

class WebDialog$3 implements View$OnClickListener
{
    final /* synthetic */ WebDialog this$0;
    
    WebDialog$3(final WebDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.sendCancelToListener();
        this.this$0.dismiss();
    }
}
