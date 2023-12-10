package com.flow.controller;

import com.flow.entity.Custom;
import com.flow.entity.CustomDto;
import com.flow.entity.Fix;
import com.flow.repository.FixRepository;
import com.flow.service.FlowService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestFlowController {

    private final FlowService flowService;

    @Autowired
    public RestFlowController(FlowService flowService) {
        this.flowService = flowService;
    }
    @PostMapping("/status")
    public ResponseEntity<Void> updateStatus(@RequestBody List<Fix> fixes) {
        flowService.updateFixStatus(fixes);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/status")
    public ResponseEntity<List<Fix>> getAllFix() {
        List<Fix> fixes = flowService.getAllFix();
        return ResponseEntity.ok(fixes);
    }
    @PostMapping("/addBadge")
    public ResponseEntity<Custom> addBadge(@RequestBody CustomDto customDto) {
        Custom custom = flowService.addCustomBadge(customDto.getCustomName());
        return ResponseEntity.ok(custom);
    }

    @DeleteMapping("/deleteBadge")
    public ResponseEntity<Void> deleteBadge(@RequestParam String customName) {
        flowService.deleteCustomBadge(customName);
        return ResponseEntity.ok().build();
    }
}
