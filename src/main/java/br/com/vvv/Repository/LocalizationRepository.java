package br.com.vvv.Repository;

import br.com.vvv.Domain.Entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationRepository extends JpaRepository<Localization, String> {
}

