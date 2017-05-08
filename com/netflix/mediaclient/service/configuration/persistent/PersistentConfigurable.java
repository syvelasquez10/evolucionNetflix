// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;

public abstract class PersistentConfigurable
{
    private static final String TAG = "PersistentConfigurable";
    private ABTestConfig$Cell mCell;
    
    public void delete(final Context context) {
        this.mCell = null;
        PreferenceUtils.removePref(context, this.getPrefKey());
    }
    
    public ABTestConfig$Cell getCell(final Context context) {
        return this.getCell(context, ABTestConfig$Cell.CELL_ONE);
    }
    
    public ABTestConfig$Cell getCell(final Context context, final ABTestConfig$Cell mCell) {
        if (this.mCell == null) {
            this.mCell = ABTestConfig$Cell.fromInt(PreferenceUtils.getIntPref(context, this.getPrefKey(), mCell.getCellId()));
            if (this.mCell == null) {
                this.mCell = mCell;
            }
            ApmLogUtils.reportABTestLoadedEvent(context, this.getTestId(), this.mCell.getCellId());
        }
        return this.mCell;
    }
    
    public abstract ABTestConfig$Cell getCell(final ServiceAgent$ConfigurationAgentInterface p0);
    
    public abstract String getPrefKey();
    
    public abstract String getTestId();
    
    public void refresh() {
        this.mCell = null;
    }
    
    protected boolean shouldForceUpdateMemory() {
        return false;
    }
    
    public void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        final ABTestConfig$Cell cell = this.getCell(serviceAgent$ConfigurationAgentInterface);
        if (cell != null) {
            PreferenceUtils.putIntPref(context, this.getPrefKey(), cell.getCellId());
            ApmLogUtils.reportABTestReceivedEvent(context, this.getTestId(), cell.getCellId());
        }
        if (this.shouldForceUpdateMemory()) {
            this.mCell = cell;
        }
    }
}
