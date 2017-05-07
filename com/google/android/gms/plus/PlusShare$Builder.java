// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import java.util.Iterator;
import java.util.List;
import android.os.Parcelable;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.internal.n;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;

public class PlusShare$Builder
{
    private boolean alg;
    private ArrayList<Uri> alh;
    private final Context mContext;
    private final Intent mIntent;
    
    public PlusShare$Builder(final Activity mContext) {
        this.mContext = (Context)mContext;
        (this.mIntent = new Intent().setAction("android.intent.action.SEND")).addFlags(524288);
        if (mContext != null && mContext.getComponentName() != null) {
            this.alg = true;
        }
    }
    
    public PlusShare$Builder(final Activity activity, final PlusClient plusClient) {
        this(activity);
        n.a(plusClient != null, (Object)"PlusClient must not be null.");
        n.a(plusClient.isConnected(), (Object)"PlusClient must be connected to create an interactive post.");
        n.a(plusClient.mX().cd("https://www.googleapis.com/auth/plus.login"), (Object)"Must request PLUS_LOGIN scope in PlusClient to create an interactive post.");
        final Person currentPerson = plusClient.getCurrentPerson();
        String id;
        if (currentPerson != null) {
            id = currentPerson.getId();
        }
        else {
            id = "0";
        }
        this.mIntent.putExtra("com.google.android.apps.plus.SENDER_ID", id);
    }
    
    public PlusShare$Builder(final Context mContext) {
        this.mContext = mContext;
        this.mIntent = new Intent().setAction("android.intent.action.SEND");
    }
    
    public PlusShare$Builder addCallToAction(final String s, final Uri uri, final String s2) {
        n.a(this.alg, (Object)"Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
        n.b(uri != null && !TextUtils.isEmpty((CharSequence)uri.toString()), (Object)"Must provide a call to action URL");
        final Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            bundle.putString("label", s);
        }
        bundle.putString("url", uri.toString());
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            n.a(PlusShare.ca(s2), (Object)"The specified deep-link ID was malformed.");
            bundle.putString("deepLinkId", s2);
        }
        this.mIntent.putExtra("com.google.android.apps.plus.CALL_TO_ACTION", bundle);
        this.mIntent.putExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", true);
        this.mIntent.setType("text/plain");
        return this;
    }
    
    public PlusShare$Builder addStream(final Uri stream) {
        final Uri uri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri == null) {
            return this.setStream(stream);
        }
        if (this.alh == null) {
            this.alh = new ArrayList<Uri>();
        }
        this.alh.add(uri);
        this.alh.add(stream);
        return this;
    }
    
    public Intent getIntent() {
        final boolean b = true;
        boolean b2;
        if (this.alh != null && this.alh.size() > 1) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final boolean equals = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        final boolean booleanExtra = this.mIntent.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
        n.a(!b2 || !booleanExtra, (Object)"Call-to-action buttons are only available for URLs.");
        n.a(!booleanExtra || this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL"), (Object)"The content URL is required for interactive posts.");
        boolean b3 = b;
        if (booleanExtra) {
            b3 = b;
            if (!this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL")) {
                b3 = (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID") && b);
            }
        }
        n.a(b3, (Object)"Must set content URL or content deep-link ID to use a call-to-action button.");
        if (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
            n.a(PlusShare.ca(this.mIntent.getStringExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")), (Object)"The specified deep-link ID was malformed.");
        }
        if (!b2 && equals) {
            this.mIntent.setAction("android.intent.action.SEND");
            if (this.alh != null && !this.alh.isEmpty()) {
                this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.alh.get(0));
            }
            else {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
            this.alh = null;
        }
        if (b2 && !equals) {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.alh != null && !this.alh.isEmpty()) {
                this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)this.alh);
            }
            else {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
        }
        if ("com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.mIntent.getAction())) {
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
        }
        if (!this.mIntent.hasExtra("android.intent.extra.STREAM")) {
            this.mIntent.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
        }
        this.mIntent.setPackage("com.google.android.apps.plus");
        return this.mIntent;
    }
    
    public PlusShare$Builder setContentDeepLinkId(final String s) {
        return this.setContentDeepLinkId(s, null, null, null);
    }
    
    public PlusShare$Builder setContentDeepLinkId(final String s, final String s2, final String s3, final Uri uri) {
        n.b(this.alg, (Object)"Must include the launching activity with PlusShare.Builder constructor before setting deep links");
        n.b(!TextUtils.isEmpty((CharSequence)s), (Object)"The deepLinkId parameter is required.");
        final Bundle a = PlusShare.a(s2, s3, uri);
        this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID", s);
        this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA", a);
        this.mIntent.setType("text/plain");
        return this;
    }
    
    public PlusShare$Builder setContentUrl(final Uri uri) {
        String string = null;
        if (uri != null) {
            string = uri.toString();
        }
        if (TextUtils.isEmpty((CharSequence)string)) {
            this.mIntent.removeExtra("com.google.android.apps.plus.CONTENT_URL");
            return this;
        }
        this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_URL", string);
        return this;
    }
    
    public PlusShare$Builder setRecipients(final Person person, final List<Person> recipients) {
        final Intent mIntent = this.mIntent;
        String id;
        if (person != null) {
            id = person.getId();
        }
        else {
            id = "0";
        }
        mIntent.putExtra("com.google.android.apps.plus.SENDER_ID", id);
        return this.setRecipients(recipients);
    }
    
    @Deprecated
    public PlusShare$Builder setRecipients(final List<Person> list) {
        int size;
        if (list != null) {
            size = list.size();
        }
        else {
            size = 0;
        }
        if (size == 0) {
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_IDS");
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES");
            return this;
        }
        final ArrayList list2 = new ArrayList<String>(size);
        final ArrayList list3 = new ArrayList<String>(size);
        for (final Person person : list) {
            list2.add(person.getId());
            list3.add(person.getDisplayName());
        }
        this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_IDS", list2);
        this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES", list3);
        return this;
    }
    
    public PlusShare$Builder setStream(final Uri uri) {
        this.alh = null;
        this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
        return this;
    }
    
    public PlusShare$Builder setText(final CharSequence charSequence) {
        this.mIntent.putExtra("android.intent.extra.TEXT", charSequence);
        return this;
    }
    
    public PlusShare$Builder setType(final String type) {
        this.mIntent.setType(type);
        return this;
    }
}
