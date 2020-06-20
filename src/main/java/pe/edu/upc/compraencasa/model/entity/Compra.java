package pe.edu.upc.compraencasa.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "compras")
@Getter
@Setter
public class Compra {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "fecha_compra",nullable = false, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date fechaCompra;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="factura_id", updatable = false, nullable = false)
	private Factura factura;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="detalleCarrito_id", updatable = false, nullable = false)
	private DetalleCarrito detalleCarrito;
	
	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY)
	private List<Producto> productos;
	
	public Compra() {
		productos = new ArrayList<>();
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}
	
	
}
