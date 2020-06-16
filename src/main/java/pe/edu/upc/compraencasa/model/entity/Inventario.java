package pe.edu.upc.compraencasa.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Inventarios")
@Getter
@Setter
public class Inventario {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "cantidad",nullable = false)
	private double cantidad;
	
	@OneToMany(mappedBy = "inventario", fetch = FetchType.LAZY)
	private List<Producto> productos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="compra_id", updatable = false, nullable = false)
	private Compra compra;
	
	public Inventario(){
		productos = new ArrayList<>();
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}
	

}
