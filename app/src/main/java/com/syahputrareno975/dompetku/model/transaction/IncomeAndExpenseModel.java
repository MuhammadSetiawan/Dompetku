package com.syahputrareno975.dompetku.model.transaction;

import androidx.room.ColumnInfo;

import com.syahputrareno975.dompetku.model.BaseModel;

public class IncomeAndExpenseModel extends BaseModel {

    @ColumnInfo(name = "total_income")
    private Double TotalIncome;

    @ColumnInfo(name = "total_expense")
    private Double TotalExpense;

    public IncomeAndExpenseModel() {
        super();
    }

    public IncomeAndExpenseModel(Double totalIncome, Double totalExpense) {
        TotalIncome = totalIncome;
        TotalExpense = totalExpense;
    }

    public Double getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        TotalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return TotalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        TotalExpense = totalExpense;
    }
}