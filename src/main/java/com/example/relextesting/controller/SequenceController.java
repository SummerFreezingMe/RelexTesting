package com.example.relextesting.controller;

import com.example.relextesting.SequenceService;
import com.example.relextesting.domain.NumberSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;
    private NumberSequence sequence;

    @RequestMapping(
            value = {"/start"},
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> loadData(@RequestBody String json) {
        System.out.println(json);
        sequence = sequenceService.readFile(json);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/get_max_value", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Map<String, String> getMaxValue() {
        Map<String, String> model = new HashMap<>();
        Long n = sequenceService.getMaxValue(sequence);
        model.put("maximum_value", String.valueOf(n));
        return model;
    }

    @GetMapping("/get_min_value")
    public Map<String, String> getMinValue() {
        Long n = sequenceService.getMinValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("minimum_value", String.valueOf(n));
        return model;
    }

    @GetMapping("/get_medium_value")
    public Map<String, String> getMediumValue() {

        OptionalDouble n = sequenceService.getMediumValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("medium_value", String.valueOf(n));
        return model;
    }

    @GetMapping("/get_average_value")
    public Map<String, String> getAverageValue() {

        Long n = sequenceService.getAverageValue(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("average_value", String.valueOf(n));
        return model;
    }

    @GetMapping("/get_asc_sequence")
    public Map<String, String> getAscSequence() {

        List<List<Long>> n = sequenceService.getAscSequence(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("max_ascending_sequence", String.valueOf(n));
        return model;
    }

    @GetMapping("/get_desc_sequence")
    public Map<String, String> getDescSequence() {
        List<List<Long>> n = sequenceService.getDescSequence(sequence);
        Map<String, String> model = new HashMap<>();
        model.put("max_descending_sequence", String.valueOf(n));
        return model;
    }

    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    public Map<String, String> getValue(@RequestBody String json) {
        return switch (sequenceService.readOperation(json)) {
            case ("get_max_value") -> getMaxValue();
            case ("get_min_value") -> getMinValue();
            case ("get_average_value") -> getAverageValue();
            case ("get_medium_value") -> getMediumValue();
            case ("get_asc_sequence") -> getAscSequence();
            case ("get_desc_sequence") -> getDescSequence();
            default -> null;
        };

    }
}
