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
import android.widget.Toast;

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
    private Spinner spinnerNumber;


    private Sex spinnerSex;
    private String addressString;
    private int houseNumber;

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
        spinnerNumber = (Spinner) v.findViewById(R.id.spinner_house_number);


//        ArrayAdapter<Integer> houseNumberAdapter = ArrayAdapter<Integer>.createFromResource(getContext(),
//                R.array.sex_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerNumber.setAdapter(houseNumberAdapter);


        ArrayAdapter addressAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.address_array, android.R.layout.simple_spinner_item);
        addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(addressAdapter);
        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        addressString = "E-602";

//                        Integer[] items = createItems(38);

                        Integer[] items0 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38};
                        ArrayAdapter<Integer> numberSpinnerAdapter = new ArrayAdapter<>(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items0);
                        spinnerNumber.setAdapter(numberSpinnerAdapter);
                        break;
                    case 1:
                        addressString = "E-604";

//                        Integer[] items1 = createItems(39);

                        Integer[] items1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};

                        ArrayAdapter numberSpinnerAdapter1 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items1);
                        spinnerNumber.setAdapter(numberSpinnerAdapter1);
                        break;
                    case 2:
                        addressString = "E-605";

//                        Integer[] items2 = createItems(21);

                        Integer[] items2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21};
                        ArrayAdapter numberSpinnerAdapter2 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items2);
                        spinnerNumber.setAdapter(numberSpinnerAdapter2);

                        break;
                    case 3:
                        addressString = "E-606";

//                        Integer[] items3 = createItems(24);

                        Integer[] items3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24};

                        ArrayAdapter numberSpinnerAdapter3 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items3);
                        spinnerNumber.setAdapter(numberSpinnerAdapter3);

                        break;
                    case 4:
                        addressString = "E-607";

//                        Integer[] items4 = createItems(55);

                        Integer[] items4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
                                39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55};
                        ArrayAdapter numberSpinnerAdapter4 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items4);
                        spinnerNumber.setAdapter(numberSpinnerAdapter4);
//
                        break;
                    case 5:
                        addressString = "E-350";

//                        Integer[] items5 = createItems(26);

                        Integer[] items5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26};

                        ArrayAdapter numberSpinnerAdapter5 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items5);
                        spinnerNumber.setAdapter(numberSpinnerAdapter5);
                        break;
                    case 6:
                        addressString = "E-487";

//                        Integer[] items6 = createItems(49);

                        Integer[] items6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
                                39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
                        ArrayAdapter numberSpinnerAdapter6 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items6);
                        spinnerNumber.setAdapter(numberSpinnerAdapter6);
                        break;
                    case 7:
                        addressString = "E-486";

//                        Integer[] items7 = createItems(28);

                        Integer[] items7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
                        ArrayAdapter numberSpinnerAdapter7 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items7);
                        spinnerNumber.setAdapter(numberSpinnerAdapter7);
                        break;
                    case 8:
                        addressString = "E-488";

//                        Integer[] items8 = createItems(22);

                        Integer[] items8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22};

                        ArrayAdapter numberSpinnerAdapter8 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items8);
                        spinnerNumber.setAdapter(numberSpinnerAdapter8);
                        break;
                    case 9:
                        addressString = "Будапешт";

//                        Integer[] items9 = createItems(41);

                        Integer[] items9 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};

                        ArrayAdapter numberSpinnerAdapter9 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items9);
                        spinnerNumber.setAdapter(numberSpinnerAdapter9);

                        break;
                    case 10:
                        addressString = "Лазурная";

//                        Integer[] items10 = createItems(23);

                        Integer[] items10 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                                19, 20, 21, 22, 23};

                        ArrayAdapter numberSpinnerAdapter10 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items10);
                        spinnerNumber.setAdapter(numberSpinnerAdapter10);
                        break;
                    case 11:
                        addressString = "Жемчужинная";

//                        Integer[] items11 = createItems(18);

                        Integer[] items11 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

                        ArrayAdapter numberSpinnerAdapter11 = new ArrayAdapter(getContext(),
                                R.layout.item_spinner_sing_up_fragment, items11);
                        spinnerNumber.setAdapter(numberSpinnerAdapter11);
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

        spinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                houseNumber = i + 1;

                Log.d("singup", "housenumber = " + houseNumber + " " + adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sendConfirmCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("singupfragment", "onclickSendCode");

                String name = firstNameEditText.getText().toString().trim();
                String age_string = ageEditText.getText().toString().trim();

                if (!firstNameEditText.getText().toString().isEmpty() && !age_string.equals("")) {
//                    startAuth(firstNameEditText.getText().toString().trim());
                    startAuth(name, Integer.parseInt(age_string),
                            addressString, spinnerSex, houseNumber);

                }else{
                    Toast.makeText(getContext(), "Заполните все поля пожалуйста", Toast.LENGTH_LONG).show();
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

        if (i == 0) {
            spinnerSex = Sex.MALE;
        } else {
            spinnerSex = Sex.FEMALE;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

   /* public Integer[] createItems(int maxNumber){
        Integer[] array = new Integer[maxNumber + 1];

        for (int i = 1; i < maxNumber + 1; i++) {
            array[i] = i;
        }

        return array;
    }*/

}