package com.softech.alfasdk.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.softech.alfasdk.Models.NetShareModel.NetShareCustody;
import com.example.alfasdk.R;
import com.google.gson.Gson;


/**
 * Developed by hasham on 4/20/17.
 */

public class NetShareDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
//    @BindView(R.id.textView_symbol)
    TextView textView_symbol;
//    @BindView(R.id.textView_symbolName)
    TextView textView_symbolName;
//    @BindView(R.id.textView_cdcTradable)
    TextView textView_cdcTradable;
//    @BindView(R.id.textView_closingRate)
    TextView textView_closingRate;
//    @BindView(R.id.textView_CorpAct)
    TextView textView_CorpAct;
//    @BindView(R.id.textView_CustodyBalance)
    TextView textView_CustodyBalance;
//    @BindView(R.id.textView_Forward)
    TextView textView_Forward;
//    @BindView(R.id.textView_PhyTradable)
    TextView textView_PhyTradable;
//    @BindView(R.id.textView_Registered)
    TextView textView_Registered;
//    @BindView(R.id.textView_Regular)
    TextView textView_Regular;
//    @BindView(R.id.textView_Spot)
    TextView textView_Spot;
//    @BindView(R.id.textView_UnRegistered)
    TextView textView_UnRegistered;

    NetShareCustody shareCustody = null;

    public NetShareDetailFragment() {
    }

    public static NetShareDetailFragment newInstance(String param1) {
        NetShareDetailFragment fragment = new NetShareDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            String mParam1 = getArguments().getString(ARG_PARAM1);
            shareCustody = new Gson().fromJson(mParam1, NetShareCustody.class);
        }

        setHasOptionsMenu(true);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_home);
        item.setVisible(false);

        MenuItem item2 = menu.findItem(R.id.action_net_share);
        item2.setVisible(false);

        MenuItem item1 = menu.findItem(R.id.action_feed_status);
        item1.setVisible(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net_custody_detail, container, false);
//        ButterKnife.bind(this, view);
        textView_symbol=view.findViewById(R.id.textView_symbol);
        textView_symbolName=view.findViewById(R.id.textView_symbolName);
        textView_cdcTradable=view.findViewById(R.id.textView_cdcTradable);
        textView_closingRate=view.findViewById(R.id.textView_closingRate);
        textView_CorpAct=view.findViewById(R.id.textView_CorpAct);
        textView_CustodyBalance=view.findViewById(R.id.textView_CustodyBalance);
        textView_Forward=view.findViewById(R.id.textView_Forward);
        textView_PhyTradable=view.findViewById(R.id.textView_PhyTradable);
        textView_Registered=view.findViewById(R.id.textView_Registered);
        textView_Regular=view.findViewById(R.id.textView_Regular);
        textView_Spot=view.findViewById(R.id.textView_Spot);
        textView_UnRegistered=view.findViewById(R.id.textView_UnRegistered);
        return view;
    }

    @Override
    public void onResume() {

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("Detail");
        }
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        if(shareCustody!=null) {
            
            textView_symbol.setText(shareCustody.getSymbol());
            textView_symbolName.setText(shareCustody.getSymbolName());
            textView_cdcTradable.setText(shareCustody.getCdcTradable());
            textView_closingRate.setText(shareCustody.getCloseRate());
            textView_CorpAct.setText(shareCustody.getCorporateActivity());
            textView_CustodyBalance.setText(shareCustody.getCustodyBalance());
            textView_Forward.setText(shareCustody.getForward());
            textView_PhyTradable.setText(shareCustody.getPhysicalTradable());
            textView_Registered.setText(shareCustody.getRegistered());
            textView_Regular.setText(shareCustody.getRegular());
            textView_Spot.setText(shareCustody.getSpot());
            textView_UnRegistered.setText(shareCustody.getUnregistered());
        }
        
    }
    
}
