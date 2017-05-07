// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;

public class BackStackRecord$TransitionState
{
    public FragmentTransitionCompat21$EpicenterView enteringEpicenterView;
    public ArrayList<View> hiddenFragmentViews;
    public ArrayMap<String, String> nameOverrides;
    public View nonExistentView;
    final /* synthetic */ BackStackRecord this$0;
    
    public BackStackRecord$TransitionState(final BackStackRecord this$0) {
        this.this$0 = this$0;
        this.nameOverrides = new ArrayMap<String, String>();
        this.hiddenFragmentViews = new ArrayList<View>();
        this.enteringEpicenterView = new FragmentTransitionCompat21$EpicenterView();
    }
}
