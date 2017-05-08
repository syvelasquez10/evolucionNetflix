// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import android.support.v7.app.AlertDialog;
import android.text.method.MovementMethod;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.support.v7.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.view.inputmethod.InputMethodManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.Log;
import android.widget.EditText;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View$OnKeyListener;

class PinDialog$PinDialogOnKeyPress implements View$OnKeyListener
{
    final /* synthetic */ PinDialog this$0;
    
    private PinDialog$PinDialogOnKeyPress(final PinDialog this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (n != 67) {
            this.this$0.mPinEditText.setError((CharSequence)null, (Drawable)null);
            this.this$0.showErrorIcon(false);
            final String string = ((EditText)view).getText().toString();
            if (string.length() >= PinDialog.PIN_LENGTH) {
                if (this.this$0.getServiceManager() == null) {
                    Log.d("nf_pin", "serviceManager is null");
                    return false;
                }
                this.this$0.processUserInputPin(string);
                return false;
            }
        }
        return false;
    }
}
