package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    CryptoStakingProfitFragment depositProfitFragment = new CryptoStakingProfitFragment();
    CryptoLoanCalculatorFragment creditPaymentFragment = new CryptoLoanCalculatorFragment();
    ExchangeFragment exchangeFragment = new ExchangeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Accounting of material assets");
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (item.getItemId() == R.id.loan_calc) {
                    actionBar.setTitle("Crypto Loan Calculator");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, creditPaymentFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                else if (item.getItemId() == R.id.exchange_rate) {
                    actionBar.setTitle("Cryptocurrency");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, exchangeFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                else if (item.getItemId() == R.id.deposit_calc) {
                    actionBar.setTitle("Staking Calculator");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, depositProfitFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            });
        }

    }
}