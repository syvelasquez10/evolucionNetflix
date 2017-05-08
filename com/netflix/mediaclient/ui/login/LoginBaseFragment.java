// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class LoginBaseFragment extends NetflixFrag
{
    protected static final String TAG = "LoginBaseFragment";
    protected Context mContext;
    protected LoginFragmentListener mLoginFragmentListener;
    
    public boolean isLoadingData() {
        return false;
    }
    
    @TargetApi(22)
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (Build$VERSION.SDK_INT < 23) {
            this.onAttachContext((Context)activity);
        }
    }
    
    public void onAttach(final Context context) {
        super.onAttach(context);
        this.onAttachContext(context);
    }
    
    protected void onAttachContext(final Context mContext) {
        this.mContext = mContext;
        if (mContext instanceof LoginFragmentListener) {
            this.mLoginFragmentListener = (LoginFragmentListener)mContext;
            return;
        }
        throw new RuntimeException(mContext.toString() + " must implement LoginFragmentListener");
    }
    
    protected void setupViews(final View view) {
        if (Log.isLoggable()) {
            Log.i("LoginBaseFragment", "setupViews");
        }
    }
}
