// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.ArrayList;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.LoMo;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import android.widget.FrameLayout;

@SuppressLint({ "ViewConstructor" })
public class SkidmarkMoreButton extends FrameLayout
{
    private static final String TAG = "SkidmarkMoreButton";
    private final NetflixActivity activity;
    private final List<AdvancedImageView> imgViews;
    private final View$OnClickListener launchLomoDetailsClickListener;
    private LoMo lomo;
    private final TextView lomoTitle;
    private final PressedStateHandler pressedStateHandler;
    
    public SkidmarkMoreButton(final NetflixActivity activity) {
        super((Context)activity);
        this.imgViews = new ArrayList<AdvancedImageView>(3);
        this.launchLomoDetailsClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (SkidmarkMoreButton.this.lomo == null) {
                    Log.w("SkidmarkMoreButton", "No lomo available!");
                    return;
                }
                SkidmarkMoreButton.this.launchAndLogKidsDetailsActivity();
            }
        };
        this.activity = activity;
        Log.v("SkidmarkMoreButton", "Creating more button");
        activity.getLayoutInflater().inflate(2130903101, (ViewGroup)this);
        this.setOnClickListener(this.launchLomoDetailsClickListener);
        this.imgViews.add(this.findAndConfigureView(2131165406));
        this.imgViews.add(this.findAndConfigureView(2131165407));
        this.imgViews.add(this.findAndConfigureView(2131165408));
        this.lomoTitle = (TextView)this.findViewById(2131165410);
        final int dimensionPixelSize = activity.getResources().getDimensionPixelSize(2131361918);
        this.setPadding(dimensionPixelSize, 0, dimensionPixelSize, AndroidUtils.dipToPixels((Context)activity, 50));
        this.pressedStateHandler = new PressedStateHandler((View)this);
    }
    
    private AdvancedImageView findAndConfigureView(final int n) {
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.findViewById(n);
        advancedImageView.setPressedStateHandlerEnabled(false);
        return advancedImageView;
    }
    
    private void launchAndLogKidsDetailsActivity() {
        LogUtils.reportUIViewCommandStarted((Context)this.activity, UIViewLogging.UIViewCommandName.moreButton, IClientLogging.ModalView.homeScreen, new DataContext(this.lomo));
        KidsLomoDetailActivity.show(this.activity, this.lomo);
        LogUtils.reportUIViewCommandEnded((Context)this.activity);
    }
    
    protected void dispatchSetPressed(final boolean b) {
        this.pressedStateHandler.handleSetPressed(b);
        super.dispatchSetPressed(b);
    }
    
    public void update(final LoMo lomo) {
        this.lomo = lomo;
        if (lomo == null) {
            Log.w("SkidmarkMoreButton", "No lomo to use for More button");
        }
        else {
            this.lomoTitle.setText((CharSequence)lomo.getTitle());
            if (lomo.getMoreImages() == null) {
                Log.w("SkidmarkMoreButton", "No images in lomo to use for More button");
                return;
            }
            if (Log.isLoggable("SkidmarkMoreButton", 2)) {
                Log.v("SkidmarkMoreButton", "Updating for lomo: " + lomo.getTitle() + ", more images: " + lomo.getMoreImages());
            }
            for (int i = 0; i < Math.min(lomo.getMoreImages().size(), this.imgViews.size()); ++i) {
                NetflixActivity.getImageLoader((Context)this.activity).showImg(this.imgViews.get(i), lomo.getMoreImages().get(i), null, null, false, true);
            }
        }
    }
}
