// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.SessionState;
import android.support.v4.app.Fragment;
import com.facebook.android.R$id;
import com.facebook.android.R$layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Intent;
import java.util.List;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionDefaultAudience;
import java.net.URL;
import com.facebook.android.R$drawable;
import com.facebook.android.R$string;
import com.facebook.android.R$color;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.net.MalformedURLException;
import android.content.Context;
import com.facebook.android.R$dimen;
import android.os.Bundle;
import com.facebook.Request;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import com.facebook.Session$StatusCallback;
import android.widget.TextView;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.facebook.Session;
import com.facebook.Request$GraphUserCallback;

class UserSettingsFragment$1 implements Request$GraphUserCallback
{
    final /* synthetic */ UserSettingsFragment this$0;
    final /* synthetic */ Session val$currentSession;
    
    UserSettingsFragment$1(final UserSettingsFragment this$0, final Session val$currentSession) {
        this.this$0 = this$0;
        this.val$currentSession = val$currentSession;
    }
    
    @Override
    public void onCompleted(final GraphUser graphUser, final Response response) {
        if (this.val$currentSession == this.this$0.getSession()) {
            this.this$0.user = graphUser;
            this.this$0.updateUI();
        }
        if (response.getError() != null) {
            this.this$0.loginButton.handleError(response.getError().getException());
        }
    }
}
