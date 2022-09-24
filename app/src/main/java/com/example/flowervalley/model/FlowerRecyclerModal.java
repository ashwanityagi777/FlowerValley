package com.example.flowervalley.model;

public class FlowerRecyclerModal {


    private int price;
    private String flowerURL;
    private String flowername;



    public int getPrice() {
        return price;
    }

    public String getFlowerURL() {
        return flowerURL;
    }

    public String getFlowername() {
        return flowername;
    }

    public FlowerRecyclerModal(int price, String flowerURL, String flowername) {
        this.price = price;
        this.flowerURL = flowerURL;
        this.flowername = flowername;
    }


}
