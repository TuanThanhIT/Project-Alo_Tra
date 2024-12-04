package vn.iotstar.service.seller;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.MilkTea;

public interface IMilkTeaService {
	public List<MilkTea> findAll();

	public Optional<MilkTea> findById(int id);

	public MilkTea save(MilkTea milkTea);// thêm mới hoặc cập nhật

	public void deleteById(int id);
}