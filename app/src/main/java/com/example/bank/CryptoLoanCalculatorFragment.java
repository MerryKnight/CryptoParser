package com.example.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CryptoLoanCalculatorFragment extends Fragment {

    private EditText loanAmountEditText;
    private EditText interestRateEditText;
    private EditText loanTermEditText;
    private TextView monthlyPaymentTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_loan_calculator, container, false);

        loanAmountEditText = view.findViewById(R.id.loanAmountEditText);
        interestRateEditText = view.findViewById(R.id.interestRateEditText);
        loanTermEditText = view.findViewById(R.id.loanTermEditText);
        monthlyPaymentTextView = view.findViewById(R.id.monthlyPaymentTextView);
        Button calculateButton = view.findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(v -> calculateMonthlyPayment());

        return view;
    }

    private void calculateMonthlyPayment() {
        String loanAmountString = loanAmountEditText.getText().toString();
        String interestRateString = interestRateEditText.getText().toString();
        String loanTermString = loanTermEditText.getText().toString();

        if (!loanAmountString.isEmpty() && !interestRateString.isEmpty() && !loanTermString.isEmpty()) {
            double loanAmount = Double.parseDouble(loanAmountString);
            double interestRate = Double.parseDouble(interestRateString);
            int loanTermMonths = Integer.parseInt(loanTermString);

            double monthlyInterestRate = interestRate / 100 / 12;
            int numberOfPayments = loanTermMonths;

            double monthlyPayment = (loanAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

            monthlyPaymentTextView.setText(String.format("Ежемесячный платеж: %.2f USDT", monthlyPayment));
        } else {
            monthlyPaymentTextView.setText("Введите сумму займа, процентную ставку и срок займа");
        }
    }
}
