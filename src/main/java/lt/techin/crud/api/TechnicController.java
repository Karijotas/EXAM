package lt.techin.crud.api;

import lombok.extern.slf4j.Slf4j;
import lt.techin.crud.api.dto.technic.TechnicDto;
import lt.techin.crud.api.dto.technic.TechnicEntityDto;
import lt.techin.crud.model.Technic;
import lt.techin.crud.service.TechnicService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static lt.techin.crud.api.dto.mapper.TechnicMapper.toTechnic;
import static lt.techin.crud.api.dto.mapper.TechnicMapper.toTechnicEntityDto;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/technic")
@Validated
@Slf4j
public class TechnicController {

    private final TechnicService service;

    public TechnicController(TechnicService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Technic> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{technicId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TechnicEntityDto> getIndividual(@PathVariable Long technicId) {
        var optional = service.getById(technicId);

        return optional
                .map(technic -> ok(toTechnicEntityDto(technic)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Technic> create(@Valid @RequestBody TechnicEntityDto technicEntityDto) {
        return ok(service.create(toTechnic(technicEntityDto)));
    }

    @PatchMapping("/{technicId}")
    public ResponseEntity<Technic> update(@PathVariable Long technicId, @Valid @RequestBody TechnicDto technicDto) {
        log.info("Trying to update technic by id: {}", technicId);
        return ok(service.update(technicId, toTechnic(technicDto)));
    }

    @DeleteMapping("/delete/{technicId}")
    public ResponseEntity<Void> delete(@PathVariable Long technicId) {
        log.info("Trying to delete technic by id: {}", technicId);

        if (service.deleteById(technicId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
