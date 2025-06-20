package com.project.Backend.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InventoryId implements Serializable {
    private Long product;
    private Long store;

    @Override
    public boolean equals(Object obj) {
        // Verifica si es la misma instancia
        if (this == obj) {
            return true;
        }
        // Verifica si el objeto es nulo o no es de la misma clase
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Realiza un casteo seguro
        InventoryId that = (InventoryId) obj;

        // Compara los atributos relevantes
        return Objects.equals(product, that.getProduct()) &&
                Objects.equals(store, that.getStore());
    }
    @Override
    public int hashCode() {
        return Objects.hash(product, store);
    }
}
