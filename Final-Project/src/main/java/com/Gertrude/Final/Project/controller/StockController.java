package com.Gertrude.Final.Project.controller;

import com.Gertrude.Final.Project.model.Stock;
import com.Gertrude.Final.Project.model.Cylinder;
import com.Gertrude.Final.Project.services.StockService;
import com.Gertrude.Final.Project.services.CylinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class StockController {

    private final StockService stockService;
    private  final CylinderService cylinderService;

    @Autowired
    public StockController(StockService stockService, CylinderService cylinderService) {
        this.stockService = stockService;
        this.cylinderService = cylinderService;
    }

    // home page with list of Stocks
    @GetMapping("/stocks")
    public String home(Model model) {
        List<Stock> allStocks = stockService.getAllStocks();
        model.addAttribute("allStocks", allStocks);
        return "/stock/stock-list";
    }

    // create page: where we save new Stock
    @GetMapping("/stocks/new")
    public String newStockForm(Model model) {
        Stock stock = new Stock();
        List<Cylinder> cylinder = cylinderService.getAllCylinders();// get  all Cylinder combo box
        model.addAttribute("stock", stock);
        model.addAttribute("cylinderObj", cylinder);
        return "/stock/new-stock";
    }
    // create Stock
    @PostMapping("/stocks/new")
    public String newStock(@ModelAttribute("stock") Stock stock) {
        stockService.saveStock(stock); // save Stock
        return "redirect:/stocks";
    }

    // update page: where we update Stock info
    @GetMapping("/{id}/edit_stock")
    public String updateStockForm(@PathVariable String id, Model model) {
        UUID StockId = UUID.fromString(id);
        Optional<Stock> stock = stockService.findByID(StockId);
        List<Cylinder> cylinder = cylinderService.getAllCylinders();// get  all Cylinder combo box
        model.addAttribute("stockObj", stock);
        model.addAttribute("cylinder", cylinder);
        return "/stock/edit-stock";
    }
    // update Stock
    @PostMapping("/{id}/edit_stock")
    public String editStock(@PathVariable String id, @ModelAttribute("stockObj") Stock stock) {
        UUID StockId = UUID.fromString(id);
        stock.setId(StockId);
        stockService.updateStock(stock);
        return "redirect:/stocks";
    }
    // delete Stock
    @GetMapping ("/{id}/delete_stock")
    public String removeStock(@PathVariable UUID id) {
        stockService.deleteById(id);
        return "redirect:/stocks";
    }
}
