// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.text.Html;
import android.text.Spanned;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.util.ArrayList;
import android.content.Intent;
import android.content.ComponentName;
import android.app.Activity;

public class ShareCompat$IntentReader
{
    private static final String TAG = "IntentReader";
    private Activity mActivity;
    private ComponentName mCallingActivity;
    private String mCallingPackage;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    
    private ShareCompat$IntentReader(final Activity mActivity) {
        this.mActivity = mActivity;
        this.mIntent = mActivity.getIntent();
        this.mCallingPackage = ShareCompat.getCallingPackage(mActivity);
        this.mCallingActivity = ShareCompat.getCallingActivity(mActivity);
    }
    
    public static ShareCompat$IntentReader from(final Activity activity) {
        return new ShareCompat$IntentReader(activity);
    }
    
    public ComponentName getCallingActivity() {
        return this.mCallingActivity;
    }
    
    public Drawable getCallingActivityIcon() {
        if (this.mCallingActivity == null) {
            return null;
        }
        final PackageManager packageManager = this.mActivity.getPackageManager();
        try {
            return packageManager.getActivityIcon(this.mCallingActivity);
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("IntentReader", "Could not retrieve icon for calling activity", (Throwable)ex);
            return null;
        }
    }
    
    public Drawable getCallingApplicationIcon() {
        if (this.mCallingPackage == null) {
            return null;
        }
        final PackageManager packageManager = this.mActivity.getPackageManager();
        try {
            return packageManager.getApplicationIcon(this.mCallingPackage);
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("IntentReader", "Could not retrieve icon for calling application", (Throwable)ex);
            return null;
        }
    }
    
    public CharSequence getCallingApplicationLabel() {
        if (this.mCallingPackage == null) {
            return null;
        }
        final PackageManager packageManager = this.mActivity.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mCallingPackage, 0));
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("IntentReader", "Could not retrieve label for calling application", (Throwable)ex);
            return null;
        }
    }
    
    public String getCallingPackage() {
        return this.mCallingPackage;
    }
    
    public String[] getEmailBcc() {
        return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
    }
    
    public String[] getEmailCc() {
        return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
    }
    
    public String[] getEmailTo() {
        return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
    }
    
    public String getHtmlText() {
        final String stringExtra = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
        if (stringExtra == null) {
            final CharSequence text = this.getText();
            if (text instanceof Spanned) {
                return Html.toHtml((Spanned)text);
            }
            if (text != null) {
                return ShareCompat.IMPL.escapeHtml(text);
            }
        }
        return stringExtra;
    }
    
    public Uri getStream() {
        return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
    }
    
    public Uri getStream(final int n) {
        if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = (ArrayList<Uri>)this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        }
        if (this.mStreams != null) {
            return this.mStreams.get(n);
        }
        if (n == 0) {
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }
        throw new IndexOutOfBoundsException("Stream items available: " + this.getStreamCount() + " index requested: " + n);
    }
    
    public int getStreamCount() {
        if (this.mStreams == null && this.isMultipleShare()) {
            this.mStreams = (ArrayList<Uri>)this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        }
        if (this.mStreams != null) {
            return this.mStreams.size();
        }
        if (this.mIntent.hasExtra("android.intent.extra.STREAM")) {
            return 1;
        }
        return 0;
    }
    
    public String getSubject() {
        return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
    }
    
    public CharSequence getText() {
        return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
    }
    
    public String getType() {
        return this.mIntent.getType();
    }
    
    public boolean isMultipleShare() {
        return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
    }
    
    public boolean isShareIntent() {
        final String action = this.mIntent.getAction();
        return "android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action);
    }
    
    public boolean isSingleShare() {
        return "android.intent.action.SEND".equals(this.mIntent.getAction());
    }
}
