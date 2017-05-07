// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.Set;

class UserAgent$SendRecommendationsTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$SendRecommendationsTask this$1;
    
    UserAgent$SendRecommendationsTask$1(final UserAgent$SendRecommendationsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onRecommendationsSent(final Set<FriendForRecommendation> set, final Status status) {
        Label_0182: {
            if (!status.isSucces() || set == null || set.size() <= 0) {
                break Label_0182;
            }
            Object firstName = null;
            final Iterator<FriendForRecommendation> iterator = set.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final FriendForRecommendation friendForRecommendation = iterator.next();
                if (friendForRecommendation.wasRecommended()) {
                    if (firstName == null) {
                        firstName = friendForRecommendation.getFriendProfile().getFirstName();
                    }
                    else {
                        ++n;
                    }
                }
            }
            if (firstName == null) {
                break Label_0182;
            }
            String s;
            if (n == 0) {
                s = this.this$1.this$0.getContext().getString(2131493351, new Object[] { firstName });
            }
            else {
                s = this.this$1.this$0.getContext().getResources().getQuantityString(2131623938, n, new Object[] { n, firstName });
            }
            Toast.makeText(this.this$1.this$0.getContext(), (CharSequence)s, 1).show();
            return;
        }
        if (Log.isLoggable()) {
            Log.e("nf_service_useragent", "Problem occured trying to send recommendations! Result: " + status + "; Friends: " + set);
        }
    }
}
