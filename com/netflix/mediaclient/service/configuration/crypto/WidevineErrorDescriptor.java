// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import android.app.Activity;
import com.netflix.mediaclient.util.StringUtils;
import android.net.Uri;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.content.Context;
import com.netflix.mediaclient.service.error.ErrorDescriptor;

public class WidevineErrorDescriptor implements ErrorDescriptor
{
    private static final String TAG = "ErrorAgent";
    private Throwable mCause;
    private Context mContext;
    private AlertDialogFactory$AlertDialogDescriptor mData;
    private String mLink;
    private int mMessageId;
    private Runnable mOkTask;
    private int mPriority;
    private StatusCode mStatusCode;
    private Runnable mTask;
    
    public WidevineErrorDescriptor(final Context context, final StatusCode statusCode, final Runnable runnable, final int n) {
        this(context, statusCode, null, Integer.MAX_VALUE, null, n, null, runnable);
    }
    
    public WidevineErrorDescriptor(final Context mContext, final StatusCode mStatusCode, final Throwable mCause, final int mPriority, final Runnable mTask, final int mMessageId, final String mLink, final Runnable mOkTask) {
        this.mPriority = Integer.MAX_VALUE;
        this.mContext = mContext;
        this.mStatusCode = mStatusCode;
        this.mCause = mCause;
        this.mPriority = mPriority;
        this.mTask = mTask;
        this.mMessageId = mMessageId;
        this.mLink = mLink;
        this.mOkTask = mOkTask;
        this.createData();
    }
    
    private void createData() {
        if (this.mStatusCode == null) {
            Log.e("ErrorAgent", "Required to display error dialog without status code!");
        }
        else if (Log.isLoggable()) {
            Log.d("ErrorAgent", "Required to display error dialog with status code " + this.mStatusCode.name() + ", " + this.mStatusCode.getValue());
        }
        final String message = this.getMessage(this.mMessageId, this.mStatusCode);
        final Uri uri = this.getUri(this.mLink);
        Runnable mOkTask;
        if ((mOkTask = this.mOkTask) == null) {
            mOkTask = new WidevineErrorDescriptor$1(this);
        }
        if (uri != null) {
            this.mData = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", message, null, mOkTask, this.mContext.getString(2131296631), (Runnable)new LaunchBrowser(this.mContext, uri));
            return;
        }
        this.mData = new AlertDialogFactory$AlertDialogDescriptor("", message, null, mOkTask);
    }
    
    private String getMessage(final int n, final StatusCode statusCode) {
        String string;
        if (n == Integer.MAX_VALUE) {
            string = this.mContext.getString(2131296651);
        }
        else {
            String s;
            if (statusCode != null) {
                s = this.mContext.getString(n, new Object[] { statusCode.getValue() });
            }
            else {
                s = this.mContext.getString(n);
            }
            string = s;
            if (s == null) {
                return this.mContext.getString(2131296651);
            }
        }
        return string;
    }
    
    private Uri getUri(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return Uri.parse(s);
    }
    
    @Override
    public Runnable getBackgroundTask() {
        return this.mTask;
    }
    
    public Throwable getCause() {
        return this.mCause;
    }
    
    @Override
    public AlertDialogFactory$AlertDialogDescriptor getData() {
        return this.mData;
    }
    
    @Override
    public int getPriority() {
        return this.mPriority;
    }
    
    @Override
    public boolean shouldReportToUserAsDialog(final Activity activity) {
        return true;
    }
}
