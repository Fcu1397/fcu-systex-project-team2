package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID", nullable = false)
    private Integer id;

    @Column(name = "SupplierName", nullable = false, length = 50)
    private String supplierName;

    @Column(name = "ContactName", nullable = false, length = 50)
    private String contactName;

    @Column(name = "Mobile", nullable = false, length = 20)
    private String mobile;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    /*以上swagger測試OK! */
    // 還缺 One-to-Many relationship with Product
}
