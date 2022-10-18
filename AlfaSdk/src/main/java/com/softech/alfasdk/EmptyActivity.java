package com.softech.alfasdk;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.softech.alfasdk.Adapters.NavAdapter;
import com.softech.alfasdk.Fragments.MarketFragment;
import com.softech.alfasdk.Fragments.OrderStatsFragment;
import com.softech.alfasdk.Fragments.QuotesFragment;
import com.softech.alfasdk.Models.MarketModel.MarketSymbol;
import com.softech.alfasdk.Models.Menu;
import com.softech.alfasdk.Models.OrderStatsModel.OrdersList;
import com.softech.alfasdk.Models.SymbolsModel.Symbol;
import com.example.alfasdk.R;

public class EmptyActivity extends BaseActivity implements NavAdapter.OnMenuInteractionListener,
        MarketFragment.OnMarketFragmentListener, MarketFragment.OnSymbolRequest,
        OrderStatsFragment.OnOrderDeleteRequest, QuotesFragment.OnQoutesFragmentListener {

    TextView tvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        String loginJson = mPrefs.getString("loginResult", "");

        tvText=findViewById(R.id.tvText);
        tvText.setText("asdasd");
    }

    @Override
    public void onMenuInteraction(Menu item) {

    }

    @Override
    public void onMarketFragmentListener(int which, MarketSymbol sym) {

    }

    @Override
    public void onSymbolAddRequest(Symbol symbol) {

    }

    @Override
    public void onSymbolDeleteRequest(MarketSymbol symbol) {

    }

    @Override
    public void onOrderDeleteRequest(OrdersList order) {

    }

    @Override
    public void onOnQoutesFragmentListener(Symbol symbol) {

    }
}