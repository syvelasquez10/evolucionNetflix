// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import java.util.Comparator;

class WPInteractiveMomentsManager$2 implements Comparator<WPInteractiveMomentsModel$WPMoment>
{
    final /* synthetic */ WPInteractiveMomentsManager this$0;
    
    WPInteractiveMomentsManager$2(final WPInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final WPInteractiveMomentsModel$WPMoment wpInteractiveMomentsModel$WPMoment, final WPInteractiveMomentsModel$WPMoment wpInteractiveMomentsModel$WPMoment2) {
        if (wpInteractiveMomentsModel$WPMoment == null || wpInteractiveMomentsModel$WPMoment2 == null) {
            return -1;
        }
        return wpInteractiveMomentsModel$WPMoment.getPugStartTimeMS() - wpInteractiveMomentsModel$WPMoment2.getPugStartTimeMS();
    }
}
