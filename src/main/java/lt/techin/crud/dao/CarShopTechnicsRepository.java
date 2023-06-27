package lt.techin.crud.dao;

import lt.techin.crud.model.CarShopTechnics;
import lt.techin.crud.model.Technic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Set;

public interface CarShopTechnicsRepository extends JpaRepository<CarShopTechnics, Long> {
    Set<Technic> findByCarShop_Id(Long id);

    @Transactional
    @Modifying
    long deleteByTechnic_Id(long technic);

}
