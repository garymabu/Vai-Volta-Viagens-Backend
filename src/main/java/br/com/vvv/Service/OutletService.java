package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataOutlet;
import br.com.vvv.Domain.DTO.DataRegisterOutlet;
import br.com.vvv.Domain.DTO.DataUpdateOutlet;
import br.com.vvv.Domain.Entity.Outlet;
import br.com.vvv.Repository.OutletRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OutletService {

    @Autowired
    private OutletRepository outletRepository;

    public Outlet createOutlet(DataRegisterOutlet dataRegisterOutlet) {
        log.info("[OutletService.createOutlet] - [Service]");
        return outletRepository.save(new Outlet(dataRegisterOutlet));
    }

    public Outlet updateOutlet(DataUpdateOutlet dataUpdateOutlet) {
        log.info("[OutletService.updateOutlet] - [Service]");
        Outlet outlet = outletRepository.findById(dataUpdateOutlet.id())
            .orElseThrow(() -> new IllegalArgumentException("Ponto de Venda não encontrado"));

        outlet.updateOutlet(dataUpdateOutlet);

        return outletRepository.save(outlet);
    }

    public Page<DataOutlet> findAllOutlets(Pageable pageable) {
        log.info("[OutletService.findAllOutlets] - [Service]");
        Page<Outlet> outlet = outletRepository.findAll(pageable);
        return outlet.map(DataOutlet::new);
    }

    public void deleteOutlet(String id) throws IllegalArgumentException {
        log.info("[OutletService.deleteOutlet] - [Service]");
        Outlet outlet = outletRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Outlet não encontrado"));

        try {
            outletRepository.deleteById(outlet.getId());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar excluir o Ponto de Venda", ex);
        }
    }

}
