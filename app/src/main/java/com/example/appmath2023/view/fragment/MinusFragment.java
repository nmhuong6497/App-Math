package com.example.appmath2023.view.fragment;

import java.util.Random;

public class MinusFragment extends PlusFragment {
    @Override
    public String key() {
        String key = "historyMinus";
        return key;
    }

    @Override
    public void randomNumber() {
        Random random = new Random();
        number1 = random.nextInt(9) + 1;
        number2 = random.nextInt(9) + 1;
        result = number1 - number2;
        isTrue = random.nextBoolean();
        ifResultFalse = random.nextInt(3 - 1) + 1;

        // Kết quả phép tính
        if (isTrue) {
            binding.textViewCaculation.setText(number1 + " - " + number2 + " = " + result);
            caculation = "✅ " + binding.textViewCaculation.getText().toString();
        } else {
            binding.textViewCaculation.setText(number1 + " - " + number2 + " = " + (result + ifResultFalse));
            caculation = "❌ " + binding.textViewCaculation.getText().toString();
        }
    }
}
