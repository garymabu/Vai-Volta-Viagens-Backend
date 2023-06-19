package br.com.vvv.Service;

import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

    public Optional<Localization> getLocalization(String localizationId) {
        return localizationRepository.findById(localizationId);
    }
}
