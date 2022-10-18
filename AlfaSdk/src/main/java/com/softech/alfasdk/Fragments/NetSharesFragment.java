package com.softech.alfasdk.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softech.alfasdk.Adapters.NetShareCustodyAdapter;
import com.softech.alfasdk.Adapters.SearchClientListAdapter;
import com.softech.alfasdk.MyMainActivity;
import com.softech.alfasdk.Models.NetShareModel.NetShareCustody;
import com.example.alfasdk.R;
import com.softech.alfasdk.Util.Alert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class NetSharesFragment extends Fragment implements NetShareCustodyAdapter.OnNetShareClickListener {

//    @BindView(R.id.textView_dateTimeNow)
    TextView textView_dateTimeNow;
//    @BindView(R.id.textView_dateTime)
    TextView textView_dateTime;
//    @BindView(R.id.recyclerView_netShares)
    RecyclerView recyclerView_netShares;
//    @BindView(R.id.textView_clientCode)
    TextView textView_clientCode;

    String dateToDisplay = "";
//    @BindView(R.id.etclientcode)
    EditText clientcode;
//    @BindView(R.id.search_list1)
    ListView listSearch1;
//    @BindView(R.id.search_list_view1)
    LinearLayout listSearch_view1;
    private SearchClientListAdapter searchClientListAdapter;
    ArrayList<String> clientlist;
    boolean isSetInitialText = false;

    public NetSharesFragment() {
    }

    public static NetSharesFragment newInstance() {
        return new NetSharesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_home);
        item.setVisible(false);

        MenuItem item2 = menu.findItem(R.id.action_net_share);
        item2.setVisible(true);

        MenuItem item1 = menu.findItem(R.id.action_feed_status);
        item1.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_net_share) {

            showDatePicker();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net_custody, container, false);
        textView_dateTimeNow=view.findViewById(R.id.textView_dateTimeNow);
        textView_dateTime=view.findViewById(R.id.textView_dateTime);
        recyclerView_netShares=view.findViewById(R.id.recyclerView_netShares);
        textView_clientCode=view.findViewById(R.id.textView_clientCode);
        clientcode=view.findViewById(R.id.etclientcode);
        listSearch1=view.findViewById(R.id.search_list1);
        listSearch_view1=view.findViewById(R.id.search_list_view1);
//        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("Net Shares");
        }
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_netShares.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_netShares.setHasFixedSize(true);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        textView_dateTimeNow.setText(currentDateTimeString);

        textView_clientCode.setText(MyMainActivity.loginResponse.getResponse().getClient());
        if (MyMainActivity.loginResponse.getResponse().getUsertype() == 1 ||
                MyMainActivity.loginResponse.getResponse().getUsertype() == 2) {

            clientcode.setText(MyMainActivity.loginResponse.getResponse().getClient());
            clientcode.setEnabled(false);
            setAsOnDate(null);
        } else if (MyMainActivity.loginResponse.getResponse().getUsertype() == 0 ||
                MyMainActivity.loginResponse.getResponse().getUsertype() == 3) {
            clientlist = new ArrayList<String>(MyMainActivity.loginResponse.getResponse().getClientlist());
            Log.d("clientlist",clientlist.toString());
            searchClientListAdapter = new SearchClientListAdapter(getActivity(), clientlist);
            listSearch1.setAdapter(searchClientListAdapter);
        }
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
                isSetInitialText = true;
                clientcode.setText(clientlist.get(position));
                textView_clientCode.setText(clientlist.get(position));
                setAsOnDate(null);

            }
        });
    }

    public void setAsOnDate(Calendar c) {

        if (c == null) {
            c = Calendar.getInstance();
        }

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateToDisplay = df.format(c.getTime());

        textView_dateTime.setText(String.format("As On: %s", dateToDisplay));


        ((MyMainActivity) getActivity()).netSharesRequest(dateToDisplay,clientcode.getText().toString());
    }

    public void setValues(List<NetShareCustody> netShareCustodies) {

        if (netShareCustodies != null && netShareCustodies.size() > 0) {

            NetShareCustodyAdapter adapter = new NetShareCustodyAdapter(getActivity(), netShareCustodies, this);

            recyclerView_netShares.setAdapter(adapter);

        } else {

            Alert.show(getActivity(), getString(R.string.app_name), "No Net Shares to display, Try again later.");
        }

    }

    private void showDatePicker() {

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                setAsOnDate(c);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    @Override
    public void onNetShareClick(NetShareCustody mItem) {

        ((MyMainActivity) getActivity()).goToNetShareDetail(mItem);
    }
}
