package br.Cardatabase.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long ownerid;

    private String firstName;
    private String lastName;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL,mappedBy = "owner")
    private List<Car> cars;
        
    public Owner(String firstName, String lastName) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public Owner() {
    }

    public void setCars(List<Car> cars) {
      this.cars = cars;
    }

    public List<Car> getCars() {
      return cars;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }
    public String getLastName() {
      return lastName;
    }
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
}
