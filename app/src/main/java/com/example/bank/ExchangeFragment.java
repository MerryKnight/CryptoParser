package com.example.bank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ExchangeFragment extends Fragment {
    private List<ListItemClass> arrayList;
    private CustomArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        init(view, inflater);
        return view;
    }

    private void init(View view, LayoutInflater inflater) {
        ListView listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(requireActivity(), R.layout.list_item_1, arrayList, inflater);
        listView.setAdapter(adapter);
        new Thread(() -> getWeb()).start();
    }

    private void getWeb() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CryptoItem>>(){}.getType();
                    List<CryptoItem> cryptoList = gson.fromJson(jsonData, listType);

                    arrayList.clear();
                    ListItemClass header = new ListItemClass();
                    header.setData_1("Название");
                    header.setData_2("Цена $");
                    header.setData_3("Капитализация");
                    arrayList.add(header);
                    for (CryptoItem crypto : cryptoList) {
                        ListItemClass item = new ListItemClass();
                        item.setData_1(crypto.name);
                        item.setData_2(String.valueOf(crypto.current_price));
                        item.setData_3(String.valueOf(crypto.market_cap));
                        arrayList.add(item);
                    }
                    if (isAdded()) {
                        requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                    } else {
                        Log.d("Error","RRR");
                    }

                }
            }
        });
        }
    }
