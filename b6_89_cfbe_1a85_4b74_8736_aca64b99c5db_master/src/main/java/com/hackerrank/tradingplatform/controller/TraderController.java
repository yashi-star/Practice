package com.hackerrank.tradingplatform.controller;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.TraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/trading/traders")
public class TraderController {
    @Autowired
    private TraderService traderService;

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Trader> registerTrader(@RequestBody @Valid Trader trader) {
    	Trader trade=traderService.getTraderByEmail(trader.getEmail());
    	if(trade.getEmail()!=null)
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       return  new ResponseEntity<Trader>(traderService.registerTrader(trader),HttpStatus.CREATED);
    }

    //get by email
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TraderDTO> getTraderByEmail(@RequestParam("email") String email) {
    	Trader trade=traderService.getTraderByEmail(email);
    	if(trade.getEmail()==null)
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	TraderDTO t=new TraderDTO(traderService.getTraderByEmail(email));
    	return new ResponseEntity<TraderDTO>(t,HttpStatus.OK);
    }

    //get all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TraderDTO> getAllTraders() {
        return traderService.getAllTraders()
                .stream()
                .map(TraderDTO::new)
                .collect(toList());
    }

    //update by email
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTrader(@RequestBody @Valid UpdateTraderDTO trader) {
        traderService.updateTrader(trader);
    }

    //add money
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(@RequestBody @Valid AddMoneyTraderDTO trader) {
        traderService.addMoney(trader);
    }
}
