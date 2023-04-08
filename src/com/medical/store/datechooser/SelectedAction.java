package com.medical.store.datechooser;

public interface SelectedAction {

    int DAY_SELECTED = 1;
    int MONTH_SELECTED = 2;
    int YEAR_SELECTED = 3;

    int getAction();
}
