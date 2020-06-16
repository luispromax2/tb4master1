package pe.edu.upc.compraencasa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "nombres",length = 50,nullable = false)
	private String nombres;
	
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "codigo_Producto",length = 50,nullable = false)
	private String codigoProducto;
	
	@Min(value = 1, message = "El valor ingresado no es valido")
	@Column(name = "cantidad",nullable = false)
	private double cantidad;
	
	@Min(value = 1, message = "El valor ingresado no es valido")
	@Column(name = "costo",nullable = false)
	private double costo;
	
    @ManyToOne()
	@JoinColumn(name="inventario_id")
	private Inventario inventario;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;
	
	//
	@Column(name = "foto", nullable=false)
	private String Foto;

}
