package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataLocalizations;
import br.com.vvv.Domain.DTO.DataRegisterLocalization;
import br.com.vvv.Domain.DTO.DataUpdateLocalization;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Repository.LocalizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

    public Optional<Localization> getLocalization(String localizationId) {
        return localizationRepository.findById(localizationId);
    }

    public Localization createLocalization(DataRegisterLocalization dataRegisterLocalization) {
        log.info("[LocalizationService.createLocalization] - [Service]");
        return localizationRepository.save(new Localization(dataRegisterLocalization));
    }

    public Page<DataLocalizations> findAllLocalizations(Pageable pageable) {
        log.info("[LocalizationService.findAllLocalizations] - [Service]");
        Page<Localization> localizations = localizationRepository.findAll(pageable);
        return localizations.map(DataLocalizations::new);
    }

    public Localization updateLocalization(String id, DataUpdateLocalization dataUpdateLocalization) {
        log.info("[LocalizationService.updateLocalization] - [Service]");
        Localization localization = localizationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + id));

        localization.updateData(dataUpdateLocalization);

        return localizationRepository.save(localization);
    }

    public void deleteLocalization(String id) {
        log.info("[LocalizationService.deleteLocalization] - [Service]");
        Localization localization = localizationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Localization not found with id: " + id));

        localizationRepository.deleteById(localization.getId());
    }
}
