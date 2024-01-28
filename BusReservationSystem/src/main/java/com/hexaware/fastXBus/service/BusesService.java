package com.hexaware.fastXBus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.BusesDTO;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.repository.IBusesRepository;


@Service
public class BusesService implements IBusesService {
	@Autowired
	IBusesRepository repository;

	@Autowired
	RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BusesService.class);
	@Override
	public Buses createBuses(BusesDTO busesdto) {
		Buses buses=new Buses();
		buses.setBusNumber(busesdto.getBusNumber());
		buses.setCapacity(busesdto.getCapacity());
		buses.setSourceCity(busesdto.getSourceCity());
		buses.setDestinationCity(busesdto.getDestinationCity());
		buses.setDepartureTime(busesdto.getDepartureTime());
		buses.setDistanceInKms(busesdto.getDistanceInKms());
		buses.setDurationInHours(busesdto.getDurationInHours());
		buses.setFare(busesdto.getFare());
		buses.setDate(busesdto.getDate());
		buses.setTotalSeats(busesdto.getTotalSeats());
		
		
		return repository.save(buses);
	}

	@Override
	public Buses updateBuses(BusesDTO busesdto, Long busId) {
	    // Find the existing bus by ID
	    Optional<Buses> busOptional = repository.findById(busId);
	    
	    if (busOptional.isPresent()) {
	        // Get the existing bus
	        Buses existingBus = busOptional.get();
	        
	        existingBus.setBusNumber(busesdto.getBusNumber());
	        existingBus.setCapacity(busesdto.getCapacity());
	        existingBus.setSourceCity(busesdto.getSourceCity());
	        existingBus.setDestinationCity(busesdto.getDestinationCity());
	        existingBus.setDepartureTime(busesdto.getDepartureTime());
	        existingBus.setDistanceInKms(busesdto.getDistanceInKms());
	        existingBus.setDurationInHours(busesdto.getDurationInHours());
	        existingBus.setFare(busesdto.getFare());
	        existingBus.setDate(busesdto.getDate());
	        existingBus.setTotalSeats(busesdto.getTotalSeats());
	      
	     
	        
	        // Save the updated bus
	        return repository.save(existingBus);
	    } else {
	    	logger.error("Buses not found");
	        return null;
	    }
	}


	@Override
	public void deleteBuses(Long busId) {
		
		repository.deleteById(busId);
	}

	@Override
	public BusesDTO getBusById(Long busId) {
		Buses buses=repository.findById(busId).orElse(new Buses());
		return new BusesDTO(buses.getBusId(),buses.getBusNumber(),buses.getCapacity(),buses.getSourceCity(),buses.getDestinationCity(),buses.getDistanceInKms(),buses.getDurationInHours(),buses.getDepartureTime(),buses.getFare(),buses.getDate(),buses.getTotalSeats());
	}

	@Override
	public List<Buses> getAllBuses() {
		return repository.findAll(Sort.by("busId"));
	}

	@Override
	 public List<Buses> getAllBusesByCitiesAndDate(String sourceCity, String destinationCity, LocalDate date) {
        return repository.findAllBySourceCityAndDestinationCityAndDate(sourceCity, destinationCity, date);
    }

	@Override
	public Buses findBusDetailsByBookingId(Long bookingId) {
		// TODO Auto-generated method stub
		return repository.findBusDetailsByBookingId(bookingId);
	}


	

	

}
