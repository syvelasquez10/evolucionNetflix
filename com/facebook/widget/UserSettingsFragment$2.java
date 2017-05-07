// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.Arrays;
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
import java.net.URI;
import com.facebook.android.R$drawable;
import com.facebook.internal.ImageDownloader;
import com.facebook.android.R$string;
import com.facebook.android.R$color;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.net.URISyntaxException;
import android.content.Context;
import com.facebook.internal.ImageRequest$Builder;
import com.facebook.android.R$dimen;
import com.facebook.internal.ImageRequest;
import android.os.Bundle;
import com.facebook.Request$GraphUserCallback;
import com.facebook.Request;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.Session$StatusCallback;
import android.widget.TextView;
import com.facebook.internal.ImageResponse;
import com.facebook.internal.ImageRequest$Callback;

class UserSettingsFragment$2 implements ImageRequest$Callback
{
    final /* synthetic */ UserSettingsFragment this$0;
    
    UserSettingsFragment$2(final UserSettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final ImageResponse imageResponse) {
        this.this$0.processImageResponse(this.this$0.user.getId(), imageResponse);
    }
}
