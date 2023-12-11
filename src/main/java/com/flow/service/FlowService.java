package com.flow.service;

import com.flow.entity.Custom;
import com.flow.entity.Fix;
import com.flow.entity.FixDto;
import com.flow.repository.CustomRespository;
import com.flow.repository.FixRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FlowService {
    private final CustomRespository customRespository;
    private final FixRepository fixRepository;

    @Autowired
    public FlowService(CustomRespository customRespository, FixRepository fixRepository) {
        this.customRespository = customRespository;
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

    public Custom addCustomBadge(String customName) {
        Optional<Fix> existingFix = fixRepository.findByFixName(customName);
        if(existingFix.isPresent() && existingFix.get().isFixStatus()) {
            throw new IllegalStateException("해당 확장자는 사용할 수 없습니다.");
        }
        Optional<Custom> existingCustom = customRespository.findByCustomName(customName);
        if(existingCustom.isPresent()) {
            throw new IllegalStateException("해당 확장자는 이미 생성되었습니다.");
        }

        Custom custom = new Custom();
        custom.setCustomName(customName);
        if(existingFix.isPresent()) {
            custom.setFix(existingFix.get());
        } else {
            custom.setFix(null);
        }
        return customRespository.save(custom);
    }
    public void deleteCustomBadge(String customName) {
        Custom custom = customRespository.findByCustomName(customName)
                .orElseThrow(() -> new EntityNotFoundException("없다"));
        customRespository.delete(custom);
    }
    public List<Custom> getAllCustoms() {
        return customRespository.findAll();
    }
    public long countCustoms() {
        return customRespository.count();
    }
    public Fix addFix(FixDto fixDto) {
        Fix fix = new Fix();
        fix.setFixName(fixDto.getFixName());
        fix.setFixStatus(fixDto.isFixStatus());
        return fixRepository.save(fix);
    }
    public void deleteFix(String fixName) {
        Fix fix = fixRepository.findByFixName(fixName)
                .orElseThrow(() -> new EntityNotFoundException("없다"));
        fixRepository.delete(fix);
    }
    public List<Fix> getAllFixOver8() {
        return fixRepository.findByIdGreaterThanEqual(8L);
    }
}
