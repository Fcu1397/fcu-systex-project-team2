package com.example.demo.service;

import com.example.demo.exception.SupplierNotFoundException;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    // 查詢所有供應商
    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    // 根據ID查詢供應商
    public Supplier findSupplierById(int supplierId) throws SupplierNotFoundException {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isPresent()) {
            return optionalSupplier.get(); // 回傳查詢到的供應商
        } else {
            throw new SupplierNotFoundException("找不到ID: " + supplierId + "的供應商");
        }
    }

    // 新增供應商
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // 更新供應商
    public Supplier updateSupplier(int supplierId, Supplier supplier) throws SupplierNotFoundException {
        // 先查找一下有沒有這個ID的供應商
        Supplier existingSupplier = findSupplierById(supplierId);

        // 更新供應商資訊
        existingSupplier.setSupplierName(supplier.getSupplierName());
        existingSupplier.setContactName(supplier.getContactName());
        existingSupplier.setMobile(supplier.getMobile());
        existingSupplier.setEmail(supplier.getEmail());

        // 保存更新後的供應商
        return supplierRepository.save(existingSupplier);
    }

    // 刪除供應商
    public void deleteSupplier(int supplierId) throws SupplierNotFoundException {
        // 先查找一下有沒有這個ID的供應商
        Supplier existingSupplier = findSupplierById(supplierId);

        // 刪除供應商
        supplierRepository.deleteById(supplierId);
    }
}