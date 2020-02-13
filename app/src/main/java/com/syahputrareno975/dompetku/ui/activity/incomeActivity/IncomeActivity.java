package com.syahputrareno975.dompetku.ui.activity.incomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.core.polar.series.Line;
import com.syahputrareno975.dompetku.BuildConfig;
import com.syahputrareno975.dompetku.R;
import com.syahputrareno975.dompetku.di.component.ActivityComponent;
import com.syahputrareno975.dompetku.di.component.DaggerActivityComponent;
import com.syahputrareno975.dompetku.di.module.ActivityModule;
import com.syahputrareno975.dompetku.model.transaction.TransactionModel;
import com.syahputrareno975.dompetku.ui.adapter.listReportAdapter.ListReportAdapter;
import com.syahputrareno975.dompetku.ui.dialog.DialogSimpleEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import static com.syahputrareno975.dompetku.util.ExpenseCategory.CATEGORY_NON_EXPENSE;
import static com.syahputrareno975.dompetku.util.Flow.FLOW_INCOME;
import static com.syahputrareno975.dompetku.util.Formatter.formatter;

public class IncomeActivity extends AppCompatActivity implements IncomeActivityContract.View {

    @Inject
    public IncomeActivityContract.Presenter presenter;

    private Context context;
    private TransactionModel income = new TransactionModel(CATEGORY_NON_EXPENSE,"", 0.0, "", null, FLOW_INCOME);

    private ImageView back;
    private LinearLayout addDate,addDes,addAmount;
    private TextView date,des,amount;

    private Button save;

    private NestedScrollView scroll;
    private RecyclerView listTransaction;
    private ListReportAdapter listReportAdapter;
    private ArrayList<TransactionModel> transactions = new ArrayList<TransactionModel>();
    private int offset = 0,limit = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        initWidget();
    }

    private void initWidget(){
        this.context = this;

        injectDependency();
        presenter.attach(this);
        presenter.subscribe();

        date = findViewById(R.id.date_income_textview);
        Calendar cal = Calendar.getInstance();
        income.setDate(new Date(cal.getTime().getTime()));
        date.setText(income.getDate().toString());

        des = findViewById(R.id.des_income_textview);
        amount = findViewById(R.id.amount_income_textview);

        back = findViewById(R.id.back_imageview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addDate = findViewById(R.id.date_income_layout);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // date picker
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.YEAR, year);
                                cal.set(Calendar.MONTH, monthOfYear);
                                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                income.setDate(new Date(cal.getTime().getTime()));
                                date.setText(income.getDate().toString());
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(ContextCompat.getColor(context,R.color.colorPrimaryLight));
                dpd.show(getSupportFragmentManager().beginTransaction(), "Datepickerdialog");
            }
        });

        addDes = findViewById(R.id.des_income_layout);
        addDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // show dialog edit text for description
                new DialogSimpleEditText(context, context.getString(R.string.add_income_des), income.getDescription(), new DialogSimpleEditText.OnDialogListener() {
                    @Override
                    public void onOk(String text) {
                        income.setDescription(text);
                        des.setText(text);
                    }

                    @Override
                    public void onEmpty() {

                    }

                    @Override
                    public void onCancel() {

                    }
                }).show();

            }
        });

        addAmount = findViewById(R.id.amount_income_layout);
        addAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // show dialog edit text for amount
                new DialogSimpleEditText(context, context.getString(R.string.add_income_amount), income.getAmount().toString(), new DialogSimpleEditText.OnDialogListener() {
                    @Override
                    public void onOk(String text) {
                        income.setAmount(Double.parseDouble(text));
                        amount.setText(formatter.format(income.getAmount()));
                    }

                    @Override
                    public void onEmpty() {

                    }

                    @Override
                    public void onCancel() {

                    }
                },true).show();
            }
        });



        save = findViewById(R.id.add_income_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (income.getDate() == null || income.getAmount() == 0.0 || income.getDescription().isEmpty()){
                    Toast.makeText(context,context.getString(R.string.data_income_is_not_valid),Toast.LENGTH_SHORT).show();
                    return;
                }
                income.setCurrency(BuildConfig.CURRENCY);
                presenter.addIncome(income);
            }
        });


        // for header
        transactions.add(new TransactionModel(true));

        listReportAdapter = new ListReportAdapter(context, transactions, new ListReportAdapter.OnListReportAdapterListener() {
            @Override
            public void onClick(@NonNull TransactionModel t, int pos) {
                // dialog delete transaction

            }
        });

        listTransaction = findViewById(R.id.list_tansaction_recycleview);
        listTransaction.setAdapter(listReportAdapter);
        listTransaction.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));


        scroll = findViewById(R.id.income_scroll_nestedscrollview);
        scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // pagination if user reach scroll to bottom on course
                    offset += limit;
                    presenter.getAllTransactionIncome(offset,limit);
                }
            }
        });


        // get all record transaction income
        presenter.getAllTransactionIncome(offset,limit);
    }

    @Override
    public void onAddIncome() {
        finish();
    }

    @Override
    public void onGetAllTransactionIncome(@Nullable List<TransactionModel> t) {
        if (t != null) transactions.addAll(t);
        listReportAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(Boolean show) {

    }

    @Override
    public void showError(String error) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }


    private void injectDependency(){
        ActivityComponent listcomponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();

        listcomponent.inject(this);
    }

}