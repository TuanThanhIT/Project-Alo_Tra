package vn.iotstar.service.impl.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.MilkTea;
import vn.iotstar.repository.admin.MilkTeaRepository;
import vn.iotstar.service.admin.IMilkTea;

@Service
public class MilkTeaImpl implements IMilkTea {
	@Autowired
	MilkTeaRepository milkTeaRepository;

	public MilkTeaImpl(MilkTeaRepository milkTeaRepository) {
		super();
		this.milkTeaRepository = milkTeaRepository;
	}

	@Override
	public <S extends MilkTea> S save(S entity) {
		return milkTeaRepository.save(entity);
	}

	@Override
	public Page<MilkTea> findAll(Pageable pageable) {
		return milkTeaRepository.findAll(pageable);
	}

	@Override
	public List<MilkTea> findAll() {
		return milkTeaRepository.findAll();
	}

	@Override
	public Optional<MilkTea> findById(Integer id) {
		return milkTeaRepository.findById(id);
	}

	@Override
	public long count() {
		return milkTeaRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		milkTeaRepository.deleteById(id);
	}

	@Override
	public void delete(MilkTea entity) {
		milkTeaRepository.delete(entity);
	}

	@Override
	public List<MilkTea> findByMilkTeaNameContaining(String keyword) {
		return  milkTeaRepository.findByMilkTeaNameContaining(keyword);
	}

	
}
