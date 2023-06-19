package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Outlet;

public record DataOutlet(
    String id,
    String name,
    String location
) {

    public DataOutlet(Outlet outlet) {
        this(outlet.getId(), outlet.getName(), outlet.getLocation());
    }

}
