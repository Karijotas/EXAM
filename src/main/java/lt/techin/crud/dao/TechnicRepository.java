package lt.techin.crud.dao;

import lt.techin.crud.model.Technic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicRepository extends JpaRepository<Technic, Long> {

}
