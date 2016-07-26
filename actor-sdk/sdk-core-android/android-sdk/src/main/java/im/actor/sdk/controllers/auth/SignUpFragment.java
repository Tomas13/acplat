package im.actor.sdk.controllers.auth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import im.actor.core.entity.Sex;
import im.actor.runtime.Log;
import im.actor.sdk.ActorSDK;
import im.actor.sdk.R;
import im.actor.sdk.controllers.Intents;
import im.actor.sdk.util.Screen;
import im.actor.sdk.view.SelectorFactory;
import im.actor.sdk.util.Fonts;
import im.actor.sdk.util.KeyboardHelper;

import static im.actor.sdk.util.ActorSDKMessenger.messenger;

public class SignUpFragment extends BaseAuthFragment implements AdapterView.OnItemSelectedListener {

    private EditText firstNameEditText;
    private KeyboardHelper keyboardHelper;

    private EditText ageEditText;   //OTG
    private Spinner sexSpinner;
    private Spinner addressSpinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        v.setBackgroundColor(ActorSDK.sharedActor().style.getMainBackgroundColor());

        keyboardHelper = new KeyboardHelper(getActivity());
        TextView buttonConfirm = (TextView) v.findViewById(R.id.button_confirm_sms_code_text);
        buttonConfirm.setTypeface(Fonts.medium());
        StateListDrawable states = SelectorFactory.get(ActorSDK.sharedActor().style.getMainColor(), getActivity());
        buttonConfirm.setBackgroundDrawable(states);
        buttonConfirm.setTextColor(ActorSDK.sharedActor().style.getTextPrimaryInvColor());

        firstNameEditText = (EditText) v.findViewById(R.id.et_first_name_enter);
        firstNameEditText.setTextColor(ActorSDK.sharedActor().style.getTextPrimaryColor());
        firstNameEditText.setHintTextColor(ActorSDK.sharedActor().style.getTextHintColor());
        final View sendConfirmCodeButton = v.findViewById(R.id.button_confirm_sms_code);
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ((TextView) v.findViewById(R.id.sign_up_hint)).setTextColor(ActorSDK.sharedActor().style.getTextSecondaryColor());

        sexSpinner = (Spinner) v.findViewById(R.id.sex_spinner);
        addressSpinner = (Spinner) v.findViewById(R.id.spinner_address);
        ageEditText = (EditText) v.findViewById(R.id.tv_age);


        ArrayAdapter addressAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.address_array, android.R.layout.simple_spinner_item);
        addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(addressAdapter);
        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0: addressString = "E-602 1-38";
                        break;
                    case 1: addressString = "E-604 1-39";
                        break;
                    case 2: addressString = "E-605 1-21";
                        break;
                    case 3: addressString = "E-606 1-24";
                        break;
                    case 4: addressString = "E-607 1-55";
                        break;
                    case 5: addressString = "E-350 1-26";
                        break;
                    case 6: addressString = "E-487 1-49";
                        break;
                    case 7: addressString = "E-486 1-28";
                        break;
                    case 8: addressString = "E-488 1-22";
                        break;
                    case 9: addressString = "Будапешт 1-41";
                        break;
                    case 10: addressString = "Лазурная 1-23";
                        break;
                    case 11: addressString = "Жемчужинная 1-18";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter);
        sexSpinner.setOnItemSelectedListener(this);


        sendConfirmCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstNameEditText.getText().toString().isEmpty()) {
//                    startAuth(firstNameEditText.getText().toString().trim());
                    startAuth(firstNameEditText.getText().toString().trim(),
                            Integer.parseInt( ageEditText.getText().toString().trim()),
                                    addressString, spinnerSex);


                }
            }
        });
        v.findViewById(R.id.divider).setBackgroundColor(style.getDividerColor());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(R.string.auth_profile_title);
        focus(firstNameEditText);
        keyboardHelper.setImeVisibility(firstNameEditText, true);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.sign_up, menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("singup", "i = " + i + " " + adapterView.getItemAtPosition(i).toString());

        if (i == 0){
            spinnerSex = Sex.MALE;
        }else{
            spinnerSex = Sex.FEMALE;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    Sex spinnerSex;
    String addressString;
}