package com.robinhood.librarysample;

import com.robinhood.librarysample.ui.issues.viewmodel.IssueItemViewModel;

import java.util.List;

/**
 * Created by jh on 17. 7. 24.
 */

public class MessageEvent {
    List<IssueItemViewModel> itemVMList;

    public MessageEvent(List<IssueItemViewModel> itemVMList) {
        this.itemVMList = itemVMList;
    }

    public List<IssueItemViewModel> getItemVMList() {
        return itemVMList;
    }

    public void setItemVMList(List<IssueItemViewModel> itemVMList) {
        this.itemVMList = itemVMList;
    }
}
