// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;

public abstract class PersistentConfigurable
{
    private ABTestConfig$Cell mCell;
    
    public ABTestConfig$Cell getCell(final Context context) {
        if (this.mCell == null) {
            this.mCell = ABTestConfig$Cell.fromInt(PreferenceUtils.getIntPref(context, this.getPrefKey(), ABTestConfig$Cell.CELL_ONE.getCellId()));
        }
        return this.mCell;
    }
    
    public abstract ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface p0);
    
    public abstract String getPrefKey();
    
    public void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        this.mCell = this.getCell(serviceAgent$ConfigurationAgentInterface);
        if (this.mCell != null) {
            PreferenceUtils.putIntPref(context, this.getPrefKey(), this.mCell.getCellId());
        }
    }
}
