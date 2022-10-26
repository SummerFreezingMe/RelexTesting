package com.example.relextesting;

import com.example.relextesting.domain.NumberSequence;


import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class SequenceService {
    public NumberSequence readFile(String jsonString) {
        JSONObject jo = new JSONObject(jsonString);
        File file = new File(String.valueOf(jo.get("file_path")));
        List<Long> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                list.add(Long.valueOf(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new NumberSequence(list);
    }

    public String readOperation(String jsonString) {
        JSONObject jo = new JSONObject(jsonString);
        return String.valueOf(jo.get("operation"));
    }
    public Long getMaxValue(NumberSequence n) {
        return Collections.max(n.getSequence());
    }

    public Long getMinValue(NumberSequence n) {
        return Collections.min(n.getSequence());
    }

    public Double getMediumValue(NumberSequence n) {
        return n.getSequence().isEmpty() ? null : n.getSequence().stream().mapToLong(a -> a).sorted()
                .skip((n.getSize() - 1) / 2).limit(2 - n.getSize() % 2).average().getAsDouble();
    }

    public Long getAverageValue(NumberSequence n) {
        return n.getSequence().stream()
                .mapToLong(a -> a)
                .sum() / n.getSize();
    }

    public List<List<Long>> getAscSequence(NumberSequence n) {
        int[] lengthMatrix = new int[n.getSize()];
        List<List<Long>> list = new ArrayList<>();
        Arrays.fill(lengthMatrix, 1);
        int max = 0;
        for (int i = 1; i < lengthMatrix.length; i++) {
            if (n.getSequence().get(i) > n.getSequence().get(i - 1)) {
                int peak = lengthMatrix[i - 1] + 1;
                lengthMatrix[i] = peak;
                if (peak > max) {
                    max = peak;
                }
            }
        }
        for (int k = 0; k < lengthMatrix.length; k++) {
            if (max == lengthMatrix[k]) {
                list.add(n.getSequence().subList(k - max + 1, k + 1));
            }
        }
        return list;
    }

    public List<List<Long>> getDescSequence(NumberSequence n) {
        int[] lengthMatrix = new int[n.getSize()];
        List<List<Long>> list = new ArrayList<>();
        Arrays.fill(lengthMatrix, -1);
        int min = 0;
        for (int i = 1; i < lengthMatrix.length; i++) {
            if (n.getSequence().get(i) < n.getSequence().get(i - 1)) {
                int peak = lengthMatrix[i - 1] - 1;
                lengthMatrix[i] = peak;
                if (peak < min) {
                    min = peak;
                }
            }
        }
        for (int k = 0; k < lengthMatrix.length; k++) {
            if (min == lengthMatrix[k]) {
                list.add(n.getSequence().subList(k + min + 1, k + 1));
            }
        }
        return list;
    }
}
