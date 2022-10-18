package com.softech.alfasdk.Adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.softech.alfasdk.Models.MarginModel.CustodyHeader;
import com.softech.alfasdk.Models.MarginModel.CustodyList;
import com.softech.alfasdk.Models.MarginModel.MarginDetail;
import com.example.alfasdk.R;

import java.util.ArrayList;


/**
 * Created by saimshafqat on 23/11/2017.
 */

public class MarginDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    Context context;
    private ArrayList<MarginDetail> allData;

    public MarginDetailAdapter(Context context, ArrayList<MarginDetail> data) {

        this.context = context;
        this.allData = data;

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            return TYPE_HEADER;


        } else if (position > 0 && allData.get(position) != null) {

            return TYPE_NORMAL;

        }

        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case TYPE_NORMAL: {
                View v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.symbols_list_item, viewGroup, false);
                viewHolder = new ItemViewHolder(v);

            }
            break;
            case TYPE_HEADER: {
                View v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_list_margin_header, viewGroup, false);
                viewHolder = new HeaderViewHolder(v);
            }
            break;
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NORMAL: {
                if (position % 2 == 1) {
                    viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.greyBar));
                } else {
                    viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                }
                CustodyList obj = (CustodyList) allData.get(position);

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                itemViewHolder.symbol_symbol.setText(obj.getSymbol());
                itemViewHolder.symbol_market.setText(obj.getBalance());
                itemViewHolder.symbol_exchange.setText(obj.getAmount());
            }
            break;
            case TYPE_HEADER: {
                HeaderViewHolder headerView = (HeaderViewHolder) viewHolder;
                CustodyHeader header = (CustodyHeader) allData.get(0);
                Log.d("header",header.getClientcode());
                headerView.usertext.setText(header.getClientcode());
                headerView.textViewDateTime.setText(header.getDate());
                headerView.cashtext.setText(header.getEquityCashBalance());
                headerView.worthText.setText(header.getTotalWorth());
                headerView.custodyText.setText(header.getEquityReducedValue());
                headerView.mtmText.setText(header.getEquityProfitLos());
                headerView.blockedMtmText.setText(header.getEquityBlockedMTMProfit());
                headerView.marginText.setText(header.getNetLiquidityEquity());
                headerView.exposureText.setText(header.getOpenPositionEquity());
                headerView.marginRequiredText.setText(header.getEquityMarginRequired());
                headerView.cashRequiredText.setText(header.getEquityMarginRequiredAsCash());
                headerView.availableMarginText.setText(header.getEquityFreeMargin());
                headerView.currentMarginText.setText(header.getEquityMarginPerc());
                headerView.cashWithdrawalText.setText(header.getEquityCashWithdrawalinProcess());
                headerView.cashWithdrawalLimitText.setText(header.getEquityCashWithdrawal());
                headerView.marginCallText.setText(header.getEquityCashMarginCall());
                headerView.cashCallText.setText(header.getEquityNetMarginCall());
                headerView.availableCashText.setText(header.getEquityFreeCash());
            }
            break;
        }
    }

    @Override
    public int getItemCount() {
        return allData.size();

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.symbol_symbol)
        TextView symbol_symbol;
//        @BindView(R.id.symbol_market)
        TextView symbol_market;
//        @BindView(R.id.symbol_exchange)
        TextView symbol_exchange;


        public ItemViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            symbol_symbol=itemView.findViewById(R.id.symbol_symbol);
            symbol_market=itemView.findViewById(R.id.symbol_market);
            symbol_exchange=itemView.findViewById(R.id.symbol_exchange);

        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        View mView;
//        @BindView(R.id.textViewDateTime)
        TextView textViewDateTime;
//        @BindView(R.id.usertext)
        TextView usertext;
//        @BindView(R.id.cashtext)
        TextView cashtext;
//        @BindView(R.id.worthText)
        TextView worthText;
//        @BindView(R.id.custodyText)
        TextView custodyText;
//        @BindView(R.id.mtmText)
        TextView mtmText;
//        @BindView(R.id.blockedMtmText)
        TextView blockedMtmText;
//        @BindView(R.id.marginText)
        TextView marginText;
//        @BindView(R.id.exposureText)
        TextView exposureText;
//        @BindView(R.id.marginRequiredText)
        TextView marginRequiredText;
//        @BindView(R.id.cashRequiredText)
        TextView cashRequiredText;
//        @BindView(R.id.availableMarginText)
        TextView availableMarginText;
//        @BindView(R.id.currentMarginText)
        TextView currentMarginText;
//        @BindView(R.id.cashWithdrawalText)
        TextView cashWithdrawalText;
//        @BindView(R.id.cashWithdrawalLimitText)
        TextView cashWithdrawalLimitText;
//        @BindView(R.id.marginCallText)
        TextView marginCallText;
//        @BindView(R.id.cashCallText)
        TextView cashCallText;
//        @BindView(R.id.availableCashText)
        TextView availableCashText;

        public HeaderViewHolder(View view) {
            super(view);
            textViewDateTime=view.findViewById(R.id.textViewDateTime);
            usertext=view.findViewById(R.id.usertext);
            usertext=view.findViewById(R.id.usertext);
            cashtext=view.findViewById(R.id.cashtext);
            worthText=view.findViewById(R.id.worthText);
            custodyText=view.findViewById(R.id.custodyText);
            mtmText=view.findViewById(R.id.mtmText);
            marginText=view.findViewById(R.id.marginText);
            exposureText=view.findViewById(R.id.exposureText);
            marginRequiredText=view.findViewById(R.id.marginRequiredText);
            cashRequiredText=view.findViewById(R.id.cashRequiredText);
            availableMarginText=view.findViewById(R.id.availableMarginText);
            currentMarginText=view.findViewById(R.id.currentMarginText);
            cashWithdrawalText=view.findViewById(R.id.cashWithdrawalText);
            cashWithdrawalLimitText=view.findViewById(R.id.cashWithdrawalLimitText);
            marginCallText=view.findViewById(R.id.marginCallText);
            cashCallText=view.findViewById(R.id.cashCallText);
            availableCashText=view.findViewById(R.id.availableCashText);
//            ButterKnife.bind(this, view);

        }
    }
}

