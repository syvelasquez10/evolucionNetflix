// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.SearchManager;
import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector$OnGestureListener;
import com.netflix.mediaclient.util.ViewUtils$ClickGestureDetector;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.search.VoiceSearchABTestUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.view.GestureDetector;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class SearchActionBar$1 implements View$OnTouchListener
{
    final /* synthetic */ SearchActionBar this$0;
    
    SearchActionBar$1(final SearchActionBar this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.this$0.clickDetector != null && this.this$0.clickDetector.onTouchEvent(motionEvent)) {
            UIViewLogUtils.reportUIViewCommand((Context)this.this$0.activity, UIViewLogging$UIViewCommandName.search, this.this$0.activity.getUiScreen(), CommandEndedEvent$InputMethod.voice, this.this$0.activity.getDataContext());
        }
        return false;
    }
}
