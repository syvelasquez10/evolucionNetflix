// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import java.util.Iterator;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.internal.fq;
import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import android.content.Intent;
import com.google.android.gms.internal.ih;
import com.google.android.gms.plus.model.people.Person;
import android.util.Log;
import android.text.TextUtils;
import android.os.Bundle;
import android.net.Uri;

public final class PlusShare
{
    public static final String EXTRA_CALL_TO_ACTION = "com.google.android.apps.plus.CALL_TO_ACTION";
    public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
    public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
    public static final String EXTRA_CONTENT_URL = "com.google.android.apps.plus.CONTENT_URL";
    public static final String EXTRA_IS_INTERACTIVE_POST = "com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST";
    public static final String EXTRA_SENDER_ID = "com.google.android.apps.plus.SENDER_ID";
    public static final String KEY_CALL_TO_ACTION_DEEP_LINK_ID = "deepLinkId";
    public static final String KEY_CALL_TO_ACTION_LABEL = "label";
    public static final String KEY_CALL_TO_ACTION_URL = "url";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
    public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
    public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";
    
    protected PlusShare() {
        throw new AssertionError();
    }
    
    public static Bundle a(final String s, final String s2, final Uri uri) {
        final Bundle bundle = new Bundle();
        bundle.putString("title", s);
        bundle.putString("description", s2);
        if (uri != null) {
            bundle.putString("thumbnailUrl", uri.toString());
        }
        return bundle;
    }
    
    protected static boolean bd(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            Log.e("GooglePlusPlatform", "The provided deep-link ID is empty.");
            return false;
        }
        if (s.contains(" ")) {
            Log.e("GooglePlusPlatform", "Spaces are not allowed in deep-link IDs.");
            return false;
        }
        return true;
    }
    
    public static Person createPerson(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("MinimalPerson ID must not be empty.");
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            throw new IllegalArgumentException("Display name must not be empty.");
        }
        return new ih(s2, s, null, 0, null);
    }
    
    public static String getDeepLinkId(final Intent intent) {
        String queryParameter = null;
        if (intent != null) {
            queryParameter = queryParameter;
            if (intent.getData() != null) {
                queryParameter = intent.getData().getQueryParameter("deep_link_id");
            }
        }
        return queryParameter;
    }
    
    public static class Builder
    {
        private boolean TZ;
        private ArrayList<Uri> Ua;
        private final Context mContext;
        private final Intent mIntent;
        
        public Builder(final Activity mContext) {
            this.mContext = (Context)mContext;
            (this.mIntent = new Intent().setAction("android.intent.action.SEND")).addFlags(524288);
            if (mContext != null && mContext.getComponentName() != null) {
                this.TZ = true;
            }
        }
        
        public Builder(final Activity activity, final PlusClient plusClient) {
            this(activity);
            fq.a(plusClient != null, (Object)"PlusClient must not be null.");
            fq.a(plusClient.isConnected(), (Object)"PlusClient must be connected to create an interactive post.");
            fq.a(plusClient.iI().bg("https://www.googleapis.com/auth/plus.login"), (Object)"Must request PLUS_LOGIN scope in PlusClient to create an interactive post.");
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
        
        public Builder(final Context mContext) {
            this.mContext = mContext;
            this.mIntent = new Intent().setAction("android.intent.action.SEND");
        }
        
        public Builder addCallToAction(final String s, final Uri uri, final String s2) {
            fq.a(this.TZ, (Object)"Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
            fq.b(uri != null && !TextUtils.isEmpty((CharSequence)uri.toString()), "Must provide a call to action URL");
            final Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty((CharSequence)s)) {
                bundle.putString("label", s);
            }
            bundle.putString("url", uri.toString());
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                fq.a(PlusShare.bd(s2), (Object)"The specified deep-link ID was malformed.");
                bundle.putString("deepLinkId", s2);
            }
            this.mIntent.putExtra("com.google.android.apps.plus.CALL_TO_ACTION", bundle);
            this.mIntent.putExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", true);
            this.mIntent.setType("text/plain");
            return this;
        }
        
        public Builder addStream(final Uri stream) {
            final Uri uri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if (uri == null) {
                return this.setStream(stream);
            }
            if (this.Ua == null) {
                this.Ua = new ArrayList<Uri>();
            }
            this.Ua.add(uri);
            this.Ua.add(stream);
            return this;
        }
        
        public Intent getIntent() {
            final boolean b = true;
            boolean b2;
            if (this.Ua != null && this.Ua.size() > 1) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final boolean equals = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
            final boolean booleanExtra = this.mIntent.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
            fq.a(!b2 || !booleanExtra, (Object)"Call-to-action buttons are only available for URLs.");
            fq.a(!booleanExtra || this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL"), (Object)"The content URL is required for interactive posts.");
            boolean b3 = b;
            if (booleanExtra) {
                b3 = b;
                if (!this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL")) {
                    b3 = (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID") && b);
                }
            }
            fq.a(b3, (Object)"Must set content URL or content deep-link ID to use a call-to-action button.");
            if (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
                fq.a(PlusShare.bd(this.mIntent.getStringExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")), (Object)"The specified deep-link ID was malformed.");
            }
            if (!b2 && equals) {
                this.mIntent.setAction("android.intent.action.SEND");
                if (this.Ua != null && !this.Ua.isEmpty()) {
                    this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.Ua.get(0));
                }
                else {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                }
                this.Ua = null;
            }
            if (b2 && !equals) {
                this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.Ua != null && !this.Ua.isEmpty()) {
                    this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)this.Ua);
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
        
        public Builder setContentDeepLinkId(final String s) {
            return this.setContentDeepLinkId(s, null, null, null);
        }
        
        public Builder setContentDeepLinkId(final String s, final String s2, final String s3, final Uri uri) {
            fq.b(this.TZ, "Must include the launching activity with PlusShare.Builder constructor before setting deep links");
            fq.b(!TextUtils.isEmpty((CharSequence)s), "The deepLinkId parameter is required.");
            final Bundle a = PlusShare.a(s2, s3, uri);
            this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID", s);
            this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA", a);
            this.mIntent.setType("text/plain");
            return this;
        }
        
        public Builder setContentUrl(final Uri uri) {
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
        
        public Builder setRecipients(final Person person, final List<Person> recipients) {
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
        public Builder setRecipients(final List<Person> list) {
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
        
        public Builder setStream(final Uri uri) {
            this.Ua = null;
            this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
            return this;
        }
        
        public Builder setText(final CharSequence charSequence) {
            this.mIntent.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }
        
        public Builder setType(final String type) {
            this.mIntent.setType(type);
            return this;
        }
    }
}
