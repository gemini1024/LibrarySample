package com.robinhood.librarysample.ui.issues;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robinhood.librarysample.ErrorEvent;
import com.robinhood.librarysample.MessageEvent;
import com.robinhood.librarysample.R;
import com.robinhood.librarysample.base.command.AlertNotifyCommand;
import com.robinhood.librarysample.databinding.FragmentIssuesBinding;
import com.robinhood.librarysample.ui.issues.viewmodel.IssueViewModel;
import com.robinhood.librarysample.ui.issues.viewmodel.IssueViewModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class IssuesFragment extends Fragment {

    private IssuesAdapter mIssuesAdapter;

    private FragmentIssuesBinding fragmentIssuesBinding;

    private IssueViewModel issuesViewModel;

    public static IssuesFragment newInstance() {
        return new IssuesFragment();
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssuesAdapter = new IssuesAdapter();
        issuesViewModel = new IssueViewModelImpl();
        issuesViewModel.setNotifyCommand(new AlertNotifyCommand(getActivity()));
//        issuesViewModel.setUpdateViewModelListener(new NotifyUpdateViewModelListener<List<IssueItemViewModel>>() {
//            @Override
//            public void onUpdatedViewModel(List<IssueItemViewModel> viewModel) {
//                //TODO EventBus를 이용하여 Event를 전달 받아보자
//                mIssuesAdapter.replaceData(viewModel);
//                if (fragmentIssuesBinding.scrollChildSwipeRefreshLayout.isRefreshing()) {
//                    fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setRefreshing(false);
//                }
//            }
//        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if(messageEvent.getItemVMList() != null) {
            mIssuesAdapter.replaceData(messageEvent.getItemVMList());
            if (fragmentIssuesBinding.scrollChildSwipeRefreshLayout.isRefreshing()) {
                fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        new AlertNotifyCommand(getActivity()).execute(errorEvent.getErrMessage());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        fragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false);
        return fragmentIssuesBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentIssuesBinding.issuesRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(getContext()));
        fragmentIssuesBinding.issuesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        fragmentIssuesBinding.issuesRecyclerView.setAdapter(mIssuesAdapter);
        fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                issuesViewModel.refreshIssues();
            }
        });
        issuesViewModel.fetchIssues();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
