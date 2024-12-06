package vn.iotstar.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.repository.IIncomeRepository;
import vn.iotstar.services.IIncomeService;

@Service
public class IncomeServiceImpl implements IIncomeService {
	@Autowired
	IIncomeRepository iIncomeRepository;

	public IncomeServiceImpl(IIncomeRepository iIncomeRepository) {
		super();
		this.iIncomeRepository = iIncomeRepository;
	}

	@Override
	public double sumIncomeValue() {
		return iIncomeRepository.sumIncomeValue();
	}

	@Override
	public List<Object[]> findMonthlyIncome(int year) {
		return iIncomeRepository.findMonthlyIncome(year);
	}

}
