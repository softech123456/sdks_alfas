package com.softech.alfasdk.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.softech.alfasdk.Adapters.ChartTypeAdapter;
import com.example.alfasdk.R;


/**
 * Developed by Hasham.Tahir on 10/27/2016.
 */

public class ChartTypeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnChartTypeListener mListener;

    public ChartTypeFragment() {
        // Required empty public constructor
    }

    public static ChartTypeFragment newInstance() {
        return new ChartTypeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("Chart Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView listView = (ListView) inflater.inflate(R.layout.fragment_chart_duration, container, false);

        listView.setAdapter(new ChartTypeAdapter(getActivity(), Charts.getChartTypes()));
        listView.setOnItemClickListener(this);
        return listView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnChartTypeListener) {
            mListener = (OnChartTypeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnChartTypeListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnChartTypeListener) {
            mListener = (OnChartTypeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnChartTypeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mListener != null) {
            Charts[] arr = Charts.getChartTypes();
            mListener.onChartTypeInteraction(arr[position].getType());
        }
    }

    public enum Type {

        CHART_LINE, CHART_BAR, CHART_CANDLE_STICK,
        //        CHART_FORCE_INDEX, CHART_BOLLINGER,
//        CHART_MEDIAN_PRICE, CHART_TRUE_RANGE, CHART_BOLLINGER_WIDTH,
        CHART_HYBRID
    }

    public interface OnChartTypeListener {
        void onChartTypeInteraction(Type val);
    }

    public static class Charts {

        private String title;
        private Type type;

        Charts(String title, Type type) {
            this.title = title;
            this.type = type;
        }

        static Charts[] getChartTypes() {

            Charts[] types = new Charts[4];

            types[0] = new Charts("Line Chart", Type.CHART_LINE);
            types[1] = new Charts("Bar Chart", Type.CHART_BAR);
            types[2] = new Charts("Candle Stick Chart", Type.CHART_CANDLE_STICK);
            types[3] = new Charts("Hybrid Chart", Type.CHART_HYBRID);

            return types;
        }

        public String getTitle() {
            return title;
        }

        public Type getType() {
            return type;
        }
    }
}
