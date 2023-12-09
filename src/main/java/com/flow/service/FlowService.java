package com.flow.service;

import com.flow.entity.Fix;
import com.flow.repository.FixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowService {

    private final FixRepository fixRepository;

    @Autowired
    public FlowService(FixRepository fixRepository) {
        this.fixRepository = fixRepository;
    }

    public void updateFixStatus(List<Fix> fixes) {
        for(Fix fix : fixes) {
            fixRepository.findByFixName(fix.getFixName())
                    .ifPresent(existing -> {
                        existing.setFixStatus(fix.isFixStatus());
                        fixRepository.save(existing);
                    });
        }
    }

    public List<Fix> getAllFix() {
        return fixRepository.findAll();
    }
}
