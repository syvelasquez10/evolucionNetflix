// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import android.app.Fragment;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.Log;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class SocialOptInDialogFrag extends NetflixDialogFrag
{
    private static final String TAG = "social";
    private final AtomicBoolean mClicked;
    
    public SocialOptInDialogFrag() {
        this.mClicked = new AtomicBoolean(false);
    }
    
    public static SocialOptInDialogFrag newInstance() {
        final SocialOptInDialogFrag socialOptInDialogFrag = new SocialOptInDialogFrag();
        socialOptInDialogFrag.setStyle(1, 2131558710);
        socialOptInDialogFrag.setArguments(new Bundle());
        return socialOptInDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903145, viewGroup);
        final Button button = (Button)inflate.findViewById(2131165291);
        final Button button2 = (Button)inflate.findViewById(2131165295);
        button2.requestFocus();
        final Activity activity = this.getActivity();
        if (!(activity instanceof OptInResponseHandler)) {
            Log.e("social", "Activity is not OptInResponseHandler! THis should not happen!");
            return inflate;
        }
        final OptInResponseHandler optInResponseHandler = (OptInResponseHandler)activity;
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.d("social", "User opted In!");
                synchronized (SocialOptInDialogFrag.this.mClicked) {
                    if (SocialOptInDialogFrag.this.mClicked.get()) {
                        Log.w("social", "Already clicked!");
                        return;
                    }
                    SocialOptInDialogFrag.this.mClicked.set(true);
                    // monitorexit(SocialOptInDialogFrag.access$000(this.this$0))
                    SocialOptInDialogFrag.this.dismiss();
                    SocialOptInDialogFrag.this.getFragmentManager().beginTransaction().remove((Fragment)SocialOptInDialogFrag.this).commit();
                    optInResponseHandler.onAccept();
                }
            }
        });
        if (button != null) {
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Log.d("social", "User opted out!");
                    synchronized (SocialOptInDialogFrag.this.mClicked) {
                        if (SocialOptInDialogFrag.this.mClicked.get()) {
                            Log.w("social", "Already clicked!");
                            return;
                        }
                        SocialOptInDialogFrag.this.mClicked.set(true);
                        // monitorexit(SocialOptInDialogFrag.access$000(this.this$0))
                        SocialOptInDialogFrag.this.dismiss();
                        SocialOptInDialogFrag.this.getFragmentManager().beginTransaction().remove((Fragment)SocialOptInDialogFrag.this).commit();
                        optInResponseHandler.onDecline();
                    }
                }
            });
            return inflate;
        }
        Log.e("social", "NO button not found! THis should not happen!");
        return inflate;
    }
    
    public interface OptInResponseHandler
    {
        void onAccept();
        
        void onDecline();
    }
}
