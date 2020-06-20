package com.teo.chess.gui.layers;


public enum LayerType {

    BOARD(new Layer()), OVERLAY(new Layer()), PIECE(new Layer()), MOVING(new Layer()), TEXT(new TextLayer());
    private Layer layer;

    private LayerType(Layer layer) {
        this.layer = layer;
    }

    public Layer getObj() {
        return layer;
    }
}
