// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Bundle;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.Log;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.os.Parcelable;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecyclerProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class KidsLomoDetailActivity extends FragmentHostActivity implements ObjectRecycler$ViewRecyclerProvider
{
    private static final String EXTRA_LOMO_PARCELABLE = "lomo_parcelable";
    private static final String TAG = "KidsLomoDetailActivity";
    private LoMo lomo;
    
    public static void show(final NetflixActivity netflixActivity, final LoMo loMo) {
        netflixActivity.startActivity(new Intent((Context)netflixActivity, (Class)KidsLomoDetailActivity.class).putExtra("lomo_parcelable", (Parcelable)loMo));
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new KidsLomoDetailActivity$1(this);
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
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.browseTitles;
    }
    
    @Override
    public ObjectRecycler$ViewRecycler getViewRecycler() {
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
        netflixActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
    }
}
