package co.inventorsoft.warehouse.domain.controller;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.service.ShoeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shoes")
public class ShoeController {

    private ShoeService shoeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoeDto> getAllShoes() {
        return shoeService.getAllShoe();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoeDto getShoeById(@PathVariable("id") Long id) {
        return shoeService.getShoeById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ShoeDto> search(@RequestParam(name = "manufacturer", required = false) String manufacturer,
                                @RequestParam(name = "type", required = false) String type) {

        return shoeService.search(manufacturer, type);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoeDto createNewShoe(@RequestBody @Valid ShoeDto shoeDto) {
        return shoeService.save(shoeDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoeDto updateShoe(@PathVariable("id") Long id, @RequestBody @Valid ShoeDto shoeDto) {
        return shoeService.save(id, shoeDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoeById(@PathVariable("id") Long id) {
        shoeService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllShoes() {
        shoeService.delete();
    }

}
