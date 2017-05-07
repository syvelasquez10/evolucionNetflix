// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import android.app.AlertDialog;
import android.text.method.MovementMethod;
import android.view.View$OnKeyListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.view.View;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class PinDialog$PinDialogOnCancel implements DialogInterface$OnClickListener
{
    final /* synthetic */ PinDialog this$0;
    
    private PinDialog$PinDialogOnCancel(final PinDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.this$0.mActive = false;
        this.this$0.dismissAndNotifyCallers();
    }
}
