// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.persistent;

import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
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
        return this.getCell(context, this.getDefaultCell());
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
    
    public int getCellCountForAllocateABTestActivity() {
        return ABTestConfig$Cell.values().length;
    }
    
    public CharSequence getCellFriendlyTextForAllocateABTestActivity(final ABTestConfig$Cell abTestConfig$Cell) {
        return "Cell " + abTestConfig$Cell.getCellId();
    }
    
    public ABTestConfig getConfiguration(final ABTestConfigData abTestConfigData) {
        return abTestConfigData.getConfigForId(this.getTestId());
    }
    
    public ABTestConfig$Cell getDefaultCell() {
        return ABTestConfig$Cell.CELL_ONE;
    }
    
    public CharSequence getFriendlyTextForAllocateABTestActivity() {
        return this.getClass().getSimpleName();
    }
    
    public abstract String getPrefKey();
    
    public abstract String getTestId();
    
    public boolean isMobileOnly() {
        return false;
    }
    
    public boolean isTabletOnly() {
        return false;
    }
    
    public void refresh() {
        this.mCell = null;
    }
    
    protected boolean shouldForceUpdateMemory() {
        return false;
    }
    
    public void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        final ABTestConfig$Cell defaultCell = this.getDefaultCell();
        final ABTestConfigData abTestConfig = serviceAgent$ConfigurationAgentInterface.getABTestConfig();
        ABTestConfig$Cell mCell = defaultCell;
        if (abTestConfig != null) {
            final ABTestConfig configuration = this.getConfiguration(abTestConfig);
            mCell = defaultCell;
            if (configuration != null) {
                final ABTestConfig$Cell cell = configuration.getCell();
                if ((mCell = cell) != null) {
                    PreferenceUtils.putIntPref(context, this.getPrefKey(), cell.getCellId());
                    ApmLogUtils.reportABTestReceivedEvent(context, this.getTestId(), cell.getCellId());
                    mCell = cell;
                }
            }
        }
        if (this.shouldForceUpdateMemory()) {
            this.mCell = mCell;
        }
    }
}
