package com.example.bank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CryptoStakingProfitFragment extends Fragment {

    private EditText stakingAmountEditText;
    private EditText apyEditText;
    private EditText stakingTermEditText;
    private TextView stakingProfitTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_staking_profit, container, false);

        stakingAmountEditText = view.findViewById(R.id.stakingAmountEditText);
        apyEditText = view.findViewById(R.id.apyEditText);
        stakingTermEditText = view.findViewById(R.id.stakingTermEditText);
        stakingProfitTextView = view.findViewById(R.id.stakingProfitTextView);
        Button calculateButton = view.findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(v -> calculateStakingProfit());

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void calculateStakingProfit() {
        String stakingAmountString = stakingAmountEditText.getText().toString();
        String apyString = apyEditText.getText().toString();
        String stakingTermString = stakingTermEditText.getText().toString();

        if (!stakingAmountString.isEmpty() && !apyString.isEmpty() && !stakingTermString.isEmpty()) {
            double stakingAmount = Double.parseDouble(stakingAmountString);
            double apy = Double.parseDouble(apyString);
            int stakingTerm = Integer.parseInt(stakingTermString); // в днях

            // Расчёт прибыли с учетом сложных процентов по APY
            double dailyRate = apy / 100 / 365;
            double totalProfit = stakingAmount * (Math.pow(1 + dailyRate, stakingTerm) - 1);

            stakingProfitTextView.setText(String.format("Ожидаемая прибыль: %.2f", totalProfit));
        } else {
            stakingProfitTextView.setText("Введите сумму, APY и срок стейкинга");
        }
    }
}
