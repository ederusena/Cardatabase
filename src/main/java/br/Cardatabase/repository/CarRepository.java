package br.Cardatabase.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.Cardatabase.domain.Car;

// @RepositoryRestResource(path="vehicles")
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByBrand(@Param("brand") String brand);

    // Fetch cars by color
    List<Car> findByColor(@Param("color") String color);
    // Fetch cars by brand
    // List<Car> findByBrand(String brand);

    // Fetch cars by color
    // List<Car> findByColor(String color);

    // Fetch cars by model year
    // List<Car> findByModelYear(int modelYear);

    // Fetch cars by brand and model
    // List<Car> findByBrandAndModel(String brand, String model);

    // Fetch cars by brand or color
    // List<Car> findByBrandOrColor(String brand, String color);

    // Fetch cars by brand and sort by year
    // List<Car> findByBrandOrderByModelYearAsc(String brand);

    // Fetch cars by brand using SQL
    // @Query("select c from Car c where c.brand = ?1")
    // List<Car> findByBrand(String brand);

    // Fetch cars by brand using SQL
    // @Query("select c from Car c where c.brand like %?1")
    // List<Car> findByBrandEndsWith(String brand);
}
