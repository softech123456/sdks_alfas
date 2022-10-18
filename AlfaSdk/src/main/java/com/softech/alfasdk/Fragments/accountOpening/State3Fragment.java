package com.softech.alfasdk.Fragments.accountOpening;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softech.alfasdk.AccountOpeningActivity;
import com.softech.alfasdk.Adapters.CustomArrayAdapter;
import com.softech.alfasdk.Const.Constants;
import com.softech.alfasdk.Models.AccountOpening.AccountOpeningObject;
import com.softech.alfasdk.Models.CityTownVillageModel.CityTownVillage;
import com.example.alfasdk.R;
import com.softech.alfasdk.Util.Alert;
import com.softech.alfasdk.Util.CityTownVillages;
import com.softech.alfasdk.Util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class State3Fragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "State3Fragment";

    private ImageView ivBack;
    private TextView tvTitle;

    private TextInputEditText etMailingAddress;
    private TextInputLayout textInputLayoutMailingAddress;

    private AutoCompleteTextView atvMailingCity;
    private TextInputLayout textInputLayoutMailingCity;

    private AutoCompleteTextView atvMailingProvince;
    private TextInputLayout textInputLayoutMailingProvince;

    private TextInputEditText etMailingCountry;
    private TextInputLayout textInputLayoutMailingCountry;

    private TextInputEditText etTelOff;
    private TextInputLayout textInputLayoutTelOff;

    private TextInputEditText etTelRes;
    private TextInputLayout textInputLayoutTelRes;

    private TextInputEditText etFax;
    private TextInputLayout textInputLayoutFax;

    private TextInputEditText etMobile;
    private TextInputLayout textInputLayoutMobile;

    private TextInputEditText etEmail;
    private TextInputLayout textInputLayoutEmail;


    private CheckBox checkBox;
    private TextInputEditText etPermanentAddress;
    private TextInputLayout textInputLayoutPermanentAddress;

    private AutoCompleteTextView atvPermanentCity;
    private TextInputLayout textInputLayoutPermanentCity;

    private AutoCompleteTextView atvPermanentProvince;
    private TextInputLayout textInputLayoutPermanentProvince;

    private TextInputEditText etPermanentCountry;
    private TextInputLayout textInputLayoutPermanentCountry;


    private Button btnNext;

    private Boolean isMailingProvinceEnabled = false;
    private Boolean isPermanentProvinceEnabled = false;
    public String [] mListProvinces = {"SINDH", "PUNJAB", "KHYBER PAKHTUNKHWA", "BALOCHISTAN", "FEDERAL CAPITAL", "A.J.K.", "FATA / FANA"};
    public ArrayList<CityTownVillage> mListCityTownVillages = new ArrayList();

    private AccountOpeningObject obj;
    private ArrayAdapter<String> arrayAdapterProvinces;
    private CustomArrayAdapter customArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_state3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        checkData();
    }

    private void initViews(View view) {

        textInputLayoutMailingAddress = view.findViewById(R.id.textInputLayoutMailingAddress);
        textInputLayoutMailingCountry = view.findViewById(R.id.textInputLayoutMailingCountry);
        textInputLayoutMailingProvince = view.findViewById(R.id.textInputLayoutMailingProvince);
        textInputLayoutMailingCity = view.findViewById(R.id.textInputLayoutMailingCity);
        textInputLayoutTelOff = view.findViewById(R.id.textInputLayoutTelOff);
        textInputLayoutTelRes = view.findViewById(R.id.textInputLayoutTelRes);
        textInputLayoutFax = view.findViewById(R.id.textInputLayoutFax);
        textInputLayoutMobile = view.findViewById(R.id.textInputLayoutMobile);
        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPermanentAddress = view.findViewById(R.id.textInputLayoutPermanentAddress);
        textInputLayoutPermanentCity = view.findViewById(R.id.textInputLayoutPermanentCity);
        textInputLayoutPermanentProvince = view.findViewById(R.id.textInputLayoutPermanentProvince);
        textInputLayoutPermanentCountry = view.findViewById(R.id.textInputLayoutPermanentCountry);





        obj = ((AccountOpeningActivity) requireActivity()).accountOpeningObject;
        ivBack = view.findViewById(R.id.ivBack);
        tvTitle = view.findViewById(R.id.tvTitle);

        etMailingAddress = view.findViewById(R.id.etMailingAddress);


        atvMailingCity = view.findViewById(R.id.atvMailingCity);


        atvMailingProvince = view.findViewById(R.id.atvMailingProvince);


        etMailingCountry = view.findViewById(R.id.etMailingCountry);


        etTelOff = view.findViewById(R.id.etTelOff);


        etTelRes = view.findViewById(R.id.etTelRes);


        etFax = view.findViewById(R.id.etFax);


        etMobile = view.findViewById(R.id.etMobile);


        etEmail = view.findViewById(R.id.etEmail);



        checkBox = view.findViewById(R.id.checkBox);
        etPermanentAddress = view.findViewById(R.id.etPermanentAddress);


        atvPermanentCity = view.findViewById(R.id.atvPermanentCity);


        atvPermanentProvince = view.findViewById(R.id.atvPermanentProvince);


        etPermanentCountry = view.findViewById(R.id.etPermanentCountry);


        btnNext = view.findViewById(R.id.btnNext);

        ivBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        atvMailingProvince.setOnClickListener(this);
        atvPermanentProvince.setOnClickListener(this);

        atvMailingProvince.setOnItemClickListener((adapterView, view1, i, l) -> {
            obj.setPROVINCESTATE(mListProvinces[i]);
        });
        atvPermanentProvince.setOnItemClickListener((adapterView, view1, i, l) -> {
            obj.setPERMANENTPROVINCE(mListProvinces[i]);
        });

        atvMailingCity.setOnItemClickListener((adapterView, view1, i, l) -> {
            CityTownVillage cityTownVillage = (CityTownVillage) adapterView.getAdapter().getItem(i);
            Log.e(TAG, "Mailing City: "+ cityTownVillage.getCityName());
        });
        atvPermanentCity.setOnItemClickListener((adapterView, view1, i, l) -> {
            CityTownVillage cityTownVillage = (CityTownVillage) adapterView.getAdapter().getItem(i);
            Log.e(TAG, "Permanent City: "+ cityTownVillage.getCityName());
        });

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                etPermanentAddress.setText(etMailingAddress.getText().toString());
                atvPermanentCity.setText(atvMailingCity.getText().toString());
                atvPermanentProvince.setText(atvMailingProvince.getText().toString());
            }else{
                etPermanentAddress.setText("");
                atvPermanentCity.setText("");
                atvPermanentProvince.setText("");
            }
        });

    }

    private void checkData() {
        if(obj.getCOUNTRY().isEmpty() || obj.getCOUNTRY()==null){
            obj.setCOUNTRY("Pakistan");
        }
        if(obj.getPERMANENTCOUNTRY().isEmpty() || obj.getPERMANENTCOUNTRY()==null){
            obj.setPERMANENTCOUNTRY("Pakistan");
        }

        if((obj.getPERMANENTADDRESS1().isEmpty() || obj.getPERMANENTADDRESS1()==null) &&
                (obj.getPERMANENTCITYTOWN().isEmpty() || obj.getPERMANENTCITYTOWN()==null) &&
                (obj.getPERMANENTPROVINCE().isEmpty() || obj.getPERMANENTPROVINCE()==null)
        ){
            Constants.shouldAddressCheckBoxVisible = true;
        }

        setData();
    }

    private void setData() {

        etMailingAddress.setText(obj.getMAILINGADDRESS1());
        atvMailingCity.setText(obj.getCITYTOWNVILLAGE());
        atvMailingProvince.setText(obj.getPROVINCESTATE());
        if(obj.getCOUNTRY().isEmpty() || obj.getCOUNTRY()==null){
            etMailingCountry.setText("Pakistan");
        }else{
            etMailingCountry.setText(obj.getCOUNTRY());
        }
        etTelOff.setText(obj.getTELEPHONEOFFICE());
        etTelRes.setText(obj.getTELEPHONERESIDENCE());
        etFax.setText(obj.getFAX());
        etMobile.setText(obj.getMOBILENO());
        etEmail.setText(obj.getEMAIL());
        etPermanentAddress.setText(obj.getPERMANENTADDRESS1());
        atvPermanentCity.setText(obj.getPERMANENTCITYTOWN());
        atvPermanentProvince.setText(obj.getPERMANENTPROVINCE());
        if(obj.getPERMANENTCOUNTRY().isEmpty() || obj.getPERMANENTCOUNTRY()==null){
            etPermanentCountry.setText("Pakistan");
        }else{
            etPermanentCountry.setText(obj.getPERMANENTCOUNTRY());
        }

        arrayAdapterProvinces = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, mListProvinces);

        mListCityTownVillages.clear();
        mListCityTownVillages.addAll(CityTownVillages.getCityTownVillages());
        customArrayAdapter = new CustomArrayAdapter(requireActivity(), R.layout.simple_text_view, mListCityTownVillages);

        setInputsEditAble();


    }

    private void setInputsEditAble() {


        if(obj.getMAILINGADDRESS1().isEmpty() || obj.getMAILINGADDRESS1()==null){
            Util.setInputEditable(etMailingAddress, true);
        }
        else
        {
            textInputLayoutMailingAddress.setVisibility(View.GONE);
        }

        if(obj.getCITYTOWNVILLAGE().isEmpty() || obj.getCITYTOWNVILLAGE()==null){
            Util.setInputEditable(atvMailingCity, true);
            atvMailingCity.setThreshold(0);
            atvMailingCity.setAdapter(customArrayAdapter);
        }
        else
        {
            textInputLayoutMailingCity.setVisibility(View.GONE);
        }

        if(obj.getPROVINCESTATE().isEmpty() || obj.getPROVINCESTATE()==null){
            isMailingProvinceEnabled = true;
            atvMailingProvince.setAdapter(arrayAdapterProvinces);
        } else
        {
            textInputLayoutMailingProvince.setVisibility(View.GONE);
        }


        if(obj.getCOUNTRY().isEmpty() || obj.getCOUNTRY()==null){
            Util.setInputEditable(etMailingCountry, true);
        }else
        {
            textInputLayoutMailingCountry.setVisibility(View.GONE);
        }

        if(obj.getTELEPHONEOFFICE().isEmpty() || obj.getTELEPHONEOFFICE()==null){
            Util.setInputEditable(etTelOff, true);
        }else
        {
            textInputLayoutTelOff.setVisibility(View.GONE);
        }

        if(obj.getTELEPHONERESIDENCE().isEmpty() || obj.getTELEPHONERESIDENCE()==null){
            Util.setInputEditable(etTelRes, true);
        }else
        {
            textInputLayoutTelRes.setVisibility(View.GONE);
        }

        if(obj.getFAX().isEmpty() || obj.getFAX()==null){
            Util.setInputEditable(etFax, true);
        }else
        {
            textInputLayoutFax.setVisibility(View.GONE);
        }

        if(obj.getMOBILENO().isEmpty() || obj.getMOBILENO()==null){
            Util.setInputEditable(etMobile, true);
        }else
        {
            textInputLayoutMobile.setVisibility(View.GONE);
        }

        if(obj.getEMAIL().isEmpty() || obj.getEMAIL()==null){
            Util.setInputEditable(etEmail, true);
        }else
        {
            textInputLayoutEmail
                    .setVisibility(View.GONE);
        }

        if(obj.getPERMANENTADDRESS1().isEmpty() || obj.getPERMANENTADDRESS1()==null){
            Util.setInputEditable(etPermanentAddress, true);
        }else
        {
            textInputLayoutPermanentAddress.setVisibility(View.GONE);
        }

        if(obj.getPERMANENTCITYTOWN().isEmpty() || obj.getPERMANENTCITYTOWN()==null){
            Log.e(TAG, "setInputsEditAble: "+ mListCityTownVillages);
            Util.setInputEditable(atvPermanentCity, true);
            atvPermanentCity.setThreshold(0);
            atvPermanentCity.setAdapter(customArrayAdapter);
        }else
        {
          textInputLayoutPermanentCity
                    .setVisibility(View.GONE);
        }

        if(obj.getPERMANENTPROVINCE().isEmpty() || obj.getPERMANENTPROVINCE()==null){
            isPermanentProvinceEnabled = true;
            atvPermanentProvince.setAdapter(arrayAdapterProvinces);
        }else
        {
            textInputLayoutPermanentProvince.setVisibility(View.GONE);
        }

        if(obj.getPERMANENTCOUNTRY().isEmpty() || obj.getPERMANENTCOUNTRY()==null){
            Util.setInputEditable(etPermanentCountry, true);
        }else
        {
            textInputLayoutPermanentCountry.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNext){
            if(isValidInputs()){
                Util.performNavigation(requireActivity(), R.id.action_state3Fragment_to_state4Fragment);
            }
        }
        else if(view.getId()==R.id.ivBack){
            requireActivity().onBackPressed();
        }
        else if(view.getId()==R.id.atvMailingProvince){
            if(isMailingProvinceEnabled){
                atvMailingProvince.showDropDown();
            }
        }
        else if(view.getId()==R.id.atvPermanentProvince){
            if(isPermanentProvinceEnabled){
                atvPermanentProvince.showDropDown();
            }
        }

    }

    private Boolean isValidInputs() {

        if(etMailingAddress.getText().toString().isEmpty()){
            //Util.setInputEditable(etMobile,true);
            Util.setInputError(etMailingAddress);
            return false;
        }else {
            obj.setMAILINGADDRESS1(etMailingAddress.getText().toString());
        }

        if(atvMailingCity.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a City for mailing address.");
            return false;
        }else {
            obj.setCITYTOWNVILLAGE(atvMailingCity.getText().toString());
            //            if(isValidCityName(atvMailingCity.getText().toString())){
//                obj.setCITYTOWNVILLAGE(atvMailingCity.getText().toString());
//            }else{
//                Alert.show(requireActivity(), "", "Please select a Valid City name for mailing address.");
//                return false;
//            }
        }

        if(atvMailingProvince.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a Province for mailing address.");
            return false;
        }else {
            obj.setPROVINCESTATE(atvMailingProvince.getText().toString());
        }

        if(etMailingCountry.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a Country for mailing address.");
            return false;
        }else {
            obj.setCOUNTRY(etMailingCountry.getText().toString());
        }

        if(etMobile.getText().toString().isEmpty()){
            //Util.setInputEditable(etMobile,true);
            Util.setInputError(etMobile);
            return false;
        }else {
            obj.setMOBILENO(etMobile.getText().toString());
        }

        if(etEmail.getText().toString().isEmpty()){
            //Util.setInputEditable(etEmail,true);
            Util.setInputError(etEmail);
            return false;
        }else {
            obj.setEMAIL(etEmail.getText().toString());
        }

        if(etPermanentAddress.getText().toString().isEmpty()){
            //Util.setInputEditable(etMobile,true);
            Util.setInputError(etPermanentAddress);
            return false;
        }else {
            obj.setPERMANENTADDRESS1(etPermanentAddress.getText().toString());
        }

        if(atvPermanentCity.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a City for permanent address.");
            return false;
        }else {
            obj.setPERMANENTCITYTOWN(atvPermanentCity.getText().toString());
            //            if(isValidCityName(atvPermanentCity.getText().toString())){
//                obj.setPERMANENTCITYTOWN(atvPermanentCity.getText().toString());
//            }else{
//                Alert.show(requireActivity(), "", "Please select a Valid City name for permanent address.");
//                return false;
//            }
        }

        if(atvPermanentProvince.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a Province for permanent address.");
            return false;
        }else {
            obj.setPERMANENTPROVINCE(atvPermanentProvince.getText().toString());
        }

        if(etPermanentCountry.getText().toString().isEmpty()){
            //Show Alert
            Alert.show(requireActivity(), "", "Please select a Country for permanent address.");
            return false;
        }else {
            obj.setPERMANENTCOUNTRY(etPermanentCountry.getText().toString());
        }

        return true;
    }

    private boolean isValidCityName(String cityName) {
        for (CityTownVillage cityTownVillage: mListCityTownVillages) {
            if(cityName.equals(cityTownVillage.getCityName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Constants.shouldAddressCheckBoxVisible){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }
        ((AccountOpeningActivity) requireActivity()).stepView.go(2, true);
    }


}
