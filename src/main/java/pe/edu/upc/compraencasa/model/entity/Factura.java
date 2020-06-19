package pe.edu.upc.compraencasa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "facturas")
@Getter
@Setter
public class Factura {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "fecha_facturacion",nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date fechaFacturacion;
	
	@NotBlank(message ="No puede estar vacio")
	@Column(name = "valor_total",nullable = false)
	private double valorTotal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cliente_id", updatable = false, nullable = false)
	private Cliente cliente;
	
	@OneToOne(mappedBy="factura",fetch = FetchType.LAZY)
	private Compra compra;

}
