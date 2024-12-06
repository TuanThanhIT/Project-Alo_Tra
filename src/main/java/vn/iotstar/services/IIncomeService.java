package vn.iotstar.services;

import java.util.List;

public interface IIncomeService {
    double sumIncomeValue();
    List<Object[]> findMonthlyIncome(int year);

}
