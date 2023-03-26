package com.example.appmath2023.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmath2023.local.AppCache;
import com.example.appmath2023.view.adapter.HistoryAdapter;
import com.example.appmath2023.databinding.FragmentPlusBinding;

import java.util.List;
import java.util.Random;

public class PlusFragment extends Fragment {
    FragmentPlusBinding binding;
    int number1, number2, result, ifResultFalse;
    int point = 100;
    int time = 5;
    boolean isTrue;
    boolean isTime = true;
    String caculation;
    HistoryAdapter historyAdapter;
    AppCache appCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlusBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyAdapter = new HistoryAdapter();
        binding.recyclerviewHistory.setAdapter(historyAdapter);
        binding.recyclerviewHistory.setHasFixedSize(true);
        getHistory();
        randomNumber();
        time();
        delayButtonReset();
        eventListener();
    }

    private void eventListener() {
        binding.checkboxDeleteHistory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.recyclerviewHistory.setVisibility(View.VISIBLE);
                    binding.textViewDeleteHistory.setVisibility(View.VISIBLE);
                } else {
                    binding.recyclerviewHistory.setVisibility(View.GONE);
                    binding.textViewDeleteHistory.setVisibility(View.GONE);
                }
            }
        });

        binding.textViewDeleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyAdapter.updateHistoryList(null);
            }
        });

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomNumber();
                time();
                isTime = true;
                binding.buttonReset.setEnabled(false);
                binding.buttonCorrect.setEnabled(true);
                binding.buttonWrong.setEnabled(true);
                time = 5;
                binding.textViewTime.setText(time + "");
            }
        });

        binding.buttonCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTrue) {
                    Toast.makeText(getActivity(), "✅ Chính xác", Toast.LENGTH_SHORT).show();
                    binding.textViewTime.setText("Đáp án là A.Đúng");
                    point = point + 10;
                } else {
                    Toast.makeText(getActivity(), "❌ Chưa chính xác", Toast.LENGTH_SHORT).show();
                    binding.textViewTime.setText("Đáp án là B.Sai");
                    point = point - 10;
                }
                binding.textViewPoint.setText(point + "");
                historyAdapter.updateHistoryList(caculation);
                isTime = false;
                binding.buttonReset.setEnabled(true);
                binding.buttonCorrect.setEnabled(false);
                binding.buttonWrong.setEnabled(false);
            }
        });

        binding.buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isTrue) {
                    Toast.makeText(getActivity(), "✅ Chính xác", Toast.LENGTH_SHORT).show();
                    binding.textViewTime.setText("Đáp án là B.Sai");
                    point = point + 10;
                } else {
                    Toast.makeText(getActivity(), "❌ Chưa chính xác", Toast.LENGTH_SHORT).show();
                    binding.textViewTime.setText("Đáp án là A.Đúng");
                    point = point - 10;
                }
                binding.textViewPoint.setText(point + "");
                historyAdapter.updateHistoryList(caculation);
                isTime = false;
                binding.buttonReset.setEnabled(true);
                binding.buttonCorrect.setEnabled(false);
                binding.buttonWrong.setEnabled(false);
            }
        });
    }

    public void delayButtonReset() {
        binding.buttonReset.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.buttonReset.setEnabled(true);
            }
        }, 6000);
    }

    private void getHistory() {
        appCache = AppCache.getInstance(getActivity());
        List<String> historySaved = appCache.getDataList(key());
        if (historySaved != null) {
            historyAdapter.setHistoryList(historySaved);
        }
    }

    public String key() {
        String key = "historyPlus";
        return key;
    }

    public void randomNumber() {
        Random random = new Random();
        number1 = random.nextInt(9) + 1;
        number2 = random.nextInt(9) + 1;
        result = number1 + number2;
        isTrue = random.nextBoolean();
        ifResultFalse = random.nextInt(3 - 1) + 1;

        // Kết quả phép tính
        if (isTrue) {
            binding.textViewCaculation.setText(number1 + " + " + number2 + " = " + result);
            caculation = "✅ " + binding.textViewCaculation.getText().toString();
        } else {
            binding.textViewCaculation.setText(number1 + " + " + number2 + " = " + (result + ifResultFalse));
            caculation = "❌ " + binding.textViewCaculation.getText().toString();
        }
    }

    private void time() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time > 0 && isTime) {
                    time = time - 1;
                    binding.textViewTime.setText(time + "");
                    handler.postDelayed(this, 1000);
                } else if (time == 0) {
                    if (isTrue) {
                        binding.textViewTime.setText("Hết giờ, đáp án là A.Đúng");
                    } else {
                        binding.textViewTime.setText("Hết giờ, đáp án là B.Sai");
                    }
                    handler.removeCallbacks(this);
                    point = point - 10;
                    binding.textViewPoint.setText(point + "");
                    historyAdapter.updateHistoryList(caculation);
                    binding.buttonReset.setEnabled(true);
                    binding.buttonCorrect.setEnabled(false);
                    binding.buttonWrong.setEnabled(false);
                }

            }
        }, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        appCache = AppCache.getInstance(getActivity());
        appCache.saveDataList(key(), historyAdapter.getHistoryList());
    }
}
