// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.ActivityNotFoundException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import android.content.Intent;
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import java.util.Date;
import android.os.Handler;
import java.util.List;
import android.os.Bundle;
import android.content.Context;
import java.util.Set;
import java.io.Serializable;

class Session$3 implements AuthorizationClient$OnCompletedListener
{
    final /* synthetic */ Session this$0;
    
    Session$3(final Session this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final AuthorizationClient$Result authorizationClient$Result) {
        int n;
        if (authorizationClient$Result.code == AuthorizationClient$Result$Code.CANCEL) {
            n = 0;
        }
        else {
            n = -1;
        }
        this.this$0.handleAuthorizationResult(n, authorizationClient$Result);
    }
}
