package com.example.relextesting.domain;

import java.util.List;


public class NumberSequence {
    public List<Long> getSequence() {
        return sequence;
    }

    public void setSequence(List<Long> sequence) {
        this.sequence = sequence;
    }

    List<Long> sequence;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;

    public NumberSequence(List<Long> sequence) {
        this.sequence = sequence;
        this.size = sequence.size();
    }
}
