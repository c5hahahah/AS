package com.xinlifm.my.community_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinlifm.my.adapter.CommunityPinglunAdapter;
import com.xinlifm.my.callback.PullToRefrushInPinglun;
import com.xinlifm.my.xinlifm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/7/16.
 */
@SuppressLint("ValidFragment")
public class ListviewInCommunityofPinglun extends Fragment {
    View view;
    //Ui相关
    public PullToRefreshListView pullToRefreshListView;

    //标识最新和精华
    int typeid;
    //回调
    public PullToRefrushInPinglun callback;

    //ListView相关
    public List<Object> data;
    ListView listView ;
    CommunityPinglunAdapter mAdapter;
    Handler handler;


    @SuppressLint("ValidFragment")
    public ListviewInCommunityofPinglun(int typeid,Handler handler) {
        this.typeid = typeid;
        this.handler = handler;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.community_pinglun_fragment,null);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.community_pinglun_pulltoRefrashListView);
        pullToRefreshListView.setOnRefreshListener(callback);
        data = new ArrayList<>();
        listView = pullToRefreshListView.getRefreshableView();
        mAdapter = new CommunityPinglunAdapter();
        listView.setAdapter(mAdapter);



        return view;
    }

    //准备数据
    public  void initdata(){
       // Log.i("12345","方法调用了");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                   // Log.i("12345","睡了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                       // Log.i("12345","更新了");
                        if(data.size()==0){
                            pullToRefreshListView.onRefreshComplete();
                            callback.IFnoNetToNoNet();
                        }
                    }
                });

            }
        }).start();
    }
}
