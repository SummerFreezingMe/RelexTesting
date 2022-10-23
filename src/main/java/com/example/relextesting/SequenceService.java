package com.example.relextesting;

import com.example.relextesting.domain.NumberSequence;


import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class SequenceService {
    public NumberSequence readFile() {
        File file = new File("src/main/java/com/example/relextesting/file_path.json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String jsonString = sb.toString();
        JSONObject jo = new JSONObject(jsonString);
        return readSequence(jo);
    }

    public NumberSequence readSequence(JSONObject jo) {
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

    public Long getMaxValue(NumberSequence n) {
        return Collections.max(n.getSequence());
    }

    public Long getMinValue(NumberSequence n) {
        return Collections.min(n.getSequence());
    }

    public OptionalDouble getMediumValue(NumberSequence n) {
        return n.getSequence().stream().mapToLong(a -> a).sorted()
                .skip((n.getSize() - 1) / 2).limit(2 - n.getSize() % 2).average();
    }

    public Long getAverageValue(NumberSequence n) {
        return n.getSequence().stream()
                .mapToLong(a -> a)
                .sum() / n.getSize();
    }

    public List<List<Long>> getAscSequence(NumberSequence n) {
        int[] ans = new int[n.getSize()];
        List<List<Long>> l = new ArrayList<>();
        Arrays.fill(ans, 1);
        int max = 0;
        for (int i = 1; i < ans.length; i++) {
                if (n.getSequence().get(i) > n.getSequence().get(i-1)) {
                    int peak = ans[i-1] + 1;
                    ans[i] = peak;
                    max = peak;
                }
            }
        for (int k = 0; k < ans.length; k++) {
           if (max == ans[k]){
               l.add(n.getSequence().subList(k-max+1,k+1));
           }
        }
        return l;
    }

    public List<List<Long>> getDescSequence(NumberSequence n) {
        int[] ans = new int[n.getSize()];
        List<List<Long>> l = new ArrayList<>();
        Arrays.fill(ans, -1);
        int min = 0;
        for (int i = 1; i < ans.length; i++) {
            if (n.getSequence().get(i) < n.getSequence().get(i-1)) {
                int peak = ans[i-1] - 1;
                ans[i] = peak;
                min = peak;
            }
        }
        for (int k = 0; k < ans.length; k++) {
            if (min == ans[k]){
                l.add(n.getSequence().subList(k+min+1,k+1));
            }
        }
        return l;
    }
}
