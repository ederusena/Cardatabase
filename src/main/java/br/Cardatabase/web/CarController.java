package br.Cardatabase.web;

import br.Cardatabase.domain.Car;
import br.Cardatabase.repository.CarRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarRepository _repository;
    
    public CarController(CarRepository repository) {
        this._repository = repository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return _repository.findAll();
    }
}
