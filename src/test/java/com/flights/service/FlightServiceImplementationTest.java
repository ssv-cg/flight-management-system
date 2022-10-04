package com.flights.service;

import com.flights.bean.Flight;
import com.flights.dao.FlightDao;
import com.flights.exception.InvalidDataEntry;
import com.flights.exception.RecordAlreadyExists;
import com.flights.exception.RecordNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FlightServiceImplementationTest {
  @Autowired
  FlightService flightservice;

  @MockBean
  FlightDao dao;
    @Test
    void addFlight() throws RecordAlreadyExists, InvalidDataEntry {
      Flight f=new Flight();
      f.setFlightNumber(BigInteger.valueOf(1));
      f.setFlightModel("ABC");
      f.setCarrierName("Juhi");
      f.setSeatCapacity(140);
      Mockito.when(dao.save(f)).thenReturn(f);

      assertThat(flightservice.addFlight(f)).isEqualTo(f);
    }

    @Test
    void modifyFlight() throws RecordNotFound, InvalidDataEntry {
      Flight f=new Flight();
      f.setFlightNumber(BigInteger.valueOf(1));
      f.setFlightModel("ABC");
      f.setCarrierName("Juhi");
      f.setSeatCapacity(140);
      Optional<Flight> f2=Optional.of(f);

      Mockito.when(dao.findById(BigInteger.valueOf(1))).thenReturn(f2);

      Mockito.when(dao.save(f)).thenReturn(f);
      f.setFlightModel("DEF");
      f.setCarrierName("Airlines");
      f.setSeatCapacity(250);
      assertThat(flightservice.modifyFlight(f)).isEqualTo(f);
    }

    @Test
    void viewFlight() throws RecordNotFound {
      Flight f=new Flight();
      f.setFlightNumber(BigInteger.valueOf(1));
      f.setFlightModel("ABC");
      f.setCarrierName("Juhi");
      f.setSeatCapacity(140);


      Optional<Flight> f1=Optional.of(f);

      Mockito.when(dao.findById(BigInteger.valueOf(1))).thenReturn(f1);

      assertThat(flightservice.viewFlight(BigInteger.valueOf(1))).isEqualTo(f);
    }

    @Test
    void testViewFlight() {
      Flight f1=new Flight();
      f1.setFlightNumber(BigInteger.valueOf(1));
      f1.setFlightModel("ABC");
      f1.setCarrierName("Juhi");
      f1.setSeatCapacity(140);

      Flight f2=new Flight();
      f2.setFlightNumber(BigInteger.valueOf(2));
      f2.setFlightModel("DEF");
      f2.setCarrierName("Vishakha");
      f2.setSeatCapacity(150);

      List<Flight> flightList = new ArrayList<>();
      flightList.add(f1);
      flightList.add(f2);

      Mockito.when(dao.findAll()).thenReturn(flightList);

      assertThat(flightservice.viewFlight()).isEqualTo(flightList);
    }

    @Test
    void deleteFlight() {
      Flight f=new Flight();
      f.setFlightNumber(BigInteger.valueOf(1));
      f.setFlightModel("ABC");
      f.setCarrierName("Juhi");
      f.setSeatCapacity(140);


      Optional<Flight> f1=Optional.of(f);

      Mockito.when(dao.findById(BigInteger.valueOf(1))).thenReturn(f1);
      Mockito.when(dao.existsById(f.getFlightNumber())).thenReturn(false);
      assertFalse(dao.existsById(f.getFlightNumber()));
    }

    //@Test
    //void validateFlight() {
    //}
}