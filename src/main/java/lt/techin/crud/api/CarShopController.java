package lt.techin.crud.api;

import lombok.extern.slf4j.Slf4j;
import lt.techin.crud.api.dto.carShop.CarShopDto;
import lt.techin.crud.api.dto.carShop.CarShopEntityDto;
import lt.techin.crud.api.dto.technic.TechnicEntityDto;
import lt.techin.crud.dao.CarShopRepository;
import lt.techin.crud.dao.TechnicRepository;
import lt.techin.crud.model.CarShop;
import lt.techin.crud.model.Technic;
import lt.techin.crud.service.CarShopService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static lt.techin.crud.api.dto.mapper.CarShopMapper.toCarShop;
import static lt.techin.crud.api.dto.mapper.CarShopMapper.toCarShopEntityDto;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/carshop")
@Validated
@Slf4j
public class CarShopController {
    private final CarShopRepository carShopRepository;
    private final TechnicRepository technicRepository;

    private final CarShopService service;

    public CarShopController(CarShopService service,
                             TechnicRepository technicRepository,
                             CarShopRepository carShopRepository) {
        this.service = service;
        this.technicRepository = technicRepository;
        this.carShopRepository = carShopRepository;
    }

    @GetMapping
    @ResponseBody
    public List<CarShop> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{carShopId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CarShopEntityDto> getIndividual(@PathVariable Long carShopId) {
        var carShopOptional = service.getById(carShopId);

        return carShopOptional
                .map(carShop -> ok(toCarShopEntityDto(carShop)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{carShopId}/technics", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Technic> getAllTechnicsInShop(@PathVariable Long carShopId) {
        return service.getAllTechnicsInShopByCarShopId(carShopId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<CarShop> create(@Valid @RequestBody CarShopEntityDto carShopEntityDto) {
        return ok(service.create(toCarShop(carShopEntityDto)));
    }

    @PatchMapping("/{carShopId}")
    @ResponseBody
    public ResponseEntity<CarShop> update(@PathVariable Long carShopId, @Valid @RequestBody CarShopDto carShopDto) {
        log.info("Trying to update carShop by id: {}", carShopId);
        return ok(service.update(carShopId, toCarShop(carShopDto)));
    }

    @DeleteMapping("/delete/{carShopId}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long carShopId) {
        log.info("Trying to delete carShop by id: {}", carShopId);

        if (service.deleteById(carShopId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //For adding technic to a car shop
    @PostMapping("/{carShopId}/add/{technicId}")
    public ResponseEntity<CarShopEntityDto> addTechnicToCarShop(@PathVariable Long carShopId, @Valid @PathVariable Long technicId) {

        var updatedCarShop = service.addTechnicToCarShop(carShopId, technicId);

        return ok(toCarShopEntityDto(updatedCarShop));
    }

}
