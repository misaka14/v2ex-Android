package com.wutouqishi.v2ex_android.domain;

/**
 * Created by gengjie on 16/9/7.
 */
public class Node
{
    String name;

    String nodeUrl;

    public Node() {

    }

    public Node(String name, String nodeUrl) {
        this.name = name;
        this.nodeUrl = nodeUrl;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", nodeUrl='" + nodeUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }
}
