package searchengine.parsers;

import searchengine.model.SiteModel;

import java.util.concurrent.CopyOnWriteArrayList;

public class NodeUrl {
    private volatile NodeUrl parent;
    private String urlPage;
    private volatile CopyOnWriteArrayList<NodeUrl> children;

    private volatile SiteModel siteModel;

    public NodeUrl(String urlPage) {
        this.urlPage = urlPage;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    public synchronized boolean addChild(NodeUrl element) {
        NodeUrl root = getRootElement();
        if (!root.contains(element.getUrl())) {
            element.setParent(this);
            children.add(element);
            return true;
//            System.out.println(element.getUrl());
        } else return false;
    }


    private boolean contains(String url) {
        if (this.urlPage.equals(url)) {
            return true;
        }
        for (NodeUrl child : children) {
            if (child.contains(url))
                return true;
        }

        return false;
    }

    public String getUrl() {
        return urlPage;
    }

    private void setParent(NodeUrl nodeUrl) {
        synchronized (this) {
            this.parent = nodeUrl;
        }
    }

    public NodeUrl getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }

    public CopyOnWriteArrayList<NodeUrl> getChildren() {
        return children;
    }

    public SiteModel getSiteModel() {
        return siteModel;
    }

    public synchronized void setSiteModel(SiteModel siteModel) {
        this.siteModel = siteModel;
    }
}
