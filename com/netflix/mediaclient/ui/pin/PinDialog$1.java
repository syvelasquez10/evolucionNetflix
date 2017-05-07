// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.app.AlertDialog;
import android.text.method.MovementMethod;
import android.view.View$OnKeyListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.view.MotionEvent;
import android.text.Spannable;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;

class PinDialog$1 extends LinkMovementMethod
{
    final /* synthetic */ PinDialog this$0;
    
    PinDialog$1(final PinDialog this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouchEvent(final TextView textView, final Spannable spannable, final MotionEvent motionEvent) {
        this.this$0.mPinForgotView.setLinkTextColor(this.this$0.getResources().getColor(2131296422));
        return super.onTouchEvent(textView, spannable, motionEvent);
    }
}
