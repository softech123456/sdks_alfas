package com.softech.alfasdk.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.softech.alfasdk.Adapters.SearchListAdapter;
import com.softech.alfasdk.MyMainActivity;
import com.softech.alfasdk.Models.MarketModel.MarketSymbol;
import com.softech.alfasdk.Models.SymbolsModel.Symbol;
import com.example.alfasdk.R;
import com.softech.alfasdk.Util.Alert;
import com.softech.alfasdk.Util.Util;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;


public class
QuotesFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
//    @BindView(R.id.search)
    EditText search;
//    @BindView(R.id.name)
    TextView name;
//    @BindView(R.id.symbol)
    TextView symbol;
//    @BindView(R.id.textView16)
    TextView last;
//    @BindView(R.id.bidtext)
    TextView bid_size;
//    @BindView(R.id.offertext)
    TextView offersize;
//    @BindView(R.id.changetext)
    TextView change;
//    @BindView(R.id.lowtext)
    TextView lowhigh;
//    @BindView(R.id.limittext)
    TextView limits;
//    @BindView(R.id.turnovertext)
    TextView turnover;
//    @BindView(R.id.exchangetext)
    TextView exchange;
//    @BindView(R.id.markettext)
    TextView market;
//    @BindView(R.id.lottext)
    TextView lotsize;
//    @BindView(R.id.lowhighPrice30)
    TextView lowhighPrice30;
//    @BindView(R.id.lowhighPrice90)
    TextView lowhighPrice90;
//    @BindView(R.id.lowhighPrice180)
    TextView lowhighPrice180;
//    @BindView(R.id.lowhighPrice52)
    TextView lowhighPrice52;
//    @BindView(R.id.avgPrice30)
    TextView avgPrice30;
//    @BindView(R.id.avgPrice90)
    TextView avgPrice90;
//    @BindView(R.id.avgPrice180)
    TextView avgPrice180;
//    @BindView(R.id.avgPrice52)
    TextView avgPrice52;
//    @BindView(R.id.AvgVolume30)
    TextView AvgVolume30;
//    @BindView(R.id.AvgVolume90)
    TextView AvgVolume90;
//    @BindView(R.id.AvgVolume180)
    TextView AvgVolume180;
//    @BindView(R.id.AvgVolume52)
    TextView AvgVolume52;

    ImageView cancel_search;

//    @BindView(R.id.search_list)
    ListView listSearch;
    Button trade_btn;

//    @BindView(R.id.search_list_view)
    LinearLayout listSearch_view;
    List<Symbol> searchKeywordsList;
    private SearchListAdapter searchAdapter;

    private MarketSymbol marketSymbol;

    private OnQoutesFragmentListener mListener;

    public QuotesFragment() {
        // Required empty public constructor
    }


    public static QuotesFragment newInstance(String param1) {
        QuotesFragment fragment = new QuotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
//        Log.d("Qoutes",param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            String json = getArguments().getString(ARG_PARAM1);
            marketSymbol = new Gson().fromJson(json, MarketSymbol.class);
        }

        searchKeywordsList = new ArrayList<>(MyMainActivity.symbolsResponse.getResponse().getSymbols());
        searchAdapter = new SearchListAdapter(getActivity(), searchKeywordsList);

    }

    @Override
    public void onResume() {

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("Symbol Summary");
        }

        listSearch_view.setVisibility(View.GONE);

        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
//        ButterKnife.bind(this, view);
        search=view.findViewById(R.id.search);
        name=view.findViewById(R.id.name);
        symbol=view.findViewById(R.id.symbol);
        last=view.findViewById(R.id.textView16);
        bid_size=view.findViewById(R.id.bidtext);
        offersize=view.findViewById(R.id.offertext);
        change=view.findViewById(R.id.changetext);
        lowhigh=view.findViewById(R.id.lowtext);
        limits=view.findViewById(R.id.limittext);
        turnover=view.findViewById(R.id.turnovertext);
        exchange=view.findViewById(R.id.exchangetext);
        market=view.findViewById(R.id.markettext);
        lotsize=view.findViewById(R.id.lottext);
        lowhighPrice30=view.findViewById(R.id.lowhighPrice30);
        lowhighPrice90=view.findViewById(R.id.lowhighPrice90);
        lowhighPrice180=view.findViewById(R.id.lowhighPrice180);
        lowhighPrice52=view.findViewById(R.id.lowhighPrice52);
        avgPrice30=view.findViewById(R.id.avgPrice30);
        avgPrice90=view.findViewById(R.id.avgPrice90);
        avgPrice180=view.findViewById(R.id.avgPrice180);
        avgPrice52=view.findViewById(R.id.avgPrice52);
        AvgVolume30=view.findViewById(R.id.AvgVolume30);
        AvgVolume90=view.findViewById(R.id.AvgVolume90);
        AvgVolume180=view.findViewById(R.id.AvgVolume180);
        AvgVolume52=view.findViewById(R.id.AvgVolume52);
        listSearch=view.findViewById(R.id.search_list);
        listSearch_view=view.findViewById(R.id.search_list_view);
        cancel_search=view.findViewById(R.id.cancel_search);
        trade_btn=view.findViewById(R.id.trade_btn);


        cancel_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSearch_view.setVisibility(View.GONE);

            }
        });


        trade_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradeButton();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQoutesFragmentListener) {
            mListener = (OnQoutesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQoutesFragmentListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnQoutesFragmentListener) {
            mListener = (OnQoutesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQoutesFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) { super.onViewCreated(view, savedInstanceState);

        listSearch.setAdapter(searchAdapter);
        if (marketSymbol != null) {
            setValues(marketSymbol);
        }
        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listSearch_view.setVisibility(View.GONE);

                Symbol symbol = searchKeywordsList.get(position);


                if (mListener != null) {
                    mListener.onOnQoutesFragmentListener(symbol);
                } else {
                    Log.d("onItemClick", "mAddSymbol==null");
                }


            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.length() >= 2) {
                    listSearch_view.setVisibility(View.VISIBLE);
                    String text = search.getText().toString();
                    searchAdapter.filter(text);
                } else {
                    listSearch_view.setVisibility(View.GONE);

                }
            }
        });
    }

//    @OnClick(R.id.cancel_search)
//    public void cancelSearch(View view) {
//        listSearch_view.setVisibility(View.GONE);
//    }

    public void tradeButton() {

        String TrnCodes = MyMainActivity.loginResponse.getResponse().getTrnCodes();


        int buyflag = 0, sellflag = 0;

        if (TrnCodes.contains("OM06")) {
            buyflag = 1;
            Log.d("TrnCodes", "buyflag: " + buyflag);
        }
        if (TrnCodes.contains("OM12")) {
            sellflag = 1;
            Log.d("TrnCodes", "sellflag: " + sellflag);
        }

        if (buyflag == 0 & sellflag == 0) {

            Alert.show(getActivity(), getString(R.string.app_name), "You cannot buy or sell shares.");
        } else {

            if (marketSymbol == null) {
                Alert.show(getActivity(), getString(R.string.app_name), "Please select a symbol first.");
            } else {
                ((MyMainActivity) getActivity()).goToTrade(marketSymbol);
            }
        }

    }

    public void setValues(MarketSymbol marketSymbol) {

        this.marketSymbol = marketSymbol;

        Util.hideKeyboard(getActivity());

        try {
            float ichange = Float.parseFloat(marketSymbol.getChange());

            name.setText(marketSymbol.getName());
            symbol.setText(marketSymbol.getSymbol() + "-" + marketSymbol.getMarket());
            last.setText(marketSymbol.getCurrent());
            bid_size.setText(marketSymbol.getBuyPrice() + "(" + marketSymbol.getBuyVolume() + ")");
            offersize.setText(marketSymbol.getSellPrice() + "(" + marketSymbol.getSellVolume() + ")");

            if (ichange > 0) {
                change.setTextColor(Color.GREEN);
            }
            if (ichange < 0) {
                change.setTextColor(Color.RED);
            }
            if (ichange == 0) {
                change.setTextColor(Color.GRAY);
            }
            double percentage = 0;
            String changeStr = marketSymbol.getChange().replace(",", "");
            String lastStr = marketSymbol.getCurrent().replace(",", "");
            Double chnge = Double.parseDouble(changeStr);
            double last = Double.parseDouble(lastStr);
            double open = last - chnge;
            //if (last > 0 && open > 0) {
            percentage = chnge * 100 / open;
            Log.d("Perc ", String.valueOf(percentage));
            String perc = String.format("%.2f", percentage);
            change.setText(marketSymbol.getChange() + "(" + perc + "%)");
            lowhigh.setText(marketSymbol.getLowPrice() + "-" + marketSymbol.getHighPrice());
            limits.setText(marketSymbol.getLowerLimit() + "-" + marketSymbol.getUpperLimit());
            turnover.setText(marketSymbol.getTurnOver());
            exchange.setText(marketSymbol.getExchangeCode());
            market.setText(marketSymbol.getMarket());
            lotsize.setText(marketSymbol.getLotSize());
            lowhighPrice30.setText(marketSymbol.getLowPrice30() + "-" + marketSymbol.getHighPrice30());
            lowhighPrice90.setText(marketSymbol.getLowPrice90() + "-" + marketSymbol.getHighPrice90());
            lowhighPrice180.setText(marketSymbol.getLowPrice180() + "-" + marketSymbol.getHighPrice180());
            lowhighPrice52.setText(marketSymbol.getWLowPrice52() + "-" + marketSymbol.getWHighPrice52());
            avgPrice30.setText(marketSymbol.getAvgPrice30());
            avgPrice52.setText(marketSymbol.getWAvgPrice52());
            avgPrice90.setText(marketSymbol.getAvgPrice90());
            avgPrice180.setText(marketSymbol.getAvgPrice180());
            AvgVolume30.setText(marketSymbol.getAvgVolume30());
            AvgVolume52.setText(marketSymbol.getWAvgVolume52());
            AvgVolume90.setText(marketSymbol.getAvgVolume90());
            AvgVolume180.setText(marketSymbol.getAvgVol180());


        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert.show(getActivity(), "ERROR", e.getLocalizedMessage());
        }
    }

    public interface OnQoutesFragmentListener {
        void onOnQoutesFragmentListener(Symbol symbol);
    }
}
