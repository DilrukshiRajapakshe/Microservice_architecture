package com.adams.place_order_management_system.controller.Impl;

import com.adams.place_order_management_system.controller.PlaceOrderController;
import com.adams.place_order_management_system.module.PlaceOrder;
import com.adams.place_order_management_system.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin()
@RestController
@RequestMapping("/adams/api/v1")
public class PlaceOrderControllerImpl implements PlaceOrderController {
    @Autowired
    private final PlaceOrderService placeOrderService;

    public PlaceOrderControllerImpl(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }

    @Override
    @GetMapping("/find/all")
    public ResponseEntity<List<PlaceOrder>> FindAllPlaceOrder() {
        try {
            if (placeOrderService.FindAllPlaceOrder().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(placeOrderService.FindAllPlaceOrder(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Object> FindPlaceOrderByID(@PathVariable("id") String id) {
        try {
            if (placeOrderService.FindPlaceOrderByID(id) == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(placeOrderService.FindPlaceOrderByID(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/status/{status}")
    @Override
    public ResponseEntity<List<PlaceOrder>> FindNonApprovedPlaceOrder(@PathVariable("status") String status) {
        try {
            if (placeOrderService.FindNonApprovedPlaceOrder(status).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(placeOrderService.FindNonApprovedPlaceOrder(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/{sd}/between/{ed}")
    public ResponseEntity<List<PlaceOrder>> FindPlaceOrderHistoryByDate(@PathVariable("sd") Date sd, @PathVariable("ed") Date ed) {
        try {
            if (placeOrderService.FindPlaceOrderHistoryByDate(sd, ed).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(placeOrderService.FindPlaceOrderHistoryByDate(sd, ed), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/email/{email}")
    public ResponseEntity<List<PlaceOrder>> FindPlaceOrderHistoryByEmail(@PathVariable("email")  String email) {
        try {
            if (placeOrderService.FindPlaceOrderHistoryByEmail(email).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(placeOrderService.FindPlaceOrderHistoryByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value ="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> CreatPlaceOrder(@RequestBody PlaceOrder placeOrder) {
        try {
            placeOrderService.CreatPlaceOrder(placeOrder);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> UpdatePlaceOrder(@PathVariable("id") String id, PlaceOrder placeOrder) {
        try {
            placeOrderService.UpdatePlaceOrder(id, placeOrder);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> DeletePlaceOrder(@PathVariable("id") String id) {
        try {
            placeOrderService.DeletePlaceOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping(value = "/update/status/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> UpdatePlaceOrderStatusOny(@PathVariable("id") String id, PlaceOrder placeOrder) {
        try {
            placeOrderService.UpdatePlaceOrderStatusOny(id, placeOrder);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/correct")
    public String check(){
        return "placeOrderService";
    }
}
