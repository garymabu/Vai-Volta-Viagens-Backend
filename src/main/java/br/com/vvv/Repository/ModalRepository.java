package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvv.Domain.Entity.Modal;

public interface ModalRepository extends JpaRepository<Modal, String> {

}
