package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Client;

public record DataProfileClient(
        String name,
        Integer age,
        String login,
        String cpf,
        String profession,
        String tell
) {

    public DataProfileClient(Client client) {
        this(client.getTell(), client.getAge(), client.getName(), client.getLogin(), client.getCpf(), client.getProfession());
    }

}