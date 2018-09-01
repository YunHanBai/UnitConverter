package com.bai.unitconverter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import static com.bai.unitconverter.ConversionActivity.baseUnit;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.ViewHolder> {

    private List<Unit> unitList;
    private DecimalFormat df;

    public UnitAdapter(List<Unit> units, String formatPattern){
        unitList = units;
        df = new DecimalFormat(formatPattern);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView unitText;
        TextView valutText;

        public ViewHolder(View view){
            super(view);
            unitText = view.findViewById(R.id.unit_textview);
            valutText = view.findViewById(R.id.value_textview);
        }
    }

    @Override
    public UnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_unit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        double result;
        Unit unit = unitList.get(position);
        holder.unitText.setText(unit.getUnitName() + " (" + unit.getSymbol() +")");
        // Conversion calculation
        result = baseUnit.convertTo(unit);
        if(("" + result).length() > 10){
            holder.valutText.setText("" + df.format(result));
        } else {
            holder.valutText.setText("" + df.format(result));
        }

    }

    @Override
    public int getItemCount(){
        return unitList.size();
    }
}
