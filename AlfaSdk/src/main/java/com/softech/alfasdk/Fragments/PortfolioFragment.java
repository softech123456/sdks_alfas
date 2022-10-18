package com.softech.alfasdk.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softech.alfasdk.Adapters.PortfolioAdapter;
import com.softech.alfasdk.Adapters.SearchClientListAdapter;
import com.softech.alfasdk.MyMainActivity;
import com.softech.alfasdk.Models.LoginModel.LoginResponse;
import com.softech.alfasdk.Models.PortfolioModel.Portfolio;
import com.softech.alfasdk.Models.PortfolioModel.PortfolioFooter;
import com.softech.alfasdk.Models.PortfolioModel.PortfolioSymbol;
import com.example.alfasdk.R;
import com.softech.alfasdk.Util.Alert;
import com.softech.alfasdk.Util.Preferences;
import com.softech.alfasdk.charts.Pacpie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;



public class PortfolioFragment extends Fragment implements PortfolioAdapter.OnPortofolioClickListner {

    public static final String TYPE = "type";
    public static Pacpie pacpie;
    public static Preferences preferences;
    public static LoginResponse loginResponse;
    private static int[] COLORS = new int[]{Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN};
    int[] pieChartValues = {25, 15, 20, 40};
    float values[] = {700, 400, 100, 500, 600};

    EditText clientcode;
    RecyclerView portfolio_list;
    ListView listSearch1;
    LinearLayout listSearch_view1;
    PieChart pieChart;

    ImageView cancel_search1;
    ArrayList<String> clientlist;
    public static final int[] chartColors = {
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37), Color.rgb(178, 41, 37),
            Color.rgb(178, 41, 37)
    };


    private SearchClientListAdapter searchClientListAdapter;
    List<PortfolioSymbol> values1;
    View v;
    boolean isSetInitialText = false;

    public PortfolioFragment() {
    }

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("My Portfolio");
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
//        ButterKnife.bind(this, view);
        clientcode=view.findViewById(R.id.etclientcode);
        portfolio_list=view.findViewById(R.id.portfolio_list);
        listSearch1=view.findViewById(R.id.search_list1);
        listSearch_view1=view.findViewById(R.id.search_list_view1);
        pieChart=view.findViewById(R.id.piechart);
        cancel_search1=view.findViewById(R.id.cancel_search1);

        cancel_search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSearch(v);
            }
        });


        portfolio_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        preferences = StoreBox.create(getActivity(), Preferences.class);
        if (MyMainActivity.loginResponse.getResponse().getUsertype() == 1 ||
                MyMainActivity.loginResponse.getResponse().getUsertype() == 2) {

            clientcode.setText(MyMainActivity.loginResponse.getResponse().getClient());
            clientcode.setEnabled(false);
            ((MyMainActivity) getActivity()).portfolioRequestRequest(clientcode.getText().toString());
        } else if (MyMainActivity.loginResponse.getResponse().getUsertype() == 0 ||
                MyMainActivity.loginResponse.getResponse().getUsertype() == 3) {

            clientlist = new ArrayList<String>(MyMainActivity.loginResponse.getResponse().getClientlist());
            searchClientListAdapter = new SearchClientListAdapter(getActivity(), clientlist);
        }
        ;
        v = view;

        return view;
    }

    private void setupViews() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listSearch1.setAdapter(searchClientListAdapter);
        clientcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSetInitialText) {
                    isSetInitialText = false;
                    listSearch_view1.setVisibility(View.GONE);
                } else {
                    if (s.length() > 0) {

                        listSearch_view1.setVisibility(View.VISIBLE);

                        String text = clientcode.getText().toString();
                        searchClientListAdapter.filter(text);
                    } else {
                        listSearch_view1.setVisibility(View.GONE);
                    }
                }

            }
        });
        listSearch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listSearch_view1.setVisibility(View.GONE);
                ((MyMainActivity) getActivity()).portfolioRequestRequest(clientlist.get(position));
                isSetInitialText = true;
                clientcode.setText(clientlist.get(position));

            }
        });

        // ((MainActivity) getActivity()).portfolioRequestRequest();
    }

    public void cancelSearch(View view) {
        listSearch_view1.setVisibility(View.GONE);
    }

    public void setValues(final List<PortfolioSymbol> values) {


        if (values.size() > 0) {

            values1 = values;
            Collections.sort(values1, new Comparator<PortfolioSymbol>() {
                @Override
                public int compare(PortfolioSymbol portfolioSymbol, PortfolioSymbol t1) {
                    String pfWeight1 = portfolioSymbol.getPfWeight();
                    String pfWeight2 = t1.getPfWeight();

                    return pfWeight1.compareTo(pfWeight2);

                }
            });
//            View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.acc_list_item_footer, portfolio_list, false);
            double totalValue = 0;
            for (PortfolioSymbol obj : values) {


                try {
                    double amountVal = Double.parseDouble(obj.getCapGainLoss().replace(",", ""));

                    totalValue = totalValue + amountVal;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            PortfolioFooter portfolioFooter = new PortfolioFooter();

//            TextView sym = (TextView) footerView.findViewById(R.id.acc_sym);
//            TextView qty = (TextView) footerView.findViewById(R.id.acc_qty);
//            TextView amount = (TextView) footerView.findViewById(R.id.acc_ammount);
//            TextView totalPortfolio = (TextView) footerView.findViewById(R.id.acc_totalPort);
//            TextView totalPortfolioValue = (TextView) footerView.findViewById(R.id.acc_totalPortValue);
//            footerView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.greyDarkBar));

//            if (portfolio_list.getFooterViewsCount() > 0) {
//
//            } else {
//                portfolio_list.addFooterView(footerView);
//                sym.setText("");
//                qty.setText("Total Profit/loss");
//                totalPortfolio.setText("Total Portfolio");
//
//            }
            //  double cashvalue = Double.parseDouble("");
            Double totportfoliosum = totalValue;

//            totalValue= Math.round(totalValue);

            String sum = String.format("%.0f", totalValue);
            String portsum = String.format("%.0f", totportfoliosum);
            double portfoliosum = Double.parseDouble(portsum);
            DecimalFormat formatter = new DecimalFormat("#,###,###,###.00");
            //totalPortfolioValue.setText(formatter.format(portfoliosum));
            String yourFormattedString = NumberFormat.getNumberInstance(Locale.UK).format(totalValue);
            portfolioFooter.setTotalProfitloss(yourFormattedString);
            ArrayList<Portfolio> arrayMain = new ArrayList<>();
            arrayMain.addAll(values);
            arrayMain.add(portfolioFooter);
            PortfolioAdapter portfolioAdapter = new PortfolioAdapter(getActivity(), arrayMain, this);
            portfolio_list.setAdapter(portfolioAdapter);
            portfolioAdapter.notifyDataSetChanged();
            setUpPieChart();

//                amount.setText(yourFormattedString);
//            portfolio_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
            //   ((MainActivity) getActivity()).showPortFolioDetail(values.get(position));
//                }
//            });

        } else {
            Alert.show(getActivity(), getString(R.string.app_name), "You do not have any portfolio yet.");
        }
    }

    private void setUpPieChart() {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(false);

        pieChart.setDrawEntryLabels(false);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(false);

        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        if (values1 != null) {

            ArrayList<PieEntry> entries = new ArrayList<>();
            List<LegendEntry> legendEntries = new ArrayList<>();

            for (int i = 0; i < values1.size(); i++) {

                float percent = Float.valueOf(values1.get(i).getPfWeight());

                if (percent > 0) {

                    entries.add(new PieEntry(percent, values1.get(i).getSymbol()));
                }
            }

            for (int i = 0; i < entries.size(); i++) {

                legendEntries.add(new LegendEntry(entries.get(i).getLabel(), Legend.LegendForm.CIRCLE,
                        Float.NaN, Float.NaN, null, chartColors[i]));
            }

            PieDataSet dataSet = new PieDataSet(entries, "Allocation by Funds");
            dataSet.setDrawIcons(false);
            dataSet.setSliceSpace(3f);
            dataSet.setIconsOffset(new MPPointF(0, 40));
            dataSet.setSelectionShift(5f);
            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            dataSet.setValueTextColor(Color.BLACK);
            dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            dataSet.setValueLinePart1OffsetPercentage(80.f);
            dataSet.setValueLinePart1Length(0.2f);
            dataSet.setValueLinePart2Length(0.4f);

            dataSet.setColors(chartColors);
            dataSet.setSelectionShift(4f);
            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(11f);
            data.setValueTextColor(Color.BLACK);
            pieChart.setData(data);

            // undo all highlights
            pieChart.highlightValues(null);

            pieChart.invalidate();


            Legend l = pieChart.getLegend();
            l.setCustom(legendEntries);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
        }

    }

    @Override
    public void onPortfolioClick(PortfolioSymbol mItem) {
        ((MyMainActivity) getActivity()).showPortFolioDetail(mItem);

    }
}
