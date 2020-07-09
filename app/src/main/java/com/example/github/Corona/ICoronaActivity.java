package com.example.github.Corona;

import android.content.res.Resources;

import java.util.List;
import java.util.ResourceBundle;

public interface ICoronaActivity {
    void setCoronaInfo(List<CoronaInfoItem> coronaInfoItemList);

    Resources getResources();
}
