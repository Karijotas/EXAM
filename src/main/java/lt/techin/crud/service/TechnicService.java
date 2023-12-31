package lt.techin.crud.service;

import lt.techin.crud.config.exception.CustomValidationException;
import lt.techin.crud.dao.CarShopRepository;
import lt.techin.crud.dao.CarShopTechnicsRepository;
import lt.techin.crud.dao.TechnicRepository;
import lt.techin.crud.model.CarShop;
import lt.techin.crud.model.CarShopTechnics;
import lt.techin.crud.model.Technic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TechnicService {
    private final TechnicRepository repository;

    private final Validator validator;
    private final CarShopTechnicsRepository carShopTechnicsRepository;

    @Autowired
    public TechnicService(TechnicRepository repository, Validator validator,
                          CarShopTechnicsRepository carShopTechnicsRepository) {
        this.repository = repository;
        this.validator = validator;
        this.carShopTechnicsRepository = carShopTechnicsRepository;
    }

    void validateInputWithInjectedValidator(Technic technic) {
        Set<ConstraintViolation<Technic>> violations = validator.validate(technic);
        if (!violations.isEmpty()) {
            throw new CustomValidationException(violations.toString(), "Technic", "Error in technic entity", technic.toString());
        }
    }

    public List<Technic> getAll() {
        return repository.findAll();
    }

    public Optional<Technic> getById(Long id) {
        return repository.findById(id);
    }

    public Technic create(Technic technic) {
        var newTechnic = new Technic();

        newTechnic.setId(technic.getId());
        newTechnic.setName(technic.getName());
        newTechnic.setSurname(technic.getSurname());
        newTechnic.setSpeciality(technic.getSpeciality());
        newTechnic.setCity(technic.getCity());
        newTechnic.setReview(technic.getReview());

        return repository.save(newTechnic);
    }



    public Technic update(Long id, Technic technic) {
        Technic existing = repository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Technic doesn't exist", "id", "Technic not found", id.toString()));

        existing.setName(technic.getName());
        existing.setSurname(technic.getSurname());
        existing.setSpeciality(technic.getSpeciality());
        existing.setCity(technic.getCity());
        existing.setReview(technic.getReview());

        return repository.save(existing);
    }

    public Boolean deleteById(Long id) {

        try {
            carShopTechnicsRepository.deleteByTechnic_Id(id);
            repository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Technic addAReview(Long id, Double score){
        Technic existing = repository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Technic doesn't exist", "id", "Technic not found", id.toString()));

        Double newScore = existing.getReview() + score;
        existing.setReview(newScore);

        return repository.save(existing);
    }
}