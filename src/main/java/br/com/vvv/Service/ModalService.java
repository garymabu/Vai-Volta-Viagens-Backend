package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataModal;
import br.com.vvv.Domain.DTO.DataRegisterModal;
import br.com.vvv.Domain.DTO.DataUpdateModal;
import br.com.vvv.Domain.Entity.Modal;
import br.com.vvv.Repository.ModalRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModalService {

    @Autowired
    private ModalRepository modalRepository;

    public Modal createModal(DataRegisterModal modal) {
        log.info("[ModalService.createModal] - [Service]");
        return modalRepository.save(new Modal(modal));
    }

    public Modal updateModal(DataUpdateModal dataUpdateModal) {
        log.info("[ModalService.updateModal] - [Service]");
        Modal modal = modalRepository.findById(dataUpdateModal.id())
            .orElseThrow(() -> new IllegalArgumentException("Modal não encontrado"));

        modal.updateData(dataUpdateModal);

        return modalRepository.save(modal);
    }

    public Page<DataModal> findAllModals(Pageable pageable) {
        Page<Modal> modalPage = modalRepository.findAll(pageable);
        return modalPage.map(DataModal::new);
    }

    public void deleteModal(String id) {
        log.info("[ModalService.deleteModal] - [Service]");
        Modal modal = modalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Modal não encontrado"));

        modalRepository.deleteById(modal.getId());
    }

}
