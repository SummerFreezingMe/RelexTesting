package com.example.relextesting.controller;

import com.example.relextesting.SequenceService;
import com.example.relextesting.domain.NumberSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;

    @RequestMapping(value = "/get_max_value",method = RequestMethod.GET,produces = { "application/json", "application/xml" })
    public Map<String, String> getMaxValue( ) {
        Map<String, String> model = new HashMap<>();
        NumberSequence sequence = sequenceService.readFile();
        Long n = sequenceService.getMaxValue(sequence);
        model.put("maximum_value",String.valueOf(n));
        return model;
    }

    @GetMapping("/get_min_value")
    public Map<String, String> getMinValue() {
        NumberSequence sequence = sequenceService.readFile();
        Long n = sequenceService.getMinValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("minimum_value",String.valueOf(n));
        return model;
    }

    @GetMapping("/get_medium_value")
    public Map<String, String> getMediumValue() {
        NumberSequence sequence = sequenceService.readFile();
        OptionalDouble n = sequenceService.getMediumValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("medium_value",String.valueOf(n));
        return model;
    }

    @GetMapping("/get_average_value")
    public Map<String, String> getAverageValue() {
        NumberSequence sequence = sequenceService.readFile();
        Long n = sequenceService.getAverageValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("average_value",String.valueOf(n));
        return model;
    }

    @GetMapping("/get_asc_sequence")
    public Map<String, String> getAscSequence() {
        NumberSequence sequence = sequenceService.readFile();
        List<List<Long>> n = sequenceService.getAscSequence(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("max_ascending_sequence",String.valueOf(n));
        return model;
    }

    @GetMapping("/get_desc_sequence")
    public Map<String, String> getDescSequence() {
        NumberSequence sequence = sequenceService.readFile();
        List<List<Long>> n = sequenceService.getDescSequence(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("max_descending_sequence",String.valueOf(n));
        return model;
    }
}
