package com.softech.alfasdk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.softech.alfasdk.Models.SymbolsModel.Symbol;
import com.example.alfasdk.R;

import java.util.List;


/**
 * Developed by Hasham.Tahir on 2/4/2016.
 */
public class SymbolsAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Symbol> arrayList;
    private int m_flag = 1;

    public SymbolsAdapter(Context context, List<Symbol> arrayList) {
        mContext = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Symbol getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_list_symbol, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        if (position % 2 == 1) {
            view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.greyBar));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }

        Symbol obj = arrayList.get(position);


        holder.sym.setText(obj.getSymbol());
        holder.mar.setText(obj.getMarket());
        //holder.exc.setText(obj.getExchangeCode());


        return view;
    }
    public void updateList(List<Symbol> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView sym;
        TextView mar;
        TextView exc;

        public ViewHolder(View view) {
            sym=view.findViewById(R.id.symbol_symbol);
            mar=view.findViewById(R.id.symbol_market);
            //exc=view.findViewById(R.id.symbol_exchange);
        }
    }
}
