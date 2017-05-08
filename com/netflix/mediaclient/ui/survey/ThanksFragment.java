// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.survey;

import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Handler;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import android.support.v4.app.Fragment;

public class ThanksFragment extends Fragment
{
    private static final long DELAY;
    
    static {
        DELAY = TimeUnit.SECONDS.toMillis(1L);
    }
    
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        new Handler().postDelayed((Runnable)new ThanksFragment$1(this), ThanksFragment.DELAY);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return layoutInflater.inflate(2130903130, viewGroup, false);
    }
}
