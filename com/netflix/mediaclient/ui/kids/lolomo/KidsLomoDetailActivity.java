// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.Log;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.os.Parcelable;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class KidsLomoDetailActivity extends FragmentHostActivity implements ViewRecyclerProvider
{
    private static final String EXTRA_LOMO_PARCELABLE = "lomo_parcelable";
    private static final String TAG = "KidsLomoDetailActivity";
    private LoMo lomo;
    
    public static void show(final NetflixActivity netflixActivity, final LoMo loMo) {
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)KidsLomoDetailActivity.class).putExtra("lomo_parcelable", (Parcelable)loMo));
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                KidsLomoDetailActivity.this.getPrimaryFrag().onManagerReady(serviceManager, status);
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                KidsLomoDetailActivity.this.getPrimaryFrag().onManagerUnavailable(serviceManager, status);
            }
        };
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        Log.v("KidsLomoDetailActivity", "createPrimaryFrag, lomo id: " + this.lomo.getId());
        return KidsLomoDetailFrag.create(this.lomo);
    }
    
    @Override
    public KidsLomoDetailFrag getPrimaryFrag() {
        return (KidsLomoDetailFrag)super.getPrimaryFrag();
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.browseTitles;
    }
    
    @Override
    public ViewRecycler getViewRecycler() {
        return null;
    }
    
    @Override
    public boolean isForKids() {
        return true;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.lomo = (LoMo)this.getIntent().getParcelableExtra("lomo_parcelable");
        if (Log.isLoggable("KidsLomoDetailActivity", 2)) {
            Log.v("KidsLomoDetailActivity", this.lomo.toString());
        }
        super.onCreate(bundle);
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        netflixActionBar.setTitle(this.lomo.getTitle());
        netflixActionBar.setLogoType(NetflixActionBar.LogoType.GONE);
    }
}
