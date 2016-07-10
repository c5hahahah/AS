package com.game3d.my.fragmentofmain.fragmentofwenzhang;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.game3d.my.Callback;
import com.game3d.my.adapter.WenzhangItemAdapter;
import com.game3d.my.game3duse.R;
import com.game3d.my.listen.ListItemSelectListener;
import com.game3d.my.modles.MyBander;
import com.game3d.my.service.DownloadService;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class WenzhangOtherFragment extends Fragment implements Callback{
    int type;
    View view;
    PullToRefreshListView pullToRefreshListView;
    ListView listview;
    List<HashMap<String,String>> data;
    WenzhangItemAdapter adapter ;
    Handler handler;
    Cursor cursor;
    int pageindex = 1;
    int pagesize = 7;
    Intent serviceintent;
    SqliteHander sqliteHander;


    ListItemSelectListener listener;
//    public WenzhangOtherFragm nent() {
//}pageindex

    IntentFilter intentFilter;
    MyBroadcast myBroadcast;
    @SuppressLint("ValidFragment")
    public WenzhangOtherFragment(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.wenzhang_otherfragment,null);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.wenzhang_other_pulltorefreshlistview);
        listview = pullToRefreshListView.getRefreshableView();
        data = new ArrayList<>();
        handler = new Handler();
        adapter = new WenzhangItemAdapter(getContext(),data,handler,this);
        listview.setAdapter(adapter);
        sqliteHander = new SqliteHander(getContext());

        setData();
        listener = new ListItemSelectListener(getActivity(),data);
        listview.setOnItemClickListener(listener);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Intent intent = new Intent(getActivity(), DownloadService.class);
                intent.putExtra("pageindex",pageindex);
                getActivity().bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.MODE_PRIVATE);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                intentFilter = new IntentFilter();
                intentFilter.addAction("ok");
                myBroadcast = new MyBroadcast();
                getActivity().registerReceiver(myBroadcast,intentFilter);
                pageindex = pageindex+1;
                serviceintent = new Intent(getActivity(),DownloadService.class);
                serviceintent.putExtra("type",type);
                serviceintent.putExtra("pageindex",pageindex);
                getActivity().startService(serviceintent);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });


        return view;
    }
    //得到数据
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cursor = sqliteHander.getTypeCursor(type,pageindex, pagesize);
                if(cursor!=null){
                while (cursor.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String click = cursor.getString(cursor.getColumnIndex("click"));
                    String senddate = cursor.getString(cursor.getColumnIndex("senddate"));
                    String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
                    map.put("litpic", litpic);
                    map.put("title", title);
                    map.put("click", click);
                    map.put("senddate", senddate);
                    map.put("arcurl",arcurl);
                    data.add(map);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshListView.onRefreshComplete();
                        Log.i("12345","data改变了");
                        adapter.notifyDataSetChanged();

                    }
                });
            }
            }

        }).start();
    }
        // 回调的接口
    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }


    //注册广播
    class MyBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if("ok".equals(intent.getStringExtra("ok"))){
                    Log.i("12345","得到返回的了");
                    data.clear();
                    setData();
                getActivity().unregisterReceiver(myBroadcast);
                //getActivity().stopService(serviceintent);
            }
        }
    }
}
