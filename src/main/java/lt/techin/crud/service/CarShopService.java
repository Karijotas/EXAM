package lt.techin.crud.service;

import lombok.extern.slf4j.Slf4j;
import lt.techin.crud.config.exception.CustomValidationException;
import lt.techin.crud.dao.CarShopRepository;
import lt.techin.crud.dao.CarShopTechnicsRepository;
import lt.techin.crud.dao.TechnicRepository;
import lt.techin.crud.model.CarShop;
import lt.techin.crud.model.Technic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static lt.techin.crud.api.dto.mapper.CarShopMapper.toCarShopEntityDto;
import static lt.techin.crud.service.FinderClass.findCarShop;

@Service
@Slf4j
public class CarShopService {
    private final CarShopTechnicsRepository carShopTechnicsRepository;

    private final CarShopRepository carShopRepository;
   private final TechnicRepository technicRepository;

    private final Validator validator;

    @Autowired
    public CarShopService(CarShopRepository carShopRepository, TechnicRepository technicRepository, Validator validator,
                          CarShopTechnicsRepository carShopTechnicsRepository) {
        this.carShopRepository = carShopRepository;
        this.technicRepository = technicRepository;

        this.validator = validator;
        this.carShopTechnicsRepository = carShopTechnicsRepository;
    }

    void validateInputWithInjectedValidator(CarShop carShop) {
        Set<ConstraintViolation<CarShop>> violations = validator.validate(carShop);
        if (!violations.isEmpty()) {
            throw new CustomValidationException(violations.toString(), "CarShop", "Error in CarShop entity", carShop.toString());
        }
    }

    public List<CarShop> getAll() {
        return carShopRepository.findAll();
    }

    public Optional<CarShop> getById(Long id) {
        return carShopRepository.findById(id);
    }

    public Set<Technic> getTechnicsByShopId(Long id) {
        return carShopRepository.findById(id).get().getTechnics();
    }

    public CarShop create(CarShop carShop) {
        var newShop = new CarShop();

        newShop.setId(carShop.getId());
        newShop.setName(carShop.getName());
        newShop.setAdress(carShop.getAdress());
       newShop.setOwner(carShop.getOwner());

        return carShopRepository.save(newShop);
    }

    public CarShop update(Long id, CarShop carShop) {
        validateInputWithInjectedValidator(carShop);
        CarShop existing = findCarShop(id);

        existing.setId(carShop.getId());
        existing.setName(carShop.getName());
        existing.setAdress(carShop.getAdress());
        existing.setOwner(carShop.getOwner());

        return carShopRepository.save(existing);
    }

    public Boolean deleteById(Long id) {
        try {
            carShopRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Set<Technic> getAllTechnicsInShopByCarShopId(Long carShopId) {

        return carShopTechnicsRepository.findByCarShop_Id(carShopId);
    }
}
