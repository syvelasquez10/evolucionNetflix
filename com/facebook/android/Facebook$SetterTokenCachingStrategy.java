// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Collections;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Settings;
import android.content.ContentResolver;
import java.util.Arrays;
import com.facebook.Session$StatusCallback;
import com.facebook.Session$OpenRequest;
import android.content.Context;
import com.facebook.Session$Builder;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.Session;
import android.app.Activity;
import android.net.Uri;
import com.facebook.AccessTokenSource;
import java.util.List;
import android.os.Bundle;
import com.facebook.TokenCachingStrategy;

class Facebook$SetterTokenCachingStrategy extends TokenCachingStrategy
{
    final /* synthetic */ Facebook this$0;
    
    private Facebook$SetterTokenCachingStrategy(final Facebook this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void clear() {
        this.this$0.accessToken = null;
    }
    
    @Override
    public Bundle load() {
        final Bundle bundle = new Bundle();
        if (this.this$0.accessToken != null) {
            TokenCachingStrategy.putToken(bundle, this.this$0.accessToken);
            TokenCachingStrategy.putExpirationMilliseconds(bundle, this.this$0.accessExpiresMillisecondsAfterEpoch);
            TokenCachingStrategy.putPermissions(bundle, stringList(this.this$0.pendingAuthorizationPermissions));
            TokenCachingStrategy.putSource(bundle, AccessTokenSource.WEB_VIEW);
            TokenCachingStrategy.putLastRefreshMilliseconds(bundle, this.this$0.lastAccessUpdateMillisecondsAfterEpoch);
        }
        return bundle;
    }
    
    @Override
    public void save(final Bundle bundle) {
        this.this$0.accessToken = TokenCachingStrategy.getToken(bundle);
        this.this$0.accessExpiresMillisecondsAfterEpoch = TokenCachingStrategy.getExpirationMilliseconds(bundle);
        this.this$0.pendingAuthorizationPermissions = stringArray(TokenCachingStrategy.getPermissions(bundle));
        this.this$0.lastAccessUpdateMillisecondsAfterEpoch = TokenCachingStrategy.getLastRefreshMilliseconds(bundle);
    }
}
