package com.ybao.demo.adapter.tree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.ybao.demo.R;
import com.ybao.sticky.StickyHeaderView;

import java.util.ArrayList;
import java.util.List;

public class TreeActivity extends AppCompatActivity {
    StickyHeaderView stickyHeaderView;
    StickyHeaderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        stickyHeaderView = (StickyHeaderView) findViewById(R.id.stickyheaderview);
        List<SimpleNode> simpleNodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SimpleNode simpleNode = new SimpleNode();
            simpleNode.id = i;
            simpleNode.pid = 0;
            simpleNode.name = i + "";
            simpleNodes.add(simpleNode);
        }

        for (int i = 11; i <= 60; i++) {
            SimpleNode simpleNode = new SimpleNode();
            simpleNode.id = i;
            simpleNode.pid = i % 10 + 1;
            simpleNode.name = simpleNode.pid + "-" + i;
            simpleNodes.add(simpleNode);
        }
        for (int i = 61; i <= 300; i++) {
            SimpleNode simpleNode = new SimpleNode();
            simpleNode.id = i;
            simpleNode.pid = i % 50 + 11;
            simpleNode.name = simpleNode.pid + "-" + i;
            simpleNodes.add(simpleNode);
        }
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.id_tree);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new StickyHeaderAdapter();
        mAdapter.setSingleBranch(true);
        mAdapter.setChangeGroup(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(simpleNodes);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerView.getChildCount() > 0) {
                    int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    stickyHeaderView.update(recyclerView.getChildAt(0), firstVisibleItem, mAdapter.getStickyHeaderHelper());
                } else {
                    stickyHeaderView.setVisibility(View.GONE);
                }
            }
        });
    }
}
