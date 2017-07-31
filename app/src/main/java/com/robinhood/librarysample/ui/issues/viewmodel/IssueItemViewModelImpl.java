package com.robinhood.librarysample.ui.issues.viewmodel;

import android.content.Intent;
import android.view.View;

import com.robinhood.librarysample.data.issue.Issue;
import com.robinhood.librarysample.ui.issuedetail.IssueDetailActivity;

import org.parceler.Parcels;

public class IssueItemViewModelImpl implements IssueItemViewModel {

    private Issue mIssue;

    public IssueItemViewModelImpl(Issue issue) {
        this.mIssue = issue;
    }

    @Override
    public String getProfileThumbnailUrl() {
        return mIssue.getProfileThumbnailUrl();
    }

    @Override
    public String getTitleText() {
        return mIssue.getTitle();
    }

    @Override
    public String getIssueIdText() {
        return mIssue.getWriterName() + "(" + String.valueOf(mIssue.getId()) + ")";
    }

    @Override
    public void onItemClick(View view) {
        Intent intent = new Intent(view.getContext(), IssueDetailActivity.class);
        //TODO Parceler를 이용하여 객체를 전달해보자
//        intent.putExtra(IssueDetailActivity.EXTRA_ISSUE_NUMBER, mIssue.getNumber());
//        intent.putExtra(IssueDetailActivity.EXTRA_ISSUE_TITLE, mIssue.getTitle());
//        intent.putExtra(IssueDetailActivity.EXTRA_ISSUE_BODY, mIssue.getBody());
        intent.putExtra(IssueDetailActivity.EXTRA_ISSUE_PARCEL, Parcels.wrap(Issue.class, mIssue));
        view.getContext().startActivity(intent);
    }
}
