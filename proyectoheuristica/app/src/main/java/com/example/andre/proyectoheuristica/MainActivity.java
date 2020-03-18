package com.example.andre.proyectoheuristica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawLineChart();
    }

    private void drawLineChart() {
        LineChart lineChart=findViewById(R.id.lineChart);

        Description description = new Description();
        description.setText("Tiempos de arribo");
        lineChart.setDescription(description);

        LineDataSet lineDataSet = new LineDataSet(getDataSet(), "Tiempos de arribo");
        lineDataSet.setDrawFilled(true);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        LineData lineData = new LineData(lineDataSet);
        lineData.setValueFormatter(new ReportChartXAxisValueFormatter(getXAxisValues()));

        lineChart.setData(lineData);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }

    private List<Entry> getDataSet() {
    List<Entry> entries = new ArrayList<Entry>();
    entries.add(new Entry(0f,0));
        entries.add(new Entry(20f,0));
        entries.add(new Entry(100f,0));

        return entries;
    }

    private List<String> getXAxisValues(){
        List<String> xAxis = new ArrayList<String>();
        xAxis.add("");
        xAxis.add("");
        xAxis.add("");
        return xAxis;
    }

    private class ReportChartXAxisValueFormatter implements IValueFormatter {

    private List<String> labels;

    public ReportChartXAxisValueFormatter(List<String> labels){
        this.labels = labels;
    }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler
                viewPortHandler) {
            try{
                int index=(int) value;
            return this.labels.get(index);
            }
            catch (Exception e){
                return null;
            }

        }
    }
}
