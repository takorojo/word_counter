package com.takorojo.DOM;

import com.takorojo.DOM.nodes.DOMNode;
import com.takorojo.DOM.nodes.Doctype;
import com.takorojo.DOM.nodes.HtmlTag;

public class DOMTree {
    private DOMNode _root;
    private DOMNode _doctype;
    private DOMNode _html;
    private DOMNode _body;
    private DOMNode _head;

    /**
     * A DOM tree of HTML elements.
     */
    public DOMTree() {
        this._root = new DOMNode();
        this._doctype = new Doctype();
        this._html = new HtmlTag();
        this._body = new DOMNode("body");
        this._head = new DOMNode("head");
        this._root.setIndentLevel(-1);

        this._root.addChild(this._doctype);
        this._root.addChild(this._html);
        this._html.addChild(this._head);
        this._html.addChild(this._body);
    }

    /**
     * Get the entire DOM tree.
     *
     * @return the entire DOM tree, including the root node
     */
    public DOMNode getDOMTree() {
        return this._root;
    }

    /**
     * Add the given node to the HTML body.
     *
     * @param node node to add to the body
     */
    public void addToBody(DOMNode node) {
        this._body.addChild(node);
    }
}
