/*package pe.edu.upc.compraencasa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.compraencasa.model.entity.Inventario;
import pe.edu.upc.compraencasa.model.repository.InventarioRepository;
import pe.edu.upc.compraencasa.service.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Override
	@Transactional
	public Inventario create(Inventario entity) throws Exception {
		return inventarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inventario> readAll() throws Exception {
		// TODO Auto-generated method stub
		return inventarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Inventario> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return inventarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Inventario update(Inventario entity) throws Exception {
		// TODO Auto-generated method stub
		return inventarioRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		inventarioRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		inventarioRepository.deleteAll();
		
	}

}*/
