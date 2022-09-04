package com.takorojo.DOM.nodes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;

public class DOMNode {
    protected String _name = "";
    protected String _content = "";
    protected ArrayList<String> _ids = new ArrayList<>();
    protected ArrayList<String> _classes = new ArrayList<>();
    protected Integer _indentLevel = 0;
    protected ArrayList<DOMNode> _children = new ArrayList<>();

    /**
     * DOM node representing an HTML tag without content.
     *
     * @param name    name of the HTML tag
     * @param content content that goes between the opening and closing tags
     */
    public DOMNode(String name, String content) {
        this(name);
        this._content = content;
    }

    /**
     * DOM node representing an HTML tag with no content.
     *
     * @param name name of the HTML tag
     */
    public DOMNode(String name) {
        this._name = name;
    }

    /**
     * Generic DOM node representing an HTML tag.
     */
    public DOMNode() {
        // Constructor
    }

    /**
     * Add a child node to the current node.
     *
     * @param child child node to add
     */
    public void addChild(DOMNode child) {
        child.setIndentLevel(this._indentLevel + 1);
        this._children.add(child);
    }

    /**
     * Set the indent level of the tag to be used when printed.
     *
     * @param level indent level
     */
    public void setIndentLevel(int level) {
        this._indentLevel = level;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (Objects.equals(this._name, "")) {
            listChildren(sb);
        } else {
            sb.append(MessageFormat.format("<{0}", this._name));

            listAttributes(this._ids, sb, "id");

            listAttributes(this._classes, sb, "class");

            sb.append(">");

            if (!Objects.equals(this._content, "")) {
                sb.append(this._content);
            }

            listChildren(sb);

            sb.append(MessageFormat.format("{0}</{1}>", this.indent(), _name));
        }

        return sb.toString().replaceAll(">(\\d+|[a-zA-Z']+)\\ {3,}</", ">$1</").replaceAll("\n\n", "\n").replaceAll("> {3,}<", "><");
    }

    /**
     * List all children of the node.
     *
     * @param sb StringBuilder to attach the list to
     */
    private void listChildren(StringBuilder sb) {
        if (!this._children.isEmpty()) {
            for (DOMNode child : this._children) {
                sb.append(MessageFormat.format("\n{1}{0}\n", child, child.indent()));
            }
        }
    }

    /**
     * List all attributes of the given attribute type and name.
     *
     * @param attributes    list of attributes
     * @param sb            StringBuilder to attach the list to
     * @param attributeName attribute name (id, class, etc.)
     */
    private void listAttributes(ArrayList<String> attributes, StringBuilder sb, String attributeName) {
        if (!attributes.isEmpty()) {
            sb.append(MessageFormat.format(" {0}=\'", attributeName));

            for (String id : attributes) {
                sb.append(id + " ");
            }

            sb.append("\'");
        }
    }

    /**
     * Generate an indent equal to 4 times the indent level.
     * <p>
     * This means that each indent level equals 4 spaces.
     *
     * @return string of spaces to use for indentation
     */
    private String indent() {
        return new String(new char[this._indentLevel * 4]).replace("\0", " ");
    }
}
