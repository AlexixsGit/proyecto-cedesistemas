package co.edu.cedesistemas.ecommerce.controller;

import co.edu.cedesistemas.ecommerce.model.Store;
import co.edu.cedesistemas.ecommerce.service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/stores")
    public ResponseEntity<Store> createStore(@RequestBody String storeString) throws JsonProcessingException {
        Store store = objectMapper.readValue(storeString, Store.class);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }
}
