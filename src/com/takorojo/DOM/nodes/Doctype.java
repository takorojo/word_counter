package com.takorojo.DOM.nodes;

import java.text.MessageFormat;

public class Doctype extends DOMNode {
    /**
     * HTML5 DOCTYPE tag
     */
    public Doctype() {
        super("!doctype html");
    }

    @Override
    public String toString() {
        return MessageFormat.format("<{0}>", this._name);
    }
}
