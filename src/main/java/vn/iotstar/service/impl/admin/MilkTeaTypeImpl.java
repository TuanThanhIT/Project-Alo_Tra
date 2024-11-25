package vn.iotstar.service.impl.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.repository.admin.MilkTeaTypeRepository;
import vn.iotstar.service.admin.IMilkTeaType;

@Service
public class MilkTeaTypeImpl implements IMilkTeaType {
	@Autowired
	MilkTeaTypeRepository milkTeaTypeRepository;

	public MilkTeaTypeImpl(MilkTeaTypeRepository milkTeaTypeRepository) {
		super();
		this.milkTeaTypeRepository = milkTeaTypeRepository;
	}

	@Override
	public <S extends MilkTeaType> S save(S entity) {
		return milkTeaTypeRepository.save(entity);
	}

	@Override
	public List<MilkTeaType> findAll(Sort sort) {
		return milkTeaTypeRepository.findAll(sort);
	}

	@Override
	public Page<MilkTeaType> findAll(Pageable pageable) {
		return milkTeaTypeRepository.findAll(pageable);
	}

	@Override
	public List<MilkTeaType> findAll() {
		return milkTeaTypeRepository.findAll();
	}

	@Override
	public Optional<MilkTeaType> findById(Integer id) {
		return milkTeaTypeRepository.findById(id);
	}

	@Override
	public long count() {
		return milkTeaTypeRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		milkTeaTypeRepository.deleteById(id);
	}

	@Override
	public void delete(MilkTeaType entity) {
		milkTeaTypeRepository.delete(entity);
	}

	
}
