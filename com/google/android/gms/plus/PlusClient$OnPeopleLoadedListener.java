// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.common.ConnectionResult;

@Deprecated
public interface PlusClient$OnPeopleLoadedListener
{
    void onPeopleLoaded(final ConnectionResult p0, final PersonBuffer p1, final String p2);
}
