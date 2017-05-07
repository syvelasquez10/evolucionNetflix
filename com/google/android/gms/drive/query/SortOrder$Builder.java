// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import java.util.ArrayList;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.List;

public class SortOrder$Builder
{
    private final List<FieldWithSortOrder> QA;
    private boolean QB;
    
    public SortOrder$Builder() {
        this.QA = new ArrayList<FieldWithSortOrder>();
        this.QB = false;
    }
    
    public SortOrder$Builder addSortAscending(final SortableMetadataField sortableMetadataField) {
        this.QA.add(new FieldWithSortOrder(sortableMetadataField.getName(), true));
        return this;
    }
    
    public SortOrder$Builder addSortDescending(final SortableMetadataField sortableMetadataField) {
        this.QA.add(new FieldWithSortOrder(sortableMetadataField.getName(), false));
        return this;
    }
    
    public SortOrder build() {
        return new SortOrder(this.QA, this.QB, null);
    }
}
