package com.xinlifm.my.community_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xinlifm.my.callback.NoNetBtnCallBackInCommunity;
import com.xinlifm.my.xinlifm.R;


/**
 * Created by my on 2016/7/16.
 */
public class NoNetFragmentInCommunity extends Fragment {
    View view;
    public NoNetBtnCallBackInCommunity callBack;
    public Button btn ;

    public NoNetFragmentInCommunity(NoNetBtnCallBackInCommunity callBack) {
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.community_nonet_fragment,null);
        btn = (Button) view.findViewById(R.id.community_nonet_fragment_btn);
        btn.setOnClickListener(callBack);
        return view;
    }
}
