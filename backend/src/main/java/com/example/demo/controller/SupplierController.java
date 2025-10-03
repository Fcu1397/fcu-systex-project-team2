package com.example.demo.controller;

import com.example.demo.exception.SupplierNotFoundException;
import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "供應商管理", description = "供應商的CRUD API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api") // 定義前綴路徑
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    // 查詢所有供應商
    @Operation(summary = "查詢所有供應商", description = "查詢所有供應商")
    @GetMapping(value = "/suppliers")
    public List<Supplier> findAllSuppliers() {
        return supplierService.findAllSuppliers();
    }

    // 用ID查詢供應商
    @Operation(summary = "用ID查詢供應商", description = "用ID查詢供應商")
    @GetMapping(value = "/supplier/{id}")
    public Supplier findSupplierById(@PathVariable int id) throws SupplierNotFoundException {
        return supplierService.findSupplierById(id);
    }

    // 新增供應商
    @Operation(summary = "新增供應商", description = "新增一個供應商")
    @PostMapping(value = "/supplier")
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    // 更新供應商
    @Operation(summary = "更新供應商", description = "更新一個供應商")
    @PutMapping(value = "/supplier/{id}")
    public Supplier updateSupplier(@PathVariable int id, @RequestBody Supplier updateSupplier) throws SupplierNotFoundException {
        return supplierService.updateSupplier(id, updateSupplier);
    }

    // 刪除供應商
    @Operation(summary = "刪除供應商", description = "刪除一個供應商")
    @DeleteMapping(value = "/supplier/{id}")
    public void deleteSupplier(@PathVariable int id) throws SupplierNotFoundException {
        supplierService.deleteSupplier(id);
    }
}
