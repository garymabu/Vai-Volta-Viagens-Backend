package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvv.Domain.Entity.Outlet;

public interface OutletRepository extends JpaRepository<Outlet, String> {

}
