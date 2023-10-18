package co.inventorsoft.warehouse.controllers;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import co.inventorsoft.warehouse.mappers.Mapper;
import co.inventorsoft.warehouse.services.ShoeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ShoeController {

    private ShoeService shoeService;
    private Mapper<ShoeEntity, ShoeDto> shoeMapper;

    @GetMapping("/shoe")
    public ResponseEntity<List<ShoeDto>> getAllShoe() {
        return new ResponseEntity<>(shoeService.getAllShoe().stream().map(shoeMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/shoe/{id}")
    public ResponseEntity<ShoeDto> getShoeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(shoeMapper.mapTo(shoeService.getShoeById(id)), HttpStatus.OK);
    }

    @GetMapping("/shoe/search")
    public ResponseEntity<List<ShoeDto>> search(@RequestParam(name = "manufacturer", required = false) String manufacturer,
                                                @RequestParam(name = "type", required = false) String type) {

        return new ResponseEntity<>(shoeService.search(manufacturer, type)
                .stream()
                .map(shoeMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.FOUND);
    }

    @PostMapping("/shoe")
    public ResponseEntity<ShoeDto> createNewShoe(@RequestBody @Valid ShoeDto shoe) {
        return new ResponseEntity<>(shoeMapper.mapTo(shoeService.save(shoeMapper.mapFrom(shoe))), HttpStatus.CREATED);
    }

    @PutMapping("/shoe/{id}")
    public ResponseEntity<ShoeDto> updateShoe(@PathVariable("id") Long id, @RequestBody @Valid ShoeDto shoe) {
        ShoeEntity shoeEntity = shoeMapper.mapFrom(shoe);

        if (!shoeService.isExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shoeMapper.mapTo(shoeService.save(shoeEntity)), HttpStatus.OK);
    }

    @DeleteMapping("/shoe/{id}")
    public ResponseEntity<ShoeDto> deleteById(@PathVariable("id") Long id) {

        if (!shoeService.isExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ShoeDto shoe = getShoeById(id).getBody();

        shoe.setManufacturer(null);
        shoe.setSize(null);

        shoeService.delete(shoeMapper.mapFrom(shoe));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/shoe")
    public ResponseEntity<ShoeDto> deleteAll() {
        shoeService.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
