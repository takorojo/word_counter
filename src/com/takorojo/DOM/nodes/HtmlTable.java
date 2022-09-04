package com.takorojo.DOM.nodes;

public class HtmlTable extends DOMNode {
    /**
     * An HTML table element
     */
    public HtmlTable() {
        this._name = "table";
    }

    /**
     * Set header data.
     *
     * @param headerData data to be placed in the header row
     */
    public void setHeaderData(String... headerData) {
        DOMNode row = new DOMNode("tr");
        this.addChild(row);

        for (String label : headerData) {
            row.addChild(new DOMNode("th", label));
        }
    }

    /**
     * Add a row of data to the table.
     *
     * @param data data to add to the table
     */
    public void addDataRow(String... data) {
        DOMNode row = new DOMNode("tr");
        this.addChild(row);

        for (String str : data) {
            row.addChild(new DOMNode("td", str));
        }
    }
}
