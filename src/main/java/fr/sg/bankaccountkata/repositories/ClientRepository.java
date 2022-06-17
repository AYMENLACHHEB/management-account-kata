package fr.sg.bankaccountkata.repositories;

import fr.sg.bankaccountkata.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(int id);
    List<Client> findByLastName(String name);
}
