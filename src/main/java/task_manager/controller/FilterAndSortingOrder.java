package task_manager.controller;

import java.util.ArrayList;

public class FilterAndSortingOrder {
    private ArrayList<Boolean> filter;
    private String category;

    public FilterAndSortingOrder(ArrayList<Boolean> filter, String category) {
        this.filter = filter;
        this.category = category;
    }

    public ArrayList<Boolean> getFilter() {
        return filter;
    }

    public String getCategory() {
        return category;
    }
}

