package com.bai.unitconverter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.*;

public class MultiSelectionSpinner extends AppCompatSpinner implements OnMultiChoiceClickListener {

    public interface OnMultipleItemsSelectedListener{
        void selectedIndices(List<Integer> indices);
        void selectedStrings(List<String> strings);
    }

    private OnMultipleItemsSelectedListener listener;
    private String[] items = null;
    private boolean[] selections = null;
    private ArrayAdapter<String> adapter;
    private boolean isAllSelected = false;

    /**
     * Constructor
     * @param context
     */
    public MultiSelectionSpinner(Context context) {
        super(context);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    /**
     * Constructor
     * @param context
     * @param attrs
     */
    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    public void setListener(OnMultipleItemsSelectedListener listener){
        this.listener = listener;
    }

    public void onClick(DialogInterface dialog, int choice, boolean isChecked) {
        // When the check box is clicked
        if (selections != null && choice < selections.length) {
            selections[choice] = isChecked;
            adapter.clear();
            // Update selected unit names in the spinner bar
            adapter.add(getSelectedItemString());
        }
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getContext().getString(R.string.select_units));
        builder.setMultiChoiceItems(items, selections, this);
        String neutralText = (isAllSelected)? getContext().getString(R.string.clear_all) :
                getContext().getString(R.string.select_all);

        builder.setPositiveButton(getContext().getString(R.string.submit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.selectedIndices(getSelectedIndices());
                listener.selectedStrings(getSelectedUnitNames());
            }
        });
        builder.setNegativeButton(getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.selectedStrings(getSelectedUnitNames());
            }
        });
        builder.setNeutralButton(neutralText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isAllSelected){
                    // Updating the conversion table with no item selected
                    listener.selectedIndices(new ArrayList<Integer>());
                    listener.selectedStrings(getSelectedUnitNames());
                    Arrays.fill(selections, false);
                    isAllSelected = false;
                    adapter.clear();
                } else {
                    List<Integer> indicesList = new ArrayList<>();
                    for(int i = 0; i < items.length; i++){
                        indicesList.add(i);
                    }
                    // Updating the conversion table with all items selected
                    listener.selectedIndices(indicesList);
                    listener.selectedStrings(Arrays.asList(items));
                    Arrays.fill(selections, true);
                    isAllSelected = true;
                    adapter.clear();
                    // Update selected unit names in the spinner bar
                    adapter.add(getSelectedItemString());
                }
            }
        });

        isAllSelected = (items.length == getSelectedIndices().size())? true : false;
        builder.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "Method setAdapter is not supported");
    }

    /**
     * Populate the spinner with a list of choices
     * @param items list of choices
     */
    public void setUnits(List<String> items) {
        this.items = items.toArray(new String[items.size()]);
        selections = new boolean[this.items.length];
        adapter.clear();
        adapter.add(getContext().getString(R.string.select_units));
        Arrays.fill(selections, false);
    }


    public List<String> getSelectedUnitNames() {
        List<String> selection = new LinkedList<>();
        for (int i = 0; i < items.length; ++i) {
            if (selections[i]) {
                selection.add(items[i]);
            }
        }
        return selection;
    }

    public List<Integer> getSelectedIndices() {
        List<Integer> selection = new LinkedList<>();
        for (int i = 0; i < items.length; ++i) {
            if (selections[i]) {
                selection.add(i);
            }
        }
        return selection;
    }

    private String getSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.length; ++i) {
            if (selections[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
                sb.append(items[i]);
            }
        }
        return sb.toString();
    }
}
